// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin.client.entity;

import com.salhack.summit.events.player.EventPlayerPushedByWater;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.salhack.summit.events.player.EventPlayerApplyCollision;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.entity.MoverType;
import com.salhack.summit.SummitMod;
import com.salhack.summit.events.player.EventPlayerTravel;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.entity.player.EntityPlayer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { EntityPlayer.class }, priority = Integer.MAX_VALUE)
public abstract class MixinEntityPlayer extends MixinEntityLivingBase
{
    @Shadow
    public void onUpdate() {
    }
    
    @Inject(method = { "travel" }, at = { @At("HEAD") }, cancellable = true)
    public void travel(final float strafe, final float vertical, final float forward, final CallbackInfo info) {
        final EntityPlayer us = (EntityPlayer)this;
        if (!(us instanceof EntityPlayerSP)) {
            return;
        }
        final EventPlayerTravel event = new EventPlayerTravel(strafe, vertical, forward);
        SummitMod.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            info.cancel();
        }
    }
    
    @Inject(method = { "applyEntityCollision" }, at = { @At("HEAD") }, cancellable = true)
    public void applyEntityCollision(final Entity p_Entity, final CallbackInfo info) {
        final EventPlayerApplyCollision l_Event = new EventPlayerApplyCollision(p_Entity);
        SummitMod.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            info.cancel();
        }
    }
    
    @Inject(method = { "isPushedByWater()Z" }, at = { @At("HEAD") }, cancellable = true)
    public void isPushedByWater(final CallbackInfoReturnable<Boolean> ci) {
        final EventPlayerPushedByWater l_Event = new EventPlayerPushedByWater();
        SummitMod.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            ci.setReturnValue((Object)false);
        }
    }
}
