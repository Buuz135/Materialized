package com.buuz135.materialized.api;

import com.buuz135.materialized.api.material.BlockMaterial;
import com.buuz135.materialized.api.material.CreatedMaterial;
import com.buuz135.materialized.api.material.info.MaterialInfo;
import com.buuz135.materialized.utils.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MaterialRegistry {

    public static final MaterialRegistry INSTANCE = new MaterialRegistry();
    private HashMap<String, BlockMaterial> blockMaterials;



    private List<CreatedMaterial> materials;

    public MaterialRegistry() {
        materials = new ArrayList<>();
        blockMaterials = new HashMap<>();
        this.addBlockMaterial(new BlockMaterial("ore", Material.ROCK, "pickaxe", new ResourceLocation(Reference.MODID, "blocks/metalore"), 0));
        this.addBlockMaterial(new BlockMaterial("denseore", Material.ROCK, "pickaxe", new ResourceLocation(Reference.MODID, "blocks/metaldenseore"), 0));
        this.addBlockMaterial(new BlockMaterial("lightore", Material.ROCK, "pickaxe", new ResourceLocation(Reference.MODID, "blocks/metallightore"), 0));
        this.addBlockMaterial(new BlockMaterial("block", Material.IRON, "pickaxe", new ResourceLocation(Reference.MODID, "blocks/metaldenseore"), 0));

    }

    public void getCreatedMaterial(String name) {
//        if (Loader.instance().isInState(LoaderState.PREINITIALIZATION)) {
//            ModContainer modContainer = Loader.instance().activeModContainer();
//            String modContainerName = modContainer == null ? null : modContainer.getName();
//            Materialized.LOGGER.log(Level.ERROR, "Trying to get created material {} too soon. Call it after the PREINITIALIZATION. Mod: {}", name, modContainerName);
//        }
    }

    public CreatedMaterial addMaterial(MaterialInfo materialInfo) {
        CreatedMaterial createdMaterial = getMaterialOrCreate(materialInfo.getName(), (int) Long.parseLong(materialInfo.getColor(), 16));
        materialInfo.getBlockParts().forEach(blockPart -> createdMaterial.createBlock(getBlockMaterial(blockPart.getType()), blockPart));
        materials.add(createdMaterial);
        return createdMaterial;
    }

    public CreatedMaterial getMaterial(String name) {
        for (CreatedMaterial material : materials) {
            if (material.getName().equals(name)) return material;
        }
        return null;
    }

    private CreatedMaterial getMaterialOrCreate(String name, int color) {
        CreatedMaterial material = getMaterial(name);
        return material == null ? new CreatedMaterial(name, color) : material;
    }

    public List<CreatedMaterial> getMaterials() {
        return materials;
    }

    public void addBlockMaterial(BlockMaterial blockMaterial) {
        if (!blockMaterials.containsKey(blockMaterial.getName())) {
            blockMaterials.put(blockMaterial.getName(), blockMaterial);
        }
    }

    public BlockMaterial getBlockMaterial(String name) {
        if (blockMaterials.containsKey(name)) {
            return blockMaterials.get(name);
        }
        return null;
    }
}
