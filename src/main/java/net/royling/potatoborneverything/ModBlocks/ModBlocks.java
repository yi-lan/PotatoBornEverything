package net.royling.potatoborneverything.ModBlocks;

import com.simibubi.create.content.decoration.encasing.EncasedBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.royling.potatoborneverything.ModBlocks.create.potatowheel;
import net.royling.potatoborneverything.ModBlocks.szsy.PotatoBarrel;
import net.royling.potatoborneverything.ModBlocks.szsy.PotatoTree.PotatoLog;
import net.royling.potatoborneverything.ModItems.ModItems;
import net.royling.potatoborneverything.PotatoBornEverything;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PotatoBornEverything.MODID);
    public static final RegistryObject<Block> POTATO_BARREL =BLOCKS.register("potato_barrel",
            PotatoBarrel::new);
    //土豆树的树干和树叶，木板，台阶，半砖等等...
    public static final RegistryObject<Block> POTATO_LOG = BLOCKS.register("potato_log",
            ()->new PotatoLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> POTATO_WOOD = BLOCKS.register("potato_wood",
            ()->new PotatoLog(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_POTATO_LOG = BLOCKS.register("stripped_potato_log",
            ()->new PotatoLog(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3f)));
    public static final RegistryObject<Block> STRIPPED_POTATO_WOOD = BLOCKS.register("stripped_potato_wood",
            ()->new PotatoLog(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3f)));
    public static final RegistryObject<Block> POTATO_PLANKS = BLOCKS.register("potato_planks",
            ()->new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(3f)));
    public static final RegistryObject<Block> POTATO_LEAVES = BLOCKS.register("potato_leaves",
            ()->new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(3f)));


}
