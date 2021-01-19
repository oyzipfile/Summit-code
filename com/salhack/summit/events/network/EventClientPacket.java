// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.network;

import com.salhack.summit.events.MinecraftEvent;
import net.minecraft.network.Packet;

public class EventClientPacket extends EventPacket
{
    public EventClientPacket(final Packet<?> packet, final Stage stage) {
        super(packet, stage);
    }
}
