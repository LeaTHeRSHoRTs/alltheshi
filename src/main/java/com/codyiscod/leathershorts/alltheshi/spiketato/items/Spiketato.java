package com.codyiscod.leathershorts.alltheshi.spiketato.items;

import com.codyiscod.leathershorts.alltheshi.spiketato.effects.BleedingEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import org.jetbrains.annotations.Nullable;

public sealed class Spiketato extends Item permits RottenSpiketato {
    public Spiketato(int bleedingSecs, Rarity rarity, @Nullable MobEffectInstance... effects) {
        super(new Item.Properties().food(
                createFoodProperties(bleedingSecs, effects)
            )
            .stacksTo(16)
            .rarity(rarity));
    }

    public Spiketato(int bleedingSecs, @Nullable MobEffectInstance effect2, Rarity rarity) {
        this(bleedingSecs, rarity, effect2);
    }

    private static FoodProperties createFoodProperties(
        int bleedingSecs,
        @Nullable MobEffectInstance[] effects
    ) {
        FoodProperties.Builder foodProps = new FoodProperties.Builder()
            .alwaysEat()
            .fast()
            .nutrition(2)
            .saturationMod(0.0f)
            .effect(BleedingEffect.create(bleedingSecs), 1);

        if (effects != null) {
            for (MobEffectInstance effect : effects) {
                foodProps.effect(() -> effect, 1);
            }
        }

        return foodProps.build();
    }
}
