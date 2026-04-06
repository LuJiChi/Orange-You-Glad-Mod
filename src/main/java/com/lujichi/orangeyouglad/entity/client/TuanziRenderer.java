package com.lujichi.orangeyouglad.entity.client;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.lujichi.orangeyouglad.entity.custom.TuanziEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class TuanziRenderer extends MobRenderer<TuanziEntity, Tuanzi<TuanziEntity>> {
    public TuanziRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new Tuanzi<>(pContext.bakeLayer(ModModelLayers.TUANZI_LAYER)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(TuanziEntity pEntity) {
        return ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID, "textures/entity/tuanzi.png");
    }

    @Override
    public void render(TuanziEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
            pMatrixStack.scale(0.5F, 0.5F, 0.5F);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
