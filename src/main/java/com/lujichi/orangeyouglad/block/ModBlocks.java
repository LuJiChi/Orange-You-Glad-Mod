package com.lujichi.orangeyouglad.block;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.block.custom.FlariteFurnaceBlock;
import com.lujichi.orangeyouglad.item.ModItems;
import com.lujichi.orangeyouglad.item.custom.ModBlockFuelItem;
import com.lujichi.orangeyouglad.item.custom.ModFlammableRotatedPillarBlock;
import com.lujichi.orangeyouglad.item.custom.OnionCropBlock;
import com.lujichi.orangeyouglad.worldgen.tree.OrangeTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, OrangeYouGladMod.MOD_ID);

    public static final RegistryObject<Block> ORANGE_PLANKS =
            registerBlock("orange_planks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS)) {
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }
            });
    public static final RegistryObject<Block> ORANGE_LOG =
            registerBlock("orange_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3)));
    public static final RegistryObject<Block> ORANGE_WOOD =
            registerBlock("orange_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3)));
    public static final RegistryObject<Block> STRIPPED_ORANGE_LOG =
            registerBlock("stripped_orange_log", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3)));
    public static final RegistryObject<Block> STRIPPED_ORANGE_WOOD =
            registerBlock("stripped_orange_wood", () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(3)));
    public static final RegistryObject<Block> ORANGE_LEAVES =
            registerBlock("orange_leaves", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }
            });







    public static final RegistryObject<Block> QINGYUAN_STONE =
            registerBlock("qingyuan_stone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> PALEHARVEST_ORE =
            registerBlock("paleharvest_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_ORE)));
    public static final RegistryObject<Block> DEEPSLATE_PALEHARVEST_ORE =
            registerBlock("deepslate_paleharvest_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_GOLD_ORE)));
    public static final RegistryObject<Block> RAW_PALEHARVEST_BLOCK =
            registerBlock("raw_paleharvest_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F,6.0F)));
    public static final RegistryObject<Block> PALEHARVEST_INGOT_BLOCK =
            registerBlock("paleharvest_ingot_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F,6.0F)));
    public static final RegistryObject<Block> FLARITE_ORE =
            registerBlock("flarite_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.IRON_ORE)));
    public static final RegistryObject<Block> DEEPSLATE_FLARITE_ORE =
            registerBlock("deepslate_flarite_ore", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_IRON_ORE)));


    public static final RegistryObject<Block> FLARITE_BLOCK =
            registerFuelBlock("flarite_block", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F,6.0F).requiresCorrectToolForDrops()), 24000);

    public static final RegistryObject<Block> CHISELED_QINGYUAN_STONE =
            registerBlock("chiseled_qingyuan_stone", () -> new Block(BlockBehaviour.Properties.of().strength(2.0F,6.0F)));
    public static final RegistryObject<StairBlock> QINGYUAN_STONE_STAIRS =
            registerBlock("qingyuan_stone_stairs",
                    () -> new StairBlock(QINGYUAN_STONE.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(1.5F,6.0F)));
    public static final RegistryObject<SlabBlock> QINGYUAN_STONE_SLAB =
            registerBlock("qingyuan_stone_slab",
                    () -> new SlabBlock(BlockBehaviour.Properties.of().strength(1.5F,6.0F)));
    public static final RegistryObject<ButtonBlock> QINGYUAN_STONE_BUTTON =
            registerBlock("qingyuan_stone_button",
                    () -> new ButtonBlock(BlockBehaviour.Properties.of().strength(0.5F,0.5F), BlockSetType.STONE, 40, true));
    public static final RegistryObject<PressurePlateBlock> QINGYUAN_STONE_PRESSURE_PLATE =
            registerBlock("qingyuan_stone_pressure_plate",
                    () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().strength(0.5F,0.5F), BlockSetType.STONE));
    public static final RegistryObject<WallBlock> QINGYUAN_STONE_WALL =
            registerBlock("qingyuan_stone_wall",
                    () -> new WallBlock(BlockBehaviour.Properties.of().strength(1.5F,6.0F)));

    public static final RegistryObject<FenceGateBlock> ORANGE_FENCE_GATE =
            registerFuelBlock("orange_fence_gate",
                    () -> new FenceGateBlock(BlockBehaviour.Properties.of().strength(1.5F,3.0F), WoodType.OAK), 300);
    public static final RegistryObject<FenceBlock> ORANGE_FENCE =
            registerFuelBlock("orange_fence",
                    () -> new FenceBlock(BlockBehaviour.Properties.of().strength(1.5F,3.0F).sound(SoundType.WOOD)), 300);
    public static final RegistryObject<DoorBlock> ORANGE_DOOR =
            registerFuelBlock("orange_door",
                    () -> new DoorBlock(BlockBehaviour.Properties.of().strength(1.5F,3.0F), BlockSetType.OAK), 300);
    public static final RegistryObject<TrapDoorBlock> ORANGE_TRAPDOOR =
            registerFuelBlock("orange_trapdoor",
                    () -> new TrapDoorBlock(BlockBehaviour.Properties.of().strength(1.5F,3.0F), BlockSetType.OAK), 300);
    public static final RegistryObject<StairBlock> ORANGE_STAIRS =
            registerFuelBlock("orange_stairs",
                    () -> new StairBlock(ORANGE_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(1.5F,6.0F).sound(SoundType.WOOD)), 300);
    public static final RegistryObject<SlabBlock> ORANGE_SLAB =
            registerFuelBlock("orange_slab",
                    () -> new SlabBlock(BlockBehaviour.Properties.of().strength(1.5F,6.0F).sound(SoundType.WOOD)), 150);
    public static final RegistryObject<ButtonBlock> ORANGE_BUTTON =
            registerFuelBlock("orange_button",
                    () -> new ButtonBlock(BlockBehaviour.Properties.of().strength(0.5F,0.5F), BlockSetType.OAK, 20, true), 100);
    public static final RegistryObject<PressurePlateBlock> ORANGE_PRESSURE_PLATE =
            registerFuelBlock("orange_pressure_plate",
                    () -> new PressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().strength(0.5F,0.5F), BlockSetType.OAK), 300);


    public static final RegistryObject<Block> ONION_CROP =
            BLOCKS.register("onion_crop", () -> new OnionCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noCollission().noOcclusion()));

    public static final RegistryObject<Block> ORANGE_SAPLING = registerBlock("orange_sapling",
            () -> new SaplingBlock(new OrangeTreeGrower(),BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<Block> FLARITE_FURNACE = registerBlock("flarite_furnace",
            () -> new FlariteFurnaceBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));


    private static <T extends Block> void registerBlockItems(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> blocks = BLOCKS.register(name, block);
        registerBlockItems(name, blocks);
        return blocks;
    }

    private static <T extends Block> void registerFuelBlockItems(String name, RegistryObject<T> block, int burnTime) {
        ModItems.ITEMS.register(name, () -> new ModBlockFuelItem(block.get(), new Item.Properties(), burnTime));
    }

    private static <T extends Block> RegistryObject<T> registerFuelBlock(String name, Supplier<T> block, int burnTime) {
        RegistryObject<T> blocks = BLOCKS.register(name, block);
        registerFuelBlockItems(name, blocks, burnTime);
        return blocks;
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
