// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.world;

import com.salhack.summit.events.render.EventRenderRainStrength;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public final class Weather extends Module
{
    public final Value<String> Mode;
    @EventHandler
    private Listener<EventPlayerUpdate> OnPlayerUpdate;
    @EventHandler
    private Listener<EventRenderRainStrength> OnRainStrength;
    
    public Weather() {
        super("Weather", new String[] { "AntiWeather", "NoWeather" }, "Allows you to control the weather client-side", "NONE", 9630964, ModuleType.WORLD);
        this.Mode = new Value<String>("Mode", new String[] { "Mode" }, "Mode to use", "Clear");
        this.OnPlayerUpdate = new Listener<EventPlayerUpdate>(event -> {
            if (this.mc.world == null) {
                return;
            }
            else {
                this.setMetaData(this.getMetaData());
                if (this.Mode.getValue().equals("Rain")) {
                    this.mc.world.setRainStrength(1.0f);
                }
                else if (this.Mode.getValue().equals("Thunder")) {
                    this.mc.world.setThunderStrength(2.0f);
                }
                return;
            }
        });
        this.OnRainStrength = new Listener<EventRenderRainStrength>(p_Event -> {
            if (this.mc.world == null) {
                return;
            }
            else {
                if (this.Mode.getValue().equals("Clear")) {
                    p_Event.cancel();
                }
                return;
            }
        });
        this.setMetaData(this.getMetaData());
        this.Mode.addString("Clear");
        this.Mode.addString("Rain");
        this.Mode.addString("Thunder");
    }
    
    @Override
    public String getMetaData() {
        if (this.mc.world == null || !this.mc.world.isRaining()) {
            return "Clear";
        }
        if (this.mc.world.isThundering()) {
            return "Rain";
        }
        return "Rain";
    }
}
