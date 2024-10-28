package net.royling.potatoborneverything.ModBlocks.BlockAddition.jade;

import net.minecraft.resources.ResourceLocation;
import net.royling.potatoborneverything.ModBlocks.BlockAddition.BlockEntities.PotatoBarrelBE;
import net.royling.potatoborneverything.ModBlocks.szsy.PotatoBarrel;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class ModJades implements IWailaPlugin {
    public static final ResourceLocation POTATO_BARREL = new ResourceLocation("potato_born_everything","jade.potato_barrel");
    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(PotatoBarrelJade.INSTANCE, PotatoBarrelBE.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockComponent(PotatoBarrelJade.INSTANCE, PotatoBarrel.class);
    }
}
