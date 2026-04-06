package com.lujichi.orangeyouglad.tags;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModBlockTags {
    private static TagKey<Block> create(String pName) {
        return TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID, pName));
    }
}
