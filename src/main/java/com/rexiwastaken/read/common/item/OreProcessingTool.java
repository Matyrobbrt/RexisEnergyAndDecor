package com.rexiwastaken.read.common.item;

import com.rexiwastaken.read.common.recipe.OreProcessingToolRecipe;
import com.rexiwastaken.read.core.helper.ModHelper;
import com.rexiwastaken.read.core.init.ItemInit;
import com.rexiwastaken.read.core.init.RecipeInit;
import com.rexiwastaken.read.core.itemgroup.READItemGroup;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class OreProcessingTool extends Item {

	public OreProcessingTool() {
		super(new Item.Properties().stacksTo(1).setNoRepair().tab(READItemGroup.READ));
	}

	@Override
	public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
		ItemStack offHandItem = player.getOffhandItem();
		for (final IRecipe<?> recipe : world.getRecipeManager().getAllRecipesFor(RecipeInit.ORE_PROCESSING_TOOL_RECIPE)) {
			final OreProcessingToolRecipe oreProcessingToolRecipe = (OreProcessingToolRecipe) recipe;
			System.out.println(recipe);
			if (oreProcessingToolRecipe.isValid(offHandItem)) {
				if (ModHelper.getItemStack(player, ItemInit.RE_100_COIN.get().getRegistryName()) != null) {
					player.drop(new ItemStack(recipe.getResultItem().getItem(), recipe.getResultItem().getCount()), false);
					offHandItem.shrink(1);
					ModHelper.getItemStack(player, ItemInit.RE_100_COIN.get().getRegistryName()).shrink(1);
				} else {
					if (!world.isClientSide)
					player.sendMessage(new StringTextComponent("\u00A74" + "You don't have an 100RE coin on you!"), player.getUUID());
				}
			}
		}
		return super.use(world, player, hand);
	}

}
