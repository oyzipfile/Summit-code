// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.ingame;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemStack;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.util.EnumHandSide;
import net.minecraft.client.renderer.GlStateManager;
import com.salhack.summit.util.CameraUtils;
import com.salhack.summit.util.render.AWTFontRenderer;
import com.salhack.summit.events.render.EventRenderGameOverlay;
import net.minecraft.client.gui.ScaledResolution;
import com.salhack.summit.SummitMod;
import com.salhack.summit.main.SummitStatic;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.GuiIngameForge;

public class SalGuiIngame extends GuiIngameForge
{
    public SalGuiIngame(final Minecraft mc) {
        super(mc);
        ObfuscationReflectionHelper.setPrivateValue((Class)GuiIngame.class, (Object)this, (Object)new SalGuiPlayerTabOverlay(mc, (GuiIngame)this), new String[] { "field_175196_v" });
        if (SummitStatic.RELIANTCHAT != null && SummitStatic.RELIANTCHAT.isEnabled()) {
            SummitStatic.RELIANTCHAT.Activate();
        }
    }
    
    public void renderGameOverlay(final float partialTicks) {
        super.renderGameOverlay(partialTicks);
        if (!this.mc.gameSettings.showDebugInfo) {
            SummitMod.EVENT_BUS.post(new EventRenderGameOverlay(partialTicks, new ScaledResolution(this.mc)));
        }
        AWTFontRenderer.garbageCollectionTick();
    }
    
    public void renderHotbar(final ScaledResolution sr, final float partialTicks) {
        if (!CameraUtils.freecamEnabled()) {
            super.renderHotbar(sr, partialTicks);
            return;
        }
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(SalGuiIngame.WIDGETS_TEX_PATH);
        final EntityPlayer entityplayer = (EntityPlayer)this.mc.player;
        final ItemStack itemstack = entityplayer.getHeldItemOffhand();
        final EnumHandSide enumhandside = entityplayer.getPrimaryHand().opposite();
        final int i = sr.getScaledWidth() / 2;
        final float f = this.zLevel;
        this.zLevel = -90.0f;
        this.drawTexturedModalRect(i - 91, sr.getScaledHeight() - 22, 0, 0, 182, 22);
        this.drawTexturedModalRect(i - 91 - 1 + entityplayer.inventory.currentItem * 20, sr.getScaledHeight() - 22 - 1, 0, 22, 24, 22);
        if (!itemstack.isEmpty()) {
            if (enumhandside == EnumHandSide.LEFT) {
                this.drawTexturedModalRect(i - 91 - 29, sr.getScaledHeight() - 23, 24, 22, 29, 24);
            }
            else {
                this.drawTexturedModalRect(i + 91, sr.getScaledHeight() - 23, 53, 22, 29, 24);
            }
        }
        this.zLevel = f;
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderHelper.enableGUIStandardItemLighting();
        for (int l = 0; l < 9; ++l) {
            final int i2 = i - 90 + l * 20 + 2;
            final int j1 = sr.getScaledHeight() - 16 - 3;
            this.renderHotbarItem(i2, j1, partialTicks, entityplayer, (ItemStack)entityplayer.inventory.mainInventory.get(l));
        }
        if (!itemstack.isEmpty()) {
            final int l2 = sr.getScaledHeight() - 16 - 3;
            if (enumhandside == EnumHandSide.LEFT) {
                this.renderHotbarItem(i - 91 - 26, l2, partialTicks, entityplayer, itemstack);
            }
            else {
                this.renderHotbarItem(i + 91 + 10, l2, partialTicks, entityplayer, itemstack);
            }
        }
        if (this.mc.gameSettings.attackIndicator == 2) {
            final float f2 = this.mc.player.getCooledAttackStrength(0.0f);
            if (f2 < 1.0f) {
                final int i3 = sr.getScaledHeight() - 20;
                int j2 = i + 91 + 6;
                if (enumhandside == EnumHandSide.RIGHT) {
                    j2 = i - 91 - 22;
                }
                this.mc.getTextureManager().bindTexture(Gui.ICONS);
                final int k1 = (int)(f2 * 19.0f);
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                this.drawTexturedModalRect(j2, i3, 0, 94, 18, 18);
                this.drawTexturedModalRect(j2, i3 + 18 - k1, 18, 112 - k1, 18, k1);
            }
        }
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
    }
}
