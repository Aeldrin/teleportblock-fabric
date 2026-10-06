package com.aeldrin.teleportblock.compat.sable;

import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

// На NeoForge этот класс проецирует координаты блоков с кораблей Sable / Create: Aeronautics
// в мировые. На Fabric этих модов нет (они выходят только под NeoForge), поэтому блок всегда
// стоит в обычном мире и проецировать нечего: методы возвращают координаты как есть.
// Класс оставлен с тем же API, чтобы логика блока (телепорт, расстояния, частицы, маркеры)
// была одинаковой на обоих загрузчиках и переносилась между версиями без правок.
public class SableCompat {

	public static Vec3 toGlobalPos(Level level, Vec3 pos) {
		return pos;
	}

	public static double distanceSqr(Level level, Vec3 a, Vec3 b) {
		return a.distanceToSqr(b);
	}
}
