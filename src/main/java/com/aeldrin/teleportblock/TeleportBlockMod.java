package com.aeldrin.teleportblock;

import com.aeldrin.teleportblock.advancement.ModAdvancements;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.neoforged.fml.config.ModConfig.Type;

// Точка входа мода на Fabric (entrypoint "main" в fabric.mod.json).
// Порядок регистрации важен: звуки -> блок -> предмет -> block entity. На Fabric объекты
// регистрируются сразу при загрузке классов, и каждый следующий опирается на предыдущий.
public class TeleportBlockMod implements ModInitializer {
	public static final String MODID = "teleportblock";

	@Override
	public void onInitialize() {
		// Настройки: у каждой среды ровно один файл (подробности в ModConfig).
		// Выделенный сервер - серверные настройки; клиент - настройки одиночной игры.
		// Конфиг-система NeoForge на Fabric - через Forge Config API Port (встроен в мод),
		// поэтому файлы .toml и поведение настроек такие же, как в версии для NeoForge.
		// Экран настроек регистрируется в TeleportBlockClient (клиентский код).
		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
			NeoForgeConfigRegistry.INSTANCE.register(MODID, Type.COMMON, ModConfig.SERVER.spec, "teleportblock-server.toml");
		} else {
			NeoForgeConfigRegistry.INSTANCE.register(MODID, Type.COMMON, ModConfig.SINGLEPLAYER.spec, "teleportblock-singleplayer.toml");
		}

		ModSounds.init();
		ModBlocks.init();
		ModItems.init();
		ModBlockEntities.init();
		ModAdvancements.init();
		ModMapDecorations.init();
		ModStats.init();

		ModEventHandlers.register();
	}
}
