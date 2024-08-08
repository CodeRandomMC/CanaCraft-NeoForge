package com.coderandom.canna_craft.datagen;

import com.coderandom.canna_craft.blocks.CCBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
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
        blockWithItem(CCBlocks.HEMPITE_ORE);
        blockWithItem(CCBlocks.DEEPSLATE_HEMPITE_ORE);
        blockWithItem(CCBlocks.NETHER_HEMPITE_ORE);
        blockWithItem(CCBlocks.END_STONE_HEMPITE_ORE);
    }

    public void blockWithItem(DeferredBlock<Block> deferredBlock) {
        // Generate a simple block model and link it with an item model
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    public void blockWithItem(DeferredBlock<Block> deferredBlock, RenderType renderType) {
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

    public enum RenderType {
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
