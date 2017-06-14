package com.buuz135.materialized.proxy;

import com.buuz135.materialized.api.MaterialRegistry;
import com.buuz135.materialized.api.material.info.MaterialInfo;
import com.buuz135.materialized.api.material.info.OrePartInfo;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
        MaterialRegistry.INSTANCE.addMaterialInfo(MaterialInfo.builder().name("copper").color("a35309").
                ore(new OrePartInfo()).denseore(new OrePartInfo()).lightore(new OrePartInfo()).gravelore(new OrePartInfo()).sandore(new OrePartInfo()).build());
    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
