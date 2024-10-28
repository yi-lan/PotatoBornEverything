package net.royling.potatoborneverything.ModBlocks.szsy;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.royling.potatoborneverything.ModBlocks.BlockAddition.BlockEntities.PotatoBarrelBE;
import net.royling.potatoborneverything.ModBlocks.BlockAddition.BlockStates.PotatoBarrelBlockState;
import net.royling.potatoborneverything.ModItems.ModItems;
import org.jetbrains.annotations.Nullable;

public class PotatoBarrel extends Block implements EntityBlock {
    public PotatoBarrel() {
        super(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS));
        this.registerDefaultState(this.stateDefinition.any().setValue(POTATO_BARREL,PotatoBarrelBlockState.EMPTY));
    }

    @Override
    public void onPlace(BlockState pState, Level pLevel, BlockPos pPos, BlockState pOldState, boolean pMovedByPiston) {
        super.onPlace(pState, pLevel, pPos, pOldState, pMovedByPiston);
        //pLevel.getBlockEntity(pPos).getPersistentData().putInt("Dirting",0);
        //pLevel.getBlockEntity(pPos).getPersistentData().putInt("Translating",0);
        pLevel.scheduleTick(pPos, this, 1);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if(!pPlayer.isSpectator()){
            if(!pPlayer.isCrouching()){
                pPlayer.swing(pHand,true);
                if(pPlayer.getMainHandItem().isEmpty()){
                    pPlayer.swing(pHand,true);
                }
                if(pPlayer.getMainHandItem().getItem() == Items.POTATO && pLevel.getBlockEntity(pPos).getPersistentData().getInt("Dirting")==0){
                    ItemStack itemStack = pPlayer.getMainHandItem();
                    itemStack.setCount(itemStack.getCount()-1);
                    pLevel.playSound(null, pPos, SoundEvents.COMPOSTER_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
                    pLevel.setBlockAndUpdate(pPos,pState.setValue(POTATO_BARREL,PotatoBarrelBlockState.FULL));
                    pLevel.getBlockEntity(pPos).getPersistentData().putInt("Dirting",1);
                    pLevel.getBlockEntity(pPos).getPersistentData().putInt("Translating",200);

                }
                if(pLevel.getBlockEntity(pPos).getPersistentData().getInt("Dirting") == 2){
                    pLevel.playSound(null, pPos, SoundEvents.COMPOSTER_EMPTY, SoundSource.BLOCKS, 1.0F, 1.0F);
                    ItemEntity item = new ItemEntity(pLevel,pPos.getX(),pPos.getY(),pPos.getZ(),new ItemStack(Items.DIRT));
                    item.setPickUpDelay(10);
                    pLevel.addFreshEntity(item);
                    pLevel.getBlockEntity(pPos).getPersistentData().putInt("Dirting",0);
                    pLevel.setBlockAndUpdate(pPos,pState.setValue(POTATO_BARREL,PotatoBarrelBlockState.EMPTY));

                }
                return InteractionResult.SUCCESS;
            }
        }
        return super.use(pState, pLevel, pPos, pPlayer, pHand, pHit);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
        super.tick(pState, pLevel, pPos, pRandom);
        if(pLevel.getBlockEntity(pPos).getPersistentData().getInt("Dirting")==1){
            if(pLevel.getBlockEntity(pPos).getPersistentData().getInt("Translating")>0){
                pLevel.getBlockEntity(pPos).getPersistentData().putInt("Translating",pLevel.getBlockEntity(pPos).getPersistentData().getInt("Translating")-1);
            }
            if(pLevel.getBlockEntity(pPos).getPersistentData().getInt("Translating")<=0){
                pLevel.getBlockEntity(pPos).getPersistentData().putInt("Dirting",2);
                pLevel.setBlockAndUpdate(pPos,pState.setValue(POTATO_BARREL,PotatoBarrelBlockState.FINISH));
            }
        }
        if(pLevel.getBlockEntity(pPos).getPersistentData().getInt("Dirting")==2){
            ItemStack dirtStack = new ItemStack(Items.DIRT, 1);
            BlockPos below = pPos.below();
            BlockEntity belowBE = pLevel.getBlockEntity(below);
            if(belowBE != null){
                belowBE.getCapability(ForgeCapabilities.ITEM_HANDLER, Direction.UP).ifPresent(iItemHandler -> {
                    for(int i = 0;i<iItemHandler.getSlots();i++){
                        ItemStack itemStack = iItemHandler.getStackInSlot(i);
                        if(itemStack.isEmpty()){
                            iItemHandler.insertItem(i,dirtStack,false);
                            pLevel.getBlockEntity(pPos).getPersistentData().putInt("Dirting",0);
                            pLevel.setBlockAndUpdate(pPos,pState.setValue(POTATO_BARREL,PotatoBarrelBlockState.EMPTY));
                            return;
                        }
                        else if(itemStack.getItem().equals(Items.DIRT) && itemStack.getCount() < itemStack.getMaxStackSize()){
                            itemStack.grow(1);
                            pLevel.getBlockEntity(pPos).getPersistentData().putInt("Dirting",0);
                            pLevel.setBlockAndUpdate(pPos,pState.setValue(POTATO_BARREL,PotatoBarrelBlockState.EMPTY));
                            return;
                        }
                    }
                });
            }
        }
        pLevel.scheduleTick(pPos, this, 1);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if(level.getBlockEntity(pos).getPersistentData().getInt("Dirting")==2){
            ItemStack itemStack = new ItemStack(Items.DIRT);
            ItemEntity entity = new ItemEntity(level,pos.getX(),pos.getY(),pos.getZ(),itemStack);
            entity.setDefaultPickUpDelay();
            level.addFreshEntity(entity);
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new PotatoBarrelBE(blockPos,blockState);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POTATO_BARREL);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Block.box(1,0,1,15,16,15);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL;
    }

    public static final EnumProperty<PotatoBarrelBlockState> POTATO_BARREL = EnumProperty.create("potato_barrel",PotatoBarrelBlockState.class);
}
