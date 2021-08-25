package com.rexiwastaken.read.common.recipe;

import com.rexiwastaken.read.RexisEnergyAndDecor;

import net.minecraft.item.crafting.IRecipeType;

public class OreProcessingToolType implements IRecipeType<OreProcessingToolRecipe> {

	@Override
	public String toString() {
		return RexisEnergyAndDecor.MOD_ID + ":ore_processing_tool_recipe";
	}
	
}
