package com.lujichi.orangeyouglad.datagen;


import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.block.ModBlocks;
import com.lujichi.orangeyouglad.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {

    public ModItemTagsProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pLookupProvider, CompletableFuture<TagLookup<Block>> pBlockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(pOutput, pLookupProvider, pBlockTags, OrangeYouGladMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(ItemTags.COALS)
                .add(ModItems.FLARITE.get());

        tag(ItemTags.PLANKS)
                .add(ModBlocks.ORANGE_PLANKS.get().asItem());
        tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.ORANGE_LOG.get().asItem(),
                        ModBlocks.ORANGE_WOOD.get().asItem(),
                        ModBlocks.STRIPPED_ORANGE_LOG.get().asItem(),
                        ModBlocks.STRIPPED_ORANGE_WOOD.get().asItem());
        tag(ItemTags.FISHES)
                .add(ModItems.SALTED_FISH.get());
    }
}
