package com.aeldrin.teleportblock;

import com.aeldrin.teleportblock.block.TeleportBlock;
import com.aeldrin.teleportblock.map.TeleportMapHandler;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.CreativeModeTabs;

// Подписка на события Fabric API. Взрыв черепа визера, который на NeoForge ловится событием,
// здесь обрабатывается миксином - см. mixin/ExplosionMixin.
public class ModEventHandlers {

	public static void register() {
		// Блок во вкладке "Функциональные блоки" творческого режима
		ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.FUNCTIONAL_BLOCKS)
				.register(entries -> entries.accept(ModItems.TELEPORT_BLOCK_ITEM));

		// Чистим PENDING_LINKS/COOLDOWNS при выходе игрока, чтобы карты не росли
		// бесконечно на серверах с большим оборотом игроков (см. TeleportBlock.clearPlayerData)
		ServerPlayConnectionEvents.DISCONNECT.register((handler, server) ->
				TeleportBlock.clearPlayerData(handler.getPlayer().getUUID()));

		// Тик каждого игрока на сервере (на NeoForge - PlayerTickEvent.Post):
		//  - снимает отметку "игрок стоит на блоке прибытия", когда он с него сошёл
		//    (TeleportBlock.ARRIVALS / tickArrival - защита от пинг-понга в пассивном режиме);
		//  - белая подсветка первого блока, пока игрок выбирает пару для линковки;
		//  - маркеры на картах в руках (TeleportMapHandler).
		// Для игроков без отметок и без карт в руках - пара lookup'ов в HashMap.
		ServerTickEvents.END_SERVER_TICK.register(server -> {
			for (ServerPlayer player : server.getPlayerList().getPlayers()) {
				TeleportBlock.tickArrival(player);
				TeleportBlock.tickPendingHighlight(player);
				TeleportMapHandler.tickPlayer(player);
			}
		});
	}
}
