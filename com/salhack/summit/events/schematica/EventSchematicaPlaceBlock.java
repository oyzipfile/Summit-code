// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.schematica;

import net.minecraft.util.math.BlockPos;
import com.salhack.summit.events.MinecraftEvent;

public class EventSchematicaPlaceBlock extends MinecraftEvent
{
    public BlockPos Pos;
    
    public EventSchematicaPlaceBlock(final BlockPos p_Pos) {
        this.Pos = p_Pos;
    }
}
