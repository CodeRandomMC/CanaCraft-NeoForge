package com.coderandom.canna_craft.events;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

import static com.coderandom.canna_craft.CannaCraft.MODID;
import static com.coderandom.canna_craft.CannaCraft.LOGGER;

@EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents
{
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        // Some client setup code
        LOGGER.info("HELLO FROM CLIENT SETUP");
        LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
    }
}
