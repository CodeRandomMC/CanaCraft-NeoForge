package com.coderandom.canna_craft.datagen;

import com.coderandom.canna_craft.block.CCBlocks;
import com.coderandom.canna_craft.items.CCItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Objects;

import static com.coderandom.canna_craft.CannaCraft.MODID;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        wallItem(CCBlocks.HEMPITE_WALL, CCBlocks.HEMPITE_BLOCK);
        basicItem(CCItems.HEMPITE_CRYSTAL);
        toolItem(CCItems.WOODEN_SICKLE);
        toolItem(CCItems.STONE_SICKLE);
        toolItem(CCItems.IRON_SICKLE);
        toolItem(CCItems.GOLDEN_SICKLE);
        toolItem(CCItems.DIAMOND_SICKLE);
        toolItem(CCItems.NETHERITE_SICKLE);
    }

    public void buttonItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void wallItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(MODID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public ItemModelBuilder basicItem(DeferredItem<Item> item) {
        return this.basicItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item.get())));
    }

    public ItemModelBuilder toolItem(DeferredItem<Item> item) {
        ResourceLocation resourceLocation = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item.get()));
        return this.getBuilder(resourceLocation.toString())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(MODID, "item/" + resourceLocation.getPath()));
    }
}
