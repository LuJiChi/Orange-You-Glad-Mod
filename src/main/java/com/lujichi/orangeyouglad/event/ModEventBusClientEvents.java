package com.lujichi.orangeyouglad.event;


import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.entity.client.Hornbill;
import com.lujichi.orangeyouglad.entity.client.ModModelLayers;
import com.lujichi.orangeyouglad.entity.client.Tuanzi;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrangeYouGladMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerlayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.HORNBILL_LAYER, Hornbill::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.TUANZI_LAYER, Tuanzi::createBodyLayer);
    }
}
