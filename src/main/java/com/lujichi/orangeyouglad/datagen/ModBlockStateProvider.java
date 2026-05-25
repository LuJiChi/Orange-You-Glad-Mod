package com.lujichi.orangeyouglad.datagen;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.block.ModBlocks;
import com.lujichi.orangeyouglad.item.custom.OnionCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;


public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, OrangeYouGladMod.MOD_ID, exFileHelper);
    }
    @Override
    protected void registerStatesAndModels() {
        simpleBlockWithItem(ModBlocks.ORANGE_PLANKS.get(), cubeAll(ModBlocks.ORANGE_PLANKS.get()));
        simpleBlockWithItem(ModBlocks.QINGYUAN_STONE.get(), cubeAll(ModBlocks.QINGYUAN_STONE.get()));
        simpleBlockWithItem(ModBlocks.DANGO_ORE.get(), cubeAll(ModBlocks.DANGO_ORE.get()));
        simpleBlockWithItem(ModBlocks.DANGO_INGOT_BLOCK.get(), cubeAll(ModBlocks.DANGO_INGOT_BLOCK.get()));
        simpleBlockWithItem(ModBlocks.FLARITE_ORE.get(), cubeAll(ModBlocks.FLARITE_ORE.get()));
        simpleBlockWithItem(ModBlocks.FLARITE_BLOCK.get(), cubeAll(ModBlocks.FLARITE_BLOCK.get()));
        simpleBlockWithItem(ModBlocks.DEEPSLATE_DANGO_ORE.get(), cubeAll(ModBlocks.DEEPSLATE_DANGO_ORE.get()));
        simpleBlockWithItem(ModBlocks.DEEPSLATE_FLARITE_ORE.get(), cubeAll(ModBlocks.DEEPSLATE_FLARITE_ORE.get()));

        stairsBlock(ModBlocks.ORANGE_STAIRS.get(), blockTexture(ModBlocks.ORANGE_PLANKS.get()));
        slabBlock(ModBlocks.ORANGE_SLAB.get(), blockTexture(ModBlocks.ORANGE_PLANKS.get()), blockTexture(ModBlocks.ORANGE_PLANKS.get()));
        buttonBlock(ModBlocks.ORANGE_BUTTON.get(), blockTexture(ModBlocks.ORANGE_PLANKS.get()));
        pressurePlateBlock(ModBlocks.ORANGE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.ORANGE_PLANKS.get()));
        fenceBlock(ModBlocks.ORANGE_FENCE.get(), blockTexture(ModBlocks.ORANGE_PLANKS.get()));
        fenceGateBlock(ModBlocks.ORANGE_FENCE_GATE.get(), blockTexture(ModBlocks.ORANGE_PLANKS.get()));
        doorBlockWithRenderType(ModBlocks.ORANGE_DOOR.get(),
                modLoc("block/orange_door_bottom"), modLoc("block/orange_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.ORANGE_TRAPDOOR.get(), modLoc("block/orange_trapdoor"), true, "cutout");

        stairsBlock(ModBlocks.QINGYUAN_STONE_STAIRS.get(), blockTexture(ModBlocks.QINGYUAN_STONE.get()));
        slabBlock(ModBlocks.QINGYUAN_STONE_SLAB.get(), blockTexture(ModBlocks.QINGYUAN_STONE.get()), blockTexture(ModBlocks.QINGYUAN_STONE.get()));
        buttonBlock(ModBlocks.QINGYUAN_STONE_BUTTON.get(), blockTexture(ModBlocks.QINGYUAN_STONE.get()));
        pressurePlateBlock(ModBlocks.QINGYUAN_STONE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.QINGYUAN_STONE.get()));
        wallBlock(ModBlocks.QINGYUAN_STONE_WALL.get(), blockTexture(ModBlocks.QINGYUAN_STONE.get()));

        blockItem(ModBlocks.ORANGE_STAIRS);
        blockItem(ModBlocks.ORANGE_SLAB);
        blockItem(ModBlocks.ORANGE_PRESSURE_PLATE);
        blockItem(ModBlocks.ORANGE_FENCE_GATE);
        blockItem(ModBlocks.ORANGE_TRAPDOOR, "_bottom");

        blockItem(ModBlocks.QINGYUAN_STONE_STAIRS);
        blockItem(ModBlocks.QINGYUAN_STONE_SLAB);
        blockItem(ModBlocks.QINGYUAN_STONE_PRESSURE_PLATE);
        blockItem(ModBlocks.QINGYUAN_STONE_WALL);

        blockItem(ModBlocks.ORANGE_LOG);
        blockItem(ModBlocks.ORANGE_WOOD);
        blockItem(ModBlocks.STRIPPED_ORANGE_LOG);
        blockItem(ModBlocks.STRIPPED_ORANGE_WOOD);


        simpleBlock(ModBlocks.ORANGE_LEAVES.get());
        blockItem(ModBlocks.ORANGE_LEAVES);

        logBlock((RotatedPillarBlock) ModBlocks.ORANGE_LOG.get());
        axisBlock((RotatedPillarBlock) ModBlocks.ORANGE_WOOD.get(), blockTexture(ModBlocks.ORANGE_LOG.get()), blockTexture(ModBlocks.ORANGE_LOG.get()));

        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_ORANGE_LOG.get(), blockTexture(ModBlocks.STRIPPED_ORANGE_LOG.get()),
                modLoc("block/stripped_orange_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_ORANGE_WOOD.get(), blockTexture(ModBlocks.STRIPPED_ORANGE_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_ORANGE_LOG.get()));

        makeOnionStates((CropBlock) ModBlocks.ONION_CROP.get(), "onion_stage", "onion_stage");

        saplingStoneBlock(ModBlocks.ORANGE_SAPLING);

        simpleBlockWithItem(ModBlocks.FLARITE_FURNACE.get(), new ModelFile.UncheckedModelFile(OrangeYouGladMod.MOD_ID + ":block/flarite_furnace"));


    }

    public void saplingStoneBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    public void makeOnionStates(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> onionStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }

    private ConfiguredModel[] onionStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((OnionCropBlock) block).getAgeProperty()),
                ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID, "block/" + textureName + state.getValue(((OnionCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }

    private <T extends Block> void blockItem(RegistryObject<T> block) {
        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(OrangeYouGladMod.MOD_ID + ":block/" + block.getId().getPath()));
    }
    private <T extends Block> void blockItem(RegistryObject<T> block, String append) {
        simpleBlockItem(block.get(), new ModelFile.UncheckedModelFile(OrangeYouGladMod.MOD_ID + ":block/" + block.getId().getPath() + append));
    }
}
