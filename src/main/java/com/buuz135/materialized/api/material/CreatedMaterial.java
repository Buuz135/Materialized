package com.buuz135.materialized.api.material;

import com.buuz135.materialized.api.block.ColoredMaterializedBlock;
import com.buuz135.materialized.api.material.info.OrePartInfo;
import lombok.Data;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.text.WordUtils;

public @Data
class CreatedMaterial {

    private String name;
    private int color;

    private ColoredMaterializedBlock ore;
    private ColoredMaterializedBlock denseore;
    private ColoredMaterializedBlock lightore;
    private ColoredMaterializedBlock gravelore;
    private ColoredMaterializedBlock sandore;

    public CreatedMaterial(String name, int color) {
        this.name = name;
        this.color = color;
    }

    public void createBlock(BlockMaterial material, OrePartInfo info) {
        switch (material) {
            case ORE:
                ore = generateBlock(material, info);
                break;
            case DENSEORE:
                denseore = generateBlock(material, info);
                break;
            case LIGHTORE:
                lightore = generateBlock(material, info);
                break;
            case SANDORE:
                sandore = generateBlock(material, info);
                break;
            case GRAVELORE:
                gravelore = generateBlock(material, info);
                break;
        }
    }

    private ColoredMaterializedBlock generateBlock(BlockMaterial material, OrePartInfo info) {
        ColoredMaterializedBlock block = new ColoredMaterializedBlock(material, name, material.getMaterial(), color, info.getHarvestLevel());
        block.setDropInfo(info.getDrop());
        block.register();
        if (info.getOredict() != null) {
            for (String s : info.getOredict()) {
                OreDictionary.registerOre(s.replaceAll(":this", WordUtils.capitalize(name)), block);
            }
        }
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            block.registerRender();
        }
        return block;
    }

}
