package io.github.yunivers.pocket.block;

import io.github.yunivers.pocket.Pocket;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class MelonBlock extends TemplateBlock
{
    public MelonBlock(Identifier identifier)
    {
        super(identifier, Material.PUMPKIN);
        setHardness(1.0F);
        setSoundGroup(WOOD_SOUND_GROUP);
        ignoreMetaUpdates();
    }

    @Override
    public int getTexture(int side, int meta)
    {
        if (side <= 1)
            return Pocket.TEXTURE_MELON_TOP;
        return Pocket.TEXTURE_MELON_SIDE;
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random)
    {
        return Pocket.ITEM_MELON_SLICE.id;
    }

    @Override
    public int getDroppedItemCount(Random random)
    {
        return 3 + random.nextInt(5);
    }
}
