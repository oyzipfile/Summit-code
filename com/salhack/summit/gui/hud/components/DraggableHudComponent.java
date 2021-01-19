// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.components;

import com.salhack.summit.util.render.RenderUtil;
import net.minecraft.client.gui.ScaledResolution;

public class DraggableHudComponent extends HudComponentItem
{
    private boolean isDragging;
    private float deltaX;
    private float deltaY;
    
    public DraggableHudComponent(final String displayName, final float x, final float y, final float width, final float height) {
        super(displayName, x, y, width, height);
        this.isDragging = false;
        this.deltaX = 0.0f;
        this.deltaY = 0.0f;
    }
    
    @Override
    public void onRender(final ScaledResolution res, final float mouseX, final float mouseY, final float partialTicks) {
        if (this.isDragging()) {
            final float newX = mouseX - this.deltaX;
            final float newY = mouseY - this.deltaY;
            this.setX(Math.min(Math.max(0.0f, newX), res.getScaledWidth() - this.getWidth()));
            this.setY(Math.min(Math.max(0.0f, newY), res.getScaledHeight() - this.getHeight()));
        }
        if (this.isMouseInThisComponent(mouseX, mouseY)) {
            RenderUtil.drawRect(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), 1345864260);
            this.isMouseInThis = true;
        }
        else {
            this.isMouseInThis = false;
        }
    }
    
    @Override
    public boolean onMouseClick(final float mouseX, final float mouseY, final int button) {
        if (button == 0 && this.isMouseInThis) {
            this.setDragging(true);
            this.setDeltaX(mouseX - this.getX());
            this.setDeltaY(mouseY - this.getY());
            return true;
        }
        return false;
    }
    
    @Override
    public void onMouseRelease() {
        if (this.isDragging()) {
            this.setDragging(false);
        }
    }
    
    public boolean isDragging() {
        return this.isDragging;
    }
    
    public void setDragging(final boolean isDragging) {
        this.isDragging = isDragging;
    }
    
    public float getDeltaX() {
        return this.deltaX;
    }
    
    public void setDeltaX(final float deltaX) {
        this.deltaX = deltaX;
    }
    
    public float getDeltaY() {
        return this.deltaY;
    }
    
    public void setDeltaY(final float deltaY) {
        this.deltaY = deltaY;
    }
    
    @Override
    public void afterLoad(final boolean enabled) {
        this.setEnabled(enabled);
    }
}
