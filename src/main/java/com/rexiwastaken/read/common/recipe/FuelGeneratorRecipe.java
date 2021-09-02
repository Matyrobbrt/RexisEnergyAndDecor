package com.rexiwastaken.read.common.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.rexiwastaken.read.RexisEnergyAndDecor;
import com.rexiwastaken.read.core.init.BlockInit;
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
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class FuelGeneratorRecipe implements IRecipe<IInventory> {
	
	public static final Serializer SERIALIZER = new Serializer();
	
	private final Ingredient input;
	private final ItemStack output;
	private final ResourceLocation id;
	
	public FuelGeneratorRecipe(ResourceLocation id, Ingredient input, ItemStack output) {
		this.id = id;
		this.input = input;
		this.output = output;
	}

	@Override
	public boolean matches(IInventory p_77569_1_, World p_77569_2_) {
		return this.input.test(p_77569_1_.getItem(0));
	}

	@Override
	public ItemStack assemble(IInventory p_77572_1_) {
		return null;
	}

	@Override
	public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_) {
		return true;
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
		return RecipeInit.FUEL_GENERATOR_RECIPE;
	}
	
	@Override
	public ItemStack getToastSymbol() {
		return new ItemStack(BlockInit.FUEL_GENERATOR.get().asItem());
	}
	
	public boolean isValid(ItemStack input) {
		return this.input.test(input);
	}
	
	private static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
	implements IRecipeSerializer<FuelGeneratorRecipe> {
		public Serializer() {
			this.setRegistryName(RexisEnergyAndDecor.MOD_ID, "fuel_generator_accepted_items");
		}
		
		@Override
		public FuelGeneratorRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			final JsonElement inputEl = JSONUtils.isArrayNode(json, "input") ? JSONUtils.getAsJsonArray(json, "input")
					: JSONUtils.getAsJsonObject(json, "input");
			final ItemStack output = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "output"));
			final Ingredient input = Ingredient.fromJson(inputEl);
			return new FuelGeneratorRecipe(recipeId, input, output);
		}
		
		@Override
		public FuelGeneratorRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
			final Ingredient input = Ingredient.fromNetwork(buffer);
			final ItemStack output = buffer.readItem();
			return new FuelGeneratorRecipe(recipeId, input, output);
		}
		
		@Override
		public void toNetwork(PacketBuffer buffer, FuelGeneratorRecipe recipe) {
			recipe.input.toNetwork(buffer);
			buffer.writeItem(recipe.output);
		}
	}

}
