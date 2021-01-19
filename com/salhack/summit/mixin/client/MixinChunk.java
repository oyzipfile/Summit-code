// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.salhack.summit.events.world.EventChunkLoad;
import com.salhack.summit.SummitMod;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.world.chunk.Chunk;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Chunk.class })
public class MixinChunk
{
    @Inject(method = { "onUnload" }, at = { @At("RETURN") })
    public void onUnload(final CallbackInfo info) {
        SummitMod.EVENT_BUS.post(new EventChunkLoad(EventChunkLoad.Type.UNLOAD, (Chunk)this));
    }
}
