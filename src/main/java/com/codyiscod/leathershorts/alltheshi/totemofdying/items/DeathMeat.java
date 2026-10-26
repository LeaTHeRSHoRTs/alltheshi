package com.codyiscod.leathershorts.alltheshi.totemofdying.items;

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

    public DeathMeat(int hunger, float saturation) {
        super(new Item.Properties().food(new FoodProperties.Builder().meat().build()));
        this.saturationReduced = saturation;
        this.hungerReduced = hunger;
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack stack, @NotNull Level level, @NotNull LivingEntity entity) {
        ItemStack result = super.finishUsingItem(stack, level, entity);

        if (entity instanceof Player player && !level.isClientSide) {
            player.getFoodData().setFoodLevel(Math.max(0, player.getFoodData().getFoodLevel() - this.hungerReduced));
            player.getFoodData().setSaturation(Math.max(0.0F, player.getFoodData().getSaturationLevel() - this.saturationReduced));
        }

        return result;
    }
}
