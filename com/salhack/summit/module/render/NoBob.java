// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.render;

import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Module;

public class NoBob extends Module
{
    @EventHandler
    private Listener<EventPlayerUpdate> OnPlayerUpdate;
    
    public NoBob() {
        super("NoBob", new String[] { "NoBob" }, "Prevents bobbing by setting distance walked modifier to a static number", "NONE", 6180566, ModuleType.RENDER);
        this.OnPlayerUpdate = new Listener<EventPlayerUpdate>(event -> this.mc.player.distanceWalkedModified = 4.0f);
    }
}
