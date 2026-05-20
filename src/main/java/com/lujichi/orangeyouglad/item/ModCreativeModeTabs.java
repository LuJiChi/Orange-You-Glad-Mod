package com.lujichi.orangeyouglad.item;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, OrangeYouGladMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ORANGEYOUGLAD_TAB =
            CREATIVE_MODE_TABS.register("orangeyouglad_tab", () -> CreativeModeTab.builder()
                    .icon(() -> new ItemStack(ModItems.CHICKEN_WING.get()))
                    .title(Component.translatable("itemGroup.orangeyouglad_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.CHICKEN_WING.get());
                        pOutput.accept(ModItems.COOKED_CHICKEN_WING.get());
                        pOutput.accept(ModItems.SALTED_FISH.get());
                        pOutput.accept(ModItems.ORANGE.get());
                        pOutput.accept(ModItems.RAW_DANGO.get());
                        pOutput.accept(ModItems.DANGO_INGOT.get());
                        pOutput.accept(ModItems.FLARITE.get());
                        pOutput.accept(ModItems.ONION.get());

                        pOutput.accept(ModBlocks.ORANGE_PLANKS.get());
                        pOutput.accept(ModBlocks.ORANGE_LOG.get());
                        pOutput.accept(ModBlocks.ORANGE_WOOD.get());
                        pOutput.accept(ModBlocks.STRIPPED_ORANGE_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_ORANGE_WOOD.get());
                        pOutput.accept(ModBlocks.ORANGE_LEAVES.get());


                        pOutput.accept(ModBlocks.QINGYUAN_STONE.get());
                        pOutput.accept(ModBlocks.DANGO_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_DANGO_ORE.get());
                        pOutput.accept(ModBlocks.RAW_DANGO_BLOCK.get());
                        pOutput.accept(ModBlocks.DANGO_INGOT_BLOCK.get());
                        pOutput.accept(ModBlocks.FLARITE_ORE.get());
                        pOutput.accept(ModBlocks.DEEPSLATE_FLARITE_ORE.get());
                        pOutput.accept(ModBlocks.FLARITE_BLOCK.get());

                        pOutput.accept(ModBlocks.QINGYUAN_STONE_STAIRS.get());
                        pOutput.accept(ModBlocks.QINGYUAN_STONE_SLAB.get());
                        pOutput.accept(ModBlocks.QINGYUAN_STONE_BUTTON.get());
                        pOutput.accept(ModBlocks.QINGYUAN_STONE_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.QINGYUAN_STONE_WALL.get());

                        pOutput.accept(ModBlocks.ORANGE_STAIRS.get());
                        pOutput.accept(ModBlocks.ORANGE_SLAB.get());
                        pOutput.accept(ModBlocks.ORANGE_BUTTON.get());
                        pOutput.accept(ModBlocks.ORANGE_FENCE.get());
                        pOutput.accept(ModBlocks.ORANGE_FENCE_GATE.get());
                        pOutput.accept(ModBlocks.ORANGE_DOOR.get());
                        pOutput.accept(ModBlocks.ORANGE_TRAPDOOR.get());
                        pOutput.accept(ModBlocks.ORANGE_PRESSURE_PLATE.get());

                        pOutput.accept(ModItems.DANGO_SWORD.get());
                        pOutput.accept(ModItems.DANGO_AXE.get());
                        pOutput.accept(ModItems.DANGO_HOE.get());
                        pOutput.accept(ModItems.DANGO_PICKAXE.get());
                        pOutput.accept(ModItems.DANGO_SHOVEL.get());

                        pOutput.accept(ModItems.TUANZI_SPAWN_EGG.get());

                        pOutput.accept(ModBlocks.ORANGE_SAPLING.get());

                        pOutput.accept(ModBlocks.FLARITE_FURNACE.get());


                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
