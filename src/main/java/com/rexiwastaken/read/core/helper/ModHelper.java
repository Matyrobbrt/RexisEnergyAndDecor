package com.rexiwastaken.read.core.helper;

import com.rexiwastaken.read.core.init.ItemInit;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ModHelper {
	/**
	 * Checks if the specified player is completly dressesd up with an hazmat suit!
	 * 
	 * @param player the player to check
	 */
	public static boolean isAllHazmatSuit(PlayerEntity player) {
		if (player.inventory.getArmor(2).getItem().getRegistryName() == ItemInit.HAZMAT_SUIT_CHEST.get()
				.getRegistryName()) {
			return (NBTHelper.getBoolean(player.inventory.getArmor(2), "IsAllHazmatSuit"));
		} else
			return false;
	}

	/**
	 * Checks if the player has an item in his inventory. If the player has the item
	 * it will return it. If not it will return null.
	 * 
	 * @param player       the player to check for the item in the inventory
	 * @param registryName the registry name of the item to check for
	 * @return ItemStack / null
	 */
	public static ItemStack getItemStack(PlayerEntity player, ResourceLocation registryName) {
		// Off Hand
		if (player.getOffhandItem().getItem().getRegistryName() == registryName)
			return player.getOffhandItem();
		// Inventory
		else {
			for (int i = 0; i <= 35; ++i) {
				if (player.inventory.getItem(i).getItem().getRegistryName() == registryName)
					return player.inventory.getItem(i);
			}
		}
		return null;
	}

	public static boolean canPlaceItemInStack(ItemStack stack, ItemStack stackToPlace) {
		if (stack == ItemStack.EMPTY)
			return true;
		else if (stack.getItem() == stackToPlace.getItem()
				&& stack.getCount() <= stack.getMaxStackSize() - stackToPlace.getCount())
			return true;
		else
			return false;
	}

	public static void tryToTransferItemFromStackToStack(ItemStack originStack, ItemStack targetStack, int count) {
		if (originStack.getCount() < count)
			count = originStack.getCount();
		if (canPlaceItemInStack(targetStack, new ItemStack(originStack.getItem().getItem(), count))) {
			int oldCount = targetStack.getCount();
			targetStack = new ItemStack(originStack.getItem().getItem(), oldCount + count);
			originStack.shrink(count);
		}
	}
}
