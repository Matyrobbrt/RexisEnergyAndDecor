package com.rexiwastaken.read.core.init;

import com.rexiwastaken.read.RexisEnergyAndDecor;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundInit {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS,
			RexisEnergyAndDecor.MOD_ID);

	public static final RegistryObject<SoundEvent> WASHING_MACHINE_SOUND = SOUNDS.register("washine_machine_sounds",
			() -> new SoundEvent(new ResourceLocation(RexisEnergyAndDecor.MOD_ID, "block.washing_machine_sound")));

}
