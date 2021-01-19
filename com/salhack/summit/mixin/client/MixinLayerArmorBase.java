// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.salhack.summit.SummitMod;
import com.salhack.summit.events.render.EventRenderArmorLayer;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ LayerArmorBase.class })
public class MixinLayerArmorBase
{
    @Inject(method = { "renderArmorLayer" }, at = { @At("HEAD") }, cancellable = true)
    public void renderArmorLayer(final EntityLivingBase p_Entity, final float limbSwing, final float limbSwingAmount, final float partialTicks, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale, final EntityEquipmentSlot slotIn, final CallbackInfo p_Info) {
        final EventRenderArmorLayer l_Event = new EventRenderArmorLayer(p_Entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale, slotIn);
        SummitMod.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            p_Info.cancel();
        }
    }
}
