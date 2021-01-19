// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin.client.entity;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.salhack.summit.SummitMod;
import com.salhack.summit.events.entity.EventSteerEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.entity.passive.EntityLlama;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ EntityLlama.class })
public class MixinEntityLlama
{
    @Inject(method = { "canBeSteered" }, at = { @At("HEAD") }, cancellable = true)
    public void canBeSteered(final CallbackInfoReturnable<Boolean> cir) {
        final EventSteerEntity event = new EventSteerEntity();
        SummitMod.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            cir.cancel();
            cir.setReturnValue((Object)true);
        }
    }
}
