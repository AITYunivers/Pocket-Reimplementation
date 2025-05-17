package io.github.yunivers.pocket.block.entity;

import io.github.yunivers.pocket.Pocket;
import io.github.yunivers.pocket.block.NetherReactorBlock;
import io.github.yunivers.pocket.pattern.NetherReactorPattern;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.mob.PigZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

@SuppressWarnings("SameParameterValue")
public class NetherReactorBlockEntity extends BlockEntity
{
    private static final int[] levelStates = new int[] { 10, 13, 20, 22, 25, 30, 34, 36, 38, 40 };

    private int reactorTick;
    private int currentLevel;
    private boolean finished;
    private boolean active;

    @Override
    public void tick()
    {
        if (world.isRemote)
            return;

        if (reactorTick < 0)
        {
            setRemoved();
            return;
        }

        if (!active && !finished)
            return;

        if (finished)
        {
            if (reactorTick % 20 == 0)
            {
                if (playersAreCloseBy())
                    trySpawnPigZombies(2, 3);
                else
                    killPigZombies();
            }
        }
        else
        {
            // Initial phase: increment and evaluate reactor tick
            reactorTick++;

            if (reactorTick % 20 == 0)
            {
                int phase = reactorTick / 20;

                if (phase < 10)
                    tickGlowingRedstoneTransformation(phase);

                if (phase >= 43 && phase - 43 < 3)
                    turnGlowingObsidianLayerToObsidian(45 - phase);

                int level = reactorTick / 20;
                if (checkLevelChange(level))
                {
                    currentLevel++;
                    spawnItems(getNumItemsPerLevel(currentLevel));
                    trySpawnPigZombies(3, getNumEnemiesPerLevel(currentLevel));
                }
            }

            if (reactorTick > 912)
                finishReactorRun();
        }
    }

    private void finishReactorRun()
    {
        NetherReactorBlock.setPhase(world, x, y, z, 2);

        finished = true;
        deteriorateDome(x, y, z);

        for (int dx = x - 1; dx <= x + 1; ++dx)
            for (int dy = y - 1; dy <= y + 1; ++dy)
                for (int dz = z - 1; dz <= z + 1; ++dz)
                    if (dx != x || dy != y || dz != z)
                        world.setBlock(dx, dy, dz, Block.OBSIDIAN.id);
    }

    private void tickGlowingRedstoneTransformation(int phase)
    {
        int layer = 0;
        int blockId;

        switch (phase) {
            case 2:
                blockId = Block.COBBLESTONE.id;
                break;
            case 3:
                blockId = Block.COBBLESTONE.id;
                layer = 1;
                break;
            case 4:
                blockId = Block.COBBLESTONE.id;
                layer = 2;
                break;
            case 7:
                blockId = Block.GOLD_BLOCK.id;
                break;
            case 8:
                blockId = Block.GOLD_BLOCK.id;
                layer = 1;
                break;
            case 9:
                blockId = Block.GOLD_BLOCK.id;
                layer = 2;
                break;
            default:
                return;
        }

        turnLayerToGlowingObsidian(layer, blockId);
    }

    private void turnLayerToGlowingObsidian(int layer, int blockId)
    {
        NetherReactorPattern pattern = new NetherReactorPattern();
        for (int x = -1; x <= 1; x++)
            for (int z = -1; z <= 1; z++)
            {
                int patternId = pattern.getTileAt(x + 1, layer, z + 1);
                if (blockId == patternId)
                    world.setBlock(this.x + x, y - 1 + layer, this.z + z, Pocket.BLOCK_GLOWING_OBSIDIAN.id);
            }
    }

    private void turnGlowingObsidianLayerToObsidian(int layer)
    {
        for (int x = -1; x <= 1; x++)
            for (int z = -1; z <= 1; z++)
            {
                int blockId = world.getBlockId(this.x + x, y - 1 + layer, this.z + z);
                if (blockId != Pocket.BLOCK_NETHER_REACTOR.id)
                    world.setBlock(this.x + x, y - 1 + layer, this.z + z, Block.OBSIDIAN.id);
            }
    }

    private boolean checkLevelChange(int level)
    {
        for (int state : levelStates)
            if (level == state)
                return true;
        return false;
    }

    private void setRemoved()
    {
        if (!removed)
            world.removeBlockEntity(x, y, z);
    }

    public void lightItUp(int x, int y, int z)
    {
        NetherReactorBlock.setPhase(world, x, y, z, 1);
        active = true;
        buildDome(x, y, z);
        world.setTime(0);
    }

