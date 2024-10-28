package net.royling.potatoborneverything.ModItems.create;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.List;

public class potatowheelitem extends BlockItem {
    public potatowheelitem(Block pBlock, Properties pProperties) {
        super(pBlock, pProperties);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag){
        super.appendHoverText(stack,world,tooltip,flag);
        tooltip.add(Component.translatable("tooltip.potato_born_everything.potato_wheel_line1"));
        tooltip.add(Component.translatable("tooltip.potato_born_everything.potato_wheel_line2"));
    }
}
