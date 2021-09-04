package com.rexiwastaken.read.common.container;

import java.util.Objects;

import com.rexiwastaken.read.common.slot.OutputSlot;
import com.rexiwastaken.read.common.te.machines.FuelGeneratorTileEntity;
import com.rexiwastaken.read.core.init.BlockInit;
import com.rexiwastaken.read.core.init.ContainerTypesInit;
import com.rexiwastaken.read.core.util.FunctionalIntReferenceHolder;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IWorldPosCallable;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FuelGeneratorContainer extends Container {

	public final FuelGeneratorTileEntity te;
	private final IWorldPosCallable canInteractWithCallable;
	public FunctionalIntReferenceHolder currentWaitTime;
	public FunctionalIntReferenceHolder isGreenLight;

	public FuelGeneratorContainer(final int windowId, final PlayerInventory playerInv,
			final FuelGeneratorTileEntity te) {
		super(ContainerTypesInit.FUEL_GENERATOR_CONTAINER_TYPE.get(), windowId);
		this.te = te;
		this.canInteractWithCallable = IWorldPosCallable.create(te.getLevel(), te.getBlockPos());

		// Tile Entity
		this.addSlot(new Slot((IInventory) te, 0, 44, 20));
		this.addSlot(new OutputSlot((IInventory) te, 1, 116, 20));

		// Main Player Inventory
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 9; col++) {
				this.addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 133 - (4 - row) * 18 - 10));
			}
		}

		// Player Hotbar
		for (int col = 0; col < 9; col++) {
			this.addSlot(new Slot(playerInv, col, 8 + col * 18, 109));
		}

		this.addDataSlot(currentWaitTime = new FunctionalIntReferenceHolder(() -> this.te.currentWaitTime,
				value -> this.te.currentWaitTime = value));
		this.addDataSlot(isGreenLight = new FunctionalIntReferenceHolder(() -> this.te.greenOrRed,
				value -> this.te.greenOrRed = value));
	}

	public FuelGeneratorContainer(final int windowId, final PlayerInventory playerInv, final PacketBuffer data) {
		this(windowId, playerInv, getTileEntity(playerInv, data));
	}

	private static FuelGeneratorTileEntity getTileEntity(final PlayerInventory playerInv, final PacketBuffer data) {
		Objects.requireNonNull(playerInv, "Player Inventory cannot be null.");
		Objects.requireNonNull(data, "Packet Buffer cannot be null.");
		final TileEntity te = playerInv.player.level.getBlockEntity(data.readBlockPos());
		if (te instanceof FuelGeneratorTileEntity) {
			return (FuelGeneratorTileEntity) te;
		}
		throw new IllegalStateException("Tile Entity Is Not Correct");
	}

	@Override
	public boolean stillValid(PlayerEntity p_75145_1_) {
		return stillValid(canInteractWithCallable, p_75145_1_, BlockInit.FUEL_GENERATOR.get());
	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack stack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack stack1 = slot.getItem();
			stack = stack1.copy();
			if (index < FuelGeneratorTileEntity.slots
					&& !this.moveItemStackTo(stack1, FuelGeneratorTileEntity.slots, this.slots.size(), true)) {
				return ItemStack.EMPTY;
			}
			if (!this.moveItemStackTo(stack1, 0, FuelGeneratorTileEntity.slots, false)) {
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
				? this.currentWaitTime.get() * 35 / this.te.maxWaitTime
				: 0;
	}
	
	@OnlyIn(Dist.CLIENT)
	public boolean isGreen() {
		if (this.isGreenLight.get() == 0) return true;
		else return false;
	}
	
}
