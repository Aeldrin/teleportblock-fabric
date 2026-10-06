package com.aeldrin.teleportblock.client.mixin;

import com.aeldrin.teleportblock.block.entity.TeleportBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.DebugScreenOverlay;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

// Строки о телепорт-блоке в правой колонке экрана отладки (F3), когда игрок смотрит на блок.
// На NeoForge это делает событие CustomizeGuiOverlayEvent.DebugText; в Fabric API такого события
// нет, поэтому строки добавляются в конец ванильного getSystemInformation() - это и есть правая
// колонка F3 (список изменяемый, ванилла собирает его через Lists.newArrayList).
@Mixin(DebugScreenOverlay.class)
public abstract class DebugScreenOverlayMixin {

	@Inject(method = "getSystemInformation", at = @At("RETURN"))
	private void teleportblock$addTeleportBlockInfo(CallbackInfoReturnable<List<String>> cir) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.level == null) return;
		HitResult hit = mc.hitResult;
		if (!(hit instanceof BlockHitResult blockHit)) return;
		BlockPos pos = blockHit.getBlockPos();
		if (!(mc.level.getBlockEntity(pos) instanceof TeleportBlockEntity be)) return;

		List<String> right = cir.getReturnValue();
		right.add("");
		right.add("[TeleportBlock]");
		if (be.getTarget() != null) {
			BlockPos t = be.getTarget();
			right.add("Target: " + t.getX() + ", " + t.getY() + ", " + t.getZ());
			if (be.getTargetDimension() != null) {
				right.add("Dimension: " + be.getTargetDimension().location());
			}
		} else {
			right.add("Target: none");
		}
		if (be.getLinkName() != null) {
			right.add("Name: " + be.getLinkName());
		}
		if (be.hasLinkColor()) {
			right.add("Color: #" + String.format("%06X", be.getLinkColor()));
		}
		right.add("Uses: " + be.getTeleportCount());
	}
}
