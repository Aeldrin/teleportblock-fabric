package com.aeldrin.teleportblock;

import com.aeldrin.teleportblock.block.entity.TeleportBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.entity.BlockEntityType;

// Fabric 1.21.1: тип block entity создаётся через FabricBlockEntityTypeBuilder из Fabric API -
// ванильный BlockEntityType.Builder.of принимает приватный интерфейс BlockEntitySupplier,
// который открыт только на NeoForge.
public class ModBlockEntities {
	public static final BlockEntityType<TeleportBlockEntity> TELEPORT_BLOCK_ENTITY = Registry.register(
			BuiltInRegistries.BLOCK_ENTITY_TYPE,
			ResourceLocation.fromNamespaceAndPath(TeleportBlockMod.MODID, "teleport_block_entity"),
			FabricBlockEntityTypeBuilder.create(TeleportBlockEntity::new, ModBlocks.TELEPORT_BLOCK).build());

	public static void init() {}
}
