// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events;

import com.salhack.summit.main.Wrapper;
import com.salhack.summit.events.bus.Cancellable;

public class MinecraftEvent extends Cancellable
{
    private Stage _stage;
    private final float partialTicks;
    
    public MinecraftEvent() {
        this._stage = Stage.Pre;
        this.partialTicks = Wrapper.GetMC().getRenderPartialTicks();
    }
    
    public MinecraftEvent(final Stage stage) {
        this();
        this._stage = stage;
    }
    
    public Stage getStage() {
        return this._stage;
    }
    
    public void setEra(final Stage stage) {
        this.setCancelled(false);
        this._stage = stage;
    }
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public enum Stage
    {
        Pre, 
        Post;
    }
}
