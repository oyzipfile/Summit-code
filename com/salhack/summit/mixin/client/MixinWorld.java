// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin.client;

import com.salhack.summit.main.SummitStatic;
import net.minecraft.world.EnumSkyBlock;
import com.salhack.summit.events.world.EventGetSkyColor;
import net.minecraft.util.math.Vec3d;
import com.salhack.summit.events.entity.EventEntityRemoved;
import com.salhack.summit.events.entity.EventEntityAdded;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.entity.Entity;
import com.salhack.summit.events.world.EventWorldSetBlockState;
import com.salhack.summit.util.CrystalUtils2;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.salhack.summit.SummitMod;
import com.salhack.summit.events.render.EventRenderRainStrength;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.world.IWorldEventListener;
import java.util.List;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ World.class })
public class MixinWorld
{
    @Shadow
    protected List<IWorldEventListener> eventListeners;
    
    @Inject(method = { "getRainStrength" }, at = { @At("HEAD") }, cancellable = true)
    public void getRainStrength(final float delta, final CallbackInfoReturnable<Float> p_Callback) {
        final EventRenderRainStrength l_Event = new EventRenderRainStrength();
        SummitMod.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            p_Callback.cancel();
            p_Callback.setReturnValue((Object)0.0f);
        }
    }
    
    @Inject(method = { "setBlockState" }, at = { @At("HEAD") }, cancellable = true)
    public void setBlockState(final BlockPos pos, final IBlockState newState, final int flags, final CallbackInfoReturnable<Boolean> callback) {
        CrystalUtils2.onSetBlockState(pos, newState, flags);
        final EventWorldSetBlockState event = new EventWorldSetBlockState(pos, newState, flags);
        SummitMod.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            callback.cancel();
            callback.setReturnValue((Object)false);
        }
    }
    
    @Inject(method = { "onEntityAdded" }, at = { @At("HEAD") }, cancellable = true)
    public void onEntityAdded(final Entity p_Entity, final CallbackInfo p_Info) {
        final EventEntityAdded l_Event = new EventEntityAdded(p_Entity);
        SummitMod.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            p_Info.cancel();
        }
    }
    
    @Inject(method = { "onEntityRemoved" }, at = { @At("HEAD") }, cancellable = true)
    public void onEntityRemoved(final Entity p_Entity, final CallbackInfo p_Info) {
        CrystalUtils2.onEntityRemoved(p_Entity);
        final EventEntityRemoved l_Event = new EventEntityRemoved(p_Entity);
        SummitMod.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            p_Info.cancel();
        }
    }
    
    @Inject(method = { "getSkyColor" }, at = { @At("HEAD") }, cancellable = true)
    public void getSkyColor(final Entity entityIn, final float partialTicks, final CallbackInfoReturnable<Vec3d> callback) {
        final EventGetSkyColor event = new EventGetSkyColor();
        SummitMod.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            callback.cancel();
            callback.setReturnValue((Object)event.getVec3d());
        }
    }
    
    @Inject(method = { "updateEntities" }, at = { @At("RETURN") })
    public void updateEntities(final CallbackInfo info) {
        CrystalUtils2.onUpdate();
    }
    
    @Inject(method = { "checkLightFor" }, at = { @At("HEAD") }, cancellable = true)
    public void checkLightFor(final EnumSkyBlock lightType, final BlockPos pos, final CallbackInfoReturnable<Boolean> callback) {
        if (SummitStatic.NORENDER != null && SummitStatic.NORENDER.isEnabled() && SummitStatic.NORENDER.Skylight.getValue()) {
            callback.cancel();
            callback.setReturnValue((Object)true);
        }
    }
}
