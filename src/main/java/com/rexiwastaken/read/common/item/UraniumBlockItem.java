package com.rexiwastaken.read.common.item;

import com.rexiwastaken.read.core.helper.ModHelper;
import com.rexiwastaken.read.core.init.PotionInit;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.world.World;

public class UraniumBlockItem extends BlockItem {

	public UraniumBlockItem(Block p_i48527_1_, Properties p_i48527_2_) {
		super(p_i48527_1_, p_i48527_2_);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			if (ModHelper.isAllHazmatSuit(player) == false)
				player.addEffect(new EffectInstance(PotionInit.RADIATION.get(), 100, 2, true, true));
		else 
			player.addEffect(new EffectInstance(PotionInit.RADIATION.get(), 100, 1, true, true));
		}
	}
}
