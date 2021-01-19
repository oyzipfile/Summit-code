// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.components.util;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.salhack.summit.util.render.RenderUtil;
import net.minecraft.client.Minecraft;

public class CornerItem
{
    private String name;
    private String metaData;
    private float x;
    private float y;
    private float width;
    private int color;
    private boolean isFading;
    private boolean isAppearing;
    private boolean markedToRemove;
    private boolean firstUpdate;
    private float lastY;
    
    public CornerItem(final String name, final String metaData2, final int color, final boolean firstUpdate) {
        this.x = 0.0f;
        this.y = 0.0f;
        this.width = 0.0f;
        this.color = -1;
        this.isFading = false;
        this.isAppearing = false;
        this.markedToRemove = false;
        this.firstUpdate = false;
        this.lastY = 0.0f;
        this.setName(name);
        this.metaData = metaData2;
        this.color = color;
        this.isAppearing = true;
        this.firstUpdate = firstUpdate;
    }
    
    public float getWidth() {
        return this.width;
    }
    
    public float getHeight() {
        return 10.0f;
    }
    
    public void setMetaData(final String metaData2) {
        this.metaData = metaData2;
    }
    
    public boolean render(final float currY) {
        final float currFPS = Minecraft.getDebugFPS() / 60.0f;
        final float xIncrease = 3.0f / currFPS;
        final float yIncrease = 1.0f / currFPS;
        if (this.isFading) {
            final float x = this.x + xIncrease;
            this.x = x;
            if (x >= this.width) {
                this.markedToRemove = true;
            }
            return true;
        }
        if (this.lastY != currY) {
            if (this.lastY == 0.0f) {
                this.lastY = currY;
                return true;
            }
            if (this.lastY > currY) {
                this.lastY -= yIncrease;
                if (this.lastY <= currY) {
                    this.lastY = currY;
                }
            }
            else if (this.lastY < currY) {
                this.lastY = currY;
            }
        }
        if (this.isAppearing) {
            if (this.x > 0.0f) {
                this.x -= xIncrease;
                this.x = Math.max(0.0f, this.x);
                return !this.firstUpdate;
            }
            this.firstUpdate = false;
            this.isAppearing = false;
        }
        return true;
    }
    
    public void fadeAway() {
        this.isFading = true;
    }
    
    public boolean isMarkedToRemove() {
        return this.markedToRemove;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(final String name) {
        if (name.equals(this.name)) {
            return;
        }
        this.name = name;
        this.width = RenderUtil.getStringWidth(name);
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
    
    public float getLastY() {
        return this.lastY;
    }
    
    public void setLastY(final float lastY) {
        this.lastY = lastY;
    }
    
    public int getColor() {
        return this.color;
    }
    
    public String getDisplayName() {
        return this.getName() + ((this.metaData != null && !this.metaData.isEmpty()) ? (" " + ChatFormatting.GRAY + this.metaData) : "");
    }
    
    public void setWidth(final float f) {
        this.width = f;
    }
    
    public void resetAnimations() {
        this.isAppearing = true;
        this.firstUpdate = true;
        this.setX(this.getWidth() + 10.0f);
    }
}
