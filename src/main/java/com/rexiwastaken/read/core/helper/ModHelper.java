package com.rexiwastaken.read.core.helper;

import com.rexiwastaken.read.core.init.ItemInit;

import net.minecraft.entity.player.PlayerEntity;

public class ModHelper {
	public static boolean isAllHazmatSuit(PlayerEntity player) {
		if (player.inventory.getArmor(2).getItem().getRegistryName() == ItemInit.HAZMAT_SUIT_CHEST.get()
				.getRegistryName()) {
			return (NBTHelper.getBoolean(player.inventory.getArmor(2), "IsAllHazmatSuit"));
		} else
			return false;
	}
}
