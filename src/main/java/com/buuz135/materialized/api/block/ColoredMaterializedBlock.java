package com.buuz135.materialized.api.block;

import com.buuz135.materialized.api.IHasColor;
import com.buuz135.materialized.api.material.BlockMaterial;
import net.minecraft.block.material.Material;

public class ColoredMaterializedBlock extends MaterializedBlock implements IHasColor {

    private int color;

    public ColoredMaterializedBlock(BlockMaterial blockMaterial, String name, Material mat, int color, int harvest) {
        super(blockMaterial, name, mat, harvest);
        this.color = color;
    }

    @Override
    public int getColor() {
        return color;
    }
}
