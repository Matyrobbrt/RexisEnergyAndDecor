package com.rexiwastaken.read.core.init;

import com.rexiwastaken.read.RexisEnergyAndDecor;
import com.rexiwastaken.read.common.effect.Radiation;

import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class PotionInit {
	public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, RexisEnergyAndDecor.MOD_ID);
	
	public static final RegistryObject<Radiation> RADIATION = EFFECTS.register("radiation", Radiation::new);
}
