package com.coderandom.canna_craft.items.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
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
            BlockPos centerPos = context.getClickedPos();
            for (int dx = -1; dx <= 1; dx++) {
                for (int dz = -1; dz <= 1; dz++) {
                    BlockPos pos = centerPos.offset(dx, 0, dz);
                    BlockState blockState = level.getBlockState(pos);
                    if (blockState.getBlock() instanceof CropBlock cropBlock) {
                        level.destroyBlock(pos, true, context.getPlayer());
                        if (cropBlock.getAge(blockState) == cropBlock.getMaxAge()) {
                            level.setBlock(pos, cropBlock.defaultBlockState(), 3);
                        }
                        context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), ((ServerPlayer) context.getPlayer()),
                                item -> Objects.requireNonNull(context.getPlayer()).onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
                    }
                }
            }
            return InteractionResult.SUCCESS;
        }
        return super.useOn(context);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        if (!level.isClientSide()) {
            stack.hurtAndBreak(1, ((ServerLevel) level), miningEntity,
                    item -> Objects.requireNonNull(miningEntity).onEquippedItemBroken(item, EquipmentSlot.MAINHAND));
        }
        return super.mineBlock(stack, level, state, pos, miningEntity);
    }
}
