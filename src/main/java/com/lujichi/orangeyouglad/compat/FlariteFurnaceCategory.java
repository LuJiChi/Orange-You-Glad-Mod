package com.lujichi.orangeyouglad.compat;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.block.ModBlocks;
import com.lujichi.orangeyouglad.recipe.FlariteFurnaceRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FlariteFurnaceCategory implements IRecipeCategory<FlariteFurnaceRecipe> {
    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID, "flarite_furnace");
    public static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID,
            "textures/gui/flarite_furnace_gui.png");

    public static final RecipeType<FlariteFurnaceRecipe> FLARITE_FURNACE_TYPE =
            new RecipeType<>(UID, FlariteFurnaceRecipe.class);

    private IDrawable background;
    private IDrawable icon;



    public FlariteFurnaceCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(TEXTURE, 5, 5, 155, 68);
        this.icon = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(ModBlocks.FLARITE_FURNACE.get()));
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }



    @Override
    public RecipeType<FlariteFurnaceRecipe> getRecipeType() {
        return FLARITE_FURNACE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.orangeyouglad_mod.flarite_furnace");
    }


    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, FlariteFurnaceRecipe recipe, IFocusGroup focuses) {
        NonNullList<Ingredient> ingredients = recipe.getIngredients();

        // 安全检查：确保配方有材料
        if (ingredients.isEmpty()) {
            return;
        }

        // 根据实际配方材料数量动态添加输入槽位
        // 支持1-4个输入材料，使用2x2网格布局
        int startX = 42;
        int startY = 5;
        int slotSpacing = 22;

        for (int i = 0; i < ingredients.size() && i < 3; i++) {
            int x = startX;
            int y = startY + i * slotSpacing;

            // 获取配方中该材料需要的数量
            int requiredCount = recipe.getRequiredCounts()[i];

            // 创建带有数量的ItemStack来显示
            ItemStack[] matchingStacks = ingredients.get(i).getItems();
            if (matchingStacks.length > 0) {
                ItemStack displayStack = matchingStacks[0].copy();
                displayStack.setCount(requiredCount);

                builder.addSlot(RecipeIngredientRole.INPUT, x, y)
                        .addItemStack(displayStack);
            } else {
                // 如果没有匹配的物品，使用默认的添加方式
                builder.addSlot(RecipeIngredientRole.INPUT, x, y)
                        .addIngredients(ingredients.get(i));
            }
        }


        builder.addSlot(RecipeIngredientRole.OUTPUT, 125, 27).addItemStack(recipe.getResultItem(null));

    }

    @Override
    public void draw(FlariteFurnaceRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        int talentCost = recipe.getTalentCost();
        if (talentCost > 0) {
            Minecraft minecraft = Minecraft.getInstance();

            // 1. 在左侧绘制才华值进度条（与火炉GUI保持一致）
            int talentBarX = 7;
            int talentBarY = 5;
            int talentBarWidth = 5;
            int talentBarHeight = 60;
            // 计算才华值填充高度（最大才华值10000，进度条高度65）
            int scaledTalent = Math.min(talentCost * talentBarHeight / 10000, talentBarHeight);


            if (scaledTalent > 0) {
                // 使用纹理贴图渲染才华值条，与火炉GUI保持一致
                // 假设才华值纹理在GUI纹理的 (177, 37) 位置，尺寸为 5x65
                int textureU = 177;
                int textureV = 37 + (talentBarHeight - scaledTalent);
                // 绘制才华值填充部分
                guiGraphics.blit(TEXTURE, talentBarX, talentBarY + (talentBarHeight - scaledTalent),
                        textureU, textureV, talentBarWidth, scaledTalent);
            }

        }

    }
    @Override
    public List<Component> getTooltipStrings(FlariteFurnaceRecipe recipe, IRecipeSlotsView recipeSlotsView, double mouseX, double mouseY) {
        int talentCost = recipe.getTalentCost();
        if (talentCost > 0) {
            // 检查鼠标是否悬停在才华值进度条上
            int talentBarX = 6;
            int talentBarY = 5;
            int talentBarWidth = 5;
            int talentBarHeight = 60;

            // 扩大触碰区域，方便玩家交互
            int expandedWidth = 10; // 扩大宽度以便更容易触碰
            int expandedX = talentBarX - (expandedWidth - talentBarWidth) / 2;

            if (mouseX >= expandedX && mouseX <= expandedX + expandedWidth &&
                    mouseY >= talentBarY && mouseY <= talentBarY + talentBarHeight) {

                List<Component> tooltip = new ArrayList<>();
                // 显示具体的才华值消耗数字
                tooltip.add(Component.translatable("jei.orangeyouglad_mod.talent_cost", talentCost)
                        .withStyle(ChatFormatting.RED));
                // 显示进度条说明
                tooltip.add(Component.translatable("jei.orangeyouglad_mod.talent_bar_hint")
                        .withStyle(ChatFormatting.YELLOW));
                return tooltip;
            }
        }
        return List.of();
    }

}
