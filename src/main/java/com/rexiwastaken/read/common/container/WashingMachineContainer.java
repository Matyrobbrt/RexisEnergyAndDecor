package com.rexiwastaken.read.common.container;

import java.util.Objects;

import com.rexiwastaken.read.common.slot.ItemReservedSlot;
import com.rexiwastaken.read.common.slot.OutputSlot;
import com.rexiwastaken.read.common.slot.RECoinsSlot;
import com.rexiwastaken.read.common.te.machines.WashingMachineTileEntity;
import com.rexiwastaken.read.core.init.BlockInit;
import com.rexiwastaken.read.core.init.ContainerTypesInit;
import com.rexiwastaken.read.core.init.ItemInit;
import com.rexiwastaken.read.core.util.FunctionalIntReferenceHolder;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WashingMachineContainer extends Container {
	
	public final WashingMachineTileEntity te;
	private final IWorldPosCallable canInteractWithCallable;
	public FunctionalIntReferenceHolder currentWaitTime;
	public FunctionalIntReferenceHolder isGreenLight;
	public FunctionalIntReferenceHolder waterAmount;
	public FunctionalIntReferenceHolder REAmount;

	@Override
	public boolean stillValid(PlayerEntity p_75145_1_) {
		return stillValid(canInteractWithCallable, p_75145_1_, BlockInit.WASHING_MACHINE.get());
	}
	
	public WashingMachineContainer(final int windowId, final PlayerInventory playerInv,
			final WashingMachineTileEntity te) {
		super(ContainerTypesInit.WASHING_MACHINE_CONTAINER_TYPE.get(), windowId);
		this.te = te;
		this.canInteractWithCallable = IWorldPosCallable.create(te.getLevel(), te.getBlockPos());
		
		//Tile Entity
		this.addSlot(new Slot((IInventory) te, 0, 37, 49)); //Input 
		this.addSlot(new OutputSlot((IInventory) te, 1, 136, 34)); //Output
		this.addSlot(new ItemReservedSlot((IInventory) te, 2, 8, 70, Items.WATER_BUCKET)); //Water
		this.addSlot(new ItemReservedSlot((IInventory) te, 3, 37, 19, ItemInit.SOAP.get())); //Soap
		this.addSlot(new RECoinsSlot((IInventory) te, 4, 37, 70)); //RE slot
		
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 182 - (4 - row) * 18 - 10));
			}
		}

		// Player Hotbar
		for (int col = 0; col < 9; col++) {
			this.addSlot(new Slot(playerInv, col, 8 + col * 18, 158));
		}
		
		this.addDataSlot(currentWaitTime = new FunctionalIntReferenceHolder(() -> this.te.currentWaitTime,
				value -> this.te.currentWaitTime = value));
		this.addDataSlot(isGreenLight = new FunctionalIntReferenceHolder(() -> this.te.greenOrRed,
				value -> this.te.greenOrRed = value));
		this.addDataSlot(waterAmount = new FunctionalIntReferenceHolder(() -> this.te.currentWaterBucketsStorage,
				value -> this.te.currentWaterBucketsStorage = value));
		this.addDataSlot(REAmount = new FunctionalIntReferenceHolder(() -> this.te.currentREStorage,
				value -> this.te.currentREStorage = value));
	}
	
	public WashingMachineContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
		this(windowId, playerInv, getTileEntity(playerInv, data));
	}

	private static WashingMachineTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
		Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
		Objects.requireNonNull(data, "Packet Buffer cannot be null.");
		final TileEntity te = playerInv.player.level.getBlockEntity(data.readBlockPos());
		if (te instanceof WashingMachineTileEntity) {
			return (WashingMachineTileEntity) te;
		}
		throw new IllegalStateException("Tile Entity Is Not Correct");
	}
	
	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();
			if (index < WashingMachineTileEntity.slots
					&& !this.moveItemStackTo(stack1, WashingMachineTileEntity.slots, this.slots.size(), true)) {
				return ItemStack.EMPTY;
			}
			if (!this.moveItemStackTo(stack1, 0, WashingMachineTileEntity.slots, false)) {
				return ItemStack.EMPTY;
			}

			if (stack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}
		return stack;
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getProgressionScaled() {
		return this.currentWaitTime.get() != 0 && this.te.maxWaitTime != 0
				? this.currentWaitTime.get() * 67 / this.te.maxWaitTime
				: 0;
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getWaterAmount() {
		return this.waterAmount.get() != 0 && this.te.totalWaterBucketsStorage != 0
				? this.waterAmount.get() * 42 / this.te.totalWaterBucketsStorage
				: 0;
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getREAmount() {
		return this.REAmount.get() != 0 && this.te.totalREStorage != 0
				? this.REAmount.get() * 114 / this.te.totalREStorage
				: 0;
	}
	
	@OnlyIn(Dist.CLIENT)
	public boolean isGreen() {
		if (this.isGreenLight.get() == 0) return true;
		else return false;
	}

}
