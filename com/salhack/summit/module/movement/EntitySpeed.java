// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.movement;

import net.minecraft.entity.Entity;
import com.salhack.summit.util.MathUtil;
import com.salhack.summit.events.player.EventPlayerTravel;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public class EntitySpeed extends Module
{
    public final Value<Float> Speed;
    @EventHandler
    private Listener<EventPlayerUpdate> onPlayerUpdate;
    @EventHandler
    private Listener<EventPlayerTravel> OnTravel;
    
    public EntitySpeed() {
        super("EntitySpeed", new String[] { "HorseHax" }, "Allows you to modify the horses speed", "NONE", -1, ModuleType.MOVEMENT);
        this.Speed = new Value<Float>("Speed", new String[] { "" }, "Speed to use", 0.5f, 0.0f, 10.0f, 1.0f);
        this.onPlayerUpdate = new Listener<EventPlayerUpdate>(event -> this.setMetaData(this.getMetaData()));
        Entity riding;
        double[] dir;
        this.OnTravel = new Listener<EventPlayerTravel>(event -> {
            if (this.mc.player == null || !this.mc.player.isRiding()) {
                return;
            }
            else {
                riding = this.mc.player.getRidingEntity();
                dir = MathUtil.directionSpeed(this.Speed.getValue());
                if (this.mc.player.movementInput.moveStrafe != 0.0f || this.mc.player.movementInput.moveForward != 0.0f) {
                    riding.motionX = dir[0];
                    riding.motionZ = dir[1];
                }
                event.cancel();
                return;
            }
        });
        this.setMetaData(this.getMetaData());
    }
    
    @Override
    public String getMetaData() {
        return String.valueOf(this.Speed.getValue());
    }
}
