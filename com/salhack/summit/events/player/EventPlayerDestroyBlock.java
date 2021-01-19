// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.player;

import net.minecraft.util.math.BlockPos;
import com.salhack.summit.events.MinecraftEvent;

public class EventPlayerDestroyBlock extends MinecraftEvent
{
    public BlockPos Location;
    
    public EventPlayerDestroyBlock(final BlockPos loc) {
        this.Location = loc;
    }
}
