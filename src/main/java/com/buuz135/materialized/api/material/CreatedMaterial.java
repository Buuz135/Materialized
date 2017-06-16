package com.buuz135.materialized.api.material;

import com.buuz135.materialized.api.block.ColoredMaterializedBlock;
import com.buuz135.materialized.api.material.info.BlockPart;
import lombok.Data;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.text.WordUtils;

import java.util.HashMap;

public @Data
class CreatedMaterial {

    private String name;
    private int color;
    private HashMap<BlockMaterial, ColoredMaterializedBlock> materializedBlockHashMap;

    public CreatedMaterial(String name, int color) {
        this.name = name;
        this.color = color;
        materializedBlockHashMap = new HashMap<>();
    }

    public void createBlock(BlockMaterial material, BlockPart info) {
        if (!materializedBlockHashMap.containsKey(info)) {
            materializedBlockHashMap.put(material, generateBlock(material, info));
        }
    }


    private ColoredMaterializedBlock generateBlock(BlockMaterial material, BlockPart info) {
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
