package io.github.yunivers.pocket.compatibility.ami;

import io.github.yunivers.pocket.Pocket;
import net.glasslauncher.mods.alwaysmoreitems.api.*;
import net.glasslauncher.mods.alwaysmoreitems.config.AMIConfig;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.modificationstation.stationapi.api.util.Identifier;

import static io.github.yunivers.pocket.Pocket.NAMESPACE;

public class AMIPocketPlugin implements ModPluginProvider
{
    private AMIHelpers amiHelpers;

    @Override
    public String getName()
    {
        return "Minecraft: Pocket Edition";
    }

    @Override
    public Identifier getId()
    {
        return NAMESPACE.id("always_more_items");
    }

    @Override
    public void register(ModRegistry modRegistry)
    {
        if (AMIConfig.showRedundantItems())
            return;

        ItemBlacklist itemBlacklist = amiHelpers.getItemBlacklist();
        itemBlacklist.addItemToBlacklist(new ItemStack(Pocket.BLOCK_BRICK_SLAB_DOUBLE));
        itemBlacklist.addItemToBlacklist(new ItemStack(Pocket.BLOCK_STONE_BRICK_SLAB_DOUBLE));
        itemBlacklist.addItemToBlacklist(new ItemStack(Pocket.BLOCK_MELON_CROP));
        itemBlacklist.addItemToBlacklist(new ItemStack(Pocket.BLOCK_PUMPKIN_CROP));
        itemBlacklist.addItemToBlacklist(new ItemStack(Pocket.BLOCK_NETHERRACK_MIMIC));
    }

    @Override
    public void onAMIHelpersAvailable(AMIHelpers amiHelpers)
    {
        this.amiHelpers = amiHelpers;
    }

    // <editor-fold desc="Unused">
    @Override
    public void onItemRegistryAvailable(ItemRegistry itemRegistry)
    {

    }

    @Override
    public void onRecipeRegistryAvailable(RecipeRegistry recipeRegistry)
    {

    }

    @Override
    public SyncableRecipe deserializeRecipe(NbtCompound nbtCompound)
    {
        return null;
    }
    // </editor-fold>
}
