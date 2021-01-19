// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.components;

import net.minecraft.client.gui.ScaledResolution;
import com.salhack.summit.SummitMod;
import com.salhack.summit.main.Wrapper;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import com.salhack.summit.module.Value;
import java.util.List;
import com.salhack.summit.events.bus.EventListener;

public class HudComponentItem implements EventListener
{
    private String displayName;
    private float x;
    private float y;
    private final List<Value<?>> valueList;
    protected boolean enabled;
    private int flags;
    private float width;
    private float height;
    protected boolean isMouseInThis;
    protected final Minecraft mc;
    
    public HudComponentItem(final String displayName, final float x, final float y, final float width, final float height) {
        this.valueList = new ArrayList<Value<?>>();
        this.mc = Wrapper.GetMC();
        this.displayName = displayName;
        this.x = x;
        this.y = y;
        this.setWidth(width);
        this.setHeight(height);
        this.enabled = false;
        this.flags = HudComponentFlags.None;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }
    
    public float getX() {
        return this.x;
    }
    
    public void setX(final float x) {
        this.x = x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public void setY(final float y) {
        this.y = y;
    }
    
    public final List<Value<?>> getValueList() {
        return this.valueList;
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
        if (enabled) {
            SummitMod.EVENT_BUS.subscribe(this);
        }
        else {
            SummitMod.EVENT_BUS.unsubscribe(this);
        }
    }
    
    public int getFlags() {
        return this.flags;
    }
    
    public void setFlags(final int flags) {
        this.flags = flags;
    }
    
    public void addFlag(final int flags) {
        this.flags |= flags;
    }
    
    public void removeFlag(final int flags) {
        this.flags &= ~flags;
    }
    
    public boolean hasFlag(final int flags) {
        return (this.flags & flags) != 0x0;
    }
    
    public float getWidth() {
        return this.width;
    }
    
    public void setWidth(final float width) {
        this.width = width;
    }
    
    public float getHeight() {
        return this.height;
    }
    
    public void setHeight(final float height) {
        this.height = height;
    }
    
    protected boolean isMouseInThisComponent(final float mouseX, final float mouseY) {
        return mouseX != 0.0f && mouseY != 0.0f && mouseX > this.getX() && mouseX < this.getX() + this.getWidth() && mouseY > this.getY() && mouseY < this.getY() + this.getHeight();
    }
    
    public void onRender(final ScaledResolution res, final float mouseX, final float mouseY, final float partialTicks) {
    }
    
    public boolean onMouseClick(final float mouseX, final float mouseY, final int button) {
        return false;
    }
    
    public void onMouseRelease() {
    }
    
    public void onUpdate() {
    }
    
    public void afterLoad(final boolean enabled2) {
    }
}
