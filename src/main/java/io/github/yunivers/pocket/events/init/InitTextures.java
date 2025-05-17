package io.github.yunivers.pocket.events.init;

import io.github.yunivers.pocket.Pocket;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.item.Item;
import net.modificationstation.stationapi.api.client.event.texture.TextureRegisterEvent;
import net.modificationstation.stationapi.api.client.texture.atlas.Atlases;
import net.modificationstation.stationapi.api.client.texture.atlas.ExpandableAtlas;

import static io.github.yunivers.pocket.Pocket.NAMESPACE;

public class InitTextures
{
    @EventListener
    public void initTextures(TextureRegisterEvent event)
    {
        ExpandableAtlas terrainAtlas = Atlases.getTerrain();
        ExpandableAtlas guiItemsAtlas = Atlases.getGuiItems();

        Pocket.TEXTURE_ROSE_BLUE = terrainAtlas.addTexture(NAMESPACE.id("block/rose_blue")).index;
        Pocket.TEXTURE_STONE_BRICKS = terrainAtlas.addTexture(NAMESPACE.id("block/stone_bricks")).index;
        Pocket.TEXTURE_NETHER_BRICKS = terrainAtlas.addTexture(NAMESPACE.id("block/nether_bricks")).index;
        Pocket.TEXTURE_COAL_BLOCK = terrainAtlas.addTexture(NAMESPACE.id("block/coal_block")).index;
        Pocket.TEXTURE_HAY_BALE_TOP = terrainAtlas.addTexture(NAMESPACE.id("block/hay_bale_top")).index;
        Pocket.TEXTURE_HAY_BALE_SIDE = terrainAtlas.addTexture(NAMESPACE.id("block/hay_bale_side")).index;
        Pocket.TEXTURE_MELON_TOP = terrainAtlas.addTexture(NAMESPACE.id("block/melon_top")).index;
        Pocket.TEXTURE_MELON_SIDE = terrainAtlas.addTexture(NAMESPACE.id("block/melon_side")).index;
        Pocket.TEXTURE_STEM = terrainAtlas.addTexture(NAMESPACE.id("block/stem")).index;
        Pocket.TEXTURE_STEM_CONNECTED = terrainAtlas.addTexture(NAMESPACE.id("block/stem_connected")).index;
        Pocket.TEXTURE_NETHER_REACTOR = terrainAtlas.addTexture(NAMESPACE.id("block/nether_reactor")).index;
        Pocket.TEXTURE_NETHER_REACTOR_ACTIVE = terrainAtlas.addTexture(NAMESPACE.id("block/nether_reactor_active")).index;
        Pocket.TEXTURE_NETHER_REACTOR_DEAD = terrainAtlas.addTexture(NAMESPACE.id("block/nether_reactor_dead")).index;
        Pocket.TEXTURE_GLOWING_OBSIDIAN = terrainAtlas.addTexture(NAMESPACE.id("block/glowing_obsidian")).index;

        Pocket.TEXTURE_NETHER_BRICK = guiItemsAtlas.addTexture(NAMESPACE.id("item/nether_brick")).index;
        Pocket.TEXTURE_MELON_SLICE = guiItemsAtlas.addTexture(NAMESPACE.id("item/melon_slice")).index;
        Pocket.TEXTURE_MELON_SEEDS = guiItemsAtlas.addTexture(NAMESPACE.id("item/melon_seeds")).index;
        Pocket.TEXTURE_PUMPKIN_SEEDS = guiItemsAtlas.addTexture(NAMESPACE.id("item/pumpkin_seeds")).index;

        initBlockTextures();
        initItemTextures();
    }

    private void initBlockTextures()
    {
        Pocket.BLOCK_ROSE_BLUE.textureId = Pocket.TEXTURE_ROSE_BLUE;
        Pocket.BLOCK_STONE_BRICKS.textureId = Pocket.TEXTURE_STONE_BRICKS;
        Pocket.BLOCK_NETHER_BRICKS.textureId = Pocket.TEXTURE_NETHER_BRICKS;
        Pocket.BLOCK_COAL.textureId = Pocket.TEXTURE_COAL_BLOCK;
        Pocket.BLOCK_GLOWING_OBSIDIAN.textureId = Pocket.TEXTURE_GLOWING_OBSIDIAN;
    }

    private void initItemTextures()
    {
        Item.BLOCK_ITEMS.get(Pocket.BLOCK_ROSE_BLUE).setTextureId(Pocket.TEXTURE_ROSE_BLUE);
        Pocket.ITEM_NETHER_BRICK.setTextureId(Pocket.TEXTURE_NETHER_BRICK);
        Pocket.ITEM_MELON_SLICE.setTextureId(Pocket.TEXTURE_MELON_SLICE);
        Pocket.ITEM_MELON_SEEDS.setTextureId(Pocket.TEXTURE_MELON_SEEDS);
        Pocket.ITEM_PUMPKIN_SEEDS.setTextureId(Pocket.TEXTURE_PUMPKIN_SEEDS);
    }
}
