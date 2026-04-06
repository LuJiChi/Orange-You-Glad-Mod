package com.lujichi.orangeyouglad.tags;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
    private static TagKey<Item> bind(String pName) {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID, pName));
    }
}
