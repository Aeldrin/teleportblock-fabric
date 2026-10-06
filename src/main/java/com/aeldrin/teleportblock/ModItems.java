package com.aeldrin.teleportblock;

import com.aeldrin.teleportblock.item.TeleportBlockItem;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class ModItems {
	public static final TeleportBlockItem TELEPORT_BLOCK_ITEM = Registry.register(BuiltInRegistries.ITEM,
			ResourceLocation.fromNamespaceAndPath(TeleportBlockMod.MODID, "teleport_block"),
			new TeleportBlockItem(ModBlocks.TELEPORT_BLOCK,
					new Item.Properties()
							.component(DataComponents.FIRE_RESISTANT, Unit.INSTANCE)
							.rarity(Rarity.EPIC)));

	public static void init() {}
}
