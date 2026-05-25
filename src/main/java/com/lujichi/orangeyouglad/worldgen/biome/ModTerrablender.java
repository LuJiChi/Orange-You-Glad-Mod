package com.lujichi.orangeyouglad.worldgen.biome;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import net.minecraft.resources.ResourceLocation;
import terrablender.api.Regions;

public class ModTerrablender {
    public static void registerBiome() {
        Regions.register(new ModOverworldRegion(ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID, "overworld"), 10));
    }
}
