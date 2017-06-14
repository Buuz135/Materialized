package com.buuz135.materialized.api.material;

import net.minecraft.util.IStringSerializable;

public enum ItemMaterial implements IStringSerializable {
    INGOT, NUGGET, DUST, TINYDUST, PLATE, GEAR, GEM;

    @Override
    public String getName() {
        return this.name().toLowerCase();
    }
}
