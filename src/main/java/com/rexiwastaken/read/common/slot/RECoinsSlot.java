package com.rexiwastaken.read.common.slot;

import com.rexiwastaken.read.core.helper.ItemLists;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class RECoinsSlot extends Slot {

	public RECoinsSlot(IInventory p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_) {
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_);
	}
	
	@Override
	public boolean mayPlace(ItemStack stack) {
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

}
