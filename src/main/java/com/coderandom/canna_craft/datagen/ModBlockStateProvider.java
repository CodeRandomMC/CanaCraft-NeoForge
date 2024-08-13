package com.coderandom.canna_craft.datagen;

import com.coderandom.canna_craft.block.CCBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.WallBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import static com.coderandom.canna_craft.CannaCraft.MODID;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(CCBlocks.HEMPITE_BLOCK, RenderType.TRANSLUCENT);
        stairsWithItem(CCBlocks.HEMPITE_STAIRS, CCBlocks.HEMPITE_BLOCK, RenderType.TRANSLUCENT);
        slabWithItem(CCBlocks.HEMPITE_SLAB, CCBlocks.HEMPITE_BLOCK);
        wall(CCBlocks.HEMPITE_WALL, CCBlocks.HEMPITE_BLOCK);
        blockWithItem(CCBlocks.HEMPITE_ORE);
        blockWithItem(CCBlocks.DEEPSLATE_HEMPITE_ORE);
        blockWithItem(CCBlocks.NETHER_HEMPITE_ORE);
        blockWithItem(CCBlocks.END_STONE_HEMPITE_ORE);
    }

    private void wall(DeferredBlock<Block> wallBlock, DeferredBlock<Block> material) {
        wallBlock(((WallBlock) wallBlock.get()), blockTexture(material.get()));
    }

    private void wall(DeferredBlock<Block> wallBlock, DeferredBlock<Block> material, RenderType renderType) {
        wallBlockWithRenderType(((WallBlock) wallBlock.get()), blockTexture(material.get()), renderType.getType());
    }

    private void stairsWithItem(DeferredBlock<Block> stairBlock, DeferredBlock<Block> material){
        stairsBlock(((StairBlock) stairBlock.get()), blockTexture(material.get()));
        blockItem(stairBlock);
    }

    private void stairsWithItem(DeferredBlock<Block> stairBlock, DeferredBlock<Block> material, RenderType renderType){
        stairsBlockWithRenderType(((StairBlock) stairBlock.get()), blockTexture(material.get()), renderType.getType());
        blockItem(stairBlock);
    }

    private void slabWithItem(DeferredBlock<Block> slabBlock, DeferredBlock<Block> material){
        slabBlock(((SlabBlock) slabBlock.get()), blockTexture(material.get()), blockTexture(material.get()));
        blockItem(slabBlock);
    }

    private void blockItem(DeferredBlock<Block> deferredBlock){
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile(MODID + ":block/" + deferredBlock.getId().getPath()));
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock) {
        // Generate a simple block model and link it with an item model
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockWithItem(DeferredBlock<Block> deferredBlock, RenderType renderType) {
        // Apply render type - this might be applied differently depending on your needs
        ModelFile renderModel = models().cubeAll(name(deferredBlock.get()), blockTexture(deferredBlock.get()))
                .renderType(renderType.getType());

        // Apply the model with the render type to the block state
        simpleBlockWithItem(deferredBlock.get(), renderModel);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return BuiltInRegistries.BLOCK.getKey(block);
    }

    private enum RenderType {
        TRANSPARENT("minecraft:cutout"),
        SOLID("minecraft:solid"),
        CUTOUT("minecraft:cutout"),
        CUTOUT_MIPPED("minecraft:cutout_mipped"),
        TRANSLUCENT("minecraft:translucent");

        private final String type;

        RenderType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
