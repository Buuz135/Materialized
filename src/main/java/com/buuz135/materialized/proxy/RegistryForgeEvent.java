package com.buuz135.materialized.proxy;

import com.buuz135.materialized.api.MaterialRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Arrays;

public class RegistryForgeEvent {

    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        MaterialRegistry.INSTANCE.getMaterials().forEach((s, createdMaterial) -> createdMaterial.getMaterializedBlockHashMap().forEach((blockMaterial, coloredMaterializedBlock) ->
                coloredMaterializedBlock.registerBlock(event.getRegistry())
        ));
    }

    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        MaterialRegistry.INSTANCE.getMaterials().forEach((s, createdMaterial) -> createdMaterial.getMaterializedBlockHashMap().forEach((blockMaterial, coloredMaterializedBlock) -> {
            coloredMaterializedBlock.registerItem(event.getRegistry());
            if (coloredMaterializedBlock.getOreDict() != null)
                Arrays.stream(coloredMaterializedBlock.getOreDict()).forEach(s1 -> OreDictionary.registerOre(s1, coloredMaterializedBlock));
        }));
        MaterialRegistry.INSTANCE.getMaterials().forEach((s, createdMaterial) -> createdMaterial.getMaterializedItemHashMap().forEach((itemMaterial, coloredMaterializedItem) -> {
                    coloredMaterializedItem.register(event.getRegistry());
                    if (coloredMaterializedItem.getOreDict() != null)
                        Arrays.stream(coloredMaterializedItem.getOreDict()).forEach(s1 -> OreDictionary.registerOre(s1, coloredMaterializedItem));
                })
        );
        if (FMLCommonHandler.instance().getSide() == Side.CLIENT) {
            MaterialRegistry.INSTANCE.getMaterials().forEach((s, createdMaterial) -> {
                        createdMaterial.getMaterializedBlockHashMap().forEach((blockMaterial, coloredMaterializedBlock) -> coloredMaterializedBlock.registerRender());
                        createdMaterial.getMaterializedItemHashMap().forEach((itemMaterial, coloredMaterializedItem) -> coloredMaterializedItem.registerRender());
                    }
            );
        }
    }
}
