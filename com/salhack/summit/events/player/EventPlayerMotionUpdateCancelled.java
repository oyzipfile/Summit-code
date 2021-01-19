// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.player;

import com.salhack.summit.events.MinecraftEvent;

public class EventPlayerMotionUpdateCancelled extends EventPlayerMotionUpdate
{
    public EventPlayerMotionUpdateCancelled(final Stage stage, final float pitch, final float yaw) {
        super(stage, 0.0, 0.0, 0.0, false);
        this._pitch = pitch;
        this._yaw = yaw;
    }
}
