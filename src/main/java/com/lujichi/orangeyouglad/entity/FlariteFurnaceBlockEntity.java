package com.lujichi.orangeyouglad.entity;

import com.lujichi.orangeyouglad.item.ModItems;
import com.lujichi.orangeyouglad.recipe.FlariteFurnaceRecipe;
import com.lujichi.orangeyouglad.screen.FlariteFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FlariteFurnaceBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemHandler = new ItemStackHandler(5);


    private static final int INPUT_SLOT0 = 0;
    private static final int INPUT_SLOT1 = 1;
    private static final int INPUT_SLOT2 = 2;
    private static final int INPUT_SLOT3 = 3;
    private static final int OUTPUT_SLOT = 4;

    // 才华值系统
    private int talentValue = 0;
    private static final int MAX_TALENT_VALUE = 10000;
    private static final int TALENT_PER_FLARITE = 1000; // 每个才火矿提供的才华值

    private LazyOptional<ItemStackHandler> lazitemHandler = LazyOptional.empty();

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;

    private int getAdjustedMaxProgress() {
        int baseProgress = maxProgress;
        // 才华值越多，合成越快
        float speedMultiplier = 1.0f - (talentValue / (float)MAX_TALENT_VALUE * 0.6f);
        return Math.max(10, (int)(baseProgress * speedMultiplier));
    }

    public FlariteFurnaceBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.FLARITE_FURNACE_BE.get(), pPos, pBlockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> FlariteFurnaceBlockEntity.this.progress;
                    case 1 -> FlariteFurnaceBlockEntity.this.getAdjustedMaxProgress();
                    case 2 -> FlariteFurnaceBlockEntity.this.talentValue;
                    case 3 -> MAX_TALENT_VALUE;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> FlariteFurnaceBlockEntity.this.progress = pValue;
                    case 1 -> FlariteFurnaceBlockEntity.this.maxProgress = pValue;
                    case 2 -> FlariteFurnaceBlockEntity.this.talentValue = Math.min(pValue, MAX_TALENT_VALUE);
                }
            }

            @Override
            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazitemHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazitemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazitemHandler.invalidate();
    }

    public void dorp() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for(int slot = 0; slot < itemHandler.getSlots(); slot++) {
            inventory.setItem(slot, itemHandler.getStackInSlot(slot));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.orangeyouglad_mod.flarite_furnace");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new FlariteFurnaceMenu(pContainerId, pPlayerInventory, this, this.data);
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        pTag.put("inventory", itemHandler.serializeNBT());
        pTag.putInt("flarite_furnace_progress", progress);
        pTag.putInt("talent_value", talentValue);

        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);

        itemHandler.deserializeNBT(pTag.getCompound("inventory"));
        progress = pTag.getInt("flarite_furnace_progress");
        talentValue = pTag.getInt("talent_value");
    }

    public void tick(Level pLevel, BlockPos pPos, BlockState pState) {
        // 检查并消耗才火矿来增加才华值
        consumeFlariteForTalent();

        if(hasRecipe()) {
            increaseCraftingProgress();
            setChanged(pLevel, pPos, pState);

            if(hasProgressFinished()) {
               craftItem();
               resetProgress();
            }
        } else {
            resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }


    private void craftItem() {
        Optional<FlariteFurnaceRecipe> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return;

        FlariteFurnaceRecipe r = recipe.get();
        ItemStack result = r.getResultItem(null);

        // 消耗才华值
        int talentCost = r.getTalentCost();
        consumeTalent(talentCost);

        // 获取每个材料需要的数量
        int[] requiredCounts = r.getRequiredCounts();
        int[] remainingNeeds = requiredCounts.clone();

        // 修复：改进消耗逻辑，确保材料可以在任意槽位被识别和消耗

        // 首先收集所有槽位的物品信息
        ItemStack[] slotStacks = new ItemStack[3];
        for (int i = 0; i < 3; i++) {
            slotStacks[i] = this.itemHandler.getStackInSlot(i);
        }
        // 第二步：为每个配方材料找到匹配的槽位并消耗
        for (int ingredientIndex = 0; ingredientIndex < r.getIngredients().size(); ingredientIndex++) {
            if (remainingNeeds[ingredientIndex] <= 0) continue;

            // 在所有槽位中寻找匹配的材料
            for (int slotIndex = 0; slotIndex < 3; slotIndex++) {
                if (slotStacks[slotIndex].isEmpty()) continue;

                if (r.getIngredients().get(ingredientIndex).test(slotStacks[slotIndex])) {
                    // 从当前槽位提取所需数量（不能超过槽位存量）
                    int toExtract = Math.min(remainingNeeds[ingredientIndex], slotStacks[slotIndex].getCount());
                    if (toExtract > 0) {
                        this.itemHandler.extractItem(slotIndex, toExtract, false);
                        remainingNeeds[ingredientIndex] -= toExtract;
                        slotStacks[slotIndex] = this.itemHandler.getStackInSlot(slotIndex); // 更新槽位状态
                    }
                    break; // 找到匹配后继续下一个材料
                }

            }
        }

            boolean hasRemainingNeeds = false;
        for (int need : remainingNeeds) {
        if (need > 0) {
            hasRemainingNeeds = true;
            break;
                }
            }

        if (hasRemainingNeeds) {
            // 改进的备用逻辑：消耗所有3个槽位的物品
            // 首先计算总剩余需求
            int totalRemainingNeeds = 0;
            for (int need : remainingNeeds) {
                totalRemainingNeeds += need;
            }

            // 如果配方材料列表为空，按顺序消耗所有槽位
            if (r.getIngredients().isEmpty()) {
                for (int slotIndex = 0; slotIndex < 3 && totalRemainingNeeds > 0; slotIndex++) {
                    ItemStack slotStack = this.itemHandler.getStackInSlot(slotIndex);
                    if (!slotStack.isEmpty()) {
                        // 计算这个槽位需要消耗的数量
                        int toExtract = Math.min(slotStack.getCount(), totalRemainingNeeds);
                        if (toExtract > 0) {
                            this.itemHandler.extractItem(slotIndex, toExtract, false);
                            totalRemainingNeeds -= toExtract;
                        }
                    }
                }
            } else {
                // 如果配方材料列表不为空但仍有剩余需求，按requiredCounts消耗
                for (int slotIndex = 0; slotIndex < 3 && slotIndex < requiredCounts.length; slotIndex++) {
                    if (remainingNeeds[slotIndex] > 0) {
                        ItemStack slotStack = this.itemHandler.getStackInSlot(slotIndex);
                        if (!slotStack.isEmpty()) {
                            int toExtract = Math.min(remainingNeeds[slotIndex], slotStack.getCount());
                            if (toExtract > 0) {
                                this.itemHandler.extractItem(slotIndex, toExtract, false);
                                remainingNeeds[slotIndex] -= toExtract;
                            }
                        }
                    }
                }
            }
    }
    // 第四步：产出物品到输出槽
    ItemStack currentOutput = this.itemHandler.getStackInSlot(OUTPUT_SLOT);
        if (currentOutput.isEmpty()) {
        this.itemHandler.setStackInSlot(OUTPUT_SLOT, result.copy());
    } else {
        currentOutput.grow(result.getCount());
    }
}



    private boolean hasRecipe() {
        Optional<FlariteFurnaceRecipe> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) return false;

        FlariteFurnaceRecipe r = recipe.get();

        // 才华值检查
        if (r.getTalentCost() > 0 && !hasEnoughTalent(r.getTalentCost())) {
            return false;
        }

        // 输出槽位检查
        ItemStack result = r.getResultItem(getLevel().registryAccess());
        if (!canInsertAmountIntoOutputSlot(result.getCount()) || !canInsertItemIntoOutputSlot(result.getItem())) {
            return false;
        }

        // 使用配方自己的匹配逻辑验证物品数量
        SimpleContainer inventory = new SimpleContainer(3);
        for (int i = 0; i < 3; i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        return r.matches(inventory, level);
    }

    private Optional<FlariteFurnaceRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(3); // 只放三个输入槽
        for (int slot = 0; slot < 3; slot++) {
            inventory.setItem(slot, itemHandler.getStackInSlot(slot));
        }
        return level.getRecipeManager().getRecipeFor(FlariteFurnaceRecipe.Type.INSTANCE, inventory, level);
        }


    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count <= this.itemHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    private boolean hasProgressFinished() {
        return progress >= getAdjustedMaxProgress();
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    // 才华值相关方法
    private void consumeFlariteForTalent() {
        // 检查是否有才火矿可以消耗
        ItemStack flariteStack = this.itemHandler.getStackInSlot(INPUT_SLOT3);
        if (!flariteStack.isEmpty() && talentValue < MAX_TALENT_VALUE) {
            // 检查是否为才火矿（需要导入ModItems）
            if (flariteStack.getItem() == ModItems.FLARITE.get()) {
                // 消耗一个才火矿，增加才华值
                this.itemHandler.extractItem(INPUT_SLOT3, 1, false);
                talentValue = Math.min(talentValue + TALENT_PER_FLARITE, MAX_TALENT_VALUE);
                setChanged(level, worldPosition, getBlockState());
            }
        }
    }

    public int getTalentValue() {
        return talentValue;
    }

    public int getMaxTalentValue() {
        return MAX_TALENT_VALUE;
    }

    public boolean hasEnoughTalent(int requiredTalent) {
        return talentValue >= requiredTalent;
    }

    public void consumeTalent(int amount) {
        talentValue = Math.max(0, talentValue - amount);
        setChanged(level, worldPosition, getBlockState());
    }

}

