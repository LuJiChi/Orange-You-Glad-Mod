package com.lujichi.orangeyouglad.datagen;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, OrangeYouGladMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.QINGYUAN_STONE.get(),
                        ModBlocks.QINGYUAN_STONE_BUTTON.get(),
                        ModBlocks.QINGYUAN_STONE_PRESSURE_PLATE.get(),
                        ModBlocks.CHISELED_QINGYUAN_STONE.get(),
                        ModBlocks.QINGYUAN_STONE_WALL.get(),
                        ModBlocks.QINGYUAN_STONE_STAIRS.get(),
                        ModBlocks.QINGYUAN_STONE_SLAB.get(),
                        ModBlocks.FLARITE_ORE.get(),
                        ModBlocks.FLARITE_BLOCK.get(),
                        ModBlocks.CHISELED_QINGYUAN_STONE.get(),
                        ModBlocks.DEEPSLATE_FLARITE_ORE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.DANGO_ORE.get(),
                        ModBlocks.DANGO_INGOT_BLOCK.get(),
                        ModBlocks.DEEPSLATE_DANGO_ORE.get());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.CHISELED_QINGYUAN_STONE.get());

        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.ORANGE_FENCE.get());
        tag(BlockTags.FENCE_GATES)
                .add(ModBlocks.ORANGE_FENCE_GATE.get());
        tag(BlockTags.WALLS)
                .add(ModBlocks.QINGYUAN_STONE_WALL.get());

        tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.ORANGE_LOG.get(),
                        ModBlocks.ORANGE_WOOD.get(),
                        ModBlocks.STRIPPED_ORANGE_LOG.get(),
                        ModBlocks.STRIPPED_ORANGE_WOOD.get());

        tag(BlockTags.PLANKS)
                .add(ModBlocks.ORANGE_PLANKS.get());

        tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.FLARITE_BLOCK.get(),
                        ModBlocks.DANGO_INGOT_BLOCK.get());
    }
}
