package com.rexiwastaken.read.common.item.rexenergy;

import com.rexiwastaken.read.core.itemgroup.READItemGroup;

import net.minecraft.item.Item;

public class RE10kCoin extends Item{

	public RE10kCoin() {
		super(new Item.Properties().stacksTo(10).tab(READItemGroup.READ));
	}
	
}
