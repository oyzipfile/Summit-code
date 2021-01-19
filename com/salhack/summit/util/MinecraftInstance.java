// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.util;

import com.salhack.summit.main.Wrapper;
import net.minecraft.client.Minecraft;

public class MinecraftInstance
{
    protected static final Minecraft mc;
    
    static {
        mc = Wrapper.GetMC();
    }
}
