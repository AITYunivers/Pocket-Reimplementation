package io.github.yunivers.pocket.world.features;

import io.github.yunivers.pocket.Pocket;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.modificationstation.stationapi.api.block.BlockState;

import java.util.Random;

public class BlueRoseFeature extends Feature
{
    private static final BlockState BLUE_ROSE_STATE = Pocket.BLOCK_ROSE_BLUE.getDefaultState();
    private static final BlockState GRASS_STATE = Block.GRASS.getDefaultState();

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        BlockState state = BLUE_ROSE_STATE;
        if (Pocket.RANDOM.nextInt(10) != 0)
            state = GRASS_STATE;

        BlockState worldState = world.getBlockState(x, y, z);
        if (!worldState.isAir())
            return false;

        if (state.getBlock().canPlaceAt(world, x, y, z))
        {
            world.setBlockState(x, y, z, state);
            if (state == GRASS_STATE)
                world.setBlockMeta(x, y, z, 1);
            return true;
        }
        return false;
    }
}
