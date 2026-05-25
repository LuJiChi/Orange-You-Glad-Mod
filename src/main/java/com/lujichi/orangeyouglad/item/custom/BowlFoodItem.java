package com.lujichi.orangeyouglad.item.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class BowlFoodItem extends Item {
    public BowlFoodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        super.finishUsingItem(pStack, pLevel, pEntityLiving);

        if (pEntityLiving instanceof Player player && !pLevel.isClientSide) {
            ItemStack bowlStack = new ItemStack(Items.BOWL);
            if (!player.getInventory().add(bowlStack)) {
                player.drop(bowlStack, false);
            }
        }

        return pStack;
    }
}