// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.render;

import net.minecraft.client.gui.ScaledResolution;
import com.salhack.summit.events.MinecraftEvent;

public class EventRenderGameOverlay extends MinecraftEvent
{
    public float PartialTicks;
    public ScaledResolution scaledResolution;
    
    public EventRenderGameOverlay(final float p_PartialTicks, final ScaledResolution p_Res) {
        this.PartialTicks = p_PartialTicks;
        this.scaledResolution = p_Res;
    }
    
    public ScaledResolution getScaledResolution() {
        return this.scaledResolution;
    }
}
