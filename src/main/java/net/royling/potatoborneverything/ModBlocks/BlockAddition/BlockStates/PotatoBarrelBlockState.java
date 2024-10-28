package net.royling.potatoborneverything.ModBlocks.BlockAddition.BlockStates;

import net.minecraft.util.StringRepresentable;

public enum PotatoBarrelBlockState implements StringRepresentable {
    EMPTY("empty"),
    FULL("full"),
    FINISH("finish");
    private final String name;
    PotatoBarrelBlockState(String name){
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}
