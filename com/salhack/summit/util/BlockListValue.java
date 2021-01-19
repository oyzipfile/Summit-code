// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.util;

import net.minecraft.util.ResourceLocation;
import net.minecraft.block.Block;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import com.salhack.summit.module.Value;

public class BlockListValue extends Value<List<String>>
{
    public BlockListValue(final String name, final List<String> list) {
        super(name, new ArrayList(list));
    }
    
    public void addBlock(final Block block) {
        this.getValue().add(this.getStringFromBlock(block));
    }
    
    public void removeBlock(final Block block) {
        ((Value<List>)this).getValue().remove(this.getStringFromBlock(block));
    }
    
    public boolean containsBlock(final Block block) {
        return ((Value<List>)this).getValue().contains(this.getStringFromBlock(block));
    }
    
    private String getStringFromBlock(final Block block) {
        return ((ResourceLocation)Block.REGISTRY.getNameForObject((Object)block)).toString();
    }
}
