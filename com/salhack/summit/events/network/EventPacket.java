// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.network;

import net.minecraft.network.Packet;
import com.salhack.summit.events.MinecraftEvent;

public class EventPacket extends MinecraftEvent
{
    private Packet<?> _packet;
    
    public EventPacket(final Packet<?> packet, final Stage stage) {
        super(stage);
        this._packet = packet;
    }
    
    public Packet<?> getPacket() {
        return this._packet;
    }
}
