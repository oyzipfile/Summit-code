// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin.client;

import org.spongepowered.asm.mixin.injection.Redirect;
import com.salhack.summit.util.CameraUtils;
import net.minecraft.client.settings.KeyBinding;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.salhack.summit.events.player.EventPlayerUpdateMoveState;
import com.salhack.summit.SummitMod;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.util.MovementInputFromOptions;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.util.MovementInput;

@Mixin(value = { MovementInputFromOptions.class }, priority = 10000)
public abstract class MixinMovementInputFromOptions extends MovementInput
{
    @Inject(method = { "updatePlayerMoveState" }, at = { @At("RETURN") })
    public void updatePlayerMoveStateReturn(final CallbackInfo callback) {
        SummitMod.EVENT_BUS.post(new EventPlayerUpdateMoveState());
    }
    
    @Redirect(method = { "updatePlayerMoveState" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;isKeyDown()Z"))
    public boolean isKeyPressed(final KeyBinding keyBinding) {
        return !CameraUtils.freecamEnabled() && keyBinding.isKeyDown();
    }
}
