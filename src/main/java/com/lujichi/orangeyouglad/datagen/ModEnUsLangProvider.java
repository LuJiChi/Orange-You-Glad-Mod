package com.lujichi.orangeyouglad.datagen;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.block.ModBlocks;
import com.lujichi.orangeyouglad.effect.ModEffects;
import com.lujichi.orangeyouglad.entity.ModEntities;
import com.lujichi.orangeyouglad.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModEnUsLangProvider extends LanguageProvider {
        public ModEnUsLangProvider(PackOutput output) {
            super(output, OrangeYouGladMod.MOD_ID, "en_us");
        }

    @Override
    protected void addTranslations() {
        add(ModItems.ORANGE.get(), "Orange");
        add(ModItems.SALTED_FISH.get(), "Salted Fish");
        add(ModItems.CHICKEN_WING.get(), "Chicken Wing");
        add(ModItems.RAW_PALEHARVEST.get(), "Raw Paleharvest");
        add(ModItems.PALEHARVEST_INGOT.get(), "Paleharvest Ingot");
        add(ModItems.FLARITE.get(), "Flarite");
        add(ModItems.ONION.get(), "Onion");



        add(ModBlocks.ORANGE_PLANKS.get(), "Orange Planks");
        add(ModBlocks.ORANGE_LOG.get(), "Orange Log");
        add(ModBlocks.ORANGE_WOOD.get(), "Orange Wood");
        add(ModBlocks.STRIPPED_ORANGE_LOG.get(), "Stripped Orange Log");
        add(ModBlocks.STRIPPED_ORANGE_WOOD.get(), "Stripped Orange Wood");
        add(ModBlocks.ORANGE_LEAVES.get(), "Orange Leaves");
        add(ModBlocks.ONION_CROP.get(), "Onion Crop");
        add(ModBlocks.ORANGE_SAPLING.get(), "Orange Sapling");



        add(ModBlocks.QINGYUAN_STONE.get(), "Qingyuan Stone");
        add(ModBlocks.PALEHARVEST_ORE.get(), "Paleharvest Ore");
        add(ModBlocks.DEEPSLATE_PALEHARVEST_ORE.get(), "Deepslate Paleharvest Ore");
        add(ModBlocks.RAW_PALEHARVEST_BLOCK.get(), "Raw Paleharvest Block");
        add(ModBlocks.PALEHARVEST_INGOT_BLOCK.get(), "Paleharvest Ingot Block");
        add(ModBlocks.FLARITE_ORE.get(), "Flarite Ore");
        add(ModBlocks.DEEPSLATE_FLARITE_ORE.get(), "Deepslate Flarite Ore");
        add(ModBlocks.FLARITE_BLOCK.get(), "Flarite Block");
        add(ModBlocks.FLARITE_FURNACE.get(), "Flarite Furnace");

        add(ModBlocks.ORANGE_STAIRS.get(), "Orange Stairs");
        add(ModBlocks.ORANGE_SLAB.get(), "Orange Slab");
        add(ModBlocks.ORANGE_BUTTON.get(), "Orange Button");
        add(ModBlocks.ORANGE_PRESSURE_PLATE.get(), "Orange Pressure Plate");
        add(ModBlocks.ORANGE_FENCE.get(), "Orange Fence");
        add(ModBlocks.ORANGE_FENCE_GATE.get(), "Orange Fence Gate");
        add(ModBlocks.ORANGE_DOOR.get(), "Orange Door");
        add(ModBlocks.ORANGE_TRAPDOOR.get(), "Orange Trapdoor");

        add(ModBlocks.QINGYUAN_STONE_WALL.get(), "Qingyuan Stone Wall");
        add(ModBlocks.QINGYUAN_STONE_STAIRS.get(), "Qingyuan Stone Stairs");
        add(ModBlocks.QINGYUAN_STONE_SLAB.get(), "Qingyuan Stone Slab");
        add(ModBlocks.QINGYUAN_STONE_BUTTON.get(), "Qingyuan Stone Button");
        add(ModBlocks.QINGYUAN_STONE_PRESSURE_PLATE.get(), "Qingyuan Stone Pressure Plate");
        add(ModBlocks.CHISELED_QINGYUAN_STONE.get(), "Chiseled Qingyuan Stone");



        add(ModItems.PALEHARVEST_SWORD.get(), "Paleharvest Sword");
        add(ModItems.PALEHARVEST_PICKAXE.get(), "Paleharvest Pickaxe");
        add(ModItems.PALEHARVEST_AXE.get(), "Paleharvest Axe");
        add(ModItems.PALEHARVEST_SHOVEL.get(), "Paleharvest Shovel");
        add(ModItems.PALEHARVEST_HOE.get(), "Paleharvest Hoe");
        add(ModItems.TUANZI_SPAWN_EGG.get(), "Tuanzi Spawn Egg");

        add(ModEffects.HARVEST.get(), "Harvest");

        add(ModEntities.TUANZI.get(), "Tuanzi");
        add(ModEntities.HORNBILL.get(), "Hornbill");

        add("entity.orangeyouglad.bai_zang_painting", "Bai Zang Painting");

        add("tooltip.flarite_furnace.talent", "TalentValue:%s/%s");
        add("jei.orangeyouglad_mod.talent_cost", "TalentValueCost: %s");
        add("jei.orangeyouglad_mod.talent_bar_hint", "Flarite=1000 TalentValue");

        add("itemGroup.orangeyouglad_tab", "Orange You Glad");
    }
}
