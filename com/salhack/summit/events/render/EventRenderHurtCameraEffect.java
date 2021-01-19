// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.render;

import com.salhack.summit.events.MinecraftEvent;

public class EventRenderHurtCameraEffect extends MinecraftEvent
{
    public float Ticks;
    
    public EventRenderHurtCameraEffect(final float p_Ticks) {
        this.Ticks = p_Ticks;
    }
}
