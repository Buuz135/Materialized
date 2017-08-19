package com.buuz135.materialized.proxy.client;

import com.buuz135.materialized.api.MaterialRegistry;
import com.buuz135.materialized.api.block.ColoredMaterializedBlock;
import com.buuz135.materialized.api.item.ColoredMaterializedItem;
import com.buuz135.materialized.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.IOException;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) throws IOException {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        MaterialRegistry.INSTANCE.getMaterials().forEach((s, createdMaterial) -> {
            createdMaterial.getMaterializedBlockHashMap().forEach((blockMaterial, coloredMaterializedBlock) -> coloredMaterializedBlock.registerRender());
            createdMaterial.getMaterializedItemHashMap().forEach((itemMaterial, coloredMaterializedItem) -> coloredMaterializedItem.registerRender());
                    createdMaterial.getMaterializedBlockHashMap().forEach((blockPart, block) -> registerColor(block, blockPart.getLayer()));
                    createdMaterial.getMaterializedItemHashMap().forEach((material, coloredMaterializedItem) -> registerColor(coloredMaterializedItem, material.getLayer()));
                }
        );
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    private void registerColor(ColoredMaterializedBlock ore, int index) {
        if (ore != null) {
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                if (tintIndex == index && stack.getItem().equals(Item.getItemFromBlock(ore))) {
                    return ore.getColor();
                }
                return 0xFFFFFF;
            }, ore);
            Minecraft.getMinecraft().getBlockColors().registerBlockColorHandler((state, worldIn, pos, tintIndex) -> {
                if (tintIndex == index && state.getBlock().equals(ore)) {
                    return ore.getColor();
                }
                return 0xFFFFFF;
            }, ore);
        }
    }

    private void registerColor(ColoredMaterializedItem item, int index) {
        if (item != null) {
            Minecraft.getMinecraft().getItemColors().registerItemColorHandler((stack, tintIndex) -> {
                if (tintIndex == index && stack.getItem().equals(item)) {
                    return item.getColor();
                }
                return 0xFFFFFF;
            }, item);
        }
    }

}
