package com.codyiscod.leathershorts.alltheshi;

import com.codyiscod.leathershorts.alltheshi.calibratedmilk.CalibratedMilkEntrypoint;
import com.codyiscod.leathershorts.alltheshi.dfiamonds.DfiamondEntrypoint;
import com.codyiscod.leathershorts.alltheshi.eggtoaster.EggToasterEntrypoint;
import com.codyiscod.leathershorts.alltheshi.magicstick.MagicStickEntrypoint;
import com.codyiscod.leathershorts.alltheshi.scaryscale.ScaryScaleEntrypoint;
import com.codyiscod.leathershorts.alltheshi.shaddle.ShaddleEntrypoint;
import com.codyiscod.leathershorts.alltheshi.spiketato.SpiketatoEntrypoint;
import com.codyiscod.leathershorts.alltheshi.totemofdying.TotemOfDyingEntrypoint;
import com.mojang.logging.LogUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static com.codyiscod.leathershorts.alltheshi.spiketato.SpiketatoItems.SPIKETATO;

@Mod(AllTheShi.MOD_ID)
public final class AllTheShi {
    public static final String MOD_ID = "alltheshi";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final RegistryObject<CreativeModeTab> CREATIVE_TAB = TABS.register("alltheshi_tab",
        () -> CreativeModeTab.builder()
        .title(Component.translatable("itemGroup.alltheshi_tab"))
        .icon(() -> new ItemStack(SPIKETATO.get()))
        .build()
    );

    private final IEventBus eventBus;

    public AllTheShi(FMLJavaModLoadingContext context) {
        this.eventBus = context.getModEventBus();

        // Production
        this.setupModule(SpiketatoEntrypoint::new, true);
        this.setupModule(TotemOfDyingEntrypoint::new, true);
        this.setupModule(DfiamondEntrypoint::new, true);

        // Beta / Unstable
        this.setupModule(ScaryScaleEntrypoint::new, false);
        this.setupModule(MagicStickEntrypoint::new, false);
        this.setupModule(ShaddleEntrypoint::new, false);
        this.setupModule(EggToasterEntrypoint::new, false);
        this.setupModule(CalibratedMilkEntrypoint::new, false);

        TABS.register(this.eventBus);
        this.eventBus.addListener(this::addCreativeContents);

        MinecraftForge.EVENT_BUS.register(this);
    }

    /** Sets up a module within the alltheshi project */
    private void setupModule(Function<IEventBus, ModuleEntrypoint> module, boolean activate) {
        if (activate) module.apply(this.eventBus);
    }

    /** Sets up the creative tab for the items in alltheshi */
    private void addCreativeContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CREATIVE_TAB.getKey()) {
            List<Item> items = ForgeRegistries.ITEMS.getEntries().stream()
                .filter(e -> e.getKey().location().getNamespace().equals(MOD_ID))
                .map(Map.Entry::getValue)
                .toList();

            // 2. Log the total count accurately
            LOGGER.info("Added {} items to the {} creative tab", items.size(), MOD_ID);

            // 3. Push the counted items into the tab output
            items.forEach(event::accept);

            LOGGER.info("Registered {} items for mod {}", items.size(), MOD_ID);
        }
    }
}