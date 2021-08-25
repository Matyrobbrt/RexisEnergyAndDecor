package com.rexiwastaken.read.core.helper;

import com.rexiwastaken.read.core.init.ItemInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ModHelper {
	public static boolean isAllHazmatSuit(PlayerEntity player) {
		if (player.inventory.getArmor(2).getItem().getRegistryName() == ItemInit.HAZMAT_SUIT_CHEST.get()
				.getRegistryName()) {
			return (NBTHelper.getBoolean(player.inventory.getArmor(2), "IsAllHazmatSuit"));
		} else
			return false;
	}

	public static ItemStack getItemStack(PlayerEntity player, ResourceLocation registryName) {
		// Off Hand
		if (player.getOffhandItem().getItem().getRegistryName() == registryName)
			return player.getOffhandItem();
		else {
			for (int i = 0; i <= 35; ++i) {
				if (player.inventory.getItem(i).getItem().getRegistryName() == registryName)
					return player.inventory.getItem(i);
			}
		}
		return null;
	}
}
