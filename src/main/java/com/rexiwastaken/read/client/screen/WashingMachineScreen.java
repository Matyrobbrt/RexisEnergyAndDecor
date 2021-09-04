package com.rexiwastaken.read.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.rexiwastaken.read.RexisEnergyAndDecor;
import com.rexiwastaken.read.common.container.WashingMachineContainer;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WashingMachineScreen extends ContainerScreen<WashingMachineContainer> {

	private static final ResourceLocation WASHING_MACHINE_GUI = new ResourceLocation(RexisEnergyAndDecor.MOD_ID,
			"textures/gui/washing_machine.png");

	public WashingMachineScreen(WashingMachineContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);

		this.leftPos = 0;
		this.topPos = 0;
		this.imageWidth = 176;
		this.imageHeight = 201;
	}
	
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderTooltip(matrixStack, mouseX, mouseY);
	}
	
	@Override
	protected void renderLabels(MatrixStack matrixStack, int x, int y) {
		this.font.draw(matrixStack, this.inventory.getDisplayName(), 7.0f, 90.0f, 0x42AD98);
		this.font.draw(matrixStack, this.menu.te.getDisplayName(), 36.0f, 7.0f, 0x42AD98);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX,
			int mouseY) {
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		this.minecraft.textureManager.bind(WASHING_MACHINE_GUI);
		int x = (this.width - this.imageWidth) / 2;
		int y = (this.height - this.imageHeight) / 2;
		this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);

		this.blit(matrixStack, this.leftPos + 60, this.topPos + 24, 177, 24, this.menu.getProgressionScaled(), 31);
		this.blit(matrixStack, this.leftPos + 57, this.topPos + 79, 142, 249, this.menu.getREAmount(), 7);
		for (int i = 0; i <= 41; ++i) {
			if (this.menu.getWaterAmount() >= i) {
				this.blit(matrixStack, this.leftPos + 7, this.topPos + 21 + 41 - i, 177, 55 + 41 - i, 18, 1);
			}
		}
	}

}
