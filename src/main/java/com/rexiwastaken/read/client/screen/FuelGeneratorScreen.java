package com.rexiwastaken.read.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import com.rexiwastaken.read.RexisEnergyAndDecor;
import com.rexiwastaken.read.common.container.FuelGeneratorContainer;

import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FuelGeneratorScreen extends ContainerScreen<FuelGeneratorContainer> {
	
	private static final ResourceLocation FUEL_GENEARTOR_GUI = new ResourceLocation(RexisEnergyAndDecor.MOD_ID,
			"textures/gui/fuel_generator.png");

	public FuelGeneratorScreen(FuelGeneratorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);

		this.leftPos = 0;
		this.topPos = 0;
		this.imageWidth = 175;
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
		this.font.draw(matrixStack, this.inventory.getDisplayName(), 8.0f, 40.0f, 0x42AD98);
		this.font.draw(matrixStack, this.menu.te.getDisplayName(), 29.0f, 6.0f, 0x42AD98);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX,
			int mouseY) {
		RenderSystem.color4f(1f, 1f, 1f, 1f);
		this.minecraft.textureManager.bind(FUEL_GENEARTOR_GUI);
		int x = (this.width - this.imageWidth) / 2;
		int y = (this.height - this.imageHeight) / 2;
		this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
		
		this.blit(matrixStack, this.leftPos + 71, this.topPos + 22, 176, 0, this.menu.getSmeltProgressionScaled(), 10);
		if (this.menu.isGreen()) this.blit(matrixStack, this.leftPos + 6, this.topPos + 6, 177, 11, 9, 7);
		else this.blit(matrixStack, this.leftPos + 14, this.topPos + 6, 191, 11, 9, 7);
	}

}
