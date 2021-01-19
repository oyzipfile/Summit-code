// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.guiclick2.components;

import net.minecraft.client.gui.GuiScreen;
import com.salhack.summit.guiclick2.ModEditorScreen;
import com.salhack.summit.guiclick2.ClickGuiS;
import com.salhack.summit.module.Module;

public class MenuComponentModItem extends MenuComponentItem
{
    private final Module mod;
    
    public MenuComponentModItem(final String displayName, final float x, final float y, final float width, final float height, final Module mod) {
        super(displayName, x, y, width, height, -1, !mod.getValueList().isEmpty(), mod.isEnabled());
        this.mod = mod;
    }
    
    @Override
    public void onRender(final float mouseX, final float mouseY, final float partialTicks) {
        super.onRender(mouseX, mouseY, partialTicks);
    }
    
    @Override
    public void clicked(final int mouseButton) {
        super.clicked(mouseButton);
        if (this.mouseInside) {
            if (mouseButton == 1) {
                this.mc.displayGuiScreen((GuiScreen)new ModEditorScreen(this.mod.getDisplayName(), this.mod.getValueList(), (ClickGuiS)this.mc.currentScreen, this.mod));
            }
            else if (mouseButton == 0) {
                this.mod.toggle();
            }
        }
    }
}
