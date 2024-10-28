package net.royling.potatoborneverything.ModBlocks.szsy.PotatoTree;

import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PotatoSapling extends SaplingBlock {
    public static final IntegerProperty STAGE;
    protected static final float AABB_OFFSET = 6.0F;
    protected static final VoxelShape SHAPE;
    private final AbstractTreeGrower treeGrower;
    public PotatoSapling(AbstractTreeGrower pTreeGrower, Properties pProperties) {
        super(pTreeGrower, pProperties);
        this.treeGrower = pTreeGrower;
    }


}
