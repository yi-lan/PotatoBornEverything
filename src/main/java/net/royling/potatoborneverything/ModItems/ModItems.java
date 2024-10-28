package net.royling.potatoborneverything.ModItems;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.royling.potatoborneverything.ModBlocks.ModBlocks;
import net.royling.potatoborneverything.ModBlocks.create.ModCreateBlocks;
import net.royling.potatoborneverything.ModItems.create.potatowheelitem;
import net.royling.potatoborneverything.PotatoBornEverything;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, PotatoBornEverything.MODID);

    public static final RegistryObject<Item> COPPER_WIRE = ITEMS.register("copper_wire",
            ()->new Item(new Item.Properties()) );
    public static final RegistryObject<BlockItem> POTATO_WHEEL = ITEMS.register("potato_wheel",
            ()->new potatowheelitem(ModCreateBlocks.POTATO_WHEEL.get(),new Item.Properties().durability(36000)));
    public static final RegistryObject<BlockItem> POTATO_BARREL = ITEMS.register("potato_barrel",
            ()->new BlockItem(ModBlocks.POTATO_BARREL.get(),new Item.Properties()));
}
