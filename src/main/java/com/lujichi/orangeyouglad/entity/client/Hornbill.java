package com.lujichi.orangeyouglad.entity.client;// Made with Blockbench 4.12.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class Hornbill<T extends Entity> extends HierarchicalModel<T> {
	private final ModelPart hornbill;
	private final ModelPart body;
	private final ModelPart bb_main;

	public Hornbill(ModelPart root) {
		this.hornbill = root.getChild("hornbill");
		this.body = this.hornbill.getChild("body");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition hornbill = partdefinition.addOrReplaceChild("hornbill", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition body = hornbill.addOrReplaceChild("body", CubeListBuilder.create().texOffs(18, 28).addBox(-2.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(20, 28).addBox(1.0F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(10, 37).addBox(-2.0F, 0.0F, -1.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(10, 39).addBox(1.0F, 0.0F, -1.0F, 1.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 37).addBox(-2.0F, -3.0F, 2.0F, 4.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.7F, 8.0F, 1.2217F, 0.0F, 0.0F));

		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(22, 0).addBox(-3.0F, -9.0F, -1.0F, 6.0F, 7.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(36, 31).addBox(-3.0F, -2.0F, 0.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(36, 36).addBox(-2.0F, -1.0F, 1.0F, 4.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.6F, 6.0F, 1.4807F, 0.0F, 0.0F));

		PartDefinition wing_l_r1 = body.addOrReplaceChild("wing_l_r1", CubeListBuilder.create().texOffs(0, 14).addBox(-1.0F, -4.0F, -2.0F, 1.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -3.3F, -1.1F, -0.1864F, -0.0379F, 0.0633F));

		PartDefinition wing_r_r1 = body.addOrReplaceChild("wing_r_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -4.0F, -2.0F, 1.0F, 4.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -3.3F, -1.1F, -0.1864F, 0.0379F, -0.0633F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r2 = bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 28).addBox(-1.0F, -2.0F, -6.0F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.15F, -3.0F, 0.2182F, 0.0F, 0.0F));

		PartDefinition cube_r3 = bb_main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(18, 31).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -6.9F, -9.0F, 0.1658F, 0.0F, 0.0F));

		PartDefinition cube_r4 = bb_main.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(22, 12).addBox(-1.0F, -1.0F, -3.0F, 2.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.9F, -9.0F, 0.1658F, 0.0F, 0.0F));

		PartDefinition cube_r5 = bb_main.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(-2, -2).addBox(-1.0F, -2.0F, -6.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.0F, -2.0F, 0.3491F, 0.0F, 0.0F));

		PartDefinition cube_r6 = bb_main.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(22, 22).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.3F, -2.0F, 0.0262F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		hornbill.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return hornbill;
	}

    public ModelPart getBody() {
        return hornbill;
    }
}