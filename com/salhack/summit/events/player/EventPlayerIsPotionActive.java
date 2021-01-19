// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.player;

import net.minecraft.potion.Potion;
import com.salhack.summit.events.MinecraftEvent;

public class EventPlayerIsPotionActive extends MinecraftEvent
{
    public Potion potion;
    
    public EventPlayerIsPotionActive(final Potion p_Potion) {
        this.potion = p_Potion;
    }
}
