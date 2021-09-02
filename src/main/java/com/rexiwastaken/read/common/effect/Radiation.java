package com.rexiwastaken.read.common.effect;

import javax.annotation.Nonnull;

import com.rexiwastaken.read.core.init.DamageSourcesInit;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class Radiation extends Effect {

	public Radiation() {
		super(EffectType.HARMFUL, 0x1c910f);
	}

	@Override
	public boolean isDurationEffectTick(int duration, int amplified) {
		return true;
	}

	@Override
	public void applyEffectTick(@Nonnull LivingEntity entity, int amplifier) {
		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			World world = player.level;
			int randChanceNumber = world.random.nextInt(40) + 1;
			if (randChanceNumber <= 1 + amplifier * 2)
				if (entity.fallDistance > 1.5F) entity.hurt(DamageSourcesInit.FALL_RADIATION_DEATH, 1f);
				else entity.hurt(DamageSourcesInit.RADIATION_DEATH, 1f);
			entity.addEffect(new EffectInstance(Effects.HUNGER, 600));
			if (amplifier >= 2)
				entity.addEffect(new EffectInstance(Effects.CONFUSION, 600, 1));
		}
		super.applyEffectTick(entity, amplifier);
	}
}
