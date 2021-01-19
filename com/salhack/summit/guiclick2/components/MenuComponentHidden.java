// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.guiclick2.components;

import com.salhack.summit.module.Module;

public class MenuComponentHidden extends MenuComponentEditorItem
{
    private final Module mod;
    
    public MenuComponentHidden(final Module mod, final float x, final float y, final float width, final float height) {
        super("Hidden", x, y, width, height, true);
        this.mod = mod;
        this.displayRect = mod.isHidden();
    }
    
    @Override
    public void clicked(final int mouseButton) {
        super.clicked(mouseButton);
        if (this.hovered) {
            this.mod.setHidden(!this.mod.isHidden());
        }
    }
    
    @Override
    public void toggle() {
        super.toggle();
        this.displayRect = this.toggled;
    }
}
