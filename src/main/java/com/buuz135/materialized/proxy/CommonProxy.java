package com.buuz135.materialized.proxy;

import com.buuz135.materialized.api.MaterialRegistry;
import com.buuz135.materialized.api.material.info.MaterialInfo;
import com.buuz135.materialized.utils.Reference;
import com.google.gson.Gson;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) throws IOException {
        MinecraftForge.EVENT_BUS.register(new RegistryForgeEvent());

        File direc = new File(event.getModConfigurationDirectory().getAbsolutePath() + File.separator + Reference.MODID);
        if (!direc.exists()) direc.mkdir();
        File json = new File(direc.getAbsolutePath() + File.separator + "materials.json");
        if (json.exists()) {
            FileReader reader = new FileReader(json);
            MaterialInfo[] materialInfos = new Gson().fromJson(reader, MaterialInfo[].class);
            reader.close();
            for (MaterialInfo materialInfo : materialInfos) {
                MaterialRegistry.INSTANCE.addMaterial(materialInfo);
            }
        }

//
//        MaterialRegistry.INSTANCE.addMaterial(MaterialInfo.builder().name("copper").color("a35309")
//                .blockParts(Arrays.asList(
//                        new BlockPart("ore", 3, 3, new BlockPart.DropInfo(null, 0, 1, 0, 0)),
//                        new BlockPart("denseore", 3, 3, new BlockPart.DropInfo(null, 0, 1, 0, 0)),
//                        new BlockPart("lightore", 3, 3, new BlockPart.DropInfo("this:nugget", 0, 2, 2, 1)),
//                        new BlockPart("block", 3, 3, new BlockPart.DropInfo(null, 0, 1, 0, 0))))
//                .itemParts(Arrays.asList(
//                        new ItemPart("ingot"),
//                        new ItemPart("nugget"),
//                        new ItemPart("dust"),
//                        new ItemPart("tinydust"),
//                        new ItemPart("plate"),
//                        new ItemPart("gear")))
//                .build());
//        System.out.println(MaterialRegistry.INSTANCE.getItem("copper", "nugget").getRegistryName());

    }

    public void init(FMLInitializationEvent event) {

    }

    public void postInit(FMLPostInitializationEvent event) {

    }
}
