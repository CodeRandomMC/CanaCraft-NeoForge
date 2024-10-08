package com.coderandom.canna_craft.block.entity;

import com.coderandom.canna_craft.block.CCBlocks;
import com.coderandom.canna_craft.block.entity.custom.PedestalBlockEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.coderandom.canna_craft.CannaCraft.MODID;

public class CCBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(BuiltInRegistries.BLOCK_ENTITY_TYPE, MODID);

    public static final Supplier<BlockEntityType<PedestalBlockEntity>> PEDESTAL_BE;

    static {
        PEDESTAL_BE = BLOCK_ENTITIES.register("pedestal_be", () -> BlockEntityType.Builder.of(
                PedestalBlockEntity::new,
                CCBlocks.PEDESTAL.get()
                ).build(null));
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
