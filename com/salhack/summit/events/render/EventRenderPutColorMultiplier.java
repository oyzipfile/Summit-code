// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.render;

import com.salhack.summit.events.MinecraftEvent;

public class EventRenderPutColorMultiplier extends MinecraftEvent
{
    private float _opacity;
    
    public void setOpacity(final float opacity) {
        this._opacity = opacity;
    }
    
    public float getOpacity() {
        return this._opacity;
    }
}
