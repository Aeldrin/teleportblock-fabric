package com.aeldrin.teleportblock;

import com.aeldrin.teleportblock.block.TeleportBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

public class ModBlocks {
	public static final TeleportBlock TELEPORT_BLOCK = Registry.register(BuiltInRegistries.BLOCK,
			ResourceLocation.fromNamespaceAndPath(TeleportBlockMod.MODID, "teleport_block"),
			new TeleportBlock(BlockBehaviour.Properties.of()
					.strength(3.0f)
					.explosionResistance(1200f)
					.lightLevel(state -> 15)
					// Свой набор звуков: установка/разрушение - зарядка/разрядка якоря
					// возрождения, шаги/удар/падение - магнетит. См. ModSounds.
					.sound(ModSounds.TELEPORT_BLOCK_SOUNDS)
					// Ломается киркой (тег data/minecraft/tags/block/mineable/pickaxe.json)
					.requiresCorrectToolForDrops()
					.noOcclusion()
					.pushReaction(PushReaction.BLOCK)));

	public static void init() {}
}
