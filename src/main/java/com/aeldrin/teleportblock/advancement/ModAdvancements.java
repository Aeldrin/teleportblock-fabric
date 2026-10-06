package com.aeldrin.teleportblock.advancement;

import com.aeldrin.teleportblock.TeleportBlockMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

public class ModAdvancements {
	public static void init() {
		Registry.register(BuiltInRegistries.TRIGGER_TYPES,
				ResourceLocation.fromNamespaceAndPath(TeleportBlockMod.MODID, "teleport"), TeleportTrigger.INSTANCE);
		Registry.register(BuiltInRegistries.TRIGGER_TYPES,
				ResourceLocation.fromNamespaceAndPath(TeleportBlockMod.MODID, "link"), LinkTrigger.INSTANCE);
	}
}
