package com.lujichi.orangeyouglad.screen;

import com.lujichi.orangeyouglad.OrangeYouGladMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class FlariteFurnaceScreen extends AbstractContainerScreen<FlariteFurnaceMenu> {
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(OrangeYouGladMod.MOD_ID, "textures/gui/flarite_furnace_gui.png");
    public FlariteFurnaceScreen(FlariteFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void init() {
        super.init();
        // 设置标题位置 - 类似熔炉的布局
        this.titleLabelX = (imageWidth - this.font.width(this.title)) / 2; // 居中显示容器标题
        this.titleLabelY = 6; // 距离顶部6像素
        this.inventoryLabelX = 8; // 玩家物品栏标题左对齐
        this.inventoryLabelY = imageHeight - 94; // 在玩家物品栏上方
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float pPartialTick,int pMousex,int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        guiGraphics.blit(TEXTURE, x, y, 0, 0, imageWidth, imageHeight);

        renderProgressArrow(guiGraphics, x, y);

        renderTalentBar(guiGraphics, x, y);
    }
    private void renderProgressArrow(GuiGraphics guiGraphics,int x,int y) {
        if(menu.isCrafting()) {
            guiGraphics.blit(TEXTURE,x + 74,y + 29,176,14,menu.getScaledProgress(),23);
            int flameWidth = 14; // 火焰纹理的完整宽度
            guiGraphics.blit(TEXTURE,x + 26,y + 37,176,0,flameWidth,14);
        }
    }

    private void renderTalentBar(GuiGraphics guiGraphics,int x,int y) {

        // 渲染才华值填充
        int scaledTalent = menu.getScaledTalent();
        if (scaledTalent > 0) {
            guiGraphics.blit(TEXTURE, x + 12, y + 10 + (60 - scaledTalent), 177, 37 + (60 - scaledTalent), 5, scaledTalent);
        }
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
        // 渲染容器标题（火炉名称）
        guiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 0x404040, false);

        // 渲染玩家物品栏标题
        guiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 0x404040, false);
    }

    @Override
    public void render(GuiGraphics guiGraphics,int mousex,int mouseY, float delta){
        renderBackground(guiGraphics);
        super.render(guiGraphics, mousex, mouseY, delta);
        renderTooltip(guiGraphics, mousex, mouseY);

        // 显示才华值提示
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        if (isHovering(12, 10, 5, 60, mousex, mouseY)) {
            int talentValue = menu.getTalentValue();
            int maxTalentValue = menu.getMaxTalentValue();
            guiGraphics.renderTooltip(this.font, Component.translatable("tooltip.flarite_furnace.talent", talentValue, maxTalentValue), mousex, mouseY);
        }
    }

}
