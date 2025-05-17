package io.github.yunivers.pocket.events.init;

import io.github.yunivers.pocket.Pocket;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.template.item.TemplateSeedsItem;
import net.modificationstation.stationapi.api.template.item.TemplateStackableFoodItem;

import static io.github.yunivers.pocket.Pocket.NAMESPACE;

public class InitItems
{
    @EventListener
    public void initItems(ItemRegistryEvent event)
    {
        Pocket.ITEM_NETHER_BRICK = new TemplateItem(NAMESPACE.id("nether_brick"))
            .setTranslationKey(NAMESPACE.id("nether_brick"));
        Pocket.ITEM_MELON_SLICE = new TemplateStackableFoodItem(NAMESPACE.id("melon_slice"), 1, false, 8)
            .setTranslationKey(NAMESPACE.id("melon_slice"));
        Pocket.ITEM_MELON_SEEDS = new TemplateSeedsItem(NAMESPACE.id("melon_seeds"), Pocket.BLOCK_MELON_CROP.id)
            .setTranslationKey(NAMESPACE.id("melon_seeds"));
        Pocket.ITEM_PUMPKIN_SEEDS = new TemplateSeedsItem(NAMESPACE.id("pumpkin_seeds"), Pocket.BLOCK_PUMPKIN_CROP.id)
            .setTranslationKey(NAMESPACE.id("pumpkin_seeds"));
    }
}
