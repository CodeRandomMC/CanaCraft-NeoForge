package com.coderandom.canna_craft.datagen;

import com.coderandom.canna_craft.blocks.CCBlocks;
import com.coderandom.canna_craft.items.CCItems;
import com.coderandom.canna_craft.items.CCTabs;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.common.data.LanguageProvider;

import java.util.function.Supplier;

import static com.coderandom.canna_craft.CannaCraft.MODID;

public class ModEnUsLangProvider extends LanguageProvider {
    public ModEnUsLangProvider(PackOutput output) {
        super(output, MODID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Items
        add(CCItems.HEMPITE_CRYSTAL.get(), "Hempite Crystal");
        // Tools
        add(CCItems.WOODEN_SICKLE.get(), "Wooden Sickle");
        add(CCItems.STONE_SICKLE.get(), "Stone Sickle");
        add(CCItems.IRON_SICKLE.get(), "Iron Sickle");
        add(CCItems.GOLDEN_SICKLE.get(), "Golden Sickle");
        add(CCItems.DIAMOND_SICKLE.get(), "Diamond Sickle");
        add(CCItems.NETHERITE_SICKLE.get(), "Netherite Sickle");
        // Blocks
        add(CCBlocks.HEMPITE_BLOCK.get(), "Block of Hempite Crystal");
        add(CCBlocks.HEMPITE_ORE.get(), "Hempite Ore");
        add(CCBlocks.DEEPSLATE_HEMPITE_ORE.get(), "Deepslate Hempite Ore");
        add(CCBlocks.NETHER_HEMPITE_ORE.get(), "Nether Hempite Ore");
        add(CCBlocks.END_STONE_HEMPITE_ORE.get(), "End Stone Hempite Ore");
        // ItemGroups / Creative tabs
        add(CCTabs.CANNA_CRAFT_TOOLS, "CannaCraft Tools");
        add(CCTabs.CANNA_CRAFT_INGREDIENTS, "CannaCraft Ingredients");
        add(CCTabs.CANNA_CRAFT_NATURAL_BLOCKS, "CannaCraft Natural Blocks");
    }

    private void add(Supplier<CreativeModeTab> tab, String name) {
        add(tab.get().getDisplayName().getString(), name);
    }
}
