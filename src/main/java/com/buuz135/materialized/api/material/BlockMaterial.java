package com.buuz135.materialized.api.material;

import lombok.Getter;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public enum BlockMaterial implements IStringSerializable {
    ORE(Material.ROCK, "pickaxe"),
    DENSEORE(Material.ROCK, "pickaxe"),
    LIGHTORE(Material.ROCK, "pickaxe"),
    SANDORE(Material.SAND, "shovel"),
    GRAVELORE(Material.SAND, "shovel"),
    BLOCK(Material.IRON, "pickaxe");

    @Getter
    private Material material;
    @Getter
    private String tool;

    private BlockMaterial(Material material, String tool) {
        this.material = material;
        this.tool = tool;
    }

    @Override
    public String getName() {
        return this.name().toLowerCase();
    }

}
