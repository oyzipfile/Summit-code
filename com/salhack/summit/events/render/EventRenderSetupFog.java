// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.render;

import com.salhack.summit.events.MinecraftEvent;

public class EventRenderSetupFog extends MinecraftEvent
{
    public int StartCoords;
    public float PartialTicks;
    
    public EventRenderSetupFog(final int startCoords, final float partialTicks) {
        this.StartCoords = startCoords;
        this.PartialTicks = partialTicks;
    }
}
