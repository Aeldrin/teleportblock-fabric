package com.aeldrin.teleportblock;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.Stats;

public class ModStats {
	public static final ResourceLocation TELEPORTATIONS = ResourceLocation.fromNamespaceAndPath(
			TeleportBlockMod.MODID, "teleportations");

	// Регистрация собственной статистики: id в реестре CUSTOM_STAT + создание самой статистики
	// с форматом "просто число"
	public static void init() {
		Registry.register(BuiltInRegistries.CUSTOM_STAT, TELEPORTATIONS, TELEPORTATIONS);
		Stats.CUSTOM.get(TELEPORTATIONS, StatFormatter.DEFAULT);
	}
}
