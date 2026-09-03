package com.codyiscod.leathershorts.alltheshi.spiketato.projectiles;

import com.codyiscod.leathershorts.alltheshi.global.utils.DamageUtils;
import com.codyiscod.leathershorts.alltheshi.spiketato.SpiketatoDamageTypes;
import com.codyiscod.leathershorts.alltheshi.spiketato.SpiketatoEntityTypes;
import com.codyiscod.leathershorts.alltheshi.spiketato.SpiketatoItems;
import com.codyiscod.leathershorts.alltheshi.spiketato.SpiketatoParticles;
import com.codyiscod.leathershorts.alltheshi.spiketato.effects.BleedingEffect;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public final class RottenSpiketatoProjectile extends ThrowableItemProjectile {
    public RottenSpiketatoProjectile(EntityType<RottenSpiketatoProjectile> rottenSpiketatoProjectileEntityType, Level level) {
        super(rottenSpiketatoProjectileEntityType, level);
    }

    public RottenSpiketatoProjectile(@NotNull Level level, @NotNull Player player) {
        super(SpiketatoEntityTypes.ROTTEN_SPIKETATO_PROJECTILE.get(), player, level);
    }

    @Override
    protected @NotNull Item getDefaultItem() {
        return SpiketatoItems.ROTTEN_SPIKETATO.get();
    }

    @Override
    protected void onHit(@NotNull HitResult result) {
        super.onHit(result);
        Level level = this.level();
        if (!level.isClientSide && level instanceof ServerLevel serverLevel) {
            // Entity hit
            if (result.getType() == HitResult.Type.ENTITY && result instanceof EntityHitResult target) {
                Entity entity = target.getEntity();

                if (entity instanceof LivingEntity livingTarget) {
                    // Apply the bleeding status effect for 5 seconds (amplifier 0)
                    livingTarget.hurt(DamageUtils.getDamageSource(SpiketatoDamageTypes.SPIKED, target.getEntity(), livingTarget), 1);
                    livingTarget.addEffect(BleedingEffect.create(5).get());
                }
            }

            if (result.getType() == HitResult.Type.BLOCK) {
                level.explode(
                    this,
                    this.position().x(),
                    this.position().y(),
                    this.position().z(),
                    .5F,
                    Level.ExplosionInteraction.NONE
                );
            }

            serverLevel.sendParticles(
                new ItemParticleOption(SpiketatoParticles.ROTTEN_PARTICLE.get(), new ItemStack(SpiketatoItems.ROTTEN_SPIKETATO.get())),
                this.getX(), this.getY(), this.getZ(),
                30,
                0.2D, 0.2D, 0.2D,
                0.2D
            );

            this.discard();
        }
    }

    @Override
    protected void onHitEntity(@NotNull EntityHitResult result) {
        super.onHitEntity(result);
        if (result.getEntity() instanceof LivingEntity target) {
            Level level = this.level();

            if (level.isClientSide()) {

                Entity owner = this.getOwner();

                if (owner instanceof LivingEntity livingOwner) {
                    target.hurt(DamageUtils.getDamageSource(SpiketatoDamageTypes.SPIKED, this, livingOwner), 2.0F);
                } else {
                    target.hurt(this.damageSources().generic(), 2.0F);
                }
                level.explode(
                    this,
                    this.position().x(),
                    this.position().y(),
                    this.position().z(),
                    .5F,
                    Level.ExplosionInteraction.NONE
                );

                target.addEffect(BleedingEffect.create(5).get());

                this.discard();
            }
        }
    }

    // Required for Forge to sync entity tracking over the network
    @Override
    public @NotNull Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
