package com.lujichi.orangeyouglad.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties ORANGE = new FoodProperties.Builder()
            .nutrition(4)
            .saturationMod(0.3F)
            .build();
    public static final FoodProperties SALTED_FISH = new FoodProperties.Builder()
            .nutrition(4)
            .saturationMod(0.2F)
            .meat()
            .build();
    public static final FoodProperties CHICKEN_WING = new FoodProperties.Builder()
            .nutrition(4)
            .saturationMod(0.2F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 200), 0.3F)
            .meat()
            .build();
    public static final FoodProperties COOKED_CHICKEN_WING = new FoodProperties.Builder()
            .nutrition(6)
            .saturationMod(0.8f)
            .meat()
            .build();
    public static final FoodProperties ONION = new FoodProperties.Builder()
            .nutrition(2)
            .saturationMod(0.1F)
            .build();
    public static final FoodProperties ORANGE_BRAISED_CHICKEN_WING = new FoodProperties.Builder()
            .nutrition(8)
            .saturationMod(0.6F)
            .meat()
            .build();
}
