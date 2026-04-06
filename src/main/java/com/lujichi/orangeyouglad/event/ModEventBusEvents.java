package com.lujichi.orangeyouglad.event;


import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.entity.ModEntities;
import com.lujichi.orangeyouglad.entity.custom.HornbillEntity;
import com.lujichi.orangeyouglad.entity.custom.TuanziEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = OrangeYouGladMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.HORNBILL.get(), HornbillEntity.createAttributes().build());
        event.put(ModEntities.TUANZI.get(), TuanziEntity.createAttributes().build());
    }
}
