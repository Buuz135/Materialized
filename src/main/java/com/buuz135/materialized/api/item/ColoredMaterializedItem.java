package com.buuz135.materialized.api.item;

import com.buuz135.materialized.api.IHasColor;
import com.buuz135.materialized.api.material.ItemMaterial;

public class ColoredMaterializedItem extends MaterializedItem implements IHasColor {

    private int color;

    public ColoredMaterializedItem(ItemMaterial itemMaterial, String name, int color) {
        super(itemMaterial, name);
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }
}
