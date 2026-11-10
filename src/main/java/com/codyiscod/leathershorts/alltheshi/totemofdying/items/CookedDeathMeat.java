package com.codyiscod.leathershorts.alltheshi.totemofdying.items;

import com.codyiscod.leathershorts.alltheshi.global.utils.DamageUtils;
import com.codyiscod.leathershorts.alltheshi.totemofdying.TotemOfDyingDamageTypes;
import net.minecraft.advancements.Advancement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public final class CookedDeathMeat extends Item {
    private static final int INTERVAL = 15;
    private final SoundEvent mobSound;

    public CookedDeathMeat(SoundEvent mobSound) {
        super(new Item.Properties().food(new FoodProperties.Builder().meat().build()));
        this.mobSound = mobSound;
    }

    @Override
    public int getUseDuration(@NotNull ItemStack pStack) {
        return 64;
    }

    @Override
    public void onUseTick(Level level, @NotNull LivingEntity entity, @NotNull ItemStack stack, int remainingUseDuration) {
        int duration = getUseDuration(stack);
        int elapsed = duration - remainingUseDuration;

        if (!level.isClientSide &&
            elapsed % INTERVAL == 0 &&
            remainingUseDuration < duration &&
            entity instanceof Player player
        ) {
            level.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                this.mobSound,
                SoundSource.PLAYERS,
                1.0F,
                1.0F
            );
            player.hurt(DamageUtils.getDamageSource(level, TotemOfDyingDamageTypes.DEATH_MEAT_HARM), 2.0F);
            FoodData foodData = player.getFoodData();
            foodData.setFoodLevel(Math.max(0, foodData.getFoodLevel() - 1));
            foodData.setSaturation(Math.max(0.0F, foodData.getSaturationLevel() - 1.0F));
        }
    }

    @Override
    public @NotNull ItemStack finishUsingItem(@NotNull ItemStack pStack, Level pLevel, @NotNull LivingEntity entity) {
        if (!pLevel.isClientSide() && entity instanceof ServerPlayer player) {
            boolean wasAlive = player.isAlive();

            player.hurt(DamageUtils.getDamageSource(pLevel, TotemOfDyingDamageTypes.DEATH_MEAT_HARM), Float.MAX_VALUE);

            Advancement advancement = player.server.getAdvancements()
                .getAdvancement(ResourceLocation.fromNamespaceAndPath("alltheshi", "totem_of_dying/die_from_death_meat"));

            if (wasAlive && advancement != null && !player.isAlive()) {
                player.getAdvancements().award(advancement, "death");
            }
        }

        return super.finishUsingItem(pStack, pLevel, entity);
    }
}
