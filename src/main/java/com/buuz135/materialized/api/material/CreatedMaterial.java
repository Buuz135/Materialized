package com.buuz135.materialized.api.material;

import com.buuz135.materialized.api.block.ColoredMaterializedBlock;
import com.buuz135.materialized.api.item.ColoredMaterializedItem;
import com.buuz135.materialized.api.material.info.BlockPart;
import com.buuz135.materialized.api.material.info.ItemPart;
import lombok.Data;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.text.WordUtils;

import java.util.HashMap;

/**
 * Collects all the blocks and items from a material type
 *
 * @author Buuz135
 */
public @Data
class CreatedMaterial {

    private String name;
    private int color;
    private HashMap<BlockMaterial, ColoredMaterializedBlock> materializedBlockHashMap;
    private HashMap<ItemMaterial, ColoredMaterializedItem> materializedItemHashMap;

    public CreatedMaterial(String name, int color) {
        this.name = name;
        this.color = color;
        materializedBlockHashMap = new HashMap<>();
        materializedItemHashMap = new HashMap<>();
    }

    public void createBlock(BlockMaterial material, BlockPart info) {
        if (!materializedBlockHashMap.containsKey(info)) {
            materializedBlockHashMap.put(material, generateBlock(material, info));
        }
    }

    private ColoredMaterializedBlock generateBlock(BlockMaterial material, BlockPart info) {
        ColoredMaterializedBlock block = new ColoredMaterializedBlock(material, name, material.getMaterial(), color, info.getHarvestLevel(), info.getHardness());
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

    public void createItem(ItemMaterial material, ItemPart itemPart) {
        if (!materializedItemHashMap.containsKey(material)) {
            materializedItemHashMap.put(material, generateItem(material, itemPart));
        }
    }

    private ColoredMaterializedItem generateItem(ItemMaterial material, ItemPart itemPart) {
        ColoredMaterializedItem item = new ColoredMaterializedItem(material, name, color);
        item.register();
        if (itemPart.getOredict() != null) {
            for (String s : itemPart.getOredict()) {
                OreDictionary.registerOre(s.replaceAll(":this", WordUtils.capitalize(name)), item);
            }
        }
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            item.registerRender();
        }
        return item;
    }

    public ColoredMaterializedBlock getBlock(BlockMaterial type) {
        if (materializedBlockHashMap.containsKey(type)) {
            return materializedBlockHashMap.get(type);
        }
        return null;
    }

    public ColoredMaterializedItem getItem(ItemMaterial type) {
        if (materializedItemHashMap.containsKey(type)) {
            return materializedItemHashMap.get(type);
        }
        return null;
    }
}
