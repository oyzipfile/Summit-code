// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.salhack.summit.events.render.EventRenderGuiUpdateTick;
import com.salhack.summit.SummitMod;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.GuiIngame;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = { GuiIngame.class }, priority = Integer.MAX_VALUE)
public class MixinGuiIngame
{
    @Inject(method = { "updateTick" }, at = { @At("RETURN") })
    public void updateTick(final CallbackInfo info) {
        SummitMod.EVENT_BUS.post(new EventRenderGuiUpdateTick());
    }
}
