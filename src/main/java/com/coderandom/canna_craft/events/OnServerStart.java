package com.coderandom.canna_craft.events;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import static com.coderandom.canna_craft.CannaCraft.MODID;
import static com.coderandom.canna_craft.CannaCraft.LOGGER;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.DEDICATED_SERVER)
public class OnServerStart {
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        LOGGER.info("CannaCraft starting on server...");
    }
}
