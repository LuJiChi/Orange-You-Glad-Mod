package com.lujichi.orangeyouglad.datagen;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.item.ModItems;
import com.lujichi.orangeyouglad.loot.AddItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, OrangeYouGladMod.MOD_ID);
    }

    @Override
    protected void start() {
        add("chicken_wings_from_chicken", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft", "entities/chicken")).build(),
                LootItemRandomChanceCondition.randomChance(0.8f).build()}, ModItems.CHICKEN_WING.get(), 2));
        // Add salted fish to the fishing "fish" loot table with a small chance
        add("salted_fish_from_fishing", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft", "gameplay/fishing/fish")).build() },
                ModItems.SALTED_FISH.get(), 1));
        add("salted_fish_from_treasure", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(ResourceLocation.fromNamespaceAndPath("minecraft", "chests/buried_treasure")).build() },
                ModItems.SALTED_FISH.get(), 1));

    }
}
