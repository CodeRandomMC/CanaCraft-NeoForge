package com.coderandom.canna_craft.blocks;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.coderandom.canna_craft.CannaCraft.MODID;

public class CCBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    public static DeferredBlock<Block> HEMPITE_ORE =
            BLOCKS.registerSimpleBlock("hempite_ore", BlockBehaviour.Properties.ofFullCopy(Blocks.EMERALD_ORE));

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
