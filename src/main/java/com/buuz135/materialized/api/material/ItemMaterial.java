package com.buuz135.materialized.api.material;

import lombok.Data;
import net.minecraft.util.ResourceLocation;

/**
 * Represents a item material to be used to create item types
 *
 * @author Buuz135
 */
public @Data
class ItemMaterial {

    private String name;
    private ResourceLocation resourceLocation;
    private int layer;

    /**
     * Creates a new {@link ItemMaterial}
     *
     * @param name             Unique name for the item material
     * @param resourceLocation The resource location where the texture is located
     * @param layer            The layer to apply the tint to
     */
    public ItemMaterial(String name, ResourceLocation resourceLocation, int layer) {
        this.name = name;
        this.resourceLocation = resourceLocation;
        this.layer = layer;
    }
}
