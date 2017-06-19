package com.buuz135.materialized.api.block;

import com.buuz135.materialized.api.IHasColor;
import com.buuz135.materialized.api.material.BlockMaterial;
import net.minecraft.block.material.Material;

/**
 * Represents a block that has tint color.
 *
 * @author Buuz135
 */
public class ColoredMaterializedBlock extends MaterializedBlock implements IHasColor {

    private int color;

    public ColoredMaterializedBlock(BlockMaterial blockMaterial, String name, Material mat, int color, int harvest, float hardness) {
        super(blockMaterial, name, mat, harvest, hardness);
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }
}
