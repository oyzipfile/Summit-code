// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.misc;

import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.client.EventClientTick;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Module;

public class StopWatch extends Module
{
    public long StartMS;
    public long ElapsedMS;
    @EventHandler
    private Listener<EventClientTick> OnTick;
    
    public StopWatch() {
        super("Stopwatch", new String[] { "" }, "Counts a stopwatch starting from 0 when toggled.", "NONE", -1, ModuleType.MISC);
        this.OnTick = new Listener<EventClientTick>(p_Event -> this.ElapsedMS = System.currentTimeMillis());
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.StartMS = System.currentTimeMillis();
        this.ElapsedMS = System.currentTimeMillis();
    }
}
