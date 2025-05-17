package io.github.yunivers.pocket.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class GlowingObsidianBlock extends TemplateBlock
{
    public GlowingObsidianBlock(Identifier identifier)
    {
        super(identifier, Material.STONE);
        setHardness(10f);
        setLuminance(0.875f);
        setResistance(2000f);
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random)
    {
        return Block.OBSIDIAN.id;
    }
}
