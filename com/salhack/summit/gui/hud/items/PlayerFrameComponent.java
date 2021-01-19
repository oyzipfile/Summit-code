// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.GlStateManager;
import java.text.DecimalFormat;
import com.salhack.summit.util.render.RenderUtil;
import net.minecraft.client.gui.ScaledResolution;
import com.salhack.summit.gui.hud.components.DraggableHudComponent;

public class PlayerFrameComponent extends DraggableHudComponent
{
    public PlayerFrameComponent() {
        super("PlayerFrame", 200.0f, 2.0f, 100.0f, 100.0f);
    }
    
    @Override
    public void onRender(final ScaledResolution res, final float mouseX, final float mouseY, final float partialTicks) {
        if (this.mc.world == null) {
            return;
        }
        super.onRender(res, mouseX, mouseY, partialTicks);
        RenderUtil.drawRect(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), -1727263732);
        final float l_HealthPct = (this.mc.player.getHealth() + this.mc.player.getAbsorptionAmount()) / this.mc.player.getMaxHealth() * 100.0f;
        final float l_HealthBarPct = Math.min(l_HealthPct, 100.0f);
        final float l_HungerPct = (this.mc.player.getFoodStats().getFoodLevel() + this.mc.player.getFoodStats().getSaturationLevel()) / 20.0f * 100.0f;
        final float l_HungerBarPct = Math.min(l_HungerPct, 100.0f);
        final DecimalFormat l_Format = new DecimalFormat("#.#");
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GuiInventory.drawEntityOnScreen((int)this.getX() + 10, (int)this.getY() + 30, 15, mouseX, mouseY, (EntityLivingBase)this.mc.player);
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        RenderUtil.drawStringWithShadow(this.mc.getSession().getUsername(), this.getX() + 20.0f, this.getY() + 4.0f, 16777215);
        RenderUtil.drawGradientRect(this.getX() + 20.0f, this.getY() + 11.0f, this.getX() + 20.0f + l_HealthBarPct, this.getY() + 22.0f, -1717570715, -1726742784);
        RenderUtil.drawGradientRect(this.getX() + 20.0f, this.getY() + 22.0f, this.getX() + 20.0f + l_HungerBarPct, this.getY() + 33.0f, -1711690747, -1711690747);
        RenderUtil.drawStringWithShadow(String.format("(%s) %s / %s", l_Format.format(l_HealthPct) + "%", l_Format.format(this.mc.player.getHealth() + this.mc.player.getAbsorptionAmount()), l_Format.format(this.mc.player.getMaxHealth())), this.getX() + 20.0f, this.getY() + 13.0f, 16777215);
        RenderUtil.drawStringWithShadow(String.format("(%s) %s / %s", l_Format.format(l_HungerPct) + "%", l_Format.format(this.mc.player.getFoodStats().getFoodLevel() + this.mc.player.getFoodStats().getSaturationLevel()), "20"), this.getX() + 20.0f, this.getY() + 24.0f, 16777215);
        this.setWidth(120.0f);
        this.setHeight(33.0f);
    }
}
