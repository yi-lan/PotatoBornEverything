package net.royling.potatoborneverything.ModBlocks.BlockAddition.BlockEntities;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.royling.potatoborneverything.ModBlocks.ModBlocks;
import net.royling.potatoborneverything.PotatoBornEverything;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BETS = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, PotatoBornEverything.MODID);
    public static final RegistryObject<BlockEntityType<?>> POTATO_BARREL = register("potato_barrel", ModBlocks.POTATO_BARREL,PotatoBarrelBE::new);

    private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
        return BETS.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
    }

}
