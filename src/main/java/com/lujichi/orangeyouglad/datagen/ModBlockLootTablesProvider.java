package com.lujichi.orangeyouglad.datagen;

import com.lujichi.orangeyouglad.block.ModBlocks;
import com.lujichi.orangeyouglad.item.ModItems;
import com.lujichi.orangeyouglad.item.custom.OnionCropBlock;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTablesProvider extends BlockLootSubProvider {
    public ModBlockLootTablesProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.ORANGE_PLANKS.get());
        dropSelf(ModBlocks.ORANGE_LOG.get());
        dropSelf(ModBlocks.ORANGE_WOOD.get());
        dropSelf(ModBlocks.STRIPPED_ORANGE_LOG.get());
        dropSelf(ModBlocks.STRIPPED_ORANGE_WOOD.get());
        dropSelf(ModBlocks.ORANGE_SAPLING.get());
        add(ModBlocks.ORANGE_LEAVES.get(),block -> createLeavesDrops(block,ModBlocks.ORANGE_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES)
                .withPool(LootPool.lootPool().add(LootItem.lootTableItem(ModItems.ORANGE.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 2))))
                        .when(LootItemRandomChanceCondition.randomChance(0.3F))));

        dropSelf(ModBlocks.QINGYUAN_STONE.get());
        dropSelf(ModBlocks.DANGO_INGOT_BLOCK.get());
        dropSelf(ModBlocks.FLARITE_BLOCK.get());
        add(ModBlocks.DANGO_ORE.get(), block -> createOreDrop(ModBlocks.DANGO_ORE.get(), ModItems.RAW_DANGO.get()));
        add(ModBlocks.DEEPSLATE_DANGO_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_DANGO_ORE.get(), ModItems.RAW_DANGO.get()));
        add(ModBlocks.FLARITE_ORE.get(), block -> createOreDrop(ModBlocks.FLARITE_ORE.get(), ModItems.FLARITE.get()));
        add(ModBlocks.DEEPSLATE_FLARITE_ORE.get(), block -> createOreDrop(ModBlocks.DEEPSLATE_FLARITE_ORE.get(), ModItems.FLARITE.get()));

        dropSelf(ModBlocks.ORANGE_STAIRS.get());
        add(ModBlocks.ORANGE_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.ORANGE_SLAB.get()));
        dropSelf(ModBlocks.ORANGE_BUTTON.get());
        dropSelf(ModBlocks.ORANGE_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.ORANGE_FENCE.get());
        dropSelf(ModBlocks.ORANGE_FENCE_GATE.get());
        add(ModBlocks.ORANGE_DOOR.get(),
                block -> createDoorTable(ModBlocks.ORANGE_DOOR.get()));
        dropSelf(ModBlocks.ORANGE_TRAPDOOR.get());

        dropSelf(ModBlocks.QINGYUAN_STONE_WALL.get());
        dropSelf(ModBlocks.QINGYUAN_STONE_STAIRS.get());
        dropSelf(ModBlocks.QINGYUAN_STONE_SLAB.get());
        dropSelf(ModBlocks.QINGYUAN_STONE_BUTTON.get());
        dropSelf(ModBlocks.QINGYUAN_STONE_PRESSURE_PLATE.get());

        dropSelf(ModBlocks.FLARITE_FURNACE.get());


        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.ONION_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(OnionCropBlock.AGE, 5));
        add(ModBlocks.ONION_CROP.get(),
                block -> createCropDrops(ModBlocks.ONION_CROP.get(), ModItems.ONION.get(), ModItems.ONION.get(), lootitemcondition$builder));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
