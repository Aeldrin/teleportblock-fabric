package com.aeldrin.teleportblock.mixin;

import com.aeldrin.teleportblock.block.TeleportBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.projectile.WitherSkull;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

// Визер может сломать телепорт-блок (сопротивление взрыву у блока 1200 - выше обсидиана, обычные
// взрывы его не берут). На NeoForge это делает событие ExplosionEvent.Detonate; в Fabric API такого
// события нет, поэтому то же самое - в конце ванильного Explosion.explode(): это ровно тот момент,
// когда NeoForge вызывает своё событие - список блоков уже посчитан, но ещё не разрушен.
// Блоки из списка toBlow ломаются с обычной добычей взрыва, поэтому предмет выпадает
// (таблица добычи блока - условие survives_explosion, у черепа визера радиус 1 = выпадает всегда).
@Mixin(Explosion.class)
public abstract class ExplosionMixin {

	@Shadow
	@Final
	private Level level;

	@Shadow
	public abstract List<BlockPos> getToBlow();

	@Inject(method = "explode", at = @At("TAIL"))
	private void teleportblock$witherBreaksTeleportBlocks(CallbackInfo ci) {
		Explosion self = (Explosion) (Object) this;
		if (!(self.getDirectSourceEntity() instanceof WitherSkull)) return;

		BlockPos center = BlockPos.containing(self.center());
		int radius = 2;
		List<BlockPos> toBlow = getToBlow();
		for (BlockPos pos : BlockPos.betweenClosed(
				center.offset(-radius, -radius, -radius),
				center.offset(radius, radius, radius))) {
			if (level.getBlockState(pos).getBlock() instanceof TeleportBlock && !toBlow.contains(pos)) {
				toBlow.add(pos.immutable());
			}
		}
	}
}
