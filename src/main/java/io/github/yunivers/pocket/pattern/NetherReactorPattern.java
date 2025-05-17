package io.github.yunivers.pocket.pattern;

import io.github.yunivers.pocket.Pocket;
import net.minecraft.block.Block;

public class NetherReactorPattern
{
    private final int[] pattern = new int[27];

    public NetherReactorPattern()
    {
        // Layer 0
        // BCB
        // CCC
        // BCB
        pattern[0] = Block.GOLD_BLOCK.id;
        pattern[1] = Block.COBBLESTONE.id;
        pattern[2] = Block.GOLD_BLOCK.id;
        pattern[3] = Block.COBBLESTONE.id;
        pattern[4] = Block.COBBLESTONE.id;
        pattern[5] = Block.COBBLESTONE.id;
        pattern[6] = Block.GOLD_BLOCK.id;
        pattern[7] = Block.COBBLESTONE.id;
        pattern[8] = Block.GOLD_BLOCK.id;

        // Layer 1
        // C C
        //  R
        // C C
        pattern[9] = Block.COBBLESTONE.id;
        pattern[11] = Block.COBBLESTONE.id;
        pattern[13] = Pocket.BLOCK_NETHER_REACTOR.id;
        pattern[15] = Block.COBBLESTONE.id;
        pattern[17] = Block.COBBLESTONE.id;

        // Layer 2
        //  C
        // CCC
        //  C
        pattern[19] = Block.COBBLESTONE.id;
        pattern[21] = Block.COBBLESTONE.id;
        pattern[22] = Block.COBBLESTONE.id;
        pattern[23] = Block.COBBLESTONE.id;
        pattern[25] = Block.COBBLESTONE.id;
    }

    public int getTileAt(int x, int y, int z)
    {
        return pattern[y * 9 + x * 3 + z];
    }
}
