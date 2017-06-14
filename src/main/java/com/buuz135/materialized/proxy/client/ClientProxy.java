package com.buuz135.materialized.proxy.client;

import com.buuz135.materialized.api.MaterialRegistry;
import com.buuz135.materialized.api.block.ColoredMaterializedBlock;
import com.buuz135.materialized.api.material.CreatedMaterial;
import com.buuz135.materialized.proxy.CommonProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        for (CreatedMaterial material : MaterialRegistry.INSTANCE.getMaterials()) {
            registerColor(material.getOre(), 0);
            registerColor(material.getDenseore(), 0);
            registerColor(material.getLightore(), 0);
            registerColor(material.getSandore(), 0);
            registerColor(material.getGravelore(), 0);
        }
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
}
