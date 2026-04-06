package com.lujichi.orangeyouglad.entity.client;// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class Tuanzi<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart tuanzi;
	private final ModelPart body;
	private final ModelPart ears;
	private final ModelPart tail;

	public Tuanzi(ModelPart root) {
		this.tuanzi = root.getChild("tuanzi");
		this.body = this.tuanzi.getChild("body");
		this.ears = this.tuanzi.getChild("ears");
		this.tail = this.tuanzi.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone = partdefinition.addOrReplaceChild("tuanzi", CubeListBuilder.create(), PartPose.offsetAndRotation(-8.0F, 24.0F, 8.0F, 0.0F, 3.1416F, 0.0F));

		PartDefinition body = bone.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-11.0F, -6.0F, 9.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition ears = bone.addOrReplaceChild("ears", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = ears.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(4, 18).addBox(0.0F, -4.0F, 1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.3F, -4.7F, 12.9F, 0.0F, 0.0F, -0.3927F));

		PartDefinition cube_r2 = ears.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 18).addBox(-1.0F, -4.0F, 1.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.7F, -4.7F, 12.9F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r3 = ears.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(16, 15).addBox(0.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.9F, -6.0F, 13.9F, 0.0F, 0.0F, 0.3927F));

		PartDefinition cube_r4 = ears.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(12, 15).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.1F, -6.0F, 13.9F, 0.0F, 0.0F, -0.3927F));

		PartDefinition tail = bone.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(6, 15).addBox(-8.5F, -3.8F, 3.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r5 = tail.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 12).addBox(-2.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, -2.1F, 1.6F, 0.4102F, 0.0F, 0.0F));

		PartDefinition cube_r6 = tail.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 15).addBox(-2.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, -3.1F, 4.3F, -0.7854F, 0.0F, 0.0F));

		PartDefinition cube_r7 = tail.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(12, 12).addBox(-2.0F, -1.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.5F, -1.7F, 5.7F, -0.3927F, 0.0F, 0.0F));

		PartDefinition cube_r8 = tail.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(6, 12).addBox(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.5F, 0.0F, 8.0F, 0.3927F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		double yVelocity = entity.getDeltaMovement().y;

		if (!entity.onGround()) {
			// 在空中时：身体被拉长（挤压拉伸效果）
			float stretch = 1.0f + (float)(Math.abs(yVelocity) * 1.5f);
			this.body.yScale = 1.0f / stretch;  // Y轴拉伸
			this.body.xScale = 1.0f; // stretch; // X轴收缩（保持体积感）
			this.body.zScale = 1.0f; // stretch; // Z轴收缩

			this.ears.yScale = 1.0f / stretch;  // Y轴拉伸
			this.ears.xScale = 1.0f; // stretch; // X轴收缩（保持体积感）
			this.ears.zScale = 1.0f; // stretch; // Z轴收缩
		} else if (entity.onGround() && yVelocity < -0.1) {
			// 刚落地时：身体被压扁（快速效果）
			this.body.yScale = 0.8f;
			this.body.xScale = 1.2f;
			this.body.zScale = 1.2f;

			this.ears.yScale = 0.8f;
			this.ears.xScale = 1.2f;
			this.ears.zScale = 1.2f;
		} else {
			// 待机状态：恢复原状
			this.body.yScale = 1.0f;
			this.body.xScale = 1.0f;
			this.body.zScale = 1.0f;
		}
		
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		tuanzi.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	public ModelPart root() {
		return tuanzi;
	}
}