package com.rexiwastaken.read.core.itemgroup;

import com.rexiwastaken.read.core.init.ItemInit;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class READItemGroup extends ItemGroup {

	public static final READItemGroup READ = new READItemGroup(ItemGroup.TABS.length, "read");

	public READItemGroup(int p_i1853_1_, String p_i1853_2_) {
		super(p_i1853_1_, p_i1853_2_);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(ItemInit.COPPER_INGOT.get());
	}

}
