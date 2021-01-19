// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.guiclick2.components;

import com.salhack.summit.main.Wrapper;
import net.minecraft.client.Minecraft;

public class MenuComponent
{
    private float x;
    private float y;
    private float width;
    private float height;
    private float defaultX;
    private float defaultY;
    private String displayName;
    protected final Minecraft mc;
    
    public MenuComponent(final String displayName, final float x, final float y, final float width, final float height) {
        this.mc = Wrapper.GetMC();
        this.displayName = displayName;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.defaultX = x;
        this.defaultY = y;
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
    
    public float getDefaultX() {
        return this.defaultX;
    }
    
    public void setDefaultX(final float defaultX) {
        this.defaultX = defaultX;
    }
    
    public float getDefaultY() {
        return this.defaultY;
    }
    
    public void setDefaultY(final float defaultY) {
        this.defaultY = defaultY;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
    }
    
    public boolean isInside(final float mouseX, final float mouseY) {
        return mouseX >= this.getX() && mouseX < this.getX() + this.getWidth() && mouseY > this.getY() && mouseY < this.getY() + this.getHeight();
    }
    
    public void onRender(final float mouseX, final float mouseY, final float partialTicks) {
    }
    
    public void renderWith(final float x, final float y, final float mouseX, final float mouseY, final float partialTicks) {
    }
    
    public void onClicked(final int mouseX, final int mouseY, final int mouseButton) {
    }
    
    public void onReleased(final int mouseX, final int mouseY, final int state) {
    }
    
    public void onClickMove(final int mouseX, final int mouseY, final int clickedMouseButton, final long timeSinceLastClick) {
    }
    
    public void clicked(final int mouseButton) {
    }
    
    public void keyTyped(final char typedChar, final int keyCode) {
    }
    
    public void HandleMouseInput() {
    }
    
    public void onMouseInput() {
    }
}
