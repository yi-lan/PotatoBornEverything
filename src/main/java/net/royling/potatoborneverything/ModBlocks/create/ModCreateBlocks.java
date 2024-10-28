package net.royling.potatoborneverything.ModBlocks.create;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.royling.potatoborneverything.ModBlocks.szsy.PotatoBarrel;
import net.royling.potatoborneverything.PotatoBornEverything;

public class ModCreateBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, PotatoBornEverything.MODID);
    public static final RegistryObject<Block> POTATO_WHEEL = BLOCKS.register("potato_wheel",
            ()->new potatowheel(false, BlockBehaviour.Properties.of(),36000));

}
