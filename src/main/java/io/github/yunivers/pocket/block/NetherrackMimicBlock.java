package io.github.yunivers.pocket.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.Random;

public class NetherrackMimicBlock extends TemplateBlock
{
    public NetherrackMimicBlock(Identifier id)
    {
        super(id, Material.STONE);
        setHardness(Block.NETHERRACK.getHardness());
        setResistance(Block.NETHERRACK.resistance / 3);
        setSoundGroup(STONE_SOUND_GROUP);
        setLuminance(3f / 15f);
        setTranslationKey("hellrock");
    }

    @Override
    public int getTexture(int side)
    {
        return Block.NETHERRACK.getTexture(side);
    }

    @Override
    public int getDroppedItemId(int blockMeta, Random random)
    {
        return Block.NETHERRACK.id;
    }
}
