package com.coderandom.canna_craft.datagen;

import com.coderandom.canna_craft.items.CCItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Objects;

import static com.coderandom.canna_craft.CannaCraft.MODID;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(CCItems.HEMPITE_CRYSTAL);
        tool(CCItems.WOODEN_SICKLE);
        tool(CCItems.STONE_SICKLE);
        tool(CCItems.IRON_SICKLE);
        tool(CCItems.GOLDEN_SICKLE);
        tool(CCItems.DIAMOND_SICKLE);
        tool(CCItems.NETHERITE_SICKLE);
    }

    public ItemModelBuilder basicItem(DeferredItem<Item> item) {
        return this.basicItem(Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item.get())));
    }

    public ItemModelBuilder tool(DeferredItem<Item> item) {
        ResourceLocation resourceLocation = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item.get()));
        return this.getBuilder(resourceLocation.toString())
                .parent(new ModelFile.UncheckedModelFile("item/handheld"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(MODID, "item/" + resourceLocation.getPath()));
    }
}
