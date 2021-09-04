package com.rexiwastaken.read.core.init;

import com.rexiwastaken.read.RexisEnergyAndDecor;
import com.rexiwastaken.read.common.item.HazmatSuitChestplate;
import com.rexiwastaken.read.common.item.HazmatSuitHelmet;
import com.rexiwastaken.read.common.item.HazmatSuitLeggings;
import com.rexiwastaken.read.common.item.HazmatSuitShoes;
import com.rexiwastaken.read.common.item.OreProcessingTool;
import com.rexiwastaken.read.common.item.UraniumIngot;
import com.rexiwastaken.read.common.item.UraniumShard;
import com.rexiwastaken.read.common.item.rexenergy.RE100Coin;
import com.rexiwastaken.read.common.item.rexenergy.RE100kCoin;
import com.rexiwastaken.read.common.item.rexenergy.RE10kCoin;
import com.rexiwastaken.read.common.item.rexenergy.RE1kCoin;
import com.rexiwastaken.read.common.item.rexenergy.RE1mCoin;
import com.rexiwastaken.read.common.material.HazmatSuitMaterial;
import com.rexiwastaken.read.core.itemgroup.READItemGroup;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
			RexisEnergyAndDecor.MOD_ID);
	// Items
	public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot",
			() -> new Item(new Item.Properties().tab(READItemGroup.READ)));
	public static final RegistryObject<Item> ALUMINUM_INGOT = ITEMS.register("aluminum_ingot",
			() -> new Item(new Item.Properties().tab(READItemGroup.READ)));
	public static final RegistryObject<Item> LEAD_INGOT = ITEMS.register("lead_ingot",
			() -> new Item(new Item.Properties().tab(READItemGroup.READ)));
	public static final RegistryObject<UraniumIngot> URANIUM_INGOT = ITEMS.register("uranium_ingot", UraniumIngot::new);
	public static final RegistryObject<Item> COPPER_NUGGET = ITEMS.register("copper_nugget",
			() -> new Item(new Item.Properties().tab(READItemGroup.READ)));
	public static final RegistryObject<Item> ALUMINUM_NUGGET = ITEMS.register("aluminum_nugget",
			() -> new Item(new Item.Properties().tab(READItemGroup.READ)));
	public static final RegistryObject<Item> LEAD_NUGGET = ITEMS.register("lead_nugget",
			() -> new Item(new Item.Properties().tab(READItemGroup.READ)));
	public static final RegistryObject<Item> SOAP = ITEMS.register("soap",
			() -> new Item(new Item.Properties().tab(READItemGroup.READ)));
	public static final RegistryObject<Item> URANIUM_NUGGET = ITEMS.register("uranium_nugget", UraniumShard::new);
	public static final RegistryObject<OreProcessingTool> ORE_PROCESSING_TOOL = ITEMS.register("ore_processing_tool",
			OreProcessingTool::new);

	// Tools and Armour
	public static final RegistryObject<Item> HAZMAT_SUIT_HELMET = ITEMS.register("hazmat_suit_helmet",
			() -> new HazmatSuitHelmet(HazmatSuitMaterial.HAZMAT_SUIT, EquipmentSlotType.HEAD,
					new Item.Properties().tab(READItemGroup.READ).defaultDurability(256)));
	public static final RegistryObject<Item> HAZMAT_SUIT_CHEST = ITEMS.register("hazmat_suit_chest",
			() -> new HazmatSuitChestplate(HazmatSuitMaterial.HAZMAT_SUIT, EquipmentSlotType.CHEST,
					new Item.Properties().tab(READItemGroup.READ).defaultDurability(256)));
	public static final RegistryObject<Item> HAZMAT_SUIT_LEGGINGS = ITEMS.register("hazmat_suit_leggings",
			() -> new HazmatSuitLeggings(HazmatSuitMaterial.HAZMAT_SUIT, EquipmentSlotType.LEGS,
					new Item.Properties().tab(READItemGroup.READ).defaultDurability(256)));
	public static final RegistryObject<Item> HAZMAT_SUIT_SHOES = ITEMS.register("hazmat_suit_shoes",
			() -> new HazmatSuitShoes(HazmatSuitMaterial.HAZMAT_SUIT, EquipmentSlotType.FEET,
					new Item.Properties().tab(READItemGroup.READ).defaultDurability(256)));

	// "Energy"
	public static final RegistryObject<RE100Coin> RE_100_COIN = ITEMS.register("re_100_coin", RE100Coin::new);
	public static final RegistryObject<RE1kCoin> RE_1K_COIN = ITEMS.register("re_1k_coin", RE1kCoin::new);
	public static final RegistryObject<RE10kCoin> RE_10K_COIN = ITEMS.register("re_10k_coin", RE10kCoin::new);
	public static final RegistryObject<RE100kCoin> RE_100K_COIN = ITEMS.register("re_100k_coin", RE100kCoin::new);
	public static final RegistryObject<RE1mCoin> RE_1M_COIN = ITEMS.register("re_1m_coin", RE1mCoin::new);

}