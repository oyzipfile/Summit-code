// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.movement;

import com.salhack.summit.events.entity.EventHorseSaddled;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.entity.EventSteerEntity;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Module;

public class EntityControl extends Module
{
    @EventHandler
    private Listener<EventSteerEntity> OnSteerEntity;
    @EventHandler
    private Listener<EventHorseSaddled> OnHorseSaddled;
    
    public EntityControl() {
        super("EntityControl", new String[] { "AntiSaddle", "EntityRide", "NoSaddle" }, "Allows you to control llamas, horses, pigs without a saddle/carrot", "NONE", 1612579, ModuleType.MOVEMENT);
        this.OnSteerEntity = new Listener<EventSteerEntity>(p_Event -> p_Event.cancel());
        this.OnHorseSaddled = new Listener<EventHorseSaddled>(p_Event -> p_Event.cancel());
    }
}
