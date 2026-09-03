package com.codyiscod.leathershorts.alltheshi.totemofdying.items;

import com.codyiscod.leathershorts.alltheshi.global.utils.DamageUtils;
import com.codyiscod.leathershorts.alltheshi.totemofdying.TotemOfDyingDamageTypes;
import net.minecraft.client.gui.screens.social.PlayerEntry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.Level;

public final class TotemOfDying extends Item {
    public TotemOfDying() {
        super(new Properties()
            .stacksTo(1)
            .fireResistant()
            .rarity(Rarity.EPIC)
        );
    }

    public void hurtPlayer(Level level, Player player, boolean once) {
        if (level.isClientSide()) return;

        ResourceKey<DamageType> damageTypeKey = once
            ? TotemOfDyingDamageTypes.TOTEM_HARM_ONCE
            : TotemOfDyingDamageTypes.TOTEM_HARM;

        float absorption = player.getAbsorptionAmount();
        float damage = once ? absorption + player.getHealth() + 1.0F : 1.0F;

        DamageSource damageSource = DamageUtils.getDamageSource(player.level(), damageTypeKey);
        player.invulnerableTime = 0;
        player.hurt(damageSource, damage);
    }
}
