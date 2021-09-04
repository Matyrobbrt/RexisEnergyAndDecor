package com.rexiwastaken.read.core.init;

import com.rexiwastaken.read.RexisEnergyAndDecor;
import com.rexiwastaken.read.common.te.machines.FuelGeneratorTileEntity;
import com.rexiwastaken.read.common.te.machines.WashingMachineTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntityTypesInit {

	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPE = DeferredRegister
			.create(ForgeRegistries.TILE_ENTITIES, RexisEnergyAndDecor.MOD_ID);

	public static final RegistryObject<TileEntityType<FuelGeneratorTileEntity>> FUEL_GENERATOR_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
			.register("fuel_generator", () -> TileEntityType.Builder
					.of(FuelGeneratorTileEntity::new, BlockInit.FUEL_GENERATOR.get()).build(null));
	public static final RegistryObject<TileEntityType<WashingMachineTileEntity>> WASHING_MACHINE_TILE_ENTITY_TYPE = TILE_ENTITY_TYPE
			.register("washing_machine", () -> TileEntityType.Builder
					.of(WashingMachineTileEntity::new, BlockInit.WASHING_MACHINE.get()).build(null));

}
