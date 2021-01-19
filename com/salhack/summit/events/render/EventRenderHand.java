// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.render;

import com.salhack.summit.events.MinecraftEvent;

public class EventRenderHand extends MinecraftEvent
{
    public float PartialTicks;
    public int Pass;
    
    public EventRenderHand(final float partialTicks, final int pass) {
        this.PartialTicks = partialTicks;
        this.Pass = pass;
    }
}
