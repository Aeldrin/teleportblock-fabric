package com.aeldrin.teleportblock;

import com.aeldrin.teleportblock.block.entity.TeleportBlockEntity;
import com.aeldrin.teleportblock.client.TeleportBlockConfigScreen;
import com.aeldrin.teleportblock.compat.journeymap.JourneyMapCompat;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.client.ConfigScreenFactoryRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;

// Клиентская точка входа (entrypoint "client" в fabric.mod.json).
public class TeleportBlockClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		// Экран настроек. Forge Config API Port сам передаёт его в Mod Menu: если Mod Menu
		// установлен, у мода в списке модов появляется кнопка настроек - как кнопка Config на NeoForge.
		ConfigScreenFactoryRegistry.INSTANCE.register(TeleportBlockMod.MODID, TeleportBlockConfigScreen::new);

		// Вейпоинты JourneyMap: подписываемся на клиентские хуки блока (см. TeleportBlockEntity,
		// "Клиентские хуки"). JourneyMapCompat загружается только если JourneyMap установлен.
		if (FabricLoader.getInstance().isModLoaded("journeymap")) {
			TeleportBlockEntity.clientDataUpdated = be -> JourneyMapCompat.updateWaypoint(
					be.getBlockPos(), be.getTarget(), be.getLinkColor(), be.getLinkName(), be.getLevel().dimension());
			TeleportBlockEntity.clientRemoved = be -> JourneyMapCompat.removeWaypoint(be.getBlockPos());
		}
	}
}
