// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.player;

import net.minecraft.entity.Entity;
import com.salhack.summit.events.MinecraftEvent;

public class EventPlayerApplyCollision extends MinecraftEvent
{
    public Entity entity;
    
    public EventPlayerApplyCollision(final Entity p_Entity) {
        this.entity = p_Entity;
    }
}
