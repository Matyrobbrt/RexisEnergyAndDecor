package com.rexiwastaken.read.common.te.machines;

import java.util.ArrayList;

import javax.annotation.Nullable;

import com.rexiwastaken.read.RexisEnergyAndDecor;
import com.rexiwastaken.read.common.block.WashingMachineBlock;
import com.rexiwastaken.read.common.container.WashingMachineContainer;
import com.rexiwastaken.read.common.te.LockableSidedInventoryTileEntity;
import com.rexiwastaken.read.core.helper.ItemLists;
import com.rexiwastaken.read.core.helper.TileEntityHelper;
import com.rexiwastaken.read.core.init.ItemInit;
import com.rexiwastaken.read.core.init.SoundInit;
import com.rexiwastaken.read.core.init.TileEntityTypesInit;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.util.Constants;

public class WashingMachineTileEntity extends LockableSidedInventoryTileEntity implements ITickableTileEntity {
	private ITextComponent customName;
	public static int slots = 5;
	protected NonNullList<ItemStack> items = NonNullList.withSize(slots, ItemStack.EMPTY);
	public int currentWaitTime;
	public int maxWaitTime = 2400;
	public int totalWaterBucketsStorage = 10;
	public int currentWaterBucketsStorage;
	public int totalREStorage = 10000;
	public int currentREStorage;
	public int consumedREPerOperation = 1000;
	public int consumedWaterBucketsPerOperation = 1;
	public int durabilityRestored = 25;
	public int greenOrRed = 1;
	private static final int[] SLOTS_FOR_UP = new int[] { 0 };
	private static final int[] SLOTS_FOR_DOWN = new int[] { 1, 2 };
	private static final int[] SLOTS_FOR_SIDES = new int[] { 2, 3, 4 };

	public WashingMachineTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn, slots);
	}

	public WashingMachineTileEntity() {
		this(TileEntityTypesInit.WASHING_MACHINE_TILE_ENTITY_TYPE.get());
	}

	@Override
	public void tick() {
		if (!this.level.isClientSide) {
			this.level.setBlockAndUpdate(this.getBlockPos(),
					this.getBlockState().setValue(WashingMachineBlock.LIT, false));
			if (this.getItem(2).getItem() == Items.WATER_BUCKET
					&& this.currentWaterBucketsStorage != this.totalWaterBucketsStorage) {
				this.currentWaterBucketsStorage++;
				this.setItem(2, new ItemStack(Items.BUCKET));
				TileEntityHelper.updateTE(this);
			}
			this.currentREStorage = TileEntityHelper.acceptRE(this, this.getItem(4), this.totalREStorage,
					this.currentREStorage);

			if (ItemLists.isHazmatSuitPiece(this.getItem(0)) && this.currentREStorage >= this.consumedREPerOperation
					&& this.currentWaterBucketsStorage >= this.consumedWaterBucketsPerOperation
					&& this.getItem(3).getItem() == ItemInit.SOAP.get()) {
				if (this.currentWaitTime == this.maxWaitTime) {
					if (this.getItem(0).getDamageValue() >= this.durabilityRestored) {
						ItemStack newArmourPiece = this.getItem(0).copy();
						newArmourPiece.setDamageValue(this.getItem(0).getDamageValue() - this.durabilityRestored);
						this.setItem(1, newArmourPiece);
						this.getItem(0).shrink(1);
						this.currentREStorage = this.currentREStorage - this.consumedREPerOperation;
						this.currentWaterBucketsStorage = this.currentWaterBucketsStorage
								- this.consumedWaterBucketsPerOperation;
						this.currentWaitTime = 0;
						this.getItem(3).shrink(1);
						TileEntityHelper.updateTE(this);
					}
				} else {
					this.currentWaitTime++;
					this.setChanged();
					this.level.setBlockAndUpdate(this.getBlockPos(),
							this.getBlockState().setValue(WashingMachineBlock.LIT, true));
				}
			}
		} else {
			if (ItemLists.isHazmatSuitPiece(this.getItem(0)) && this.currentREStorage >= this.consumedREPerOperation
					&& this.currentWaterBucketsStorage >= this.consumedWaterBucketsPerOperation
					&& this.getItem(3).getItem() == ItemInit.SOAP.get()) {
					ArrayList<PlayerEntity> rangePlayers = (ArrayList<PlayerEntity>) this.level
							.getEntitiesOfClass(PlayerEntity.class, new AxisAlignedBB(this.worldPosition).inflate(5.0));
					for (PlayerEntity player : rangePlayers) {
						player.playSound(SoundInit.WASHING_MACHINE_SOUND.get(), 0.8f, 1.0f);
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
		return new TranslationTextComponent("container." + RexisEnergyAndDecor.MOD_ID + ".washing_machine");
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
		return new WashingMachineContainer(windowID, playerInv, this);
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
		compound.putInt("CurrentWaterStorage", this.currentWaterBucketsStorage);
		compound.putInt("CurrentREStorage", this.currentREStorage);
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
		this.currentWaterBucketsStorage = nbt.getInt("CurrentWaterStorage");
		this.currentREStorage = nbt.getInt("CurrentREStorage");
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
		else if (side == Direction.DOWN)
			return false;
		else if (index == 0 && side != Direction.UP)
			return false;
		else if (index == 3 && stack.getItem() != ItemInit.SOAP.get() && side != Direction.UP)
			return false;
		else if (index == 2 && stack.getItem() != Items.WATER_BUCKET && side != Direction.UP)
			return false;
		else if (index == 4)
			return ItemLists.isRECoin(stack);
		else
			return true;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction side) {
		if (index == 1 && side == Direction.DOWN)
			return true;
		else if (index == 2) {
			if (stack.getItem() == Items.BUCKET && side == Direction.DOWN)
				return true;
			else
				return false;
		} else
			return false;
	}

	@Override
	public boolean canPlaceItem(int index, ItemStack stack) {
		return true;
	}
}
