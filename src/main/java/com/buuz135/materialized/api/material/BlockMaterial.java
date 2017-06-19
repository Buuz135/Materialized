package com.buuz135.materialized.api.material;

import lombok.Data;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

/**
 * Represents a block material to be used to create block types
 *
 * @author Buuz135
 */
public @Data
class BlockMaterial {

    private String name;
    private Material material;
    private String tool;
    private ResourceLocation resourceLocation;
    private int layer;

    /**
     * Creates a new {@link BlockMaterial}
     *
     * @param name             Unique name for the block material
     * @param material         Minecraft {@link Material} system that represents
     * @param tool             The tool it can be mined with
     * @param resourceLocation The resource location where the texture is located
     * @param layer            The layer to apply the tint to
     */
    public BlockMaterial(String name, Material material, String tool, ResourceLocation resourceLocation, int layer) {
        this.name = name;
        this.material = material;
        this.tool = tool;
        this.resourceLocation = resourceLocation;
        this.layer = layer;
    }
}
