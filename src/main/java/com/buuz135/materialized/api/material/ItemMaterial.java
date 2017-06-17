package com.buuz135.materialized.api.material;

import lombok.Data;
import net.minecraft.util.ResourceLocation;


public @Data
class ItemMaterial {
//    INGOT, NUGGET, DUST, TINYDUST, PLATE, GEAR, GEM;

    private String name;
    private ResourceLocation resourceLocation;
    private int layer;

    public ItemMaterial(String name, ResourceLocation resourceLocation, int layer) {
        this.name = name;
        this.resourceLocation = resourceLocation;
        this.layer = layer;
    }
}
