// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.player;

import com.salhack.summit.events.MinecraftEvent;

public class EventPlayerSendChatMessage extends MinecraftEvent
{
    public String Message;
    
    public EventPlayerSendChatMessage(final String p_Message) {
        this.Message = p_Message;
    }
}
