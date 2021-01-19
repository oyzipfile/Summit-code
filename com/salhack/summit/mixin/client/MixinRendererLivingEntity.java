// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import net.minecraft.entity.Entity;
import com.salhack.summit.main.SummitStatic;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import net.minecraft.entity.EntityLivingBase;

@Mixin({ RenderLivingBase.class })
public abstract class MixinRendererLivingEntity<T extends EntityLivingBase> extends MixinRenderer<T>
{
    @Shadow
    protected ModelBase mainModel;
    
    @Inject(method = { "renderModel" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V") })
    private void renderModelWrapper(final EntityLivingBase entitylivingbaseIn, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scaleFactor, final CallbackInfo ci) {
        if (SummitStatic.ENTITYESP != null && SummitStatic.ENTITYESP.isEnabled()) {
            SummitStatic.ENTITYESP.doRenderOutlines(this.mainModel, (Entity)entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scaleFactor);
        }
    }
}
