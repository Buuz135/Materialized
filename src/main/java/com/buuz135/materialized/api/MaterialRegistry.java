package com.buuz135.materialized.api;

import com.buuz135.materialized.Materialized;
import com.buuz135.materialized.api.block.MaterializedBlock;
import com.buuz135.materialized.api.item.MaterializedItem;
import com.buuz135.materialized.api.material.BlockMaterial;
import com.buuz135.materialized.api.material.CreatedMaterial;
import com.buuz135.materialized.api.material.ItemMaterial;
import com.buuz135.materialized.api.material.info.MaterialInfo;
import com.buuz135.materialized.utils.Reference;
import net.minecraft.block.material.Material;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import net.minecraftforge.fml.common.ModContainer;
import org.apache.logging.log4j.Level;

import java.util.HashMap;

public class MaterialRegistry {

    public static final MaterialRegistry INSTANCE = new MaterialRegistry();

    private HashMap<String, BlockMaterial> blockMaterials;
    private HashMap<String, ItemMaterial> itemMaterials;
    private HashMap<String, CreatedMaterial> materials;

    public MaterialRegistry() {
        materials = new HashMap<>();
        itemMaterials = new HashMap<>();
        this.addItemMaterial(new ItemMaterial("ingot", new ResourceLocation(Reference.MODID, "items/metalingot"), 0));
        this.addItemMaterial(new ItemMaterial("nugget", new ResourceLocation(Reference.MODID, "items/metalnugget"), 0));
        this.addItemMaterial(new ItemMaterial("dust", new ResourceLocation(Reference.MODID, "items/metaldust"), 0));
        this.addItemMaterial(new ItemMaterial("tinydust", new ResourceLocation(Reference.MODID, "items/metaltinydust"), 0));
        this.addItemMaterial(new ItemMaterial("plate", new ResourceLocation(Reference.MODID, "items/metalplate"), 0));
        this.addItemMaterial(new ItemMaterial("gear", new ResourceLocation(Reference.MODID, "items/metalgear"), 0));
        blockMaterials = new HashMap<>();
        this.addBlockMaterial(new BlockMaterial("ore", Material.ROCK, "pickaxe", new ResourceLocation(Reference.MODID, "blocks/metalore"), 0));
        this.addBlockMaterial(new BlockMaterial("denseore", Material.ROCK, "pickaxe", new ResourceLocation(Reference.MODID, "blocks/metaldenseore"), 0));
        this.addBlockMaterial(new BlockMaterial("lightore", Material.ROCK, "pickaxe", new ResourceLocation(Reference.MODID, "blocks/metallightore"), 0));
        this.addBlockMaterial(new BlockMaterial("block", Material.IRON, "pickaxe", new ResourceLocation(Reference.MODID, "blocks/metalblock"), 0));
    }

    /**
     * Creates a material or add more types to it if it was created before.
     *
     * @param materialInfo The info provided to make a material.
     * @return The Material with the info provided or more if it was added before.
     */
    public CreatedMaterial addMaterial(MaterialInfo materialInfo) {
        if (!Loader.instance().isInState(LoaderState.PREINITIALIZATION)) {
            ModContainer modContainer = Loader.instance().activeModContainer();
            String modContainerName = modContainer == null ? null : modContainer.getName();
            Materialized.LOGGER.log(Level.ERROR, "Trying to add the material {} too late. Call it in PREINITIALIZATION. Mod: {}", materialInfo.getName(), modContainerName);
            return null;
        }
        CreatedMaterial createdMaterial = getMaterialOrCreate(materialInfo.getName(), (int) Long.parseLong(materialInfo.getColor(), 16));
        materialInfo.getBlockParts().forEach(blockPart -> createdMaterial.createBlock(getBlockMaterial(blockPart.getType()), blockPart));
        materialInfo.getItemParts().forEach(itemPart -> createdMaterial.createItem(getItemMaterial(itemPart.getType()), itemPart));
        if (!materials.containsKey(materialInfo.getName())) materials.put(materialInfo.getName(), createdMaterial);
        return createdMaterial;
    }

    /**
     * Get the created material.
     * @param name Name of the material.
     * @return The Material, {@code null} if it doesn't exist.
     */
    public CreatedMaterial getMaterial(String name) {
        if (materials.containsKey(name)) return materials.get(name);
        return null;
    }

    private CreatedMaterial getMaterialOrCreate(String name, int color) {
        CreatedMaterial material = getMaterial(name);
        return material == null ? new CreatedMaterial(name, color) : material;
    }

    /**
     * Adds a new block material to the registry if it's not present.
     * @param blockMaterial BlockMaterial to add to the Registry.
     */
    public void addBlockMaterial(BlockMaterial blockMaterial) {
        if (!blockMaterials.containsKey(blockMaterial.getName())) {
            blockMaterials.put(blockMaterial.getName(), blockMaterial);
        } else {
            ModContainer modContainer = Loader.instance().activeModContainer();
            String modContainerName = modContainer == null ? null : modContainer.getName();
            Materialized.LOGGER.log(Level.WARN, "Trying to add the block material {} that was already there. Mod: {}", blockMaterial.getName(), modContainerName);
        }
    }

    /**
     * Gets the BlockMaterial if it is present in the registry
     * @param name The name of the BlockMaterial
     * @return The specified BlockMaterial, {@code null} if it doesn't exist
     */
    public BlockMaterial getBlockMaterial(String name) {
        if (blockMaterials.containsKey(name)) {
            return blockMaterials.get(name);
        }
        return null;
    }

    /**
     * Adds a new item material to the registry if it's not present.
     * @param itemMaterial ItemMaterial to add to the Registry.
     */
    private void addItemMaterial(ItemMaterial itemMaterial) {
        if (!itemMaterials.containsKey(itemMaterial.getName())) {
            itemMaterials.put(itemMaterial.getName(), itemMaterial);
        } else {
            ModContainer modContainer = Loader.instance().activeModContainer();
            String modContainerName = modContainer == null ? null : modContainer.getName();
            Materialized.LOGGER.log(Level.WARN, "Trying to add the item material {} that was already there. Mod: {}", itemMaterial.getName(), modContainerName);
        }
    }

    /**
     * Gets the ItemMaterial if it is present in the registry.
     *
     * @param name The name of the ItemMaterial.
     * @return The specified ItemMaterial, {@code null} if it doesn't exist.
     */
    private ItemMaterial getItemMaterial(String name) {
        if (itemMaterials.containsKey(name)) {
            return itemMaterials.get(name);
        }
        return null;
    }

    /**
     * Gets the Item from the specified material and type.
     * @param name The name of the material.
     * @param type The type of the item.
     * @return The specified item, {@code null} if the material or the type doesn't exist.
     */
    public MaterializedItem getItem(String name, String type) {
        CreatedMaterial material = getMaterial(name);
        if (material == null) return null;
        ItemMaterial materialType = getItemMaterial(type);
        if (type == null) return null;
        return material.getItem(materialType);
    }

    /**
     * Gets the Block from the specified material and type.
     * @param name The name of the material.
     * @param type The type of the block.
     * @return The specified block, {@code null} if the material or the type doesn't exist.
     */
    public MaterializedBlock getBlock(String name, String type) {
        CreatedMaterial material = getMaterial(name);
        if (material == null) return null;
        BlockMaterial materialType = getBlockMaterial(type);
        if (type == null) return null;
        return material.getBlock(materialType);
    }

    public HashMap<String, CreatedMaterial> getMaterials() {
        return materials;
    }
}
