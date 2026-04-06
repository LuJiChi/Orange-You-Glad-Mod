package com.lujichi.orangeyouglad.datagen;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.item.ModItems;
import com.lujichi.orangeyouglad.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, OrangeYouGladMod.MOD_ID);
    }

    @Override
    protected void start() {
        add("chicken_wings_from_chicken", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft","entities/chicken")).build(),
                LootItemRandomChanceCondition.randomChance(0.8f).build()}, ModItems.CHICKEN_WING.get(), 2));

    }
}
