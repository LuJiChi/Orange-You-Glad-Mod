package com.lujichi.orangeyouglad.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lujichi.orangeyouglad.OrangeYouGladMod;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class FlariteFurnaceRecipe implements Recipe<SimpleContainer> {
    private final NonNullList<Ingredient> inputItems;
    private final ItemStack output;
    private final ResourceLocation id;
    private final int talentCost; // 合成所需的才华值消耗
    private final int[] requiredCounts; // 每个配方材料需要的数量

    public FlariteFurnaceRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id, int talentCost) {
        this(inputItems, output, id, talentCost, null);
    }
    public FlariteFurnaceRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id, int talentCost, int[] requiredCounts) {
        this.inputItems = inputItems;
        this.output = output;
        this.id = id;
        this.talentCost = talentCost;

        // 如果未提供数量数组，则默认每个材料需要1个
        if (requiredCounts == null || requiredCounts.length != inputItems.size()) {
            this.requiredCounts = new int[inputItems.size()];
            for (int i = 0; i < this.requiredCounts.length; i++) {
                this.requiredCounts[i] = 1;
            }
        } else {
            this.requiredCounts = requiredCounts;
        }
    }

    public FlariteFurnaceRecipe(NonNullList<Ingredient> inputItems, ItemStack output, ResourceLocation id) {
        this(inputItems, output, id, 0); // 默认才华值消耗为0
    }


    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()) {
            return false;
        }

        // 统计每个配方材料在容器中的匹配数量
        int[] matchedCounts = new int[inputItems.size()];

        // 检查每个输入槽
        for (int slot = 0; slot < 3; slot++) {
            ItemStack itemInSlot = pContainer.getItem(slot);
            if (!itemInSlot.isEmpty()) {
                boolean slotMatched = false;

                // 检查物品是否匹配任何配方材料
                for (int i = 0; i < inputItems.size(); i++) {
                    if (inputItems.get(i).test(itemInSlot)) {
                        matchedCounts[i] += itemInSlot.getCount();
                        slotMatched = true;
                        break;
                    }
                }

                if (!slotMatched) {
                    return false; // 槽位中有不匹配的物品
                }
            }
        }

        // 检查每个配方材料都有足够的匹配数量
        for (int i = 0; i < inputItems.size(); i++) {
            if (matchedCounts[i] < requiredCounts[i]) {
                return false;
            }
        }

        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputItems;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    // 获取配方所需每个材料的数量（默认为1，支持扩展）
    public int[] getRequiredCounts() {
        return requiredCounts;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    // 获取才华值消耗
    public int getTalentCost() {
        return talentCost;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<FlariteFurnaceRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "flarite_furnace_smelting";
    }

    public static class Serializer implements RecipeSerializer<FlariteFurnaceRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID, "flarite_furnace_smelting");

        @Override
        public FlariteFurnaceRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.create();

            // 读取每个材料需要的数量
            int[] requiredCounts = new int[ingredients.size()];
            for (int i = 0; i < ingredients.size(); ++i) {
                JsonObject ingredientObj = ingredients.get(i).getAsJsonObject();
                Ingredient ingredient = Ingredient.fromJson(ingredientObj);
                inputs.add(ingredient);
                // 读取count字段，默认为1
                requiredCounts[i] = GsonHelper.getAsInt(ingredientObj, "count", 1);
            }
            // 读取合成所需的才华值消耗
            int talentCost = GsonHelper.getAsInt(pSerializedRecipe, "talent_cost", 0);

            return new FlariteFurnaceRecipe(inputs, output, pRecipeId, talentCost, requiredCounts);
        }

        @Override
        public @Nullable FlariteFurnaceRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(pBuffer.readInt(), Ingredient.EMPTY);

            // 读取每个材料需要的数量
            int[] requiredCounts = new int[inputs.size()];
            for (int i = 0; i < inputs.size(); ++i) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
                requiredCounts[i] = pBuffer.readInt();
            }

            ItemStack output = pBuffer.readItem();
            // 读取合成所需的才华值消耗
            int talentCost = pBuffer.readInt();
            return new FlariteFurnaceRecipe(inputs, output, pRecipeId, talentCost, requiredCounts);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, FlariteFurnaceRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.inputItems.size());

            for(int i = 0; i < pRecipe.inputItems.size(); i++) {
                pRecipe.inputItems.get(i).toNetwork(pBuffer);
                pBuffer.writeInt(pRecipe.requiredCounts[i]);
            }

            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
            // 写入合成所需的才华值消耗
            pBuffer.writeInt(pRecipe.getTalentCost());
        }
    }
}
