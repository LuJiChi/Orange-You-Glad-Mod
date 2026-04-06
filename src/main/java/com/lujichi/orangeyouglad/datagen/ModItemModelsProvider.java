package com.lujichi.orangeyouglad.datagen;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.block.ModBlocks;
import com.lujichi.orangeyouglad.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.datafix.fixes.EntityPaintingItemFrameDirectionFix;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelsProvider extends ItemModelProvider {
    public ModItemModelsProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, OrangeYouGladMod.MOD_ID, existingFileHelper);
    }
    @Override
    protected void registerModels() {
        basicItem(ModItems.ORANGE.get());
        basicItem(ModItems.SALTED_FISH.get());
        basicItem(ModItems.CHICKEN_WING.get());
        basicItem(ModItems.RAW_PALEHARVEST.get());
        basicItem(ModItems.PALEHARVEST_INGOT.get());
        basicItem(ModItems.FLARITE.get());
        basicItem(ModItems.ONION.get());


        buttonItem(ModBlocks.ORANGE_BUTTON, ModBlocks.ORANGE_PLANKS);
        fenceItem(ModBlocks.ORANGE_FENCE, ModBlocks.ORANGE_PLANKS);
        buttonItem(ModBlocks.QINGYUAN_STONE_BUTTON, ModBlocks.QINGYUAN_STONE);
        wallItem(ModBlocks.QINGYUAN_STONE_WALL, ModBlocks.QINGYUAN_STONE);

        basicItem(ModBlocks.ORANGE_DOOR.get().asItem());

        handleItem(ModItems.PALEHARVEST_SWORD);
        handleItem(ModItems.PALEHARVEST_SHOVEL);
        handleItem(ModItems.PALEHARVEST_PICKAXE);
        handleItem(ModItems.PALEHARVEST_HOE);
        handleItem(ModItems.PALEHARVEST_AXE);

        saplingItem(ModBlocks.ORANGE_SAPLING);

        withExistingParent(ModItems.TUANZI_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

    }


    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return this.withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID, "item/" + item.getId().getPath()));
    }


    private<T extends Block>void buttonItem(RegistryObject<T> block, RegistryObject<Block> base){
            this.withExistingParent(block.getId().getPath(),mcLoc("block/button_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID,
                    "block/" +base.getId().getPath()));
    }
    private<T extends Block>void fenceItem(RegistryObject<T> block, RegistryObject<Block> base) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID,
                        "block/" + base.getId().getPath()));
    }
    private<T extends Block>void wallItem(RegistryObject<T> block, RegistryObject<Block> base) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall", ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID,
                        "block/" + base.getId().getPath()));
    }

    private ItemModelBuilder handleItem(RegistryObject<Item> item){
        return this.withExistingParent(item.getId().getPath(),
                ResourceLocation.withDefaultNamespace("item/handheld")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID, "item/" + item.getId().getPath()));
    }
}
