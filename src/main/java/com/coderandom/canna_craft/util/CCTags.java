package com.coderandom.canna_craft.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static com.coderandom.canna_craft.CannaCraft.MODID;

public class CCTags {
    public static class Blocks {
        public static final TagKey<Block> HEMPITE_ORES = create("hempite_ores");
        public static final TagKey<Block> HEMPITE_BLOCKS = create("hempite_blocks");
        public static final TagKey<Block> MINEABLE_WITH_SICKLE = create("mineable/sickle");

        private static TagKey<Block> create(String pathName) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(MODID, pathName));
        }
    }

    public static class Items {
        public static final TagKey<Item> HEMPITE_ORES = create("hempite_ores");

        private static TagKey<Item> create(String pathName) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(MODID, pathName));
        }
    }
}
