package com.rexiwastaken.read.common.recipe.type;

import com.rexiwastaken.read.RexisEnergyAndDecor;
import com.rexiwastaken.read.common.recipe.FuelGeneratorRecipe;

import net.minecraft.item.crafting.IRecipeType;

public class FuelGeneratorRecipeType implements IRecipeType<FuelGeneratorRecipe> {
	
	@Override
	public String toString() {
		return RexisEnergyAndDecor.MOD_ID + ":fuel_generator_accepted_items";
	}

}
