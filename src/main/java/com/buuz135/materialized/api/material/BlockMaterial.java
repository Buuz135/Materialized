package com.buuz135.materialized.api.material;

import lombok.Data;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

public @Data
class BlockMaterial implements IStringSerializable {

    private String name;
    private Material material;
    private String tool;
    private ResourceLocation resourceLocation;
    private int layer;

    public BlockMaterial(String name, Material material, String tool, ResourceLocation resourceLocation, int layer) {
        this.name = name;
        this.material = material;
        this.tool = tool;
        this.resourceLocation = resourceLocation;
        this.layer = layer;
    }
}
