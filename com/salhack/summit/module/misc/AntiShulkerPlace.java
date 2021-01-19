// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.misc;

import net.minecraft.block.BlockShulkerBox;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.blocks.EventCanPlaceCheck;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.module.Module;

public class AntiShulkerPlace extends Module
{
    @EventHandler
    private Listener<EventCanPlaceCheck> CheckEvent;
    
    public AntiShulkerPlace() {
        super("AntiShulkerPlace", new String[] { "NoShulkerPlace", "CancelShulkerPlace" }, "Prevents you from accidentally placing shulkers", "NONE", 14361796, ModuleType.MISC);
        this.CheckEvent = new Listener<EventCanPlaceCheck>(event -> {
            if (event.Block.isAssignableFrom(BlockShulkerBox.class)) {
                event.cancel();
            }
        });
    }
}
