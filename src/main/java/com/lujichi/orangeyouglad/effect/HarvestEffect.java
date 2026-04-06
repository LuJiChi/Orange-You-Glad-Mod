package com.lujichi.orangeyouglad.effect;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BoneMealItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class HarvestEffect extends MobEffect {

    protected HarvestEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }
    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        Level level = entity.level();
        if (level.isClientSide) return;


        if (level.getGameTime() % 20 == 0) {
            RandomSource random = entity.getRandom();
            BlockPos center = entity.blockPosition();

            float probability = 0.1f + 0.1f;

            probability = Math.min(1.0f, probability);

            for (int dx = -1; dx <= 1; dx++) {

                for (int dz = -1; dz <= 1; dz++) {
                    BlockPos pos = center.offset(dx, 0, dz);
                    if (random.nextFloat() < probability) {
                        if (BoneMealItem.growCrop(ItemStack.EMPTY, level, pos)) {
                            level.levelEvent(2005, pos, 0);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }
}
