// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.util.Timer;
import com.salhack.summit.gui.hud.components.OptionalListHudComponent;

public class FPSComponent extends OptionalListHudComponent
{
    private Timer timer;
    private int[] FPS;
    @EventHandler
    private Listener<EventPlayerUpdate> onPlayerUpdate;
    
    public FPSComponent() {
        super("FPS", 2.0f, 140.0f);
        this.timer = new Timer();
        this.FPS = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        int i;
        this.onPlayerUpdate = new Listener<EventPlayerUpdate>(event -> {
            if (this.timer.passed(1000.0)) {
                this.timer.reset();
                for (i = 0; i < this.FPS.length - 1; ++i) {
                    this.FPS[i] = this.FPS[i + 1];
                }
                this.FPS[this.FPS.length - 1] = Minecraft.getDebugFPS();
            }
            return;
        });
        this.setEnabled(true);
    }
    
    @Override
    public void onUpdate() {
        this.cornerItem.setName("FPS " + ChatFormatting.WHITE + Minecraft.getDebugFPS() + ChatFormatting.RESET + " Average " + ChatFormatting.WHITE + this.getAverage());
    }
    
    private int getAverage() {
        int avg = 0;
        for (final int i : this.FPS) {
            avg += i;
        }
        return avg / 10;
    }
}
