package com.rexiwastaken.read.core.helper;

import com.rexiwastaken.read.core.init.ItemInit;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

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

	public static void updateTE(TileEntity te) {
		te.setChanged();
		te.getLevel().sendBlockUpdated(te.getBlockPos(), te.getBlockState(), te.getBlockState(),
				Constants.BlockFlags.BLOCK_UPDATE);
	}

	public static int acceptRE(TileEntity te, ItemStack stack, int totalREStorage, int currentREStorage) {
		if (stack.getItem() == ItemInit.RE_100_COIN.get() && currentREStorage + 100 <= totalREStorage) {
			stack.shrink(1);
			TileEntityHelper.updateTE(te);
			currentREStorage = currentREStorage + 100;
		} else if (stack.getItem() == ItemInit.RE_1K_COIN.get() && currentREStorage + 1000 <= totalREStorage) {
			stack.shrink(1);
			TileEntityHelper.updateTE(te);
			currentREStorage = currentREStorage + 1000;
		} else if (stack.getItem() == ItemInit.RE_10K_COIN.get() && currentREStorage + 10000 <= totalREStorage) {
			stack.shrink(1);
			currentREStorage = currentREStorage + 10000;
			TileEntityHelper.updateTE(te);
		} else if (stack.getItem() == ItemInit.RE_100K_COIN.get() && currentREStorage + 100000 <= totalREStorage) {
			stack.shrink(1);
			currentREStorage = currentREStorage + 100000;
			TileEntityHelper.updateTE(te);
		} else if (stack.getItem() == ItemInit.RE_1M_COIN.get() && currentREStorage + 1000000 <= totalREStorage) {
			stack.shrink(1);
			currentREStorage = currentREStorage + 1000000;
			TileEntityHelper.updateTE(te);
		}
		return currentREStorage;
	}
}