    public void buildDome(int x, int y, int z)
    {
        buildFloorVolume(world, x,y + -3, z,8,2, Pocket.BLOCK_NETHERRACK_MIMIC.id);
        buildHollowedVolume(world, x,y + -1, z,8,4, Pocket.BLOCK_NETHERRACK_MIMIC.id);
        buildFloorVolume(world, x,y + 3, z,8,1, Pocket.BLOCK_NETHERRACK_MIMIC.id);
        buildCrookedRoofVolume(world,false, x,y + 4, z,8,1, Pocket.BLOCK_NETHERRACK_MIMIC.id);
        buildCrookedRoofVolume(world,true, x,y + 5, z,5,8, Pocket.BLOCK_NETHERRACK_MIMIC.id);
        buildCrookedRoofVolume(world,false, x,y + 11, z,3,14, Pocket.BLOCK_NETHERRACK_MIMIC.id);
    }

    private static void buildFloorVolume(World world, int startX, int startY, int startZ, int size, int height, int blockId)
    {
        for (int y = 0; y < height; y++)
            for (int x = -size; x <= size; x++)
                for (int z = -size; z <= size; z++)
                    world.setBlock(x + startX, y + startY, z + startZ, blockId);
    }

    private static void buildHollowedVolume(World world, int startX, int startY, int startZ, int size, int height, int blockId)
    {
        for (int y = 0; y < height; y++)
            for (int x = -size; x <= size; x++)
                for (int z = -size; z <= size; z++)
                    if (x == -size || x == size || z == -size || z == size)
                        world.setBlock(x + startX, y + startY, z + startZ, blockId);
                    else if (x < -1 || x > 1 || y >= 3 || z < -1 || z > 1)
                        world.setBlock(x + startX, y + startY, z + startZ, 0);
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
                            world.setBlock(startX + xOffset, startY + yOffset, startZ + zOffset, blockId);
                    }
            }
    }

    public void deteriorateDome(int x, int y, int z)
    {
        deteriorateHollowedVolume(world, x,y + -1, z,8,5, 0);
        deteriorateCrookedRoofVolume(world,false, x,y + 4, z,8,1, 0);
        deteriorateCrookedRoofVolume(world,true, x,y + 5, z,5,8, 0);
        deteriorateCrookedRoofVolume(world,false, x,y + 11, z,3,14, 0);
    }

    private static void deteriorateHollowedVolume(World world, int startX, int startY, int startZ, int size, int height, int blockId)
    {
        for (int y = 0; y < height; y++)
            for (int x = -size; x <= size; x++)
                for (int z = -size; z <= size; z++)
                    if (x == -size || x == size || z == -size || z == size)
                        if (world.random.nextInt(3) == 0)
                            world.setBlock(x + startX, y + startY, z + startZ, blockId);
    }

    private static void deteriorateCrookedRoofVolume(World world, boolean inverted, int startX, int startY, int startZ, int radius, int height, int blockId)
    {
        for (int xOffset = -radius; xOffset <= radius; xOffset++)
            for (int zOffset = -radius; zOffset <= radius; zOffset++)
            {
                int slopeValue = inverted ? (-xOffset - zOffset) : (xOffset + zOffset);
                int roofHeight = (slopeValue / 2) + height + radius;

                for (int yOffset = 0; yOffset < height + radius * 2; yOffset++)
                    if (yOffset <= roofHeight && world.random.nextInt(3) == 0)
                    {
                        boolean atEdge = isEdge(xOffset, radius, zOffset);
                        if (atEdge || yOffset == roofHeight)
                            world.setBlock(startX + xOffset, startY + yOffset, startZ + zOffset, blockId);
                    }
            }
    }

    private static boolean isEdge(int x, int y, int z)
    {
        if (((x != -y) && (x != y)) && (z != -y))
            return z == y;
        return true;
    }

    @SuppressWarnings("unchecked")
    private boolean playersAreCloseBy()
    {
        // Not used in MCPE but for optimization, might aswell
        double minX = x - 40.0;
        double minY = y - 40.0;
        double minZ = z - 40.0;
        double maxX = x + 1.0 + 40.0;
        double maxY = y + 1.0 + 40.0;
        double maxZ = z + 1.0 + 40.0;

        // Get all Players in the area
        List<PlayerEntity> players = world.collectEntitiesByClass(PlayerEntity.class, Box.createCached(minX, minY, minZ, maxX, maxY, maxZ));
        return !players.isEmpty();
    }

    private int getNumEnemiesPerLevel(int level)
    {
        if (level == 0)
            return 3;

        if (level > 3)
        {
            if (level < 6)
                return world.random.nextInt(2);
            return 0;
        }

        return 2;
    }

    private void trySpawnPigZombies(int maxToSpawn, int spawnAttempts)
    {
        int freeSlots = numOfFreeEnemySlots();
        int spawnableCount = maxToSpawn - (3 - freeSlots);

        if (spawnableCount > 0)
            for (int i = 0; i < spawnAttempts && i < spawnableCount; i++)
                spawnEnemy();
    }

    @SuppressWarnings("unchecked")
    private int numOfFreeEnemySlots()
    {
        // Not used in MCPE, probs caused issues in MCPE so they're stupid
        double minX = x - 40.0;
        double minY = y - 40.0;
        double minZ = z - 40.0;
        double maxX = x + 1.0 + 40.0;
        double maxY = y + 1.0 + 40.0;
        double maxZ = z + 1.0 + 40.0;

        // Get all Pigmen in the area
        List<PigZombieEntity> pigmen = world.collectEntitiesByClass(PigZombieEntity.class, Box.createCached(minX, minY, minZ, maxX, maxY, maxZ));

        int count = 0;
        for (PigZombieEntity pigman : pigmen)
            if (pigman.isAlive())
                count++;

        return 3 - count;
    }

    private void spawnEnemy()
    {
        PigZombieEntity pigman = new PigZombieEntity(world);

        BlockPos pos = getSpawnPosition();
        pigman.setPosition(pos.x, pos.y, pos.z);

        pigman.anger = 1; // Needed for getTargetInRange to work
        pigman.makeAngry(pigman.getTargetInRange());
        world.spawnEntity(pigman);
    }

    private BlockPos getSpawnPosition()
    {
        while (true)
        {
            int xRand = world.random.nextInt(12);
            int zRand = world.random.nextInt(12);

            BlockPos pos = new BlockPos(x - 6 + xRand, y - 1, z - 6 + zRand);
            if ((pos.x < x - 1 || pos.x > x + 1) && (pos.z < z - 1 || pos.z > z + 1))
                return pos;
        }
    }

    @SuppressWarnings("unchecked")
    private void killPigZombies()
    {
        // Not used in MCPE, probs caused issues in MCPE so they're stupid
        double minX = x - 40.0;
        double minY = y - 40.0;
        double minZ = z - 40.0;
        double maxX = x + 1.0 + 40.0;
        double maxY = y + 1.0 + 40.0;
        double maxZ = z + 1.0 + 40.0;

        // Get all Pigmen in the area
        List<PigZombieEntity> pigmen = world.collectEntitiesByClass(PigZombieEntity.class, Box.createCached(minX, minY, minZ, maxX, maxY, maxZ));

        for (PigZombieEntity pigman : pigmen)
            if (pigman.isAlive())
                pigman.markDead();
    }

    private int getNumItemsPerLevel(int level)
    {
        if (level == 0)
            return 9;
        if (level <= 3)
            return 15;

        int randValue;
        if (level < 8)
        {
            randValue = world.random.nextInt(42);
            return Math.max(0, randValue - 4);
        }
        else
        {
            randValue = world.random.nextInt(27);
            return Math.max(0, randValue - 2);
        }
    }

    private void spawnItems(int count)
    {
        for (int i = 0; i < count; i++)
            spawnItem();
    }

    private void spawnItem()
    {
        BlockPos pos = getSpawnPosition();
        ItemStack item = getSpawnItem();
        ItemEntity itemEntity = new ItemEntity(world, pos.x, pos.y, pos.z, item);
        world.spawnEntity(itemEntity);
    }

    @SuppressWarnings("ReassignedVariable")
    private ItemStack getSpawnItem()
    {
        int rand = world.random.nextInt(9);
        int count = 1;
        Item item;
        switch (rand)
        {
            case 0:
                item = Item.GLOWSTONE_DUST;
                count = 3;
                break;
            case 1:
                item = Pocket.ITEM_MELON_SEEDS;
                break;
            case 2:
                item = Item.BLOCK_ITEMS.get(Block.RED_MUSHROOM);
                break;
            case 3:
                item = Item.BLOCK_ITEMS.get(Block.BROWN_MUSHROOM);
                break;
            case 4:
                item = Item.SUGAR_CANE;
                break;
            case 5:
                item = Item.BLOCK_ITEMS.get(Block.CACTUS);
                break;

            // TODO
            /*case 6:
                item = Pocket.ITEM_NETHER_QUARTZ;
                count = 4;
                break;*/

            case 7:
                item = Pocket.ITEM_PUMPKIN_SEEDS;
                break;
            default:
                return GetLowOddsSpawnItem();
        }
        return new ItemStack(item, count);
    }

    private ItemStack GetLowOddsSpawnItem()
    {
        int rand = world.random.nextInt(9);
        Item item = switch (rand)
        {
            case 0 -> Item.ARROW;
            case 1 -> Item.BED;
            case 2 -> Item.BONE;
            case 3 -> Item.BOOK;
            case 4 -> Item.BOW;
            case 5 -> Item.BOWL;
            case 6 -> Item.FEATHER;
            case 7 -> Item.PAINTING;
            case 8 -> Item.WOODEN_DOOR;
            default -> null;
        };

        if (item == null)
            return null;

        return new ItemStack(item, 1);
    }
}
