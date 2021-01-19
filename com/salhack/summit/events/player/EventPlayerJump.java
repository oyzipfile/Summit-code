// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.player;

import com.salhack.summit.events.MinecraftEvent;

public class EventPlayerJump extends MinecraftEvent
{
    public double MotionX;
    public double MotionY;
    
    public EventPlayerJump(final double p_MotionX, final double p_MotionY) {
        this.MotionX = p_MotionX;
        this.MotionY = p_MotionY;
    }
}
