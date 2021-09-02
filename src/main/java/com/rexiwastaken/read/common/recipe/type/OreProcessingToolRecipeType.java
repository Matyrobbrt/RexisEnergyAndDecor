package com.rexiwastaken.read.common.recipe.type;

import com.rexiwastaken.read.RexisEnergyAndDecor;
import com.rexiwastaken.read.common.recipe.OreProcessingToolRecipe;

import net.minecraft.item.crafting.IRecipeType;

public class OreProcessingToolRecipeType implements IRecipeType<OreProcessingToolRecipe> {

	@Override
	public String toString() {
		return RexisEnergyAndDecor.MOD_ID + ":ore_processing_tool_recipe";
	}
	
}
