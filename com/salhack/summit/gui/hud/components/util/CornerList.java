// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.components.util;

import java.util.Comparator;
import java.util.Iterator;
import com.salhack.summit.managers.HudManager;
import com.salhack.summit.managers.FontManager;
import com.salhack.summit.util.render.RenderUtil;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;
import com.salhack.summit.main.Wrapper;
import net.minecraft.client.Minecraft;
import java.util.ArrayList;

public class CornerList
{
    private ArrayList<CornerItem> ToDisplay;
    protected final Minecraft mc;
    private final int Side;
    private float lastY;
    
    public CornerList(final int side) {
        this.ToDisplay = new ArrayList<CornerItem>();
        this.mc = Wrapper.GetMC();
        this.Side = side;
    }
    
    public float render(final ScaledResolution res, final boolean background, final boolean useRainbow, final int rainbowIncrease) {
        if (this.ToDisplay.isEmpty()) {
            return 0.0f;
        }
        this.sort();
        this.ToDisplay.removeIf(mod -> mod.isMarkedToRemove());
        int i = 0;
        this.lastY = 0.0f;
        boolean canUpdate = true;
        for (final CornerItem mod2 : this.ToDisplay) {
            if (canUpdate) {
                canUpdate = mod2.render(this.lastY);
            }
            mod2.render(this.lastY);
            i += rainbowIncrease;
            if (i >= 360) {
                i = 0;
            }
            float xOffset = res.getScaledWidth() + mod2.getX() - mod2.getWidth() - 2.0f;
            float yOffset = mod2.getLastY() + 2.0f;
            switch (this.Side) {
                case 1: {
                    yOffset = res.getScaledHeight() - mod2.getLastY() - 10.0f - ((this.mc.currentScreen instanceof GuiChat) ? 13 : 0);
                    break;
                }
                case 2: {
                    xOffset = -mod2.getX();
                    yOffset = res.getScaledHeight() - mod2.getLastY() - 10.0f - ((this.mc.currentScreen instanceof GuiChat) ? 20 : 0);
                    break;
                }
                case 3: {
                    xOffset = -mod2.getX();
                    break;
                }
            }
            if (background) {
                RenderUtil.drawRect(xOffset - 2.0f, yOffset, xOffset + mod2.getWidth() + 2.0f, yOffset + mod2.getHeight(), 1963986960);
            }
            FontManager.Get().getGameFont().drawStringWithShadow(mod2.getDisplayName(), xOffset, yOffset + 2.0f, useRainbow ? HudManager.Get().rainbow.GetRainbowColorAt(i) : mod2.getColor());
            this.lastY += mod2.getHeight();
        }
        return this.lastY;
    }
    
    public void clear() {
        this.ToDisplay.clear();
    }
    
    public void sort() {
        final String firstName;
        final String secondName;
        final float dif;
        final Comparator<CornerItem> comparator = (first, second) -> {
            firstName = first.getName();
            secondName = second.getName();
            dif = second.getWidth() - first.getWidth();
            return (dif != 0.0f) ? ((int)dif) : secondName.compareTo(firstName);
        };
        this.ToDisplay.sort(comparator);
    }
    
    public void removeMod(final String name) {
        for (final CornerItem mod : this.ToDisplay) {
            if (mod.getName().equals(name)) {
                mod.fadeAway();
            }
        }
    }
    
    public void setModMetaData(final String name, final String metaData) {
        for (final CornerItem mod : this.ToDisplay) {
            if (mod.getName().equals(name)) {
                mod.setMetaData(metaData);
                mod.setWidth((float)FontManager.Get().getGameFont().getStringWidth(mod.getDisplayName()));
            }
        }
    }
    
    public float getHeight() {
        return this.lastY;
    }
    
    public void addCornerItem(final CornerItem cornerItem) {
        this.ToDisplay.add(cornerItem);
        cornerItem.resetAnimations();
    }
    
    public void removeCornerItem(final CornerItem cornerItem) {
        this.ToDisplay.remove(cornerItem);
    }
    
    public void removeCornerWithAnimation(final CornerItem cornerItem) {
        cornerItem.fadeAway();
    }
    
    public boolean contains(final CornerItem cornerItem) {
        return this.ToDisplay.contains(cornerItem);
    }
}
