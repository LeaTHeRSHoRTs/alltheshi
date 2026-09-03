package com.codyiscod.leathershorts.alltheshi.dfiamonds;

import com.codyiscod.leathershorts.alltheshi.dfiamonds.items.Dfiamond;
import com.codyiscod.leathershorts.alltheshi.global.utils.ItemUtils;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class DfiamondItems {
    public static final DeferredRegister<Item> ITEMS = ItemUtils.registerItemBus();
    public static final RegistryObject<Item> DFIAMOND = ItemUtils.registerItem(ITEMS, "dfiamond", Dfiamond::new);
    public static final RegistryObject<Item> NOTHERITE = ItemUtils.registerItem(ITEMS, "notherite");
    public static final RegistryObject<Item> IYAWN = ItemUtils.registerItem(ITEMS, "iyawn");
    public static final RegistryObject<Item> GOTH = ItemUtils.registerItem(ITEMS, "goth");
    public static final RegistryObject<Item> RED_STONE = ItemUtils.registerItem(ITEMS, "red_stone");
    public static final RegistryObject<Item> LAPIS_LOOZALI = ItemUtils.registerItem(ITEMS, "lapis_loozali");
    public static final RegistryObject<Item> COLD = ItemUtils.registerItem(ITEMS, "cold");
    public static final RegistryObject<Item> CHARCOLD = ItemUtils.registerItem(ITEMS, "charcold");
    public static final RegistryObject<Item> MENERALD = ItemUtils.registerItem(ITEMS, "menerald");
}
