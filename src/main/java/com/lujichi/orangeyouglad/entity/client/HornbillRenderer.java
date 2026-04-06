package com.lujichi.orangeyouglad.entity.client;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.entity.custom.HornbillEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HornbillRenderer extends MobRenderer<HornbillEntity, Hornbill<HornbillEntity>> {
    public HornbillRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Hornbill<>(pContext.bakeLayer(ModModelLayers.HORNBILL_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(HornbillEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID, "textures/entity/hornbill.png");
    }

    @Override
        public void render(HornbillEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
            pMatrixStack.scale(0.5F, 0.5F, 0.5F);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
