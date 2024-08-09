package com.coderandom.canna_craft.util;

import com.coderandom.canna_craft.blocks.CCBlocks;
import com.coderandom.canna_craft.items.CCItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.coderandom.canna_craft.CannaCraft.MODID;

public class CCTabs {

    private static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final Supplier<CreativeModeTab> CANNA_CRAFT_INGREDIENTS;
    public static final Supplier<CreativeModeTab> CANNA_CRAFT_NATURAL_BLOCKS;
    public static final Supplier<CreativeModeTab> CANNA_CRAFT_TOOLS;

    static {
        CANNA_CRAFT_TOOLS = registerToolsTab();
        CANNA_CRAFT_INGREDIENTS = registerIngredientsTab();
        CANNA_CRAFT_NATURAL_BLOCKS = registerNaturalBlocksTab();
    }

    private static Supplier<CreativeModeTab> registerToolsTab() {
        return CREATIVE_MODE_TABS.register("tools_tab", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.canna_craft.tools_tab"))
                .icon(() -> new ItemStack(CCItems.IRON_SICKLE.get()))
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(CCItems.WOODEN_SICKLE);
                    output.accept(CCItems.STONE_SICKLE);
                    output.accept(CCItems.IRON_SICKLE);
                    output.accept(CCItems.GOLDEN_SICKLE);
                    output.accept(CCItems.DIAMOND_SICKLE);
                    output.accept(CCItems.NETHERITE_SICKLE);
                })
                .build()
        );
    }

    private static Supplier<CreativeModeTab> registerIngredientsTab() {
        return CREATIVE_MODE_TABS.register("ingredients_tab", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.canna_craft.ingredients_tab"))
                .icon(() -> new ItemStack(CCItems.HEMPITE_CRYSTAL.get()))
                .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MODID, "tools_tab"))
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(CCItems.HEMPITE_CRYSTAL);
                })
                .build()
        );
    }

    private static Supplier<CreativeModeTab> registerNaturalBlocksTab() {
        return CREATIVE_MODE_TABS.register("natural_blocks_tab", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.canna_craft.natural_blocks_tab"))
                .icon(() -> new ItemStack(CCBlocks.HEMPITE_BLOCK))
                .withTabsBefore(ResourceLocation.fromNamespaceAndPath(MODID, "ingredients_tab"))
                .displayItems((itemDisplayParameters, output) -> {
                    output.accept(CCBlocks.HEMPITE_BLOCK);
                    output.accept(CCBlocks.HEMPITE_ORE);
                    output.accept(CCBlocks.DEEPSLATE_HEMPITE_ORE);
                    output.accept(CCBlocks.NETHER_HEMPITE_ORE);
                    output.accept(CCBlocks.END_STONE_HEMPITE_ORE);
                })
                .build()
        );
    }

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
