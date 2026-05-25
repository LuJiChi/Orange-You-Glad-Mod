package com.lujichi.orangeyouglad.datagen;


import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.block.ModBlocks;
import com.lujichi.orangeyouglad.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;


public class ModRecipesProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipesProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, List.of(ModBlocks.DANGO_ORE.get()), RecipeCategory.MISC, ModItems.DANGO_INGOT.get(), 0.3F, 200, "dango");
        oreSmelting(pWriter, List.of(ModItems.RAW_DANGO.get()), RecipeCategory.MISC, ModItems.DANGO_INGOT.get(), 0.3F, 200, "dango");
        oreBlasting(pWriter, List.of(ModBlocks.DANGO_ORE.get()), RecipeCategory.MISC, ModItems.DANGO_INGOT.get(), 0.3F, 100, "dango");
        oreBlasting(pWriter, List.of(ModItems.RAW_DANGO.get()), RecipeCategory.MISC, ModItems.DANGO_INGOT.get(), 0.3F, 100, "dango");

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DANGO_INGOT_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.DANGO_INGOT.get())
                .unlockedBy(getHasName(ModItems.DANGO_INGOT.get()), has(ModItems.DANGO_INGOT.get()))
                .save(pWriter);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLARITE_BLOCK.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.FLARITE.get())
                .unlockedBy(getHasName(ModItems.FLARITE.get()), has(ModItems.FLARITE.get()))
                .save(pWriter);

        // 橙木楼梯配方 - 6个木板 -> 4个楼梯
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLARITE_FURNACE.get(), 4)
                .pattern("#/#")
                .pattern("/ /")
                .pattern("#/#")
                .define('#', ModItems.DANGO_INGOT.get())
                .define('/', ModItems.FLARITE.get())
                .unlockedBy(getHasName(ModItems.DANGO_INGOT.get()), has(ModItems.DANGO_INGOT.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DANGO_INGOT.get(), 9)
                .requires(ModBlocks.DANGO_INGOT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.DANGO_INGOT_BLOCK.get()), has(ModBlocks.DANGO_INGOT_BLOCK.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FLARITE.get(), 9)
                .requires(ModBlocks.FLARITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.FLARITE_BLOCK.get()), has(ModBlocks.FLARITE_BLOCK.get()))
                .save(pWriter);

        oreCooking(pWriter, RecipeSerializer.SMELTING_RECIPE, List.of(ModItems.CHICKEN_WING.get()), RecipeCategory.MISC, ModItems.COOKED_CHICKEN_WING.get(), 0.3F, 160, "chicken_wing", "_from_smelting");

        // 橙木木板配方 - 1个原木 -> 4个木板
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ORANGE_PLANKS.get(), 4)
                .requires(ModBlocks.ORANGE_LOG.get())
                .unlockedBy(getHasName(ModBlocks.ORANGE_LOG.get()), has(ModBlocks.ORANGE_LOG.get()))
                .save(pWriter);

        // 橙木楼梯配方 - 6个木板 -> 4个楼梯
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ORANGE_STAIRS.get(), 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', ModBlocks.ORANGE_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.ORANGE_PLANKS.get()), has(ModBlocks.ORANGE_PLANKS.get()))
                .save(pWriter);

        // 橙木台阶配方 - 3个木板 -> 6个台阶
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ORANGE_SLAB.get(), 6)
                .pattern("###")
                .define('#', ModBlocks.ORANGE_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.ORANGE_PLANKS.get()), has(ModBlocks.ORANGE_PLANKS.get()))
                .save(pWriter);

        // 橙木栅栏配方 - 4个木板 + 2根木棍 -> 3个栅栏
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.ORANGE_FENCE.get(), 3)
                .pattern("#/#")
                .pattern("#/#")
                .define('#', ModBlocks.ORANGE_PLANKS.get())
                .define('/', Items.STICK)
                .unlockedBy(getHasName(ModBlocks.ORANGE_PLANKS.get()), has(ModBlocks.ORANGE_PLANKS.get()))
                .save(pWriter);

        // 橙木栅栏门配方 - 2个木棍 + 4个木板 -> 1个栅栏门
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.ORANGE_FENCE_GATE.get())
                .pattern("/#/")
                .pattern("/#/")
                .define('#', ModBlocks.ORANGE_PLANKS.get())
                .define('/', Items.STICK)
                .unlockedBy(getHasName(ModBlocks.ORANGE_PLANKS.get()), has(ModBlocks.ORANGE_PLANKS.get()))
                .save(pWriter);

        // 橙木门配方 - 6个木板 -> 3个门
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.ORANGE_DOOR.get(), 3)
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .define('#', ModBlocks.ORANGE_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.ORANGE_PLANKS.get()), has(ModBlocks.ORANGE_PLANKS.get()))
                .save(pWriter);

        // 橙木活板门配方 - 6个木板 -> 2个活板门
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.ORANGE_TRAPDOOR.get(), 2)
                .pattern("###")
                .pattern("###")
                .define('#', ModBlocks.ORANGE_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.ORANGE_PLANKS.get()), has(ModBlocks.ORANGE_PLANKS.get()))
                .save(pWriter);

        // 橙木按钮配方 - 1个木板 -> 1个按钮
        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, ModBlocks.ORANGE_BUTTON.get())
                .requires(ModBlocks.ORANGE_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.ORANGE_PLANKS.get()), has(ModBlocks.ORANGE_PLANKS.get()))
                .save(pWriter);

        // 橙木压力板配方 - 2个木板 -> 1个压力板
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.ORANGE_PRESSURE_PLATE.get())
                .pattern("##")
                .define('#', ModBlocks.ORANGE_PLANKS.get())
                .unlockedBy(getHasName(ModBlocks.ORANGE_PLANKS.get()), has(ModBlocks.ORANGE_PLANKS.get()))
                .save(pWriter);

        // 清元石楼梯配方 - 6个清元石 -> 4个楼梯
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.QINGYUAN_STONE_STAIRS.get(), 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', ModBlocks.QINGYUAN_STONE.get())
                .unlockedBy(getHasName(ModBlocks.QINGYUAN_STONE.get()), has(ModBlocks.QINGYUAN_STONE.get()))
                .save(pWriter);

        // 清元石台阶配方 - 3个清元石 -> 6个台阶
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.QINGYUAN_STONE_SLAB.get(), 6)
                .pattern("###")
                .define('#', ModBlocks.QINGYUAN_STONE.get())
                .unlockedBy(getHasName(ModBlocks.QINGYUAN_STONE.get()), has(ModBlocks.QINGYUAN_STONE.get()))
                .save(pWriter);

        // 清元石墙配方 - 6个清元石 -> 6个墙
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ModBlocks.QINGYUAN_STONE_WALL.get(), 6)
                .pattern("   ")
                .pattern("###")
                .pattern("###")
                .define('#', ModBlocks.QINGYUAN_STONE.get())
                .unlockedBy(getHasName(ModBlocks.QINGYUAN_STONE.get()), has(ModBlocks.QINGYUAN_STONE.get()))
                .save(pWriter);

        // 清元石按钮配方 - 1个清元石 -> 1个按钮
        ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, ModBlocks.QINGYUAN_STONE_BUTTON.get())
                .requires(ModBlocks.QINGYUAN_STONE.get())
                .unlockedBy(getHasName(ModBlocks.QINGYUAN_STONE.get()), has(ModBlocks.QINGYUAN_STONE.get()))
                .save(pWriter);

        // 清元石压力板配方 - 2个清元石 -> 1个压力板
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, ModBlocks.QINGYUAN_STONE_PRESSURE_PLATE.get())
                .pattern("##")
                .define('#', ModBlocks.QINGYUAN_STONE.get())
                .unlockedBy(getHasName(ModBlocks.QINGYUAN_STONE.get()), has(ModBlocks.QINGYUAN_STONE.get()))
                .save(pWriter);


        // 清元石切石机配方
        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.QINGYUAN_STONE.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.QINGYUAN_STONE_STAIRS.get())
                .unlockedBy(getHasName(ModBlocks.QINGYUAN_STONE.get()), has(ModBlocks.QINGYUAN_STONE.get()))
                .save(pWriter, OrangeYouGladMod.MOD_ID + ":qingyuan_stone_stairs_stonecutting");

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.QINGYUAN_STONE.get()), RecipeCategory.BUILDING_BLOCKS, ModBlocks.QINGYUAN_STONE_SLAB.get(), 2)
                .unlockedBy(getHasName(ModBlocks.QINGYUAN_STONE.get()), has(ModBlocks.QINGYUAN_STONE.get()))
                .save(pWriter, OrangeYouGladMod.MOD_ID + ":qingyuan_stone_slab_stonecutting");

        SingleItemRecipeBuilder.stonecutting(Ingredient.of(ModBlocks.QINGYUAN_STONE.get()), RecipeCategory.DECORATIONS, ModBlocks.QINGYUAN_STONE_WALL.get())
                .unlockedBy(getHasName(ModBlocks.QINGYUAN_STONE.get()), has(ModBlocks.QINGYUAN_STONE.get()))
                .save(pWriter, OrangeYouGladMod.MOD_ID + ":qingyuan_stone_wall_stonecutting");

    }


    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime,
                    pCookingSerializer).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, OrangeYouGladMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}