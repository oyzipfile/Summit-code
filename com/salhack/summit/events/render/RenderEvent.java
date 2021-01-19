// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.render;

import com.salhack.summit.events.MinecraftEvent;

public class RenderEvent extends MinecraftEvent
{
    private float _partialTicks;
    
    public RenderEvent(final float partialTicks) {
        this._partialTicks = partialTicks;
    }
    
    @Override
    public float getPartialTicks() {
        return this._partialTicks;
    }
}
