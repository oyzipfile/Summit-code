// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.blocks;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import com.salhack.summit.events.bus.Cancellable;

public class EventCanPlaceCheck extends Cancellable
{
    public World World;
    public BlockPos Pos;
    public Class<?> Block;
    
    public EventCanPlaceCheck(final World world, final BlockPos pos, final Class<?> block) {
        this.World = world;
        this.Pos = pos;
        this.Block = block;
    }
}
