// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.world;

import net.minecraft.util.math.Vec3d;
import com.salhack.summit.events.MinecraftEvent;

public class EventGetSkyColor extends MinecraftEvent
{
    private Vec3d color;
    
    public void setColor(final Vec3d color) {
        this.color = color;
    }
    
    public Vec3d getVec3d() {
        return this.color;
    }
}
