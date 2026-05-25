package com.lujichi.orangeyouglad.datagen;


import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.block.ModBlocks;
import com.lujichi.orangeyouglad.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
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



        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.DANGO_INGOT.get(), 9)
                .requires(ModBlocks.DANGO_INGOT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.DANGO_INGOT_BLOCK.get()), has(ModBlocks.DANGO_INGOT_BLOCK.get()))
                .save(pWriter);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FLARITE.get(), 9)
                .requires(ModBlocks.FLARITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.FLARITE_BLOCK.get()), has(ModBlocks.FLARITE_BLOCK.get()))
                .save(pWriter);


        oreCooking(pWriter, RecipeSerializer.SMELTING_RECIPE, List.of(ModItems.CHICKEN_WING.get()), RecipeCategory.MISC, ModItems.COOKED_CHICKEN_WING.get(), 0.3F, 160, "chicken_wing", "_from_smelting");

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
