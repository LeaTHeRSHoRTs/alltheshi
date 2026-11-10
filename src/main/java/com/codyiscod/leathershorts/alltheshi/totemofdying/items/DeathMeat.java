package com.codyiscod.leathershorts.alltheshi.totemofdying.items;

import com.codyiscod.leathershorts.alltheshi.global.utils.DamageUtils;
import com.codyiscod.leathershorts.alltheshi.totemofdying.TotemOfDyingDamageTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public final class DeathMeat extends Item {
    private final int hungerReduced;
    private final float saturationReduced;
    private final SoundEvent mobSound;

    public DeathMeat(int hunger, float saturation, SoundEvent mobSound) {
        super(new Item.Properties().food(new FoodProperties.Builder().meat().build()));
        this.saturationReduced = saturation;
        this.hungerReduced = hunger;
        this.mobSound = mobSound;
    }

    @Override
    public void onUseTick(@NotNull Level pLevel, @NotNull LivingEntity pLivingEntity, @NotNull ItemStack pStack, int pRemainingUseDuration) {
        if (!pLevel.isClientSide &&
            pRemainingUseDuration % 10 == 0 &&
            pLivingEntity instanceof Player player
        ) {
            pLevel.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                this.mobSound,
                SoundSource.PLAYERS,
                1.0F,
                1.0F
            );
        }

        super.onUseTick(pLevel, pLivingEntity, pStack, pRemainingUseDuration);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (entity instanceof Player player && !level.isClientSide) {
            player.getFoodData().setFoodLevel(Math.max(0, player.getFoodData().getFoodLevel() - this.hungerReduced));
            player.getFoodData().setSaturation(Math.max(0.0F, player.getFoodData().getSaturationLevel() - this.saturationReduced));
            player.hurt(DamageUtils.getDamageSource(level, TotemOfDyingDamageTypes.DEATH_MEAT_HARM), this.hungerReduced);
        }

        return result;
    }
}
