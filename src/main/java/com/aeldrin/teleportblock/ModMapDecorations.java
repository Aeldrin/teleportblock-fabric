package com.aeldrin.teleportblock;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;

public class ModMapDecorations {
	public static final Holder<MapDecorationType> TELEPORT_BLOCK = Registry.registerForHolder(
			BuiltInRegistries.MAP_DECORATION_TYPE,
			ResourceLocation.fromNamespaceAndPath(TeleportBlockMod.MODID, "teleport_block"),
			new MapDecorationType(
					ResourceLocation.fromNamespaceAndPath(TeleportBlockMod.MODID, "teleport_block"),
					true,     // showOnItemFrame
					0x7B41E0, // mapColor (purple tint for the pixel on zoomed-out maps)
					false,    // explorationMapElement
					false     // trackCount
			));

	public static void init() {}
}
