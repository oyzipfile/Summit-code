// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.salhack.summit.SummitMod;
import com.salhack.summit.events.render.EventRenderEntity;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.entity.RenderManager;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ RenderManager.class })
public class MixinRenderManager
{
    @Inject(method = { "shouldRender" }, at = { @At("HEAD") }, cancellable = true)
    public void isPotionActive(final Entity entityIn, final ICamera camera, final double camX, final double camY, final double camZ, final CallbackInfoReturnable<Boolean> callback) {
        final EventRenderEntity event = new EventRenderEntity(entityIn, camera, camX, camY, camZ);
        SummitMod.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            callback.setReturnValue((Object)false);
        }
    }
}
