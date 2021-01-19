// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.player;

import net.minecraft.util.EnumHand;
import com.salhack.summit.events.MinecraftEvent;

public class EventPlayerSwingArm extends MinecraftEvent
{
    public EnumHand Hand;
    
    public EventPlayerSwingArm(final EnumHand p_Hand) {
        this.Hand = p_Hand;
    }
}
