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
                        new BlockPart("ore", 3, 3, new BlockPart.DropInfo(null, 0, 1, 0, 0)),
                        new BlockPart("denseore", 3, 3, new BlockPart.DropInfo(null, 0, 1, 0, 0)),
                        new BlockPart("lightore", 3, 3, new BlockPart.DropInfo("this:nugget", 0, 2, 2, 1)),
                        new BlockPart("block", 3, 3, new BlockPart.DropInfo(null, 0, 1, 0, 0))))
                .itemParts(Arrays.asList(
                        new ItemPart("ingot"),
                        new ItemPart("nugget"),
                        new ItemPart("dust"),
                        new ItemPart("tinydust"),
                        new ItemPart("plate"),
                        new ItemPart("gear")))
                .build());
        System.out.println(MaterialRegistry.INSTANCE.getItem("copper", "nugget").getRegistryName());
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
