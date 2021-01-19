// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.minecraft;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;
import com.salhack.summit.main.AlwaysEnabledModule;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import com.salhack.summit.main.SummitStatic;
import com.salhack.summit.util.Timer;
import net.minecraft.client.gui.GuiButton;

public class SalGuiReconnectButton extends GuiButton
{
    private Timer timer;
    private float ReconnectTimer;
    
    public SalGuiReconnectButton(final int buttonId, final int x, final int y, final String buttonText) {
        super(buttonId, x, y, buttonText);
        (this.timer = new Timer()).reset();
        this.ReconnectTimer = SummitStatic.AUTORECONNECT.Delay.getValue() * 1000.0f;
    }
    
    public void drawButton(final Minecraft mc, final int mouseX, final int mouseY, final float partialTicks) {
        super.drawButton(mc, mouseX, mouseY, partialTicks);
        if (this.visible) {
            if (SummitStatic.AUTORECONNECT.isEnabled() && !this.timer.passed(this.ReconnectTimer)) {
                this.displayString = "AutoReconnect (" + TimeUnit.MILLISECONDS.toSeconds(Math.abs(this.timer.getTime() + (long)this.ReconnectTimer - System.currentTimeMillis())) + ")";
            }
            else if (!SummitStatic.AUTORECONNECT.isEnabled()) {
                this.displayString = "AutoReconnect";
            }
            if (this.timer.passed(this.ReconnectTimer) && SummitStatic.AUTORECONNECT.isEnabled() && AlwaysEnabledModule.LastIP != null && AlwaysEnabledModule.LastPort != -1 && mc.world == null) {
                mc.displayGuiScreen((GuiScreen)new GuiConnecting((GuiScreen)null, mc, AlwaysEnabledModule.LastIP, AlwaysEnabledModule.LastPort));
            }
        }
    }
    
    public void Clicked() {
        SummitStatic.AUTORECONNECT.toggle();
        this.timer.reset();
    }
}
