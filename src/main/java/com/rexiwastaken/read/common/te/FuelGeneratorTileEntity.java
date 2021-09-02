package com.rexiwastaken.read.common.te;

import javax.annotation.Nullable;

import com.rexiwastaken.read.RexisEnergyAndDecor;
import com.rexiwastaken.read.common.block.FuelGeneratorBlock;
import com.rexiwastaken.read.common.container.FuelGeneratorContainer;
import com.rexiwastaken.read.common.recipe.FuelGeneratorRecipe;
import com.rexiwastaken.read.core.helper.ModHelper;
import com.rexiwastaken.read.core.init.RecipeInit;
import com.rexiwastaken.read.core.init.TileEntityTypesInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;

public class FuelGeneratorTileEntity extends LockableSidedInventoryTileEntity implements ITickableTileEntity {
	private ITextComponent customName;
	public static int slots = 2;
	protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);
	public int currentWaitTime;
	public int maxWaitTime = 200;
	public int greenOrRed = 1;
	private static final int[] SLOTS_FOR_UP = new int[] { 0 };
	private static final int[] SLOTS_FOR_DOWN = new int[] { 1 };
	private static final int[] SLOTS_FOR_SIDES = new int[] { };
	// private boolean couldAddToOutput;

	public FuelGeneratorTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn, slots);
	}

	public FuelGeneratorTileEntity() {
		this(TileEntityTypesInit.FUEL_GENERATOR_TILE_ENTITY_TYPE.get());
	}

	@Override
	public void tick() {
		if (!this.level.isClientSide) {
			this.level.setBlockAndUpdate(this.getBlockPos(),
					this.getBlockState().setValue(FuelGeneratorBlock.LIT, false));
			this.greenOrRed = 1;
			for (final IRecipe<?> recipe : this.level.getRecipeManager()
					.getAllRecipesFor(RecipeInit.FUEL_GENERATOR_RECIPE)) {
				final FuelGeneratorRecipe fuelGeneratorRecipe = (FuelGeneratorRecipe) recipe;
				if (fuelGeneratorRecipe.isValid(this.getItem(0))) {
					this.greenOrRed = 0;
					if (this.currentWaitTime == this.maxWaitTime) {
						this.setChanged();
						this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(),
								Constants.BlockFlags.BLOCK_UPDATE);
						if (ModHelper.canPlaceItemInStack(this.getItem(1), recipe.getResultItem())) {
							this.getItem(0).shrink(1);
							int oldCount = 0;
							if (this.getItem(1) != ItemStack.EMPTY)
								oldCount = this.getItem(1).getCount();
							this.setItem(1, new ItemStack(recipe.getResultItem().getItem(),
									recipe.getResultItem().getCount() + oldCount));
							this.currentWaitTime = 0;
							this.setChanged();
							this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(),
									Constants.BlockFlags.BLOCK_UPDATE);
						}
					} else
						this.currentWaitTime++;
					this.setChanged();
					this.level.setBlockAndUpdate(this.getBlockPos(),
							this.getBlockState().setValue(FuelGeneratorBlock.LIT, true));
				}

			}
		}
	}

	@Override
	public int getContainerSize() {
		return slots;
	}
	
	@Override
	protected int[] getOutputSlots() {
		return SLOTS_FOR_DOWN;
	}

	@Override
	public NonNullList<ItemStack> getItems() {
		return this.items;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> p_199721_1_) {
		this.items = p_199721_1_;

	}

	@Override
	protected ITextComponent getDefaultName() {
		return new TranslationTextComponent("container." + RexisEnergyAndDecor.MOD_ID + ".fuel_generator");
	}

	public void setCustomName(ITextComponent name) {
		this.customName = name;
	}

	public ITextComponent getName() {
		return this.customName != null ? this.customName : this.getDefaultName();
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.getName();
	}

	@Nullable
	public ITextComponent getCustomName() {
		return this.customName;
	}

	@Override
	protected Container createMenu(final int windowID, final PlayerInventory playerInv) {
		return new FuelGeneratorContainer(windowID, playerInv, this);
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);
		if (this.customName != null) {
			compound.putString("CustomName", ITextComponent.Serializer.toJson(this.customName));
		}
		if (!this.trySaveLootTable(compound)) {
			ItemStackHelper.saveAllItems(compound, this.items);
		}
		compound.putInt("CurrentWaitTime", this.currentWaitTime);
		return compound;
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		super.load(state, nbt);
		if (nbt.contains("CustomName", Constants.NBT.TAG_STRING)) {
			this.customName = ITextComponent.Serializer.fromJson(nbt.getString("CustomName"));
		}
		this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
		if (!this.tryLoadLootTable(nbt)) {
			ItemStackHelper.loadAllItems(nbt, this.items);
		}
		this.currentWaitTime = nbt.getInt("CurrentWaitTime");
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		if (side == Direction.DOWN)
			return SLOTS_FOR_DOWN;
		else if (side == Direction.UP)
			return SLOTS_FOR_UP;
		else
			return SLOTS_FOR_SIDES;
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack stack, Direction side) {
		if (index == 1)
			return false;
		else if (side == Direction.DOWN && index == 0)
			return false;
		else
			return true;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction side) {
		if (index == 0)
			return false;
		else
			return true;
	}
	
	@SuppressWarnings("static-access")
	public NonNullList<ItemStack> getAllItems() {
		NonNullList<ItemStack> allItems = NonNullList.withSize(this.slots, ItemStack.EMPTY);
		for (int i = 0; i <= this.slots - 1; ++i) {
			allItems.add(i, this.getItem(i));
		}
		return allItems;
	}

}
