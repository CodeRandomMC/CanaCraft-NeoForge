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
    public static final DeferredItem<Item> WOODEN_SICKLE;
    public static final DeferredItem<Item> STONE_SICKLE;
    public static final DeferredItem<Item> IRON_SICKLE;
    public static final DeferredItem<Item> GOLDEN_SICKLE;
    public static final DeferredItem<Item> DIAMOND_SICKLE;
    public static final DeferredItem<Item> NETHERITE_SICKLE;

    static {
        HEMPITE_CRYSTAL = ITEMS.registerSimpleItem("hempite_crystal");
        WOODEN_SICKLE = ITEMS.register("wooden_sickle", () -> new SickleItem(Tiers.WOOD));
        STONE_SICKLE = ITEMS.register("stone_sickle", () -> new SickleItem(Tiers.STONE));
        IRON_SICKLE = ITEMS.register("iron_sickle", () -> new SickleItem(Tiers.IRON));
        GOLDEN_SICKLE = ITEMS.register("golden_sickle", () -> new SickleItem(Tiers.GOLD));
        DIAMOND_SICKLE = ITEMS.register("diamond_sickle", () -> new SickleItem(Tiers.DIAMOND));
        NETHERITE_SICKLE = ITEMS.register("netherite_sickle", () -> new SickleItem(Tiers.NETHERITE));
    }

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
