package com.coderandom.canna_craft.datagen;

import com.coderandom.canna_craft.blocks.CCBlocks;
import com.coderandom.canna_craft.util.CCTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

import static com.coderandom.canna_craft.CannaCraft.MODID;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(CCTags.Blocks.HEMPITE_ORES)
                .add(
                        CCBlocks.HEMPITE_ORE.get(),
                        CCBlocks.DEEPSLATE_HEMPITE_ORE.get(),
                        CCBlocks.NETHER_HEMPITE_ORE.get(),
                        CCBlocks.END_STONE_HEMPITE_ORE.get()
                );
        tag(CCTags.Blocks.MINEABLE_WITH_SICKLE)
                .addTag(BlockTags.CROPS)
                .addTag(BlockTags.FLOWERS)
                .add(
                        Blocks.SUGAR_CANE,
                        Blocks.CHORUS_PLANT,
                        Blocks.BROWN_MUSHROOM,
                        Blocks.RED_MUSHROOM,
                        Blocks.SHORT_GRASS,
                        Blocks.TALL_GRASS,
                        Blocks.FERN,
                        Blocks.LARGE_FERN
                );
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addTag(CCTags.Blocks.HEMPITE_ORES)
                .add(
                        CCBlocks.HEMPITE_BLOCK.get()
                );
        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(CCBlocks.HEMPITE_ORE.get())
                .add(CCBlocks.HEMPITE_BLOCK.get())
        ;
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(
                        CCBlocks.DEEPSLATE_HEMPITE_ORE.get()
                );
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(
                        CCBlocks.NETHER_HEMPITE_ORE.get(),
                        CCBlocks.END_STONE_HEMPITE_ORE.get()
                );

    }
}
