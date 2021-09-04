package com.rexiwastaken.read.common.item;

import com.rexiwastaken.read.core.helper.NBTHelper;
import com.rexiwastaken.read.core.init.ItemInit;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class HazmatSuitChestplate extends ArmorItem {

	public HazmatSuitChestplate(IArmorMaterial p_i48534_1_, EquipmentSlotType p_i48534_2_, Properties p_i48534_3_) {
		super(p_i48534_1_, p_i48534_2_, p_i48534_3_);
	}

	@Override
	public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {

		if (entity instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) entity;
			if (player.inventory.getArmor(0).getItem().getRegistryName() == ItemInit.HAZMAT_SUIT_SHOES.get()
					.getRegistryName()
					&& player.inventory.getArmor(1).getItem().getRegistryName() == ItemInit.HAZMAT_SUIT_LEGGINGS.get()
							.getRegistryName()
					&& player.inventory.getArmor(2).getItem().getRegistryName() == ItemInit.HAZMAT_SUIT_CHEST.get()
							.getRegistryName()
					&& player.inventory.getArmor(3).getItem().getRegistryName() == ItemInit.HAZMAT_SUIT_HELMET.get()
							.getRegistryName())
				NBTHelper.setBoolean(stack, "IsAllHazmatSuit", true);

			else
				NBTHelper.setBoolean(stack, "IsAllHazmatSuit", false);
		}
	}
	
	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
		if (enchantment == Enchantments.MENDING) return false;
		return false;
	}

}
