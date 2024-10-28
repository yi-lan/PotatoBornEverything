package net.royling.potatoborneverything.ModBlocks.BlockAddition.jade;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.royling.potatoborneverything.ModBlocks.BlockAddition.BlockEntities.PotatoBarrelBE;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;

public enum PotatoBarrelJade implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;
    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        if(blockAccessor.getServerData().contains("Dirting")){
            if(blockAccessor.getServerData().getInt("Dirting")==0){
                IElementHelper elements = iTooltip.getElementHelper();
                IElement icon =elements.item(new ItemStack(Items.POTATO),0.75f);
                iTooltip.add(icon);
                iTooltip.append(Component.translatable("pbj.potato_barrel_empty"));
            }
            if(blockAccessor.getServerData().getInt("Dirting")==1){
                IElementHelper elements = iTooltip.getElementHelper();
                IElement icon =elements.item(new ItemStack(Items.POTATO),0.75f);
                iTooltip.add(icon);
                iTooltip.append(Component.translatable("pbj.potato_barrel_full",blockAccessor.getServerData().getInt("Translating")));
            }
            if(blockAccessor.getServerData().getInt("Dirting")==2){
                IElementHelper elements = iTooltip.getElementHelper();
                IElement icon =elements.item(new ItemStack(Items.DIRT),0.75f);
                iTooltip.add(icon);
                iTooltip.append(Component.translatable("pbj.potato_barrel_finish"));
            }
        }
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor blockAccessor) {
        PotatoBarrelBE potatobarrel = (PotatoBarrelBE) blockAccessor.getBlockEntity();
        compoundTag.putInt("Dirting",potatobarrel.getDirting());
        compoundTag.putInt("Translating",potatobarrel.getTranslating());
    }

    @Override
    public ResourceLocation getUid() {
        return ModJades.POTATO_BARREL;
    }
}
