package com.lujichi.orangeyouglad.entity.client;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation HORNBILL_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID, "hornbill"), "main");
    public static final ModelLayerLocation TUANZI_LAYER = new ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID, "tuanzi"), "main");
}
