package com.coderandom.canna_craft.datagen;

import com.coderandom.canna_craft.block.CCBlocks;
import com.coderandom.canna_craft.util.CCTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.coderandom.canna_craft.CannaCraft.MODID;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(CCTags.Items.HEMPITE_ORES)
                .add(
                        CCBlocks.HEMPITE_ORE.get().asItem(),
                        CCBlocks.DEEPSLATE_HEMPITE_ORE.get().asItem(),
                        CCBlocks.NETHER_HEMPITE_ORE.get().asItem(),
                        CCBlocks.END_STONE_HEMPITE_ORE.get().asItem()
                );
    }
}
