package com.lujichi.orangeyouglad.entity.custom;

import com.lujichi.orangeyouglad.effect.ModEffects;
import com.lujichi.orangeyouglad.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
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
    private float squish = 1.0F;
    private float prevSquish = 1.0F;
    private static final int EFFECT_RADIUS = 4;
    private static final int EFFECT_INTERVAL = 60;
    private int effectTickCounter = 0;

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
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 2.0F));
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

    @Override
    public void tick() {
        super.tick();
        this.prevSquish = this.squish;
        this.squish += (1.0F - this.squish) * 0.6F;

        if (!this.level().isClientSide && this.effectTickCounter++ >= EFFECT_INTERVAL) {
            this.effectTickCounter = 0;
            applyHarvestEffectToNearbyPlayers();
        }
    }

    private void applyHarvestEffectToNearbyPlayers() {
        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        double radiusSq = (double)EFFECT_RADIUS * (double)EFFECT_RADIUS;

        for (Player player : this.level().getEntitiesOfClass(Player.class, new net.minecraft.world.phys.AABB(
                x - EFFECT_RADIUS, y - EFFECT_RADIUS, z - EFFECT_RADIUS,
                x + EFFECT_RADIUS, y + EFFECT_RADIUS, z + EFFECT_RADIUS))) {
            if (player.distanceToSqr(this) <= radiusSq) {
                player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                        ModEffects.HARVEST.get(), 200, 0, false, true, true));
            }
        }
    }

    public float getSquish() {
        return this.squish;
    }

    public float getPrevSquish() {
        return this.prevSquish;
    }

    static class RandomJumpGoal extends Goal {
        private final TuanziEntity entity;
        private int jumpDelay;
        private boolean wasInAir = false;

        public RandomJumpGoal(TuanziEntity entity) {
            this.entity = entity;
            this.jumpDelay = 0;
        }

        @Override
        public boolean canUse() {
            return !this.entity.isInWater();
        }

        @Override
        public void tick() {
            // 史莱姆式跳跃：落地后立即再次跳跃
            if (this.entity.onGround()) {
                if (wasInAir) {
                    // 刚落地，立即准备下一次跳跃
                    jumpDelay = 10; // 短暂延迟，让落地动画完成
                    wasInAir = false;
                }

                if (--jumpDelay <= 0) {
                    // 执行跳跃
                    performSlimeJump();
                }
            } else {
                wasInAir = true;
            }
        }

        private void performSlimeJump() {
            // 设置跳跃延迟
            jumpDelay = this.entity.random.nextInt(10) + 20; // 10-30 tick延迟，让跳跃更慢

            // 让实体跳跃
            this.entity.getJumpControl().jump();

            // 跳跃时身体缩小
            this.entity.squish = 0.5F;

            // 跳跃速度降低
            double speed = 0.1 + this.entity.random.nextDouble() * 0.1; // 0.1-0.2速度，跳得更慢更近

            // 基于当前朝向跳跃，添加随机偏移
            performForwardJump(speed);

            // 播放跳跃音效
            if (this.entity.level().isClientSide()) {
                this.entity.playSound(net.minecraft.sounds.SoundEvents.SLIME_JUMP, 0.4F,
                        (this.entity.random.nextFloat() - this.entity.random.nextFloat()) * 0.2F + 1.0F);
            }
        }
        private void performForwardJump(double speed) {
            // 基于当前身体朝向进行跳跃
            double currentYaw = Math.toRadians(this.entity.getYRot());

            // 添加随机偏移，避免总是朝着一个方向
            double randomOffset = (this.entity.random.nextDouble() - 0.5) * Math.PI / 3; // -60° to 60°
            double finalAngle = currentYaw + randomOffset;

            // 让实体转向跳跃方向
            double degrees = Math.toDegrees(finalAngle);
            this.entity.setYRot((float) degrees);
            this.entity.yBodyRot = (float) degrees;
            this.entity.yHeadRot = (float) degrees;

            // 跳跃方向基于当前身体朝向（Minecraft标准：x = -sin(yaw), z = cos(yaw)）
            this.entity.setDeltaMovement(
                    -Math.sin(finalAngle) * speed,
                    this.entity.getDeltaMovement().y,
                    Math.cos(finalAngle) * speed
            );
        }
    }
}