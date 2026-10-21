package com.codyiscod.leathershorts.alltheshi.spiketato;

import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static com.codyiscod.leathershorts.alltheshi.AllTheShi.MOD_ID;

@Mod.EventBusSubscriber(
    modid = MOD_ID,
    bus = Mod.EventBusSubscriber.Bus.FORGE
)
public class SmeltEvents {
    @SubscribeEvent
    public static void onItemSmelted(PlayerEvent.ItemSmeltedEvent event) {
        // The item that was produced by the smelt
        var result = event.getSmelting();

        if (result.is(Items.COPPER_INGOT)) {
            // Your action here; this runs server-side
            event.getEntity().sendSystemMessage(
                net.minecraft.network.chat.Component.literal("Copper smelted!")
            );
        }
    }
}