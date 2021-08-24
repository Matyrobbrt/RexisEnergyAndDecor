package com.rexiwastaken.read.common.item;

import com.rexiwastaken.read.core.helper.ModHelper;
import com.rexiwastaken.read.core.init.PotionInit;
import com.rexiwastaken.read.core.itemgroup.READItemGroup;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class UraniumIngot extends Item{

	public UraniumIngot() {
		super(new Item.Properties().tab(READItemGroup.READ));
	}
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			if (ModHelper.isAllHazmatSuit(player) == false)
				player.addEffect(new EffectInstance(PotionInit.RADIATION.get(), 100, 1, true, true));
		}
	}
}
