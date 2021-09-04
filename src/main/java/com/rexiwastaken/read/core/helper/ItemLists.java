package com.rexiwastaken.read.core.helper;

import com.rexiwastaken.read.core.init.ItemInit;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemLists {

	public static Item[] allRECoins = { ItemInit.RE_100_COIN.get(), ItemInit.RE_1K_COIN.get(),
			ItemInit.RE_10K_COIN.get(), ItemInit.RE_100K_COIN.get(), ItemInit.RE_1M_COIN.get() };

	public static Item[] allHazmatSuitPieces = { ItemInit.HAZMAT_SUIT_CHEST.get(), ItemInit.HAZMAT_SUIT_HELMET.get(),
			ItemInit.HAZMAT_SUIT_LEGGINGS.get(), ItemInit.HAZMAT_SUIT_SHOES.get() };
	
	/**
	 * Checks if the ItemStack is a RE Coin
	 * @param stack the stack to check
	 */
	public static boolean isRECoin(ItemStack stack) {
		boolean found = false;
		for (Item item : ItemLists.allRECoins) {
			if (item == stack.getItem()) {
				found = true;
				break;
			}
		}
		if (found)
			return true;
		else
			return false;
	}
	
	/**
	 * Checks if the ItemStack a hazmat suit piece
	 * @param stack the stack to check
	 */
	public static boolean isHazmatSuitPiece(ItemStack stack) {
		boolean found = false;
		for (Item item : ItemLists.allHazmatSuitPieces) {
			if (item.getRegistryName() == stack.getItem().getRegistryName()) {
				found = true;
				break;
			}
		}
		if (found)
			return true;
		else
			return false;
	}

}
