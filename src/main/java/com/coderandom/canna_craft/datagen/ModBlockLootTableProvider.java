package com.coderandom.canna_craft.datagen;

import com.coderandom.canna_craft.blocks.CCBlocks;
import com.coderandom.canna_craft.items.CCItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    private static final Set<Item> EXPLOSION_RESISTANT = new HashSet<>();

    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(InitExplosionResistantSet(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    private static Set<Item> InitExplosionResistantSet() {
        EXPLOSION_RESISTANT.add(CCItems.HEMPITE_CRYSTAL.get());
        return EXPLOSION_RESISTANT;
    }

    @Override
    protected void generate() {
        dropSelf(CCBlocks.HEMPITE_BLOCK);
        dropSelf(CCBlocks.HEMPITE_STAIRS);
        slabDrop(CCBlocks.HEMPITE_SLAB);
        dropSelf(CCBlocks.HEMPITE_WALL);
        oreDrop(CCBlocks.HEMPITE_ORE, CCItems.HEMPITE_CRYSTAL);
        oreDrop(CCBlocks.DEEPSLATE_HEMPITE_ORE, CCItems.HEMPITE_CRYSTAL, 1, 2);
        oreDrop(CCBlocks.NETHER_HEMPITE_ORE, CCItems.HEMPITE_CRYSTAL, 2, 4);
        oreDrop(CCBlocks.END_STONE_HEMPITE_ORE, CCItems.HEMPITE_CRYSTAL, 3, 6);
    }

    private void dropSelf(DeferredBlock<Block> block) {
        dropSelf(block.get());
    }

    private void slabDrop(DeferredBlock<Block> slabBlock) {
        this.add(slabBlock.get(), block -> createSlabItemTable(slabBlock.get()));
    }

    private void oreDrop(DeferredBlock<Block> oreBlock, DeferredItem<Item> oreDrop) {
        this.add(oreBlock.get(), block -> createOreDrop(oreBlock.get(), oreDrop.get()));
    }

    private void oreDrop(DeferredBlock<Block> oreBlock, DeferredItem<Item> oreDrop, int minCount, int maxCount) {
        this.add(oreBlock.get(), block -> {
            HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
            return this.createSilkTouchDispatchTable(block,
                    this.applyExplosionDecay(block, LootItem.lootTableItem(oreDrop.get())
                            .apply(SetItemCountFunction.setCount(UniformGenerator.between(minCount, maxCount)))
                            .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE))))
            );
        });
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return CCBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
