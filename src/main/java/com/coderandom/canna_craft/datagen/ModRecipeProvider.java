package com.coderandom.canna_craft.datagen;

import com.coderandom.canna_craft.block.CCBlocks;
import com.coderandom.canna_craft.items.CCItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }



    @Override
    protected void buildRecipes(RecipeOutput output) {
        List<ItemLike> HEMPITE_CRYSTAL_SMELTABLES = List.of(
                CCBlocks.HEMPITE_ORE,
                CCBlocks.DEEPSLATE_HEMPITE_ORE,
                CCBlocks.NETHER_HEMPITE_ORE,
                CCBlocks.END_STONE_HEMPITE_ORE
        );

        RecipeUtil.nineBlockStorageRecipes(output, CCItems.HEMPITE_CRYSTAL, CCBlocks.HEMPITE_BLOCK);
        slab(output, RecipeCategory.BUILDING_BLOCKS, CCBlocks.HEMPITE_SLAB, CCBlocks.HEMPITE_BLOCK);
        RecipeUtil.stair(output, CCBlocks.HEMPITE_STAIRS, CCBlocks.HEMPITE_BLOCK);
        wall(output, RecipeCategory.BUILDING_BLOCKS, CCBlocks.HEMPITE_WALL, CCBlocks.HEMPITE_BLOCK);


        RecipeUtil.sickle(output, CCItems.WOODEN_SICKLE, ItemTags.PLANKS);
        RecipeUtil.sickle(output, CCItems.STONE_SICKLE, ItemTags.STONE_TOOL_MATERIALS);
        RecipeUtil.sickle(output, CCItems.IRON_SICKLE, Items.IRON_INGOT);
        RecipeUtil.sickle(output, CCItems.GOLDEN_SICKLE, Items.GOLD_INGOT);
        RecipeUtil.sickle(output, CCItems.DIAMOND_SICKLE, Items.DIAMOND);
        RecipeUtil.sickle(output, CCItems.NETHERITE_SICKLE, Items.NETHERITE_INGOT);

        RecipeUtil.oreSmelting(output, HEMPITE_CRYSTAL_SMELTABLES, RecipeCategory.MISC, CCItems.HEMPITE_CRYSTAL, 0.25f, 150, "hempite_crystal");
        RecipeUtil.oreBlasting(output, HEMPITE_CRYSTAL_SMELTABLES, RecipeCategory.MISC, CCItems.HEMPITE_CRYSTAL, 0.25f, 300, "hempite_crystal");
    }
}
