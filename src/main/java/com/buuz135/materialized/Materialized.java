package com.buuz135.materialized;

import com.buuz135.materialized.proxy.CommonProxy;
import com.buuz135.materialized.utils.Reference;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;


@Mod(modid = Reference.MODID, name = Reference.MODID, version = Reference.VERSION)
public class Materialized {

    public static Logger LOGGER = LogManager.getLogger(Reference.MODID);

    public static CreativeTabs creativeTab = new CreativeTabs(Reference.MODID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Blocks.STONE);
        }
    };

    @SidedProxy(clientSide = Reference.PROXY_CLIENT, serverSide = Reference.PROXY_COMMON)
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) throws IOException {
        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }
}
