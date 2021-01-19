// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.render;

import net.minecraft.entity.Entity;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerTravel;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Module;

public class Parkour extends Module
{
    @EventHandler
    private Listener<EventPlayerTravel> onTravel;
    
    public Parkour() {
        super("Parkour", new String[] { "Parkour", "EdgeJump", "Parkourmaster", "Parkuur", "Park" }, "Jump at the edge of a block.", "NONE", -1, ModuleType.MOVEMENT);
        this.onTravel = new Listener<EventPlayerTravel>(event -> {
            if (this.mc.player.onGround && !this.mc.player.isSneaking()) {
                if (this.mc.world.getCollisionBoxes((Entity)this.mc.player, this.mc.player.getEntityBoundingBox().offset(0.0, -0.5, 0.0).expand(-0.001, 0.0, -0.001)).isEmpty()) {
                    this.mc.player.jump();
                }
            }
        });
    }
}
