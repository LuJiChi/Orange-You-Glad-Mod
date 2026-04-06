package com.lujichi.orangeyouglad.item;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.block.ModBlocks;
import com.lujichi.orangeyouglad.entity.ModEntities;
import com.lujichi.orangeyouglad.item.custom.ModFuelItem;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, OrangeYouGladMod.MOD_ID);

    public static final RegistryObject<Item> ORANGE =
            ITEMS.register("orange", () -> new Item(new Item.Properties().food(ModFoods.ORANGE)));
    public static final RegistryObject<Item> SALTED_FISH =
            ITEMS.register("salted_fish", () -> new Item(new Item.Properties().food(ModFoods.SALTED_FISH)));
    public static final RegistryObject<Item> CHICKEN_WING =
            ITEMS.register("chicken_wing", () -> new Item(new Item.Properties().food(ModFoods.CHICKEN_WING)));
    public static final RegistryObject<Item> ONION =
            ITEMS.register("onion", () -> new ItemNameBlockItem(ModBlocks.ONION_CROP.get(),new Item.Properties().food(ModFoods.ONION)));


    public static final RegistryObject<Item> RAW_PALEHARVEST =
            ITEMS.register("raw_paleharvest", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> PALEHARVEST_INGOT =
            ITEMS.register("paleharvest_ingot", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLARITE =
            ITEMS.register("flarite", () -> new ModFuelItem(new Item.Properties(), 2400));

    public static final RegistryObject<Item> PALEHARVEST_SWORD =
            ITEMS.register("paleharvest_sword", () -> new SwordItem(ModToolTiers.PALEHARVEST_INGOT, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> PALEHARVEST_PICKAXE =
            ITEMS.register("paleharvest_pickaxe", () -> new PickaxeItem(ModToolTiers.PALEHARVEST_INGOT, 3, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> PALEHARVEST_AXE =
            ITEMS.register("paleharvest_axe", () -> new AxeItem(ModToolTiers.PALEHARVEST_INGOT, 3, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> PALEHARVEST_SHOVEL =
            ITEMS.register("paleharvest_shovel", () -> new ShovelItem(ModToolTiers.PALEHARVEST_INGOT, 3, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> PALEHARVEST_HOE =
            ITEMS.register("paleharvest_hoe", () -> new HoeItem(ModToolTiers.PALEHARVEST_INGOT, 3, -2.8F, new Item.Properties()));


    public static final RegistryObject<Item> TUANZI_SPAWN_EGG =
            ITEMS.register("tuanzi_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.TUANZI, 0xFFE4C4, 0xF5F5F5,
                    new Item.Properties()));





    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
