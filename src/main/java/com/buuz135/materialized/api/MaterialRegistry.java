package com.buuz135.materialized.api;

import com.buuz135.materialized.api.material.BlockMaterial;
import com.buuz135.materialized.api.material.CreatedMaterial;
import com.buuz135.materialized.api.material.info.MaterialInfo;

import java.util.ArrayList;
import java.util.List;

public class MaterialRegistry {

    public static final MaterialRegistry INSTANCE = new MaterialRegistry();

    private List<CreatedMaterial> materials;

    public MaterialRegistry() {
        materials = new ArrayList<>();
    }

    public void getCreatedMaterial(String name) {
//        if (Loader.instance().isInState(LoaderState.PREINITIALIZATION)) {
//            ModContainer modContainer = Loader.instance().activeModContainer();
//            String modContainerName = modContainer == null ? null : modContainer.getName();
//            Materialized.LOGGER.log(Level.ERROR, "Trying to get created material {} too soon. Call it after the PREINITIALIZATION. Mod: {}", name, modContainerName);
//        }
    }

    public CreatedMaterial addMaterialInfo(MaterialInfo materialInfo) {
        CreatedMaterial createdMaterial = getMaterialOrCreate(materialInfo.getName(), (int) Long.parseLong(materialInfo.getColor(), 16));
        if (materialInfo.getOre() != null) createdMaterial.createBlock(BlockMaterial.ORE, materialInfo.getOre());
        if (materialInfo.getDenseore() != null)
            createdMaterial.createBlock(BlockMaterial.DENSEORE, materialInfo.getDenseore());
        if (materialInfo.getLightore() != null)
            createdMaterial.createBlock(BlockMaterial.LIGHTORE, materialInfo.getLightore());
        if (materialInfo.getGravelore() != null)
            createdMaterial.createBlock(BlockMaterial.GRAVELORE, materialInfo.getGravelore());
        if (materialInfo.getSandore() != null)
            createdMaterial.createBlock(BlockMaterial.SANDORE, materialInfo.getSandore());
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
}
