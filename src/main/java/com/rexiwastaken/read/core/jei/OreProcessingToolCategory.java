package com.rexiwastaken.read.core.jei;

import com.rexiwastaken.read.RexisEnergyAndDecor;
import com.rexiwastaken.read.common.recipe.OreProcessingToolRecipe;
import com.rexiwastaken.read.core.init.ItemInit;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;

public class OreProcessingToolCategory implements IRecipeCategory<OreProcessingToolRecipe> {

	public static final ResourceLocation ID = new ResourceLocation(RexisEnergyAndDecor.MOD_ID,
			".ore_processing_tool_category");

	private final IDrawable back;
	private final IDrawable icon;

	public OreProcessingToolCategory(IGuiHelper helper) {
		this.back = helper.createBlankDrawable(180, 100);
		this.icon = helper.createDrawableIngredient(
				new Ingredient.SingleItemList(new ItemStack(ItemInit.ORE_PROCESSING_TOOL.get())));
	}

	@Override
	public ResourceLocation getUid() {
		return ID;
	}

	@Override
	public Class<? extends OreProcessingToolRecipe> getRecipeClass() {
		return OreProcessingToolRecipe.class;
	}

	@Override
	public String getTitle() {
		return new TranslationTextComponent("category." + RexisEnergyAndDecor.MOD_ID + ".ore_processing_tool_recipe")
				.getString();
	}

	@Override
	public IDrawable getBackground() {
		return back;
	}

	@Override
	public IDrawable getIcon() {
		return icon;
	}

	@Override
	public void setIngredients(OreProcessingToolRecipe recipe, IIngredients ingredients) {
		ingredients.setInputIngredients(recipe.getIngredients());
		ingredients.setOutput(VanillaTypes.ITEM, recipe.getResultItem());

	}

	@Override
	public void setRecipe(IRecipeLayout recipeLayout, OreProcessingToolRecipe recipe, IIngredients ingredients) {
		IGuiItemStackGroup itemStackGroup = recipeLayout.getItemStacks();
		
		itemStackGroup.init(0, true, 0, 0);
		itemStackGroup.init(1, false, 60, 18);
		
		itemStackGroup.set(ingredients);
	}

}
