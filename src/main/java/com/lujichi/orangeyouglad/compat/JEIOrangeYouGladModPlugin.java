package com.lujichi.orangeyouglad.compat;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.block.ModBlocks;
import com.lujichi.orangeyouglad.recipe.FlariteFurnaceRecipe;
import com.lujichi.orangeyouglad.screen.FlariteFurnaceScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.awt.*;
import java.util.List;

@JeiPlugin
public class JEIOrangeYouGladModPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new FlariteFurnaceCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<FlariteFurnaceRecipe> recipes = recipeManager.getAllRecipesFor(FlariteFurnaceRecipe.Type.INSTANCE);
        registration.addRecipes(FlariteFurnaceCategory.FLARITE_FURNACE_TYPE, recipes);
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(FlariteFurnaceScreen.class, 75, 25, 40, 30,
                FlariteFurnaceCategory.FLARITE_FURNACE_TYPE);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.FLARITE_FURNACE.get()),
                FlariteFurnaceCategory.FLARITE_FURNACE_TYPE);
    }
}