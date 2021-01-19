// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.managers;

import com.salhack.summit.main.Summit;
import com.salhack.summit.SummitMod;
import net.minecraft.util.math.MathHelper;
import net.minecraft.network.play.server.SPacketTimeUpdate;
import com.salhack.summit.events.MinecraftEvent;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.network.EventServerPacket;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.events.bus.EventListener;

public class TickRateManager implements EventListener
{
    private long prevTime;
    private float[] ticks;
    private int currentTick;
    @EventHandler
    private Listener<EventServerPacket> onServerPacket;
    
    public TickRateManager() {
        this.ticks = new float[20];
        this.onServerPacket = new Listener<EventServerPacket>(event -> {
            if (event.getStage() != MinecraftEvent.Stage.Pre) {
                return;
            }
            else {
                if (event.getPacket() instanceof SPacketTimeUpdate) {
                    if (this.prevTime != -1L) {
                        this.ticks[this.currentTick % this.ticks.length] = MathHelper.clamp(20.0f / ((System.currentTimeMillis() - this.prevTime) / 1000.0f), 0.0f, 20.0f);
                        ++this.currentTick;
                    }
                    this.prevTime = System.currentTimeMillis();
                }
                return;
            }
        });
        this.prevTime = -1L;
        for (int i = 0, len = this.ticks.length; i < len; ++i) {
            this.ticks[i] = 0.0f;
        }
        SummitMod.EVENT_BUS.subscribe(this);
    }
    
    public float getTickRate() {
        int tickCount = 0;
        float tickRate = 0.0f;
        for (int i = 0; i < this.ticks.length; ++i) {
            final float tick = this.ticks[i];
            if (tick > 0.0f) {
                tickRate += tick;
                ++tickCount;
            }
        }
        return MathHelper.clamp(tickRate / tickCount, 0.0f, 20.0f);
    }
    
    public static TickRateManager Get() {
        return Summit.GetTickRateManager();
    }
}
