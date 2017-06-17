package com.buuz135.materialized.proxy;

import com.buuz135.materialized.api.MaterialRegistry;
import com.buuz135.materialized.api.material.info.BlockPart;
import com.buuz135.materialized.api.material.info.ItemPart;
import com.buuz135.materialized.api.material.info.MaterialInfo;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Arrays;


public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        MaterialRegistry.INSTANCE.addMaterial(MaterialInfo.builder().name("copper").color("a35309")
                .blockParts(Arrays.asList(
                        new BlockPart("ore", 1, new BlockPart.DropInfo()),
                        new BlockPart("denseore", 1, new BlockPart.DropInfo()),
                        new BlockPart("lightore", 1, new BlockPart.DropInfo()),
                        new BlockPart("block", 1, new BlockPart.DropInfo())))
                .itemParts(Arrays.asList(
                        new ItemPart("ingot"),
                        new ItemPart("nugget"),
                        new ItemPart("dust"),
                        new ItemPart("tinydust"),
                        new ItemPart("plate"),
                        new ItemPart("gear")))
                .build());
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
