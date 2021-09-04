package com.rexiwastaken.read.core.init;

import com.rexiwastaken.read.RexisEnergyAndDecor;
import com.rexiwastaken.read.common.container.*;

import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ContainerTypesInit {
	
	public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister
			.create(ForgeRegistries.CONTAINERS, RexisEnergyAndDecor.MOD_ID);
	
	public static final RegistryObject<ContainerType<FuelGeneratorContainer>> FUEL_GENERATOR_CONTAINER_TYPE = CONTAINER_TYPES
			.register("fuel_generator", () -> IForgeContainerType.create(FuelGeneratorContainer::new));
	public static final RegistryObject<ContainerType<WashingMachineContainer>> WASHING_MACHINE_CONTAINER_TYPE = CONTAINER_TYPES
			.register("washing_machine", () -> IForgeContainerType.create(WashingMachineContainer::new));

}
