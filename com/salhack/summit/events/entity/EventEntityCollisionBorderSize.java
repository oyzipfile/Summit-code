// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.entity;

import com.salhack.summit.events.MinecraftEvent;

public class EventEntityCollisionBorderSize extends MinecraftEvent
{
    private float size;
    
    public float getSize() {
        return this.size;
    }
    
    public void setSize(final float s) {
        this.size = s;
    }
}
