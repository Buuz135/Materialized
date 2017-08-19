package com.buuz135.materialized.api.block;

import com.buuz135.materialized.Materialized;
import com.buuz135.materialized.api.material.BlockMaterial;
import com.buuz135.materialized.api.material.info.BlockPart;
import com.buuz135.materialized.proxy.client.RenderHelper;
import com.buuz135.materialized.utils.Reference;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.commons.lang3.text.WordUtils;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * Represents a simple block
 *
 * @author Buuz135
 */
public class MaterializedBlock extends Block {

    private BlockMaterial blockMaterial;
    private String name;
    @Setter
    private BlockPart.DropInfo dropInfo;
    @Setter
    @Getter
    private String[] oreDict;

    public MaterializedBlock(BlockMaterial blockMaterial, String name, Material mat, int harvest, float hardness) {
        super(mat);
        this.name = name;
        this.blockMaterial = blockMaterial;
        this.setRegistryName(Reference.MODID, blockMaterial.getName() + name);
        this.setHardness(hardness);
        this.setHarvestLevel(blockMaterial.getTool(), harvest);
        this.setCreativeTab(Materialized.creativeTab);
        this.setUnlocalizedName(getLocalizedName());
    }

    public void registerBlock(IForgeRegistry<Block> blocks) {
        blocks.register(this);
    }

    public void registerItem(IForgeRegistry<Item> items) {
        items.register(new ItemBlock(this) {
            @Override
            public String getItemStackDisplayName(ItemStack stack) {
                return (I18n.canTranslate("material." + name + ".name") ? I18n.translateToLocal("material." + name + ".name") : WordUtils.capitalize(name)) + " " + I18n.translateToLocal("type." + blockMaterial.getName() + ".name");
            }
        }.setRegistryName(this.getRegistryName()));
    }

    public void registerRender() {
        RenderHelper.registerBlockModel(this, blockMaterial);
    }

    @Override
    @Nonnull
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return dropInfo == null ? 0 : dropInfo.getMeta();
    }

    @Override
    public int quantityDropped(Random random) {
        return dropInfo == null || dropInfo.getAmount() == 1 ? 1 : 1 + random.nextInt(dropInfo.getAmount());
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        if (fortune > 0 && dropInfo != null && dropInfo.getAmount() > 1) {
            int i = random.nextInt(fortune * dropInfo.getFortuneModifier()) - 1;
            if (i < 0) {
                i = 0;
            }
            return this.quantityDropped(random) * (i + 1);
        } else {
            return this.quantityDropped(random);
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (dropInfo.getItem() == null) return super.getItemDropped(state, rand, fortune);
        Item item = Item.getByNameOrId(dropInfo.getItem());
        if (item != null) return item;
        item = Item.getByNameOrId(dropInfo.getItem().replaceAll("this", Reference.MODID) + name);
        if (item != null) return item;
        return super.getItemDropped(state, rand, fortune);
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        return dropInfo.getXpDrop();
    }
}
