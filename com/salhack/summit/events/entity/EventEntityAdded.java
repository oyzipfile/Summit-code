// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.entity;

import net.minecraft.entity.Entity;
import com.salhack.summit.events.MinecraftEvent;

public class EventEntityAdded extends MinecraftEvent
{
    private Entity m_Entity;
    
    public EventEntityAdded(final Entity p_Entity) {
        this.m_Entity = p_Entity;
    }
    
    public Entity GetEntity() {
        return this.m_Entity;
    }
}
