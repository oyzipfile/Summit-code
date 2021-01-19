// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.managers;

import com.salhack.summit.main.Summit;
import com.salhack.summit.SummitMod;
import com.salhack.summit.events.MinecraftEvent;
import com.salhack.summit.events.player.EventPlayerMotionUpdateCancelled;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.util.Timer;
import com.salhack.summit.events.bus.EventListener;

public class RotationManager implements EventListener
{
    private float[] _rotations;
    private Timer timer;
    @EventHandler
    private Listener<EventPlayerUpdate> onPlayerUpdate;
    @EventHandler
    private Listener<EventPlayerMotionUpdateCancelled> onMotionUpdate;
    
    public RotationManager() {
        this.timer = new Timer();
        this.onPlayerUpdate = new Listener<EventPlayerUpdate>(event -> {
            if (this.timer.passed(100.0)) {
                this.timer.reset();
                this.resetRotations();
            }
            return;
        });
        this.onMotionUpdate = new Listener<EventPlayerMotionUpdateCancelled>(event -> {
            this.timer.reset();
            if (event.getStage() == MinecraftEvent.Stage.Pre) {
                this.setRotations(event.getYaw(), event.getPitch());
            }
        });
    }
    
    public void init() {
        SummitMod.EVENT_BUS.subscribe(this);
    }
    
    public void resetRotations() {
        this._rotations = null;
    }
    
    public float getYawForMixin(final float yaw) {
        return (this._rotations != null) ? this._rotations[0] : yaw;
    }
    
    public float getPitchForMixin(final float pitch) {
        return (this._rotations != null) ? this._rotations[1] : pitch;
    }
    
    public void setRotations(final float yaw, final float pitch) {
        this._rotations = new float[] { yaw, pitch };
    }
    
    public float[] getRotations() {
        return this._rotations;
    }
    
    public static RotationManager Get() {
        return Summit.GetRotationManager();
    }
}
