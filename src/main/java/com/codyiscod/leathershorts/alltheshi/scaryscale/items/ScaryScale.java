package com.codyiscod.leathershorts.alltheshi.scaryscale.items;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public final class ScaryScale extends Item {
    private static final List<SoundEvent> CAVE_SOUNDS = new ArrayList<>();

    static {for (int i = 1; i <= 23; i++) {
            ResourceLocation id = ResourceLocation.fromNamespaceAndPath("alltheshi", "scaryscale.ambient.cave" + i);
            CAVE_SOUNDS.add(SoundEvent.createVariableRangeEvent(id));
    }}

    public ScaryScale() { super(new Item.Properties().rarity(Rarity.EPIC).fireResistant()); }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        if (!level.isClientSide()) {
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 7, 0, false, false, true));
            for (SoundEvent sound : CAVE_SOUNDS) {
                level.playSound(null, player.blockPosition(), sound, SoundSource.PLAYERS, 0.15F, 1.0F);
            }
        }
        return InteractionResultHolder.consume(player.getItemInHand(usedHand));
    }
}
