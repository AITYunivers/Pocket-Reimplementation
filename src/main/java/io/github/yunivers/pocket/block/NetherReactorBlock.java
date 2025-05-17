package io.github.yunivers.pocket.block;

import io.github.yunivers.pocket.Pocket;
import io.github.yunivers.pocket.block.entity.NetherReactorBlockEntity;
import io.github.yunivers.pocket.pattern.NetherReactorPattern;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.block.TemplateBlockWithEntity;
import net.modificationstation.stationapi.api.util.Identifier;

import java.util.List;

public class NetherReactorBlock extends TemplateBlockWithEntity
{
    public NetherReactorBlock(Identifier identifier)
    {
        super(identifier, Material.METAL);
        setSoundGroup(METAL_SOUND_GROUP);
    }

    @Override
    public int getTexture(int side, int meta)
    {
        if (meta == 1)
            return Pocket.TEXTURE_NETHER_REACTOR_ACTIVE;
        else if (meta == 2)
            return Pocket.TEXTURE_NETHER_REACTOR_DEAD;
        return Pocket.TEXTURE_NETHER_REACTOR;
    }

    @Override
    public boolean onUse(World world, int blockX, int blockY, int blockZ, PlayerEntity player)
    {
        if (world.getBlockMeta(blockX, blockY, blockZ) != 0)
            return false;

        if (world.isRemote)
            return true;

        NetherReactorPattern pattern = new NetherReactorPattern();
        for (int y = -1; y <= 1; y++)
            for (int x = -1; x <= 1; x++)
                for (int z = -1; z <= 1; z++)
                {
                    int blockId = world.getBlockId(blockX + x, blockY + y, blockZ + z);
                    int patternId = pattern.getTileAt(x + 1, y + 1, z + 1);
                    if (blockId != patternId)
                    {
                        player.sendMessage("Not the correct pattern!");
                        return true;
                    }
                }

        if (canSpawnStartNetherReactor(world, blockX, blockY, blockZ, player))
        {
            player.sendMessage("Active!");
            BlockEntity blockEntity = world.getBlockEntity(blockX, blockY, blockZ);
            if (blockEntity instanceof NetherReactorBlockEntity reactor)
                reactor.lightItUp(blockX, blockY, blockZ);
        }
        return true;
    }

    private boolean canSpawnStartNetherReactor(World world, int x, int y, int z, PlayerEntity player)
    {
        if (!allPlayersCloseToReactor(world, x, y, z))
        {
            player.sendMessage("All players need to be close to the reactor.");
            return false;
        }
        else if (y > 96)
        {
            player.sendMessage("The nether reactor needs to be built lower down.");
            return false;
        }
        else if (y < 8)
        {
            player.sendMessage("The nether reactor needs to be built higher up.");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private boolean allPlayersCloseToReactor(World world, int x, int y, int z)
    {
        for (PlayerEntity player : (List<PlayerEntity>)Minecraft.INSTANCE.world.players)
        {
            double px = player.x + 0.5;
            if (px < x - 5.0 || px > x + 5.0)
                return false;

            double py = player.y;
            if (py < y - 1.0 || py > y + 1.0)
                return false;

            double pz = player.z + 0.5;
            if (pz < z - 5.0 || pz > z + 5.0)
                return false;
        }
        return true;
    }

    public static void setPhase(World world, int x, int y, int z, int phase)
    {
        int meta = world.getBlockMeta(x, y, z);
        if (meta != phase)
            world.setBlockMeta(x, y, z, phase);
    }

    @Override
    protected BlockEntity createBlockEntity()
    {
        return new NetherReactorBlockEntity();
    }
}
