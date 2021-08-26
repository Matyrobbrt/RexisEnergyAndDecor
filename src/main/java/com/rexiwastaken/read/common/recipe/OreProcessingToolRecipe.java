package com.rexiwastaken.read.common.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rexiwastaken.read.RexisEnergyAndDecor;
import com.rexiwastaken.read.core.init.ItemInit;
import com.rexiwastaken.read.core.init.RecipeInit;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class OreProcessingToolRecipe implements IRecipe<IInventory> {
	
	public static final Serializer SERIALIZER = new Serializer();
	
	private final Ingredient input;
	private final ItemStack output;
	private final ResourceLocation id;
	
	public OreProcessingToolRecipe(ResourceLocation id, Ingredient input, ItemStack output) {
		this.id = id;
		this.input = input;
		this.output = output;
	}
	
	@Override
	public boolean matches(IInventory inv, World worldIn) {
		return this.input.test(inv.getItem(0));
	}
	
	@Override
	public ItemStack assemble(IInventory inv) {
		return this.output.copy();
	}
	
	@Override
	public NonNullList<Ingredient> getIngredients() {
		NonNullList<Ingredient> i = NonNullList.create();
		i.add(input);
		return i;
	}
	
	@Override
	public ItemStack getResultItem() {
		return this.output;
	}
	
	@Override
	public ResourceLocation getId() {
		return this.id;
	}
	
	@Override
	public IRecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}
	
	@Override
	public IRecipeType<?> getType() {
		return RecipeInit.ORE_PROCESSING_TOOL_RECIPE;
	}
	
	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(ItemInit.ORE_PROCESSING_TOOL.get());
	}

	public boolean isValid(ItemStack input) {
		return this.input.test(input);
	}
	
	@Override
	public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
		return true;
	}
	
	private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
			implements IRecipeSerializer<OreProcessingToolRecipe> {

		public Serializer() {
			this.setRegistryName(RexisEnergyAndDecor.MOD_ID, "ore_processing_tool_recipe");
		}
		
		@Override
		public OreProcessingToolRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			final JsonElement inputEl = JSONUtils.isArrayNode(json, "input") ? JSONUtils.getAsJsonArray(json, "input")
					: JSONUtils.getAsJsonObject(json, "input");
			final Ingredient input = Ingredient.fromJson(inputEl);
			final ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));
			
			return new OreProcessingToolRecipe(recipeId, input, output);
		}
		
		@Override
		public OreProcessingToolRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
			final Ingredient input = Ingredient.fromNetwork(buffer);
			final ItemStack output = buffer.readItem();
			return new OreProcessingToolRecipe(recipeId, input, output);
		}
		
		@Override
		public void toNetwork(PacketBuffer buffer, OreProcessingToolRecipe recipe) {
			recipe.input.toNetwork(buffer);
			buffer.writeItem(recipe.output);
		}
	}
}
