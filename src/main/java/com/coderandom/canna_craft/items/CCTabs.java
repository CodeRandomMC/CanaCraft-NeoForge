package com.coderandom.canna_craft.items;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.Collection;
import java.util.HashSet;
import java.util.function.Supplier;

import static com.coderandom.canna_craft.CannaCraft.MODID;

public class CCTabs {

    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    private static final Collection<ItemLike> INGREDIENTS = new HashSet<>();

    private static final Supplier<CreativeModeTab> CANNA_CRAFT_INGREDIENTS = registerIngredientsTab();

    private static Supplier<CreativeModeTab> registerIngredientsTab() {
        return CREATIVE_MODE_TABS.register("ingredients_tab", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.canna_craft.ingredients_tab"))
                .icon(() -> new ItemStack(CCItems.HEMPITE_CRYSTAL.get()))
                .displayItems((itemDisplayParameters, output) -> INGREDIENTS.forEach(output::accept))
                .build()
        );
    }

    private static void initializeIngredients() {
        INGREDIENTS.add(CCItems.HEMPITE_CRYSTAL);
    }

    private static void addCreativeTabs(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            INGREDIENTS.forEach(event::accept);
        }
    }

    public static void register(IEventBus eventBus) {
        initializeIngredients();
        CREATIVE_MODE_TABS.register(eventBus);
        eventBus.addListener(CCTabs::addCreativeTabs);
    }
}
