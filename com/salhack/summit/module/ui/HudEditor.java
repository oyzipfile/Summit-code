// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.ui;

import net.minecraft.client.gui.GuiScreen;
import com.salhack.summit.gui.hud.GuiHudEditor;
import com.salhack.summit.module.Module;

public final class HudEditor extends Module
{
    public HudEditor() {
        super("HudEditor", new String[] { "HudEditor" }, "Displays the HudEditor", "GRAVE", 14403620, ModuleType.UI);
    }
    
    @Override
    public void toggleNoSave() {
    }
    
    @Override
    public void onToggle() {
        super.onToggle();
        if (this.mc.world != null) {
            this.mc.displayGuiScreen((GuiScreen)new GuiHudEditor(this));
        }
    }
}
