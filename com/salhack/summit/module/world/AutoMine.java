// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.world;

import net.minecraft.client.settings.KeyBinding;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Module;

public class AutoMine extends Module
{
    @EventHandler
    private Listener<EventPlayerUpdate> onPlayerUpdate;
    
    public AutoMine() {
        super("AutoMine", new String[] { "AM" }, "Holds down your left click.", "NONE", -1, ModuleType.WORLD);
        this.onPlayerUpdate = new Listener<EventPlayerUpdate>(event -> KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindAttack.getKeyCode(), true));
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        KeyBinding.setKeyBindState(this.mc.gameSettings.keyBindAttack.getKeyCode(), false);
    }
}
