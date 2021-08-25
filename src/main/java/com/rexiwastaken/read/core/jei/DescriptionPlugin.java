package com.rexiwastaken.read.core.jei;

import com.rexiwastaken.read.core.init.BlockInit;
import com.rexiwastaken.read.core.init.ItemInit;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.ingredients.IIngredientType;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IIngredientManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

@JeiPlugin
public class DescriptionPlugin implements IModPlugin {

	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation("read", "default");
	}

	@Override
	public void registerRecipes(IRecipeRegistration registry) {
		IIngredientManager ingredientManager = registry.getIngredientManager();

		IIngredientType<ItemStack> itemType = ingredientManager.getIngredientType(ItemStack.class);

		registry.addIngredientInfo(new ItemStack(ItemInit.ALUMINUM_INGOT.get()), itemType,
				new StringTextComponent("The Aluminum Ingot, duh"));
		registry.addIngredientInfo(new ItemStack(ItemInit.ALUMINUM_NUGGET.get()), itemType,
				new StringTextComponent("The Aluminum Nugget, duh"));
		registry.addIngredientInfo(new ItemStack(ItemInit.COPPER_INGOT.get()), itemType,
				new StringTextComponent("The Copper Ingot, duh"));
		registry.addIngredientInfo(new ItemStack(ItemInit.COPPER_NUGGET.get()), itemType,
				new StringTextComponent("The Copper Nugget, duh"));
		registry.addIngredientInfo(new ItemStack(ItemInit.LEAD_INGOT.get()), itemType,
				new StringTextComponent("The Lead Ingot, duh"));
		registry.addIngredientInfo(new ItemStack(ItemInit.LEAD_NUGGET.get()), itemType,
				new StringTextComponent("The Lead Nugget, duh"));
		registry.addIngredientInfo(new ItemStack(ItemInit.URANIUM_INGOT.get()), itemType, new StringTextComponent(
				"The Uranium Ingot, duh. Gives you Radiation 2 when in Inventory (without Hazmat Suit)"));
		registry.addIngredientInfo(new ItemStack(ItemInit.URANIUM_NUGGET.get()), itemType, new StringTextComponent(
				"The Uranium Nugget, duh. Gives you Radiation 1 when in Inventory (without Hazmat Suit)"));
		registry.addIngredientInfo(new ItemStack(ItemInit.HAZMAT_SUIT_CHEST.get()), itemType, new StringTextComponent(
				"Its a special Suit, to protect you from Radiation. IMPORTANT: Only works with the full set. Note: Cant handle too strong Radiation."));
		registry.addIngredientInfo(new ItemStack(ItemInit.HAZMAT_SUIT_HELMET.get()), itemType, new StringTextComponent(
				"Its a special Suit, to protect you from Radiation. IMPORTANT: Only works with the full set. Note: Cant handle too strong Radiation."));
		registry.addIngredientInfo(new ItemStack(ItemInit.HAZMAT_SUIT_LEGGINGS.get()), itemType,
				new StringTextComponent(
						"Its a special Suit, to protect you from Radiation. IMPORTANT: Only works with the full set. Note: Cant handle too strong Radiation."));
		registry.addIngredientInfo(new ItemStack(ItemInit.HAZMAT_SUIT_SHOES.get()), itemType, new StringTextComponent(
				"Its a special Suit, to protect you from Radiation. IMPORTANT: Only works with the full set. Note: Cant handle too strong Radiation."));
		registry.addIngredientInfo(new ItemStack(BlockInit.ALUMINUM_BLOCK.get()), itemType,
				new StringTextComponent("The Aluminum Block, duh."));
		registry.addIngredientInfo(new ItemStack(BlockInit.ALUMINUM_ORE.get()), itemType,
				new StringTextComponent("The Aluminum Ore (Bauxite), duh. Can be found in Y-Level 0-48."));
		registry.addIngredientInfo(new ItemStack(BlockInit.COPPER_BLOCK.get()), itemType,
				new StringTextComponent("The Copper Block, duh."));
		registry.addIngredientInfo(new ItemStack(BlockInit.COPPER_ORE.get()), itemType,
				new StringTextComponent("The Copper Ore, duh. Can be found in Y-Level 0-48."));
		registry.addIngredientInfo(new ItemStack(BlockInit.LEAD_BLOCK.get()), itemType,
				new StringTextComponent("The Lead Block, duh"));
		registry.addIngredientInfo(new ItemStack(BlockInit.LEAD_ORE.get()), itemType,
				new StringTextComponent("The Lead Ore, duh. Can be found in Y-Level 0-56."));
		registry.addIngredientInfo(new ItemStack(BlockInit.URANIUM_BLOCK.get()), itemType, new StringTextComponent(
				"The Uranium Block, duh. Gives you Radiation 3 when in Inventory (without Hazmat Suit) "
						+ "ATTENTION! This Block has so high Radiation, that the Hazmat Suit, cant handle it all!"));
		registry.addIngredientInfo(new ItemStack(BlockInit.URANIUM_ORE.get()), itemType, new StringTextComponent(
				"The Uranium Ore, duh. Gives you Radiation 1 when in Inventory (without Hazmat Suit). Can be found in Y-Level 0-10."));
		registry.addIngredientInfo(new ItemStack(ItemInit.RE_100_COIN.get()), itemType,
				new StringTextComponent("The Energy Coin with the least amount of Energy (100 RE)."));
		registry.addIngredientInfo(new ItemStack(ItemInit.RE_1K_COIN.get()), itemType,
				new StringTextComponent("The Energy Coin with the second least amount of Energy (1K RE)."));
		registry.addIngredientInfo(new ItemStack(ItemInit.RE_10K_COIN.get()), itemType,
				new StringTextComponent("The Energy Coin with the average amount of Energy (10K RE)."));
		registry.addIngredientInfo(new ItemStack(ItemInit.RE_100K_COIN.get()), itemType,
				new StringTextComponent("The Energy Coin with the second most amount of Energy (100K RE)."));
		registry.addIngredientInfo(new ItemStack(ItemInit.RE_1M_COIN.get()), itemType,
				new StringTextComponent("The Energy Coin with the most amount of Energy (1M RE)."));
	}
}
