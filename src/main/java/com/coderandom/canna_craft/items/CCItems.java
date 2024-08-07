package com.coderandom.canna_craft.items;

import com.coderandom.canna_craft.CannaCraft;
import com.coderandom.canna_craft.items.custom.SickleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CCItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CannaCraft.MODID);

    public static final DeferredItem<Item> HEMPITE_CRYSTAL;
    public static final DeferredItem<Item> STONE_SICKLE;

    static {
        HEMPITE_CRYSTAL = ITEMS.registerSimpleItem("hempite_crystal");
        STONE_SICKLE = ITEMS.register("stone_sickle", () -> new SickleItem(Tiers.STONE));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
