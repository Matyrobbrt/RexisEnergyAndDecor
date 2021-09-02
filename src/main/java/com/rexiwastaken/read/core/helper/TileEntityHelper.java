package com.rexiwastaken.read.core.helper;

import net.minecraft.item.ItemStack;

public class TileEntityHelper {
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
