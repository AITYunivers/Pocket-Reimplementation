package io.github.yunivers.pocket.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.util.Identifier;

import static io.github.yunivers.pocket.Pocket.NAMESPACE;

@SuppressWarnings("SameParameterValue")
public class SPIRE_TEST extends TemplateBlock
{
    private static final int SPIRE_DIST = 8; // floor(17 / 2)

    public SPIRE_TEST()
    {
        super(NAMESPACE.id("NETHER_SPIRE_TEST"), Material.STONE);
        setTranslationKey(NAMESPACE.id("NETHER_SPIRE_TEST"));
    }

    @Override
    public void onPlaced(World world, int x, int y, int z)
    {
        world.setBlock(x, y, z, 0);
        x += 30;
        y += 15;
        z -= 30;

        int minX = x - SPIRE_DIST;
        int maxX = x + SPIRE_DIST;
        int minZ = z - SPIRE_DIST;
        int maxZ = z + SPIRE_DIST;
        int block = Block.NETHERRACK.id;

        if (Minecraft.INSTANCE.player.isSneaking())
            block = 0;

        buildFloorVolume(world, x,y + -3, z,8,2, block);
        buildHollowedVolume(world, x,y + -1, z,8,4, block);
        buildFloorVolume(world, x,y + 3, z,8,1, block);
        buildCrookedRoofVolume(world,false, x,y + 4, z,8,1, block);
        buildCrookedRoofVolume(world,true, x,y + 5, z,5,8, block);
        buildCrookedRoofVolume(world,false, x,y + 11, z,3,14, block);
    }

    private static void buildFloorVolume(World world, int startX, int startY, int startZ, int size, int height, int blockId)
    {
        for (int y = 0; y < height; y++)
            for (int x = -size; x <= size; x++)
                for (int z = -size; z <= size; z++)
                    world.setBlock(x + startX, y + startY, z + startZ, blockId, 3);
    }

    private static void buildHollowedVolume(World world, int startX, int startY, int startZ, int size, int height, int blockId)
    {
        for (int y = 0; y < height; y++)
            for (int x = -size; x <= size; x++)
                for (int z = -size; z <= size; z++)
                    if (x == -size || x == size || z == -size || z == size)
                        world.setBlock(x + startX, y + startY, z + startZ, blockId, 3);
    }

    private static void buildCrookedRoofVolume(World world, boolean inverted, int startX, int startY, int startZ, int radius, int height, int blockId)
    {
        for (int xOffset = -radius; xOffset <= radius; xOffset++)
            for (int zOffset = -radius; zOffset <= radius; zOffset++)
            {
                int slopeValue = inverted ? (-xOffset - zOffset) : (xOffset + zOffset);
                int roofHeight = (slopeValue / 2) + height + radius;

                for (int yOffset = 0; yOffset < height + radius * 2; yOffset++)
                    if (yOffset <= roofHeight)
                    {
                        boolean atEdge = isEdge(xOffset, radius, zOffset);
                        if (atEdge || yOffset == roofHeight)
                            world.setBlock(startX + xOffset, startY + yOffset, startZ + zOffset, blockId, 3);
                    }
            }
    }

    private static boolean isEdge(int x, int y, int z)
    {
        if (((x != -y) && (x != y)) && (z != -y))
            return z == y;
        return true;
    }
}
