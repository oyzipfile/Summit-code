// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.tabgui;

import com.salhack.summit.module.Module;
import java.util.ArrayList;

public class TabGuiItem
{
    private final String displayName;
    private final ArrayList<TabGuiItem> addons;
    private boolean isOpened;
    private boolean isHovered;
    private Module Mod;
    
    public TabGuiItem(final String displayName) {
        this.addons = new ArrayList<TabGuiItem>();
        this.isOpened = false;
        this.isHovered = false;
        this.Mod = null;
        this.displayName = displayName;
    }
    
    public TabGuiItem(final String displayName, final Module mod) {
        this(displayName);
        this.Mod = mod;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public ArrayList<TabGuiItem> getAddons() {
        return this.addons;
    }
    
    public boolean isOpened() {
        return this.isOpened;
    }
    
    public void setOpened(final boolean isOpened) {
        this.isOpened = isOpened;
    }
    
    public boolean isHovered() {
        return this.isHovered;
    }
    
    public void setHovered(final boolean isHovered) {
        this.isHovered = isHovered;
    }
    
    public void toggle() {
        if (this.Mod != null) {
            this.Mod.toggle();
        }
    }
    
    public int getColor() {
        return (this.Mod != null && this.Mod.isEnabled()) ? 65508 : -1;
    }
}
