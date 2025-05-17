package io.github.yunivers.pocket.events.post;

import io.github.yunivers.pocket.Pocket;
import io.github.yunivers.pocket.world.features.BlueRoseFeature;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.modificationstation.stationapi.api.bonemeal.BonemealAPI;
import net.modificationstation.stationapi.api.event.registry.AfterBlockAndItemRegisterEvent;
import net.modificationstation.stationapi.api.recipe.FuelRegistry;

public class PostBlockAndItemRegister
{
    @EventListener
    public void postBlockAndItemRegister(AfterBlockAndItemRegisterEvent event)
    {
        BonemealAPI.addPlant(Block.GRASS_BLOCK.getDefaultState(), new BlueRoseFeature(), 1);
        FuelRegistry.addFuelItem(Item.BLOCK_ITEMS.get(Pocket.BLOCK_COAL), 16000);
    }
}
