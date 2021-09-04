package com.rexiwastaken.read.common.item;

import java.util.Random;

import com.rexiwastaken.read.core.helper.ModHelper;
import com.rexiwastaken.read.core.init.PotionInit;
import com.rexiwastaken.read.core.itemgroup.READItemGroup;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class UraniumIngot extends Item {

	public UraniumIngot() {
		super(new Item.Properties().tab(READItemGroup.READ));
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			if (ModHelper.isAllHazmatSuit(player) == false)
				player.addEffect(new EffectInstance(PotionInit.RADIATION.get(), 100, 1, true, true));
			else {
				int randomChance = world.random.nextInt(80) + 1;
				if (randomChance == 40) {
					int damageAmount = (world.random.nextInt(2) + 1) * 2;
					if (!world.isClientSide) {
						player.inventory.getArmor(0).hurt(damageAmount, new Random(0), (ServerPlayerEntity) player);
						player.inventory.getArmor(1).hurt(damageAmount, new Random(0), (ServerPlayerEntity) player);
						player.inventory.getArmor(2).hurt(damageAmount, new Random(0), (ServerPlayerEntity) player);
						player.inventory.getArmor(3).hurt(damageAmount, new Random(0), (ServerPlayerEntity) player);
					}
				}
			}
		}
	}
}
