package com.codyiscod.leathershorts.alltheshi.shaddle.items;

import com.codyiscod.leathershorts.alltheshi.shaddle.ShaddleItems;
import com.codyiscod.leathershorts.alltheshi.shaddle.ShaddleKeybinds;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SaddleItem;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public final class Shaddle extends SaddleItem {
    public Shaddle() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public @NotNull ItemStack getDefaultInstance() {
        ItemStack shaddleStack = super.getDefaultInstance();
        shaddleStack.getOrCreateTag().putInt("uses", new Random().nextInt(5, 50));
        return shaddleStack;
    }

    private static void shad(ItemStack shaddle) {

    }

    private static class MountHooks {
        @SubscribeEvent
        public static void onPlayerRideTick(TickEvent.PlayerTickEvent e) {
            if (e.player.level().isClientSide) return;

            Player player = e.player;
            if (!(player.getVehicle() instanceof AbstractHorse horse)) return;

            ItemStack saddle = horse.getMainHandItem();
            if (saddle.isEmpty() || saddle.getItem() != ShaddleItems.SHADDLE.get()) return;

            while (ShaddleKeybinds.ACTIVATE.consumeClick()) {

            }
        }
    }
}
