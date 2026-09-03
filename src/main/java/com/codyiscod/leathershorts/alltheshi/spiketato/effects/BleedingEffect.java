package com.codyiscod.leathershorts.alltheshi.spiketato.effects;

import com.codyiscod.leathershorts.alltheshi.spiketato.SpiketatoDamageTypes;
import com.codyiscod.leathershorts.alltheshi.spiketato.SpiketatoEffects;
import com.codyiscod.leathershorts.alltheshi.global.utils.DamageUtils;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public final class BleedingEffect extends MobEffect {
    private static final String CHANCE_TAG = "bleed_chance";

    public BleedingEffect() {
        super(MobEffectCategory.HARMFUL, 0xff0000);
    }

    public static Supplier<MobEffectInstance> create(int secs) {
        return () -> new MobEffectInstance(
            SpiketatoEffects.BLEEDING.get(),
            secs * 20,
            0,
            false,
            false,
            true
        );
    }

    @Override
    public void addAttributeModifiers(@NotNull LivingEntity entity, @NotNull AttributeMap pAttributeMap, int pAmplifier) {
        if (!entity.level().isClientSide()) {
            if (!entity.getPersistentData().contains(CHANCE_TAG)) {
                entity.getPersistentData().putInt(CHANCE_TAG, 0);
            }

            entity.hurt(DamageUtils.getDamageSource(entity.level(), SpiketatoDamageTypes.BLEEDING), 1.0F);
        }
        super.addAttributeModifiers(entity, pAttributeMap, pAmplifier);
    }

    @Override
    public void removeAttributeModifiers(@NotNull LivingEntity pLivingEntity, @NotNull AttributeMap pAttributeMap, int pAmplifier) {
        if (!pLivingEntity.level().isClientSide()) {
            if (!pLivingEntity.hasEffect(SpiketatoEffects.BLEEDING.get())) {
                pLivingEntity.getPersistentData().remove(CHANCE_TAG);
            }
        }
        super.removeAttributeModifiers(pLivingEntity, pAttributeMap, pAmplifier);
    }

    @Override
    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public void applyEffectTick(LivingEntity entity, int amplifier) {
        if (entity.level().isClientSide()) return;

        int currentBonusChance = entity.getPersistentData().getInt(CHANCE_TAG);
        int totalChance = 20 + currentBonusChance;

        if (entity.getRandom().nextInt(100) < totalChance) {
            entity.getPersistentData().putInt(CHANCE_TAG, 0);
            entity.hurt(DamageUtils.getDamageSource(entity.level(), SpiketatoDamageTypes.BLEEDING), amplifier + 1);
        } else {
            entity.getPersistentData().putInt(CHANCE_TAG, currentBonusChance + 20);
        }
    }
}
