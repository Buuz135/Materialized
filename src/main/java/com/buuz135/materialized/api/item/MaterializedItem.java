package com.buuz135.materialized.api.item;

import com.buuz135.materialized.Materialized;
import com.buuz135.materialized.api.material.ItemMaterial;
import com.buuz135.materialized.proxy.client.RenderHelper;
import com.buuz135.materialized.utils.Reference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.commons.lang3.text.WordUtils;

/**
 * Represents a simple block
 *
 * @author Buuz135
 */
public class MaterializedItem extends Item {

    private ItemMaterial itemMaterial;
    private String name;

    public MaterializedItem(ItemMaterial itemMaterial, String name) {
        this.itemMaterial = itemMaterial;
        this.name = name;
        this.setRegistryName(Reference.MODID, itemMaterial.getName() + name);
        this.setCreativeTab(Materialized.creativeTab);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return (I18n.canTranslate("material." + name + ".name") ? I18n.translateToLocal("material." + name + ".name") : WordUtils.capitalize(name)) + " " + I18n.translateToLocal("type." + itemMaterial.getName() + ".name");
    }

    public void register() {
        GameRegistry.register(this);
    }

    public void registerRender() {
        RenderHelper.registerVariantItem(this, itemMaterial, "inventory");
    }
}
