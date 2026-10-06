package com.aeldrin.teleportblock.mixin;

import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.saveddata.maps.MapDecorationType;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

// Доступ к приватному ванильному MapItemSavedData.addDecoration - через него ставятся маркеры
// телепорт-блоков на картах (TeleportMapHandler). На NeoForge метод открыт его access transformer'ом,
// а в ванилле и на Fabric он private, поэтому вызывается через Mixin-аксессор.
@Mixin(MapItemSavedData.class)
public interface MapItemSavedDataAccessor {

	@Invoker("addDecoration")
	void teleportblock$addDecoration(Holder<MapDecorationType> type, @Nullable LevelAccessor level, String id,
									 double x, double z, double rotation, @Nullable Component name);
}
