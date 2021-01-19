// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.movement;

import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerTravel;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public class HighJump extends Module
{
    public final Value<Boolean> InAir;
    public final Value<Float> Height;
    @EventHandler
    private Listener<EventPlayerTravel> OnTravel;
    
    public HighJump() {
        super("HighJump", new String[] { "AW" }, "Jump way higher than a normal jump", "NONE", 12723419, ModuleType.MOVEMENT);
        this.InAir = new Value<Boolean>("InAir", new String[] { "Air", "OnGroundOnly", "OnGround", "GroundOnly", "Ground" }, "Should you be able to jump in air", true);
        this.Height = new Value<Float>("Height", new String[] { "Height", "Heigh", "Hight", "High", "Size", "Scaling", "Scale" }, "Height to increase", 1.4f, 0.0f, 10.0f, 1.0f);
        this.OnTravel = new Listener<EventPlayerTravel>(event -> {
            if (!event.isCancelled()) {
                if (this.mc.player != null && !this.mc.player.isRiding() && !this.mc.player.isElytraFlying()) {
                    if ((this.mc.player.movementInput.jump && this.InAir.getValue()) || this.mc.player.onGround) {
                        event.cancel();
                        this.mc.player.motionY = this.Height.getValue();
                    }
                }
            }
        });
    }
}
