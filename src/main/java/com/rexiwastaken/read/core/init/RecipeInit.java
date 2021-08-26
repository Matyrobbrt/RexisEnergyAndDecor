package com.rexiwastaken.read.core.init;

import com.rexiwastaken.read.common.recipe.OreProcessingToolRecipe;
import com.rexiwastaken.read.common.recipe.OreProcessingToolRecipeType;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.event.RegistryEvent.Register;

public class RecipeInit {
	public static final IRecipeType<OreProcessingToolRecipe> ORE_PROCESSING_TOOL_RECIPE = new OreProcessingToolRecipeType();
	
	public static void registerRecipes(Register<IRecipeSerializer<?>> event) {
		registerRecipe(event, ORE_PROCESSING_TOOL_RECIPE, OreProcessingToolRecipe.SERIALIZER);
	}
	
	private static void registerRecipe(Register<IRecipeSerializer<?>> event, IRecipeType<?> type,
			IRecipeSerializer<?> serializer) {
		Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(type.toString()), type);
		event.getRegistry().register(serializer);
	}
}
