package com.coderandom.canna_craft.items;

import com.coderandom.canna_craft.CannaCraft;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CCItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CannaCraft.MODID);

    public static final DeferredItem<Item> HEMPITE_CRYSTAL =
            ITEMS.registerSimpleItem("hempite_crystal");

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
