/*package com.rexiwastaken.read.common.material;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

// Made with Blockbench 3.9.2
// Exported for Minecraft version 1.15 - 1.16 with MCP mappings
// Paste this class into your mod and generate all required imports


public class Hazmat extends EntityModel<Entity> {
	private final ModelRenderer armorHead;
	private final ModelRenderer armorMaskFilterLeft_r1;
	private final ModelRenderer armorMaskFilterLeftBig_r1;
	private final ModelRenderer armorMaskFilterRightBig_r1;
	private final ModelRenderer armorMaskFilterRight_r1;
	private final ModelRenderer armorMaskConnectorLeft_r1;
	private final ModelRenderer armorMaskConnectorRight_r1;
	private final ModelRenderer armorMaskBase_r1;
	private final ModelRenderer armorBody;
	private final ModelRenderer armorRightArm;
	private final ModelRenderer armorLeftArm;
	private final ModelRenderer armorRightLeg;
	private final ModelRenderer armorRightBoot;
	private final ModelRenderer armorLeftLeg;
	private final ModelRenderer armorLeftBoot;

	public Hazmat() {
		texWidth = 64;
		texHeight = 64;

		armorHead = new ModelRenderer(this);
		armorHead.setPos(0.0F, 0.0F, 0.0F);
		head.addChild(armorHead);
		armorHead.texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);

		armorMaskFilterLeft_r1 = new ModelRenderer(this);
		armorMaskFilterLeft_r1.setPos(-3.6472F, -0.2549F, -5.8166F);
		armorHead.addChild(armorMaskFilterLeft_r1);
		setRotationAngle(armorMaskFilterLeft_r1, 1.1429F, 0.7863F, 0.9984F);
		armorMaskFilterLeft_r1.texOffs(54, 49).addBox(-1.5F, -1.5F, 0.25F, 3.0F, 3.0F, 2.0F, 0.0F, true);

		armorMaskFilterLeftBig_r1 = new ModelRenderer(this);
		armorMaskFilterLeftBig_r1.setPos(-3.6472F, -0.2549F, -5.8166F);
		armorHead.addChild(armorMaskFilterLeftBig_r1);
		setRotationAngle(armorMaskFilterLeftBig_r1, 1.0334F, 0.7254F, 0.839F);
		armorMaskFilterLeftBig_r1.texOffs(54, 49).addBox(-2.0F, -2.0F, -0.5F, 4.0F, 4.0F, 2.0F, 0.0F, true);

		armorMaskFilterRightBig_r1 = new ModelRenderer(this);
		armorMaskFilterRightBig_r1.setPos(3.6472F, -0.2549F, -5.8166F);
		armorHead.addChild(armorMaskFilterRightBig_r1);
		setRotationAngle(armorMaskFilterRightBig_r1, 1.0334F, -0.7254F, -0.839F);
		armorMaskFilterRightBig_r1.texOffs(54, 49).addBox(-2.0F, -2.0F, -0.5F, 4.0F, 4.0F, 2.0F, 0.0F, false);

		armorMaskFilterRight_r1 = new ModelRenderer(this);
		armorMaskFilterRight_r1.setPos(3.6472F, -0.2549F, -5.8166F);
		armorHead.addChild(armorMaskFilterRight_r1);
		setRotationAngle(armorMaskFilterRight_r1, 1.1429F, -0.7863F, -0.9984F);
		armorMaskFilterRight_r1.texOffs(54, 49).addBox(-1.5F, -1.5F, 0.25F, 3.0F, 3.0F, 2.0F, 0.0F, false);

		armorMaskConnectorLeft_r1 = new ModelRenderer(this);
		armorMaskConnectorLeft_r1.setPos(-1.0607F, -0.7325F, -3.3486F);
		armorHead.addChild(armorMaskConnectorLeft_r1);
		setRotationAngle(armorMaskConnectorLeft_r1, 0.4772F, 0.5956F, 0.5468F);
		armorMaskConnectorLeft_r1.texOffs(54, 33).addBox(-0.5F, -1.25F, -2.25F, 2.0F, 2.0F, 3.0F, 0.0F, true);

		armorMaskConnectorRight_r1 = new ModelRenderer(this);
		armorMaskConnectorRight_r1.setPos(1.0607F, -0.7325F, -3.3486F);
		armorHead.addChild(armorMaskConnectorRight_r1);
		setRotationAngle(armorMaskConnectorRight_r1, 0.4772F, -0.5956F, -0.5468F);
		armorMaskConnectorRight_r1.texOffs(54, 33).addBox(-1.5F, -1.25F, -2.25F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		armorMaskBase_r1 = new ModelRenderer(this);
		armorMaskBase_r1.setPos(0.0F, -0.4835F, -4.4944F);
		armorHead.addChild(armorMaskBase_r1);
		setRotationAngle(armorMaskBase_r1, 0.8262F, -0.5956F, -0.5468F);
		armorMaskBase_r1.texOffs(52, 43).addBox(-0.5F, -1.5F, -0.5F, 3.0F, 3.0F, 3.0F, 0.0F, false);

		armorBody = new ModelRenderer(this);
		armorBody.setPos(0.0F, 0.0F, 0.0F);
		body.addChild(armorBody);
		

		armorRightArm = new ModelRenderer(this);
		armorRightArm.setPos(0.0F, 0.0F, 0.0F);
		rightArm.addChild(armorRightArm);
		

		armorLeftArm = new ModelRenderer(this);
		armorLeftArm.setPos(0.0F, 0.0F, 0.0F);
		leftArm.addChild(armorLeftArm);
		

		armorRightLeg = new ModelRenderer(this);
		armorRightLeg.setPos(0.0F, 0.0F, 0.0F);
		rightLeg.addChild(armorRightLeg);
		

		armorRightBoot = new ModelRenderer(this);
		armorRightBoot.setPos(0.0F, 0.0F, 0.0F);
		rightLeg.addChild(armorRightBoot);
		

		armorLeftLeg = new ModelRenderer(this);
		armorLeftLeg.setPos(0.0F, 0.0F, 0.0F);
		leftLeg.addChild(armorLeftLeg);
		

		armorLeftBoot = new ModelRenderer(this);
		armorLeftBoot.setPos(0.0F, 0.0F, 0.0F);
		leftLeg.addChild(armorLeftBoot);
		
	}
	
	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		
	}
	
	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

	@Override
	public void setupAnim(Entity p_225597_1_, float p_225597_2_, float p_225597_3_, float p_225597_4_,
			float p_225597_5_, float p_225597_6_) {
		
	}

	@Override
	public void renderToBuffer(MatrixStack p_225598_1_, IVertexBuilder p_225598_2_, int p_225598_3_, int p_225598_4_,
			float p_225598_5_, float p_225598_6_, float p_225598_7_, float p_225598_8_) {
		
	}
} */