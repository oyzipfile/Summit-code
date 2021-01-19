// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.world;

import net.minecraft.world.chunk.Chunk;
import com.salhack.summit.events.MinecraftEvent;

public class EventChunkLoad extends MinecraftEvent
{
    private Chunk _chunk;
    private Type _type;
    
    public EventChunkLoad(final Type type, final Chunk chunk) {
        this._type = type;
        this._chunk = chunk;
    }
    
    public Chunk getChunk() {
        return this._chunk;
    }
    
    public Type getType() {
        return this._type;
    }
    
    public enum Type
    {
        LOAD, 
        UNLOAD;
    }
}
