package io.github.yunivers.pocket.block;

import io.github.yunivers.pocket.Pocket;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

public class HaybaleBlock extends TemplateBlock
{
    public HaybaleBlock(Identifier identifier)
    {
        super(identifier, new Material(MapColor.PALE_YELLOW));
        setHardness(0.5f);
        setSoundGroup(Block.DIRT_SOUND_GROUP);
    }

    @Override
    public int getTexture(int side)
    {
        if (side <= 1)
            return Pocket.TEXTURE_HAY_BALE_TOP;
        return Pocket.TEXTURE_HAY_BALE_SIDE;
    }
}
