package com.lujichi.orangeyouglad.entity.custom;

import com.lujichi.orangeyouglad.entity.ModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Parrot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class HornbillEntity extends Parrot {
    private static final int MIN_HEIGHT_FROM_GROUND = 3;
    
    public HornbillEntity(EntityType<? extends Parrot> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public boolean onGround() {
        return false;
    }
    
    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(Items.SWEET_BERRIES);
    }
    
    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return ModEntities.HORNBILL.get().create(pLevel);
    }
    
    @Override
    public void tick() {
        super.tick();
        this.checkHeightFromGround();
    }
    
    private void checkHeightFromGround() {
        if (this.level().isClientSide) return;
        
        BlockPos pos = this.blockPosition();
        int groundY = this.findGroundBelow(pos);
        int currentHeight = pos.getY() - groundY;
        
        if (currentHeight < MIN_HEIGHT_FROM_GROUND) {
            this.setPos(this.getX(), groundY + MIN_HEIGHT_FROM_GROUND, this.getZ());
        }
    }
    
    private int findGroundBelow(BlockPos pPos) {
        BlockPos.MutableBlockPos mutable = pPos.mutable();
        Level level = this.level();
        
        for (int y = pPos.getY(); y >= level.getMinBuildHeight(); y--) {
            mutable.setY(y);
            BlockState state = level.getBlockState(mutable);
            if (!state.isAir() && state.isSolid()) {
                return y + 1;
            }
        }
        
        return level.getMinBuildHeight();
    }
}