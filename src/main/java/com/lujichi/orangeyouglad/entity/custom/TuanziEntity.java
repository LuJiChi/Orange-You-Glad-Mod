package com.lujichi.orangeyouglad.entity.custom;

import com.lujichi.orangeyouglad.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class TuanziEntity extends Animal {
    public TuanziEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new RandomJumpGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.5D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.2D, Ingredient.of(Items.SWEET_BERRIES), false));
        this.goalSelector.addGoal(4, new FollowMobGoal(this, 1.2D, 2.0F, 2.0F));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomFlyingGoal(this, 0.6D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 2.0F));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10)
                .add(Attributes.FOLLOW_RANGE, 16)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FLYING_SPEED, 0.5D)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5D);
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.TUANZI.get().create(pLevel);
    }

    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.SWEET_BERRIES);
    }

    static class RandomJumpGoal extends Goal {
        private final TuanziEntity entity;
        private int jumpDelay;

        public RandomJumpGoal(TuanziEntity entity) {
            this.entity = entity;
            this.jumpDelay = entity.random.nextInt(20) + 10; // 10-30 tick延迟
        }

        @Override
        public boolean canUse() {
            return this.entity.onGround() && !this.entity.isInWater();
        }

        @Override
        public void tick() {
            if (--this.jumpDelay <= 0) {
                this.jumpDelay = this.entity.random.nextInt(20) + 10;

                // 让实体跳跃
                this.entity.getJumpControl().jump();

                // 如果有玩家目标，朝玩家方向跳跃
                LivingEntity target = this.entity.getTarget();
                if (target != null) {
                    double dx = target.getX() - this.entity.getX();
                    double dz = target.getZ() - this.entity.getZ();
                    double distance = Math.sqrt(dx * dx + dz * dz);

                    if (distance > 0) {
                        double speed = 0.4; // 跳跃水平速度
                        this.entity.setDeltaMovement(
                                this.entity.getDeltaMovement().add(
                                        (dx / distance) * speed,
                                        0,
                                        (dz / distance) * speed
                                )
                        );
                    }
                }
            }
        }
    }

}