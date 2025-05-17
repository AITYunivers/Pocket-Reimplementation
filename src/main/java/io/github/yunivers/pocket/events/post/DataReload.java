package io.github.yunivers.pocket.events.post;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.block.SlabBlock;
import net.minecraft.item.SlabBlockItem;
import net.minecraft.recipe.CraftingRecipeManager;
import net.minecraft.recipe.ShapedRecipe;
import net.modificationstation.stationapi.api.event.resource.DataReloadEvent;
import net.modificationstation.stationapi.impl.recipe.StationShapedRecipe;

public class DataReload
{
    @EventListener
    public void dataReload(DataReloadEvent event)
    {
        for (Object o : CraftingRecipeManager.getInstance().getRecipes())
            if (o instanceof StationShapedRecipe stationRecipe)
            {
                if (stationRecipe.output.getItem() instanceof SlabBlockItem && stationRecipe.output.count == 3)
                    stationRecipe.output.count = 6;
            }
            else if (o instanceof ShapedRecipe recipe)
            {
                if (recipe.getOutput().getItem() instanceof SlabBlockItem && recipe.getOutput().count == 3)
                    recipe.getOutput().count = 6;
            }
    }
}
