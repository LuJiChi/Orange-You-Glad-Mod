package com.lujichi.orangeyouglad.effect;


import com.lujichi.orangeyouglad.OrangeYouGladMod;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {
    public static final DeferredRegister<MobEffect> EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, OrangeYouGladMod.MOD_ID);

    public static final RegistryObject<MobEffect> HARVEST = EFFECTS.register("harvest",
            () -> new HarvestEffect(MobEffectCategory.HARMFUL, 0xFFA500));




    public static void register(IEventBus bus) {
        EFFECTS.register(bus);
    }
}

