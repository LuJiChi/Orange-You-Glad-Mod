package com.lujichi.orangeyouglad.entity;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, OrangeYouGladMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<FlariteFurnaceBlockEntity>> FLARITE_FURNACE_BE =
            BLOCK_ENTITIES.register("flarite_furnace_be", () ->
                    BlockEntityType.Builder.of(FlariteFurnaceBlockEntity::new,
                            ModBlocks.FLARITE_FURNACE.get()).build(null));

    public static void register(IEventBus bus) {
        BLOCK_ENTITIES.register(bus);
    }
}
