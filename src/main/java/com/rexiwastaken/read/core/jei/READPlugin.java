package com.rexiwastaken.read.core.jei;

import java.util.Collection;
import java.util.stream.Collectors;

import com.rexiwastaken.read.RexisEnergyAndDecor;
import com.rexiwastaken.read.core.init.RecipeInit;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;

@JeiPlugin
public class READPlugin implements IModPlugin {

	private static final ResourceLocation PLUGIN_ID = new ResourceLocation(RexisEnergyAndDecor.MOD_ID, "jei_plugin");

	@Override
	public ResourceLocation getPluginUid() {
		return PLUGIN_ID;
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		@SuppressWarnings("resource")
		RecipeManager manager = Minecraft.getInstance().level.getRecipeManager();
		
		registration.addRecipes(getRecipes(manager, RecipeInit.ORE_PROCESSING_TOOL_RECIPE), OreProcessingToolCategory.ID);
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		IGuiHelper helper = registration.getJeiHelpers().getGuiHelper();
		
		registration.addRecipeCategories(new OreProcessingToolCategory(helper));
	}

	private static Collection<?> getRecipes(RecipeManager manager, IRecipeType<?> type) {
		return manager.getRecipes().parallelStream().filter(recipe -> recipe.getType() == type)
				.collect(Collectors.toList());
	}

}
