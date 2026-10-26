package com.codyiscod.leathershorts.alltheshi.totemofdying.items;

import com.codyiscod.leathershorts.alltheshi.global.utils.DamageUtils;
import com.codyiscod.leathershorts.alltheshi.totemofdying.TotemOfDyingDamageTypes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public final class CookedDeathMeat extends Item {
    public CookedDeathMeat() {
        super(new Item.Properties().food(new FoodProperties.Builder().meat().build()));
    }

    @Override
    public int getUseDuration(@NotNull ItemStack stack) {
        return Integer.MAX_VALUE;
    }

    @Override
    public void onUseTick(Level level, @NotNull LivingEntity entity, @NotNull ItemStack stack, int remainingUseDuration) {
        int duration = getUseDuration(stack);
        int interval = Math.max(1, duration / 3);

        if (!level.isClientSide &&
            remainingUseDuration % interval == 0 &&
            remainingUseDuration < duration &&
            entity instanceof Player player
        ) {
            player.hurt(DamageUtils.getDamageSource(level, TotemOfDyingDamageTypes.DEATH_MEAT_HARM), 2.0F);
            FoodData foodData = player.getFoodData();
            foodData.setFoodLevel(Math.max(0, foodData.getFoodLevel() - 1));
            foodData.setSaturation(Math.max(0.0F, foodData.getSaturationLevel() - 1.0F));
        }
    }
}
