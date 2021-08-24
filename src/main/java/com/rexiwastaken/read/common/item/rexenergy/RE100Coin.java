package com.rexiwastaken.read.common.item.rexenergy;

import com.rexiwastaken.read.core.itemgroup.READItemGroup;

import net.minecraft.item.Item;

public class RE100Coin extends Item{

	public RE100Coin() {
		super(new Item.Properties().stacksTo(10).tab(READItemGroup.READ));
	}

}
