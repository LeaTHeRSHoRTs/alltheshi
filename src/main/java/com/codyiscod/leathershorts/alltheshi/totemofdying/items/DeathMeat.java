package com.codyiscod.leathershorts.alltheshi.totemofdying.items;

import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public abstract class DeathMeat extends Item {
    public DeathMeat() {
        super(new Item.Properties()
            .food(new FoodProperties.Builder()
                .meat()
                .nutrition(2)
                .saturationMod(0)
                .build()
            )
        );
    }

    abstract SoundEvent getSound();

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand usedHand) {
        player.addEffect(new MobEffectInstance(
            MobEffects.BLINDNESS,
            100,
            2
        ));
        level.playSound(player, player.getOnPos(), this.getSound(), SoundSource.AMBIENT);
        return super.use(level, player, usedHand);
    }
}
