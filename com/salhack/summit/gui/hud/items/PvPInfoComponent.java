// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import com.salhack.summit.util.render.RenderUtil;
import com.salhack.summit.managers.HudManager;
import com.salhack.summit.main.SummitStatic;
import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.gui.ScaledResolution;
import com.salhack.summit.module.Value;
import com.salhack.summit.gui.hud.components.DraggableHudComponent;

public class PvPInfoComponent extends DraggableHudComponent
{
    public final Value<Boolean> RainbowVal;
    
    public PvPInfoComponent() {
        super("PvPInfo", 2.0f, 290.0f, 100.0f, 100.0f);
        this.RainbowVal = new Value<Boolean>("Rainbow", new String[] { "" }, "Makes a dynamic rainbow", true);
    }
    
    @Override
    public void onRender(final ScaledResolution res, final float p_MouseX, final float p_MouseY, final float p_PartialTicks) {
        super.onRender(res, p_MouseX, p_MouseY, p_PartialTicks);
        final String aura = "KA " + ChatFormatting.WHITE + (SummitStatic.AURA.isEnabled() ? (ChatFormatting.GREEN + "ON") : (ChatFormatting.RED + "OFF"));
        final String crystal = "CA " + ChatFormatting.WHITE + (SummitStatic.AUTOCRYSTAL.isEnabled() ? (ChatFormatting.GREEN + "ON") : (ChatFormatting.RED + "OFF"));
        final String autoTrap = "AT " + ChatFormatting.WHITE + (SummitStatic.AUTOTRAP.isEnabled() ? (ChatFormatting.GREEN + "ON") : (ChatFormatting.RED + "OFF"));
        final String surround = "SU " + ChatFormatting.WHITE + (SummitStatic.SURROUND.isEnabled() ? (ChatFormatting.GREEN + "ON") : (ChatFormatting.RED + "OFF"));
        final String speed = "SP " + ChatFormatting.WHITE + (SummitStatic.SPEED.isEnabled() ? (ChatFormatting.GREEN + "ON") : (ChatFormatting.RED + "OFF"));
        RenderUtil.drawStringWithShadow(aura, this.getX(), this.getY(), ((boolean)this.RainbowVal.getValue()) ? HudManager.Get().rainbow.GetRainbowColorAt(0) : 11184810);
        RenderUtil.drawStringWithShadow(crystal, this.getX(), this.getY() + 12.0f, ((boolean)this.RainbowVal.getValue()) ? HudManager.Get().rainbow.GetRainbowColorAt(0) : 11184810);
        RenderUtil.drawStringWithShadow(autoTrap, this.getX(), this.getY() + 24.0f, ((boolean)this.RainbowVal.getValue()) ? HudManager.Get().rainbow.GetRainbowColorAt(0) : 11184810);
        RenderUtil.drawStringWithShadow(surround, this.getX(), this.getY() + 36.0f, ((boolean)this.RainbowVal.getValue()) ? HudManager.Get().rainbow.GetRainbowColorAt(0) : 11184810);
        RenderUtil.drawStringWithShadow(speed, this.getX(), this.getY() + 48.0f, ((boolean)this.RainbowVal.getValue()) ? HudManager.Get().rainbow.GetRainbowColorAt(0) : 11184810);
        this.setWidth(RenderUtil.getStringWidth(aura));
        this.setHeight(RenderUtil.getStringHeight(crystal) + RenderUtil.getStringHeight(aura) + RenderUtil.getStringHeight(autoTrap) + RenderUtil.getStringHeight(surround) + RenderUtil.getStringHeight(speed));
    }
}
