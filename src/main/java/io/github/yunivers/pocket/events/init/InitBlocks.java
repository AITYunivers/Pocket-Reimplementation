package io.github.yunivers.pocket.events.init;

import io.github.yunivers.pocket.Pocket;
import io.github.yunivers.pocket.block.*;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.modificationstation.stationapi.api.event.registry.BlockRegistryEvent;
import net.modificationstation.stationapi.api.template.block.TemplateBlock;
import net.modificationstation.stationapi.api.template.block.TemplatePlantBlock;
import net.modificationstation.stationapi.api.template.block.TemplateStairsBlock;

import static io.github.yunivers.pocket.Pocket.NAMESPACE;

public class InitBlocks
{
    @EventListener
    public void initBlocks(BlockRegistryEvent event)
    {
        Pocket.BLOCK_ROSE_BLUE = new TemplatePlantBlock(NAMESPACE.id("rose_blue"), 0)
            .setHardness(0.0f)
            .setTranslationKey(NAMESPACE.id("rose_blue"));
        Pocket.BLOCK_BRICK_STAIRS = new TemplateStairsBlock(NAMESPACE.id("brick_stairs"), Block.BRICKS)
            .setTranslationKey(NAMESPACE.id("brick_stairs"));
        Pocket.BLOCK_BRICK_SLAB = new YuniTemplateSlabBlock(NAMESPACE.id("brick_slab"), Block.BRICKS, false)
            .setTranslationKey(NAMESPACE.id("brick_slab"));
        Pocket.BLOCK_BRICK_SLAB_DOUBLE = new YuniTemplateSlabBlock(NAMESPACE.id("brick_slab_double"), Block.BRICKS, true)
            .setTranslationKey(NAMESPACE.id("brick_slab"));
        Pocket.BLOCK_STONE_BRICKS = new TemplateBlock(NAMESPACE.id("stone_bricks"), Material.STONE)
            .setHardness(1.5f)
            .setResistance(10.0f)
            .setSoundGroup(Block.STONE_SOUND_GROUP)
            .setTranslationKey(NAMESPACE.id("stone_bricks"));
        Pocket.BLOCK_STONE_BRICK_STAIRS = new TemplateStairsBlock(NAMESPACE.id("stone_brick_stairs"), Pocket.BLOCK_STONE_BRICKS)
            .setTranslationKey(NAMESPACE.id("stone_brick_stairs"));
        Pocket.BLOCK_STONE_BRICK_SLAB = new YuniTemplateSlabBlock(NAMESPACE.id("stone_brick_slab"), Pocket.BLOCK_STONE_BRICKS, false)
            .setTranslationKey(NAMESPACE.id("stone_brick_slab"));
        Pocket.BLOCK_STONE_BRICK_SLAB_DOUBLE = new YuniTemplateSlabBlock(NAMESPACE.id("stone_brick_slab_double"), Pocket.BLOCK_STONE_BRICKS, true)
            .setTranslationKey(NAMESPACE.id("stone_brick_slab"));
        Pocket.BLOCK_NETHER_BRICKS = new TemplateBlock(NAMESPACE.id("nether_bricks"), Material.STONE)
            .setHardness(2.0f)
            .setResistance(10.0f)
            .setSoundGroup(Block.STONE_SOUND_GROUP)
            .setTranslationKey(NAMESPACE.id("nether_bricks"));
        Pocket.BLOCK_NETHER_BRICK_STAIRS = new TemplateStairsBlock(NAMESPACE.id("nether_brick_stairs"), Pocket.BLOCK_NETHER_BRICKS)
            .setTranslationKey(NAMESPACE.id("nether_brick_stairs"));
        Pocket.BLOCK_COAL = new TemplateBlock(NAMESPACE.id("coal_block"), Material.STONE)
            .setHardness(5.0f)
            .setResistance(6.0f)
            .setSoundGroup(Block.STONE_SOUND_GROUP)
            .setTranslationKey(NAMESPACE.id("coal_block"));
        Pocket.BLOCK_HAY_BALE = new HaybaleBlock(NAMESPACE.id("hay_bale"))
            .setTranslationKey(NAMESPACE.id("hay_bale"));
        Pocket.BLOCK_MELON = new MelonBlock(NAMESPACE.id("melon"))
            .setTranslationKey(NAMESPACE.id("melon"));
        Pocket.BLOCK_MELON_CROP = new StemCrop(NAMESPACE.id("melon_crop"), Pocket.BLOCK_MELON)
            .setTranslationKey(NAMESPACE.id("melon_seeds"));
        Pocket.BLOCK_PUMPKIN_CROP = new StemCrop(NAMESPACE.id("pumpkin_crop"), Block.PUMPKIN)
            .setTranslationKey(NAMESPACE.id("pumpkin_seeds"));
        new SPIRE_TEST();
        Pocket.BLOCK_NETHER_REACTOR = new NetherReactorBlock(NAMESPACE.id("nether_reactor"))
            .setHardness(3f)
            .setTranslationKey(NAMESPACE.id("nether_reactor"));
        Pocket.BLOCK_NETHERRACK_MIMIC = new NetherrackMimicBlock(NAMESPACE.id("netherrack"));
        Pocket.BLOCK_GLOWING_OBSIDIAN = new GlowingObsidianBlock(NAMESPACE.id("glowing_obsidian"))
            .setTranslationKey(NAMESPACE.id("glowing_obsidian"));
        Pocket.BLOCK_OAK_FENCE_GATE = new YuniTemplateFenceGateBlock(NAMESPACE.id("oak_fence_gate"), Block.PLANKS)
            .setTranslationKey(NAMESPACE.id("oak_fence_gate"));
    }
}
