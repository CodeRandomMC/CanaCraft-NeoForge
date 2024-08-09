package com.coderandom.canna_craft.datagen;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static com.coderandom.canna_craft.CannaCraft.MODID;

public class RecipeUtil {
    protected static void sickle(RecipeOutput recipeOutput, DeferredItem<Item> sickleItem, Item ingredientItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, sickleItem)
                .pattern(" # ")
                .pattern("  #")
                .pattern("$# ")
                .define('#', ingredientItem)
                .define('$', Items.STICK)
                .unlockedBy(getHasName(ingredientItem), has(ingredientItem))
                .save(recipeOutput);
    }


    protected static void sickle(RecipeOutput recipeOutput, DeferredItem<Item> sickleItem, TagKey<Item> ingredientTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, sickleItem)
                .pattern(" # ")
                .pattern("  #")
                .pattern("$# ")
                .define('#', ingredientTag)
                .define('$', Items.STICK)
                .unlockedBy("has_from_tag", has(ingredientTag))
                .save(recipeOutput);
    }

    protected static void stair(RecipeOutput recipeOutput, DeferredBlock<Block> stairs, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, stairs, 4).define('#', material).pattern("#  ").pattern("## ").pattern("###")
                .unlockedBy(getHasName(material), has(material)).save(recipeOutput);
    }

    protected static void nineBlockStorageRecipes(RecipeOutput recipeOutput, ItemLike unpacked, ItemLike packed) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, unpacked, 9).requires(packed).group(unpacked.asItem().getDescriptionId()).unlockedBy(getHasName(packed), has(packed)).save(recipeOutput, MODID + ':' + getItemName(unpacked) + "_unpack");
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, packed).define('#', unpacked).pattern("###").pattern("###").pattern("###").group(packed.asItem().getDescriptionId()).unlockedBy(getHasName(unpacked), has(unpacked)).save(recipeOutput, MODID + ':' + getItemName(packed) + "_packed");
    }

    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, ingredients, category, result, experience, cookingTime, group, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, ingredients, category, result, experience, cookingTime, group, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> serializer, AbstractCookingRecipe.Factory<T> recipeFactory, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience, int cookingTime, String group, String suffix) {
        Iterator var10 = ingredients.iterator();

        while(var10.hasNext()) {
            ItemLike itemlike = (ItemLike)var10.next();
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), category, result, experience, cookingTime, serializer, recipeFactory).group(group).unlockedBy(getHasName(itemlike), has(itemlike)).save(recipeOutput, MODID + ':' + getItemName(result) + suffix + "_" + getItemName(itemlike));
        }

    }

    protected static Criterion<InventoryChangeTrigger.TriggerInstance> has(ItemLike itemLike) {
        return inventoryTrigger(net.minecraft.advancements.critereon.ItemPredicate.Builder.item().of(new ItemLike[]{itemLike}));
    }

    protected static Criterion<InventoryChangeTrigger.TriggerInstance> has(TagKey<Item> tag) {
        return inventoryTrigger(net.minecraft.advancements.critereon.ItemPredicate.Builder.item().of(tag));
    }

    protected static Criterion<InventoryChangeTrigger.TriggerInstance> inventoryTrigger(ItemPredicate.Builder... items) {
        return inventoryTrigger(Arrays.stream(items).map(ItemPredicate.Builder::build).toArray(ItemPredicate[]::new));
    }

    protected static Criterion<InventoryChangeTrigger.TriggerInstance> inventoryTrigger(ItemPredicate... predicates) {
        return CriteriaTriggers.INVENTORY_CHANGED.createCriterion(new InventoryChangeTrigger.TriggerInstance(Optional.empty(), InventoryChangeTrigger.TriggerInstance.Slots.ANY, List.of(predicates)));
    }

    protected static String getHasName(ItemLike itemLike) {
        return "has_" + getItemName(itemLike);
    }

    protected static String getItemName(ItemLike itemLike) {
        return BuiltInRegistries.ITEM.getKey(itemLike.asItem()).getPath();
    }
}
