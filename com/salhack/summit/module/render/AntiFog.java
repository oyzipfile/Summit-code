// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.render;

import net.minecraft.client.renderer.GlStateManager;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.render.EventRenderSetupFog;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Module;

public class AntiFog extends Module
{
    @EventHandler
    private Listener<EventRenderSetupFog> SetupFog;
    
    public AntiFog() {
        super("AntiFog", new String[] { "NoFog" }, "Prevents fog from being rendered", "NONE", 14361771, ModuleType.RENDER);
        this.SetupFog = new Listener<EventRenderSetupFog>(p_Event -> {
            p_Event.cancel();
            GlStateManager.pushMatrix();
            GlStateManager.setFogDensity(0.0f);
            GlStateManager.popMatrix();
        });
    }
}
