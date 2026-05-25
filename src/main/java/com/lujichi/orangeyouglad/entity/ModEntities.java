package com.lujichi.orangeyouglad.entity;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.entity.custom.HornbillEntity;
import com.lujichi.orangeyouglad.entity.custom.TuanziEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, OrangeYouGladMod.MOD_ID);

    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, OrangeYouGladMod.MOD_ID);

    public static final RegistryObject<EntityType<HornbillEntity>> HORNBILL = ENTITY_TYPES.register("hornbill",
            () -> EntityType.Builder.of(HornbillEntity::new, MobCategory.CREATURE).sized(0.5F, 0.5F).build("hornbill"));
    public static final RegistryObject<EntityType<TuanziEntity>> TUANZI = ENTITY_TYPES.register("tuanzi",
            () -> EntityType.Builder.of(TuanziEntity::new, MobCategory.CREATURE).sized(0.5F,0.5F).build("tuanzi"));


    // 注册自定义画变体
    public static final RegistryObject<PaintingVariant> TUANZI_PAINTING = PAINTING_VARIANTS.register("tuanzi_painting",
            () -> new PaintingVariant(64, 64));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
        PAINTING_VARIANTS.register(eventBus);
    }
}
