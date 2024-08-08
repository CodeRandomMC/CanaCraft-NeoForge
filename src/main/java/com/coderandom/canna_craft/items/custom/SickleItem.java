package com.coderandom.canna_craft.items.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class SickleItem extends DiggerItem {
    public SickleItem(Tier tier) {
        super(tier, BlockTags.MINEABLE_WITH_HOE, new Properties());
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (!level.isClientSide()) {
            BlockState blockState = level.getBlockState(context.getClickedPos());
            if (blockState.getBlock() instanceof CropBlock cropBlock) {
                level.destroyBlock(context.getClickedPos(), true, context.getPlayer());
                if (cropBlock.getAge(blockState) == cropBlock.getMaxAge()) {
                    level.setBlock(context.getClickedPos(), cropBlock.defaultBlockState(), 3);
                }
                context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), ((ServerPlayer) context.getPlayer()),
                        item -> Objects.requireNonNull(context.getPlayer()).onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                return InteractionResult.SUCCESS;
            }
        }
        return super.useOn(context);
    }
}
