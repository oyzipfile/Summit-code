// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.misc;

import com.salhack.summit.module.Module;

public class Friends extends Module
{
    public Friends() {
        super("Friends", new String[] { "Homies" }, "Allows the friend system to function, disabling this ignores friend requirements, useful for dueling friends.", "NONE", -1, ModuleType.MISC);
        this.setEnabled(true);
    }
}
