package com.rexiwastaken.read.core.init;

import net.minecraft.util.DamageSource;

public class DamageSourcesInit {
	public static final DamageSource RADIATION_DEATH = new DamageSource("readRadiationDeath").bypassInvul().bypassArmor().bypassMagic();
	public static final DamageSource FALL_RADIATION_DEATH = new DamageSource("readFallRadiationDeath").bypassInvul().bypassArmor().bypassMagic();
}
