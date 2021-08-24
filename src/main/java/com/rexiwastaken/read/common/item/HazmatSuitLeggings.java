package com.rexiwastaken.read.common.item;

import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HazmatSuitLeggings extends ArmorItem{

	public HazmatSuitLeggings(IArmorMaterial p_i48534_1_, EquipmentSlotType p_i48534_2_, Properties p_i48534_3_) {
		super(p_i48534_1_, p_i48534_2_, p_i48534_3_);
	}
	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot,
			boolean isSelected) {
		if (stack.getDamageValue() != 0) stack.setDamageValue(0);
	}
}
