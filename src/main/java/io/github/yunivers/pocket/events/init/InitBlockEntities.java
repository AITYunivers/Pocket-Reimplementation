package io.github.yunivers.pocket.events.init;

import io.github.yunivers.pocket.block.entity.NetherReactorBlockEntity;
import net.mine_diver.unsafeevents.listener.EventListener;
import net.modificationstation.stationapi.api.event.block.entity.BlockEntityRegisterEvent;

public class InitBlockEntities
{
    @EventListener
    public void initBlockEntities(BlockEntityRegisterEvent event)
    {
        event.register(NetherReactorBlockEntity.class, "nether_reactor");
    }
}
