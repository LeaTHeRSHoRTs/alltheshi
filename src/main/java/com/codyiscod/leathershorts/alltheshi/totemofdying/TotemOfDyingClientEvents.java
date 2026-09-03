package com.codyiscod.leathershorts.alltheshi.totemofdying;

import com.codyiscod.leathershorts.alltheshi.totemofdying.items.TotemOfDying;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.codyiscod.leathershorts.alltheshi.AllTheShi.MOD_ID;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE, modid = MOD_ID)
public final class TotemOfDyingClientEvents {
    @SubscribeEvent
    public static void onItemEnterInventory(PlayerEvent.ItemPickupEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        if (event.getStack().getItem() instanceof TotemOfDying totem) {
            if (player.getOffhandItem() == event.getStack()) {
                totem.hurtPlayer(event.getEntity().level(), player, true);
            } else if (player.getMainHandItem() == event.getStack()) {
                totem.hurtPlayer(event.getEntity().level(), player, true);
            }
        }
    }

    @SubscribeEvent
    public static void onInventoryChange(LivingEquipmentChangeEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;

        EquipmentSlot slot = event.getSlot();

        if (slot == EquipmentSlot.MAINHAND || slot == EquipmentSlot.OFFHAND) {
            if (event.getTo().getItem() instanceof TotemOfDying totem) {
                totem.hurtPlayer(player.level(), player, true);
            }
        }
    }


    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END || !(event.player instanceof ServerPlayer player)) return;
        if (player.isCreative() || !player.isAlive()) return;

        // Process every 10 ticks (0.5 seconds) to minimize server thread impact
        if (player.tickCount % 10 == 0) {
            AbstractContainerMenu menu = player.containerMenu;
            ItemStack cursorItem = menu.getCarried();
            if (!cursorItem.isEmpty() && cursorItem.getItem() instanceof TotemOfDying totem) {
                totem.hurtPlayer(player.level(), player, false);
            }
        }
    }
}
