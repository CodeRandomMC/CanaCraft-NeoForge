package com.coderandom.canna_craft.datagen;

import com.coderandom.canna_craft.blocks.CCBlocks;
import com.coderandom.canna_craft.items.CCItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
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

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, CCItems.HEMPITE_CRYSTAL, 9)
                .requires(CCBlocks.HEMPITE_BLOCK.get())
                .unlockedBy("has_hempite_block", has(CCBlocks.HEMPITE_BLOCK.get()))
                .save(output);

        RecipeUtil.oreSmelting(output, HEMPITE_CRYSTAL_SMELTABLES, RecipeCategory.MISC, CCItems.HEMPITE_CRYSTAL, 0.25f, 150, "hempite_crystal");
        RecipeUtil.oreBlasting(output, HEMPITE_CRYSTAL_SMELTABLES, RecipeCategory.MISC, CCItems.HEMPITE_CRYSTAL, 0.25f, 300, "hempite_crystal");
    }
}
