// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.Block;
import com.salhack.summit.events.MinecraftEvent;

public class EventBlockCanCollideCheck extends MinecraftEvent
{
    private Block _block;
    private IBlockState _state;
    
    public EventBlockCanCollideCheck(final Block block, final IBlockState state) {
        this._block = null;
        this._state = null;
        this._block = block;
        this._state = state;
    }
    
    public IBlockState getState() {
        return this._state;
    }
}
