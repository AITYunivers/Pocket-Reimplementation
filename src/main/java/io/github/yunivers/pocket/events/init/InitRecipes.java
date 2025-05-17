package io.github.yunivers.pocket.events.init;

import io.github.yunivers.pocket.Pocket;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.CraftingRecipeManager;
import net.minecraft.recipe.ShapedRecipe;
import net.modificationstation.stationapi.api.event.recipe.RecipeRegisterEvent;
import net.modificationstation.stationapi.api.recipe.CraftingRegistry;
import net.modificationstation.stationapi.api.recipe.SmeltingRegistry;

public class InitRecipes
{
    @EventListener
    public void initRecipes(RecipeRegisterEvent event)
    {
        RecipeRegisterEvent.Vanilla type = RecipeRegisterEvent.Vanilla.fromType(event.recipeId);

        if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPED)
        {
            // Blocks
            CraftingRegistry.addShapedRecipe(
                new ItemStack(Pocket.BLOCK_BRICK_STAIRS, 4),
                "b  ", "bb ", "bbb",
                'b', new ItemStack(Block.BRICKS));

            CraftingRegistry.addShapedRecipe(
                new ItemStack(Pocket.BLOCK_BRICK_SLAB, 6),
                "bbb",
                'b', new ItemStack(Block.BRICKS));

            CraftingRegistry.addShapedRecipe(
                new ItemStack(Pocket.BLOCK_STONE_BRICKS, 4),
                "ss", "ss",
                's', new ItemStack(Block.STONE));

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(Pocket.BLOCK_STONE_BRICK_STAIRS, 4),
                    "b  ", "bb ", "bbb",
                    'b', new ItemStack(Pocket.BLOCK_STONE_BRICKS));

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(Pocket.BLOCK_STONE_BRICK_SLAB, 6),
                    "bbb",
                    'b', new ItemStack(Pocket.BLOCK_STONE_BRICKS));

            CraftingRegistry.addShapedRecipe(
                new ItemStack(Pocket.BLOCK_NETHER_BRICKS),
                "nn", "nn",
                'n', new ItemStack(Pocket.ITEM_NETHER_BRICK));

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(Pocket.BLOCK_NETHER_BRICK_STAIRS, 4),
                    "b  ", "bb ", "bbb",
                    'b', new ItemStack(Pocket.BLOCK_NETHER_BRICKS));

            CraftingRegistry.addShapedRecipe(
                new ItemStack(Pocket.BLOCK_COAL),
                "ccc", "ccc", "ccc",
                'c', new ItemStack(Item.COAL));

            CraftingRegistry.addShapedRecipe(
                new ItemStack(Pocket.BLOCK_HAY_BALE),
                "www", "www", "www",
                'w', new ItemStack(Item.WHEAT));

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(Pocket.BLOCK_MELON),
                    "mmm", "mmm", "mmm",
                    'm', new ItemStack(Pocket.ITEM_MELON_SLICE));

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(Pocket.BLOCK_NETHER_REACTOR),
                    "idi", "idi", "idi",
                    'i', new ItemStack(Item.IRON_INGOT),
                    'd', new ItemStack(Item.DIAMOND));

            CraftingRegistry.addShapedRecipe(
                    new ItemStack(Pocket.BLOCK_OAK_FENCE_GATE),
                    "sps", "sps",
                    's', new ItemStack(Item.STICK),
                    'p', new ItemStack(Block.PLANKS));
        }
        else if (type == RecipeRegisterEvent.Vanilla.CRAFTING_SHAPELESS)
        {
            CraftingRegistry.addShapelessRecipe(
                new ItemStack(Item.COAL, 9),
                Pocket.BLOCK_COAL
            );

            CraftingRegistry.addShapelessRecipe(
                new ItemStack(Item.WHEAT, 9),
                Pocket.BLOCK_HAY_BALE
            );

            CraftingRegistry.addShapelessRecipe(
                new ItemStack(Pocket.ITEM_MELON_SEEDS),
                Pocket.ITEM_MELON_SLICE
            );

            CraftingRegistry.addShapelessRecipe(
                new ItemStack(Pocket.ITEM_PUMPKIN_SEEDS, 4),
                Block.PUMPKIN
            );
        }
        else if (type == RecipeRegisterEvent.Vanilla.SMELTING)
        {
            SmeltingRegistry.addSmeltingRecipe(
                new ItemStack(Block.RED_MUSHROOM),
                new ItemStack(Item.DYE, 1, 1)
            );

            SmeltingRegistry.addSmeltingRecipe(
                new ItemStack(Block.NETHERRACK),
                new ItemStack(Pocket.ITEM_NETHER_BRICK)
            );
        }
    }
}
