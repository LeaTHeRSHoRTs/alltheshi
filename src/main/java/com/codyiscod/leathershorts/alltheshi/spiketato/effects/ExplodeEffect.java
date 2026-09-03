package com.codyiscod.leathershorts.alltheshi.spiketato.effects;

import com.codyiscod.leathershorts.alltheshi.spiketato.SpiketatoDamageTypes;
import com.codyiscod.leathershorts.alltheshi.spiketato.SpiketatoEffects;
import com.codyiscod.leathershorts.alltheshi.global.utils.DamageUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public final class ExplodeEffect extends MobEffect {
    public ExplodeEffect() {
        super(MobEffectCategory.HARMFUL, 0xFF4500);
    }

    @Override public boolean isDurationEffectTick(int duration, int amplifier) {
        return true;
    }

    // Hides the effect from the player completely
    @Override
    public void initializeClient(java.util.function.Consumer<net.minecraftforge.client.extensions.common.IClientMobEffectExtensions> consumer) {
        consumer.accept(new net.minecraftforge.client.extensions.common.IClientMobEffectExtensions() {
            @Override public boolean isVisibleInInventory(MobEffectInstance instance) { return false; }
            @Override public boolean isVisibleInGui(MobEffectInstance instance) { return false; }
        });
    }

    // The damage hook
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.FORGE)
    public static class EffectEvents {
        @SubscribeEvent
        public static void onLivingHurt(LivingHurtEvent event) {
            LivingEntity entity = event.getEntity();
            DamageSource source = event.getSource();

            // Ignore bleeding and the actual effect damage
            if (
                source.is(SpiketatoDamageTypes.BLEEDING) ||
                source.is(SpiketatoDamageTypes.EXPLODE_DIE) ||
                source.is(DamageTypes.MAGIC) ||
                source.is(DamageTypes.WITHER)
            ) return;

            if (entity instanceof Player player) {
                if (
                    player.isDeadOrDying() ||
                    player.getHealth() <= 0 ||
                    player.level().isClientSide
                ) return;

                MobEffectInstance instance = player.getEffect(SpiketatoEffects.DIE.get());

                if (instance != null && instance.getEffect() instanceof ExplodeEffect) {
                    event.setCanceled(true);

                    // Remove the effect because it has served its purpose
                    ExplodeEffect effect = SpiketatoEffects.DIE.get();
                    player.removeEffect(effect);

                    double x = player.getX();
                    double y = player.getY() + 0.5;
                    double z = player.getZ();

                    // Play your explosion visual effects
                    if (player.level() instanceof ServerLevel serverLevel) {
                        serverLevel.sendParticles(ParticleTypes.EXPLOSION_EMITTER, x, y, z, 1, 0.0, 0.0, 0.0, 0.0);
                    }
                    player.level().playSound(null, x, y, z, SoundEvents.GENERIC_EXPLODE, SoundSource.PLAYERS, 1.0F, 1.0F);

                    // Run your soft knockback loop for nearby entities
                    double knockbackRadius = 3.0;
                    AABB blastZone = new AABB(x - knockbackRadius, y - knockbackRadius, z - knockbackRadius, x + knockbackRadius, y + knockbackRadius, z + knockbackRadius);
                    List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, blastZone);

                    for (LivingEntity target : entities) {
                        if (target == player || target instanceof Player) continue;

                        double dx = target.getX() - x;
                        double dy = target.getEyeY() - y;
                        double dz = target.getZ() - z;
                        double distanceSq = dx * dx + dy * dy + dz * dz;

                        if (distanceSq > 0.0) {
                            double distance = Math.sqrt(distanceSq);
                            double force = (1.0 - (distance / knockbackRadius)) * 0.4;
                            target.push((dx / distance) * force, 0.3 * force, (dz / distance) * force);
                            target.hurtMarked = true;
                        }
                    }

                    float damage = 20.0F;

                    DamageSource customSource = DamageUtils.getDamageSource(player.level(), SpiketatoDamageTypes.EXPLODE_DIE);

                    // Calculate the resistance effects
                    @Nullable MobEffectInstance resistance = player.getEffect(MobEffects.DAMAGE_RESISTANCE);
                    float absorption = player.getAbsorptionAmount();

                    // Handle the resistance first if it is applied
                    if (resistance != null) {
                        int amplifier = resistance.getAmplifier();
                        float reduction = Math.min((amplifier + 1) * 0.20F, 0.8F);
                        damage *= (1.0F - reduction);
                    }

                    // If the player has absorption
                    if (absorption > 0) {
                        // If the absorption level is greater or equal to the damage taken
                        if (absorption >= damage) {
                            player.setAbsorptionAmount(absorption - damage);
                            damage = 0.0F;
                        } else { // The player did not have enough absorption to counter the damage completely
                            damage -= absorption;
                            player.setAbsorptionAmount(0.0F);
                        }
                    }

                    if (damage > 0.0F) {
                        player.getCombatTracker().recordDamage(customSource, damage);
                        player.setHealth(player.getHealth() - damage);
                    }
                }
            }
        }
    }
}
