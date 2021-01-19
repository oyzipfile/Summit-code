// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.movement;

import net.minecraft.init.Blocks;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Module;

public class IceSpeed extends Module
{
    @EventHandler
    private Listener<EventPlayerUpdate> onPlayerUpdate;
    
    public IceSpeed() {
        super("IceSpeed", new String[] { "IceSpeed", "IceSped", "OiceSped", "OiceSpeed", "Isped", "Ispeed" }, "Allows you to move faster on ice", "NONE", 63487, ModuleType.MOVEMENT);
        this.onPlayerUpdate = new Listener<EventPlayerUpdate>(event -> {
            Blocks.ICE.setDefaultSlipperiness(0.4f);
            Blocks.PACKED_ICE.setDefaultSlipperiness(0.4f);
            Blocks.FROSTED_ICE.setDefaultSlipperiness(0.4f);
        });
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        Blocks.ICE.setDefaultSlipperiness(0.98f);
        Blocks.PACKED_ICE.setDefaultSlipperiness(0.98f);
        Blocks.FROSTED_ICE.setDefaultSlipperiness(0.98f);
    }
}
