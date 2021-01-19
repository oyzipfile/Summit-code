// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import net.minecraft.entity.Entity;
import com.salhack.summit.util.CameraUtils;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.debug.DebugRendererChunkBorder;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ DebugRendererChunkBorder.class })
public abstract class MixinDebugRendererChunkBorder
{
    @Redirect(method = { "render" }, at = @At(value = "FIELD", target = "Lnet/minecraft/client/Minecraft;player:Lnet/minecraft/client/entity/EntityPlayerSP;"))
    private EntityPlayerSP useCameraEntity(final Minecraft mc) {
        if (CameraUtils.freecamEnabled()) {
            final Entity entity = mc.getRenderViewEntity();
            if (entity instanceof EntityPlayerSP) {
                return (EntityPlayerSP)entity;
            }
        }
        return mc.player;
    }
}
