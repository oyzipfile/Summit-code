// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin.client;

import com.salhack.summit.events.entity.EventHorseSaddled;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.salhack.summit.SummitMod;
import com.salhack.summit.events.entity.EventSteerEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.passive.AbstractHorse;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ AbstractHorse.class })
public class MixinAbstractHorse
{
    @Inject(method = { "canBeSteered" }, at = { @At("HEAD") }, cancellable = true)
    public void canBeSteered(final CallbackInfoReturnable<Boolean> cir) {
        final EventSteerEntity l_Event = new EventSteerEntity();
        SummitMod.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            cir.cancel();
            cir.setReturnValue((Object)true);
        }
    }
    
    @Inject(method = { "isHorseSaddled" }, at = { @At("HEAD") }, cancellable = true)
    public void isHorseSaddled(final CallbackInfoReturnable<Boolean> cir) {
        final EventHorseSaddled l_Event = new EventHorseSaddled();
        SummitMod.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            cir.cancel();
            cir.setReturnValue((Object)true);
        }
    }
}
