package com.codyiscod.leathershorts.alltheshi.spiketato.items;

import com.codyiscod.leathershorts.alltheshi.spiketato.SpiketatoEffects;
import com.codyiscod.leathershorts.alltheshi.spiketato.SpiketatoEntityTypes;
import com.codyiscod.leathershorts.alltheshi.spiketato.projectiles.RottenSpiketatoProjectile;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.event.EntityRenderersEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Random;


public final class RottenSpiketato extends Spiketato {
    public RottenSpiketato() {
        super(
            5,
            Rarity.UNCOMMON,
            getMobEffects()
        );
    }

    private static MobEffectInstance[] getMobEffects() {
        Random seed = new Random();
        return new MobEffectInstance[]{
            new MobEffectInstance(
                SpiketatoEffects.DIE.get(),
                MobEffectInstance.INFINITE_DURATION,
                0,
                false,
                false,
                false
            ), new MobEffectInstance(
            MobEffects.CONFUSION,
            300, // 15 Seconds
            seed.nextInt(0, 2)
        ), new MobEffectInstance(
            MobEffects.HUNGER,
            seed.nextInt(600, 1200),
            seed.nextInt(0, 2)
        ), new MobEffectInstance(
            MobEffects.DIG_SLOWDOWN,
            seed.nextInt(600, 1200),
            seed.nextInt(0, 2)
        ), new MobEffectInstance(
            MobEffects.WEAKNESS,
            seed.nextInt(600, 1200),
            seed.nextInt(0, 2)
        ), new MobEffectInstance(
            MobEffects.LEVITATION,
            seed.nextInt(40, 100),
            seed.nextInt(0, 1)
        )
        };
    }

    // TODO: ADD LANDMINE CODE

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack spiketato = player.getItemInHand(hand);

        if (player.getFoodData().getFoodLevel() >= 20) {
            if (!level.isClientSide()) {
                RottenSpiketatoProjectile rottenSpiketato = new RottenSpiketatoProjectile(level, player);

                rottenSpiketato.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
                level.addFreshEntity(rottenSpiketato);
            }

            player.playSound(SoundEvents.SNOWBALL_THROW, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            player.awardStat(Stats.ITEM_USED.get(this));

            if (!player.getAbilities().instabuild) {
                spiketato.shrink(1);
            }

            return InteractionResultHolder.sidedSuccess(spiketato, level.isClientSide());
        }

        return super.use(level, player, hand);
    }

    /* Register a global event listener to register the thrown model correctly */
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(SpiketatoEntityTypes.ROTTEN_SPIKETATO_PROJECTILE.get(), ThrownItemRenderer::new);
    }
}
