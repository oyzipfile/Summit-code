// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.world;

import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.blocks.EventPlaceBlockAt;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public class NoGlitchBlocks extends Module
{
    public final Value<Boolean> Destroy;
    public final Value<Boolean> Place;
    @EventHandler
    private Listener<EventPlaceBlockAt> OnSetBlockState;
    
    public NoGlitchBlocks() {
        super("NoGlitchBlocks", new String[] { "AntiGhostBlocks" }, "Synchronizes client and server communication by canceling clientside destroy/place for blocks", "NONE", 12544803, ModuleType.WORLD);
        this.Destroy = new Value<Boolean>("Destroy", new String[] { "destroy" }, "Syncs Destroying", true);
        this.Place = new Value<Boolean>("Place", new String[] { "placement" }, "Syncs placement.", true);
        this.OnSetBlockState = new Listener<EventPlaceBlockAt>(event -> {
            if (!(!this.Place.getValue())) {
                event.cancel();
            }
        });
    }
}
