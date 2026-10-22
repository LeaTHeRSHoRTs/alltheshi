package com.codyiscod.leathershorts.alltheshi.spiketato.mixin;

import com.codyiscod.leathershorts.alltheshi.spiketato.SpiketatoItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractFurnaceBlockEntity.class)
public final class AbstractFurnaceBlockEntityMixin {
    @Inject(
        method = "serverTick(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/BlockState;Lnet/minecraft/world/level/block/entity/AbstractFurnaceBlockEntity;)V",
        at = @At("TAIL"),
        remap = false
    )
    private static void onFurnaceTickEnd(Level level, BlockPos pos, BlockState state, AbstractFurnaceBlockEntity blockEntity, CallbackInfo ci) {
        // 1. Ensure this only executes on the logical server side to prevent ghost explosions
        if (level != null && !level.isClientSide()) {

            // 2. Fetch the output slot (Slot 2 is universally the output slot for all vanilla furnace types)
            ItemStack outputSlot = blockEntity.getItem(2);

            // 3. Verify if a Baked Spiketato has completed cooking and arrived in the slot
            if (outputSlot.is(SpiketatoItems.BAKED_SPIKETATO.get())) {

                // 4. Detonate! Centered exactly at the coordinates of the furnace block
                // Arguments: SourceEntity (null), X, Y, Z, Radius (4.0F = TNT size), Causes Fire, Block Interaction
                level.explode(
                    null,
                    pos.getX() + 0.5D,
                    pos.getY() + 0.5D,
                    pos.getZ() + 0.5D,
                    4.0F,
                    true, // Set to true to ignite fire blocks around the crater
                    Level.ExplosionInteraction.BLOCK
                );

                // 5. Instantly clear out the furnace block to prevent duplicating item drops or ticking phantom code
                level.removeBlock(pos, false);
            }
        }
    }
}
