package com.coderandom.canna_craft.blocks;

import com.coderandom.canna_craft.items.CCItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.coderandom.canna_craft.CannaCraft.MODID;

public class CCBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    public static final DeferredBlock<Block> HEMPITE_BLOCK;
    public static final DeferredBlock<Block> HEMPITE_STAIRS;
    public static final DeferredBlock<Block> HEMPITE_SLAB;
    public static final DeferredBlock<Block> HEMPITE_WALL;
    public static final DeferredBlock<Block> HEMPITE_ORE;
    public static final DeferredBlock<Block> DEEPSLATE_HEMPITE_ORE;
    public static final DeferredBlock<Block> NETHER_HEMPITE_ORE;
    public static final DeferredBlock<Block> END_STONE_HEMPITE_ORE;

    static {
        HEMPITE_BLOCK = registerBlock("hempite_block",
                () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_BLOCK)
                        .requiresCorrectToolForDrops()
                        .noOcclusion()
                        .isValidSpawn((blockState, blockGetter, blockPos, entityType) -> false)
                        .isSuffocating((blockState, blockGetter, blockPos) -> false)
                        .isRedstoneConductor((state, reader, pos) -> false)
                        .isViewBlocking((state, reader, pos) -> false))
        );
        HEMPITE_STAIRS = registerBlock("hempite_stairs",
                () -> new StairBlock(HEMPITE_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(HEMPITE_BLOCK.get()))
        );
        HEMPITE_SLAB = registerBlock("hempite_slab",
                () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(HEMPITE_BLOCK.get()))
        );
        HEMPITE_WALL = registerBlock("hempite_wall",
                () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(HEMPITE_BLOCK.get()))
        );
        HEMPITE_ORE = registerBlock("hempite_ore",
                () -> new DropExperienceBlock(
                        UniformInt.of(3, 7),
                        BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE))
        );
        DEEPSLATE_HEMPITE_ORE = registerBlock("deepslate_hempite_ore",
                () -> new DropExperienceBlock(
                        UniformInt.of(3, 7),
                        BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE))
        );
        NETHER_HEMPITE_ORE = registerBlock("nether_hempite_ore",
                () -> new DropExperienceBlock(
                        UniformInt.of(3, 7),
                        BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE))
        );
        END_STONE_HEMPITE_ORE = registerBlock("end_stone_hempite_ore",
                () -> new DropExperienceBlock(
                        UniformInt.of(3, 7),
                        BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE))
        );
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        CCItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
