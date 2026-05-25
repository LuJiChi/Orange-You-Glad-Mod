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
    public static final RegistryObject<Item> COOKED_CHICKEN_WING =
            ITEMS.register("cooked_chicken_wing", () -> new Item(new Item.Properties().food(ModFoods.COOKED_CHICKEN_WING)));
    public static final RegistryObject<Item> ONION =
            ITEMS.register("onion", () -> new ItemNameBlockItem(ModBlocks.ONION_CROP.get(),new Item.Properties().food(ModFoods.ONION)));
    public static final RegistryObject<Item> ORANGE_BRAISED_CHICKEN_WING =
            ITEMS.register("orange_braised_chicken_wing", () -> new BowlFoodItem(new Item.Properties().food(ModFoods.ORANGE_BRAISED_CHICKEN_WING)));


    public static final RegistryObject<Item> RAW_DANGO =
            ITEMS.register("raw_dango", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DANGO_INGOT =
            ITEMS.register("dango_ingot", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> FLARITE =
            ITEMS.register("flarite", () -> new ModFuelItem(new Item.Properties(), 2400));

    public static final RegistryObject<Item> DANGO_SWORD =
            ITEMS.register("dango_sword", () -> new SwordItem(ModToolTiers.DANGO_INGOT, 3, -2.4F, new Item.Properties()));
    public static final RegistryObject<Item> DANGO_PICKAXE =
            ITEMS.register("dango_pickaxe", () -> new PickaxeItem(ModToolTiers.DANGO_INGOT, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> DANGO_AXE =
            ITEMS.register("dango_axe", () -> new AxeItem(ModToolTiers.DANGO_INGOT, 6, -3.1F, new Item.Properties()));
    public static final RegistryObject<Item> DANGO_SHOVEL =
            ITEMS.register("dango_shovel", () -> new ShovelItem(ModToolTiers.DANGO_INGOT, 1.5F, -3F, new Item.Properties()));
    public static final RegistryObject<Item> DANGO_HOE =
            ITEMS.register("dango_hoe", () -> new HoeItem(ModToolTiers.DANGO_INGOT, -2, -1F, new Item.Properties()));


    public static final RegistryObject<Item> TUANZI_SPAWN_EGG =
            ITEMS.register("tuanzi_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.TUANZI, 0xFFE4C4, 0xF5F5F5,
                    new Item.Properties()));
    public static final RegistryObject<Item> HORNBILL_SPAWN_EGG =
            ITEMS.register("hornbill_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.HORNBILL, 0x082e07, 0x076605,
                    new Item.Properties()));





    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }
}
