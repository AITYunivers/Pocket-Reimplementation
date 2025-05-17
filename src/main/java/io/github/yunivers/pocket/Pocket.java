package io.github.yunivers.pocket;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.mod.entrypoint.EntrypointManager;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.util.Namespace;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.util.Random;

public class Pocket
{
    @SuppressWarnings("UnstableApiUsage")
    public static final Namespace NAMESPACE = Namespace.resolve();

    public static final Logger LOGGER = NAMESPACE.getLogger();

    public static final Random RANDOM = new Random();

    // Blocks
    public static Block BLOCK_ROSE_BLUE;
    public static Block BLOCK_BRICK_STAIRS;
    public static Block BLOCK_BRICK_SLAB;
    public static Block BLOCK_BRICK_SLAB_DOUBLE;
    public static Block BLOCK_STONE_BRICKS;
    public static Block BLOCK_STONE_BRICK_STAIRS;
    public static Block BLOCK_STONE_BRICK_SLAB;
    public static Block BLOCK_STONE_BRICK_SLAB_DOUBLE;
    public static Block BLOCK_NETHER_BRICKS;
    public static Block BLOCK_NETHER_BRICK_STAIRS;
    public static Block BLOCK_COAL;
    public static Block BLOCK_HAY_BALE;
    public static Block BLOCK_MELON;
    public static Block BLOCK_MELON_CROP;
    public static Block BLOCK_PUMPKIN_CROP;
    public static Block BLOCK_NETHER_REACTOR;
    public static Block BLOCK_NETHERRACK_MIMIC;
    public static Block BLOCK_GLOWING_OBSIDIAN;
    public static Block BLOCK_OAK_FENCE_GATE;

    // Items
    public static Item ITEM_NETHER_BRICK;
    public static Item ITEM_MELON_SLICE;
    public static Item ITEM_MELON_SEEDS;
    public static Item ITEM_PUMPKIN_SEEDS;

    // Textures
    public static int TEXTURE_ROSE_BLUE;
    public static int TEXTURE_STONE_BRICKS;
    public static int TEXTURE_NETHER_BRICKS;
    public static int TEXTURE_COAL_BLOCK;
    public static int TEXTURE_NETHER_BRICK;
    public static int TEXTURE_HAY_BALE_TOP;
    public static int TEXTURE_HAY_BALE_SIDE;
    public static int TEXTURE_MELON_TOP;
    public static int TEXTURE_MELON_SIDE;
    public static int TEXTURE_MELON_SLICE;
    public static int TEXTURE_MELON_SEEDS;
    public static int TEXTURE_PUMPKIN_SEEDS;
    public static int TEXTURE_STEM;
    public static int TEXTURE_STEM_CONNECTED;
    public static int TEXTURE_NETHER_REACTOR;
    public static int TEXTURE_NETHER_REACTOR_ACTIVE;
    public static int TEXTURE_NETHER_REACTOR_DEAD;
    public static int TEXTURE_GLOWING_OBSIDIAN;

    static
    {
        EntrypointManager.registerLookup(MethodHandles.lookup());
    }

    public static int getTextureFromIdentifier(Identifier identifier)
    {
        if (identifier.namespace != NAMESPACE)
            return 0;

        try
        {
            String[] parts = identifier.path.split("/");
            Field field = Pocket.class.getField("TEXTURE_" + parts[parts.length - 1].toUpperCase());
            return field.getInt(null);
        }
        catch (Exception e)
        {
            return 0;
        }
    }
}
