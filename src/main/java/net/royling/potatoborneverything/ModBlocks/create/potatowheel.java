package net.royling.potatoborneverything.ModBlocks.create;

import com.simibubi.create.content.kinetics.base.IRotate;
import com.simibubi.create.content.kinetics.base.KineticBlockEntity;
import com.simibubi.create.content.kinetics.simpleRelays.CogWheelBlock;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.royling.potatoborneverything.ModItems.ModItems;
import org.apache.logging.log4j.LogManager;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class potatowheel extends CogWheelBlock{
    public boolean isLarge;
    public int pmaxDamage;
    public int setDamage;
    public potatowheel(boolean large, Properties properties,int maxDamage) {
        super(large,properties);
        isLarge = large;
        pmaxDamage = maxDamage;
    }
    @Override
    public boolean isLargeCog(){
        return isLarge;
    }
    @Override
    public boolean isSmallCog(){
        return !isLarge;
    }

    @Override
    public void onPlace(BlockState state, Level worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        worldIn.scheduleTick(pos, this, 1);
        super.onPlace(state, worldIn, pos, oldState, isMoving);
        worldIn.getBlockEntity(pos).getPersistentData().putInt("CurrentDamage",pmaxDamage-setDamage);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        super.tick(pState, pLevel, pPos, pRandom);
        BlockEntity blockEntity = pLevel.getBlockEntity(pPos);
        int damage = blockEntity.getPersistentData().getInt("CurrentDamage");
        float speed = ((KineticBlockEntity)blockEntity).getSpeed();
        if(Math.abs(speed) > 0 ){
            damage -= Math.ceil(Math.abs(speed) / 16.0);
            blockEntity.getPersistentData().putInt("CurrentDamage",damage);

            if(damage <= 0){
                pLevel.destroyBlock(pPos,false);
            }
        }

        System.out.println("High speed detected: " + Math.abs(speed));
        pLevel.scheduleTick(pPos,this,1);
    }


    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        int damage = level.getBlockEntity(pos).getPersistentData().getInt("CurrentDamage");
        ItemStack itemStack = new ItemStack(ModItems.POTATO_WHEEL.get());
        itemStack.setDamageValue(pmaxDamage-damage);
        ItemEntity entity = new ItemEntity(level,pos.getX(),pos.getY(),pos.getZ(),itemStack);
        entity.setDefaultPickUpDelay();
        level.addFreshEntity(entity);
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Override
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        setDamage = stack.getDamageValue();
        super.setPlacedBy(worldIn, pos, state, placer, stack);
    }

}
