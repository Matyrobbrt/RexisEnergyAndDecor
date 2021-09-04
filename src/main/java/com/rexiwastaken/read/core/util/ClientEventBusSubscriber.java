package com.rexiwastaken.read.core.util;

import com.rexiwastaken.read.RexisEnergyAndDecor;
import com.rexiwastaken.read.client.screen.FuelGeneratorScreen;
import com.rexiwastaken.read.client.screen.WashingMachineScreen;
import com.rexiwastaken.read.core.init.ContainerTypesInit;

import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = RexisEnergyAndDecor.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientEventBusSubscriber {
	
	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		ScreenManager.register(ContainerTypesInit.FUEL_GENERATOR_CONTAINER_TYPE.get(), FuelGeneratorScreen::new);
		ScreenManager.register(ContainerTypesInit.WASHING_MACHINE_CONTAINER_TYPE.get(), WashingMachineScreen::new);
	}

}
