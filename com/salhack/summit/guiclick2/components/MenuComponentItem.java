// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.guiclick2.components;

import com.salhack.summit.util.render.RenderUtil;
import com.salhack.summit.guiclick2.ClickGuiS;
import com.salhack.summit.main.SummitStatic;

public class MenuComponentItem extends MenuComponent
{
    private int color;
    private boolean drawCircle;
    protected boolean enabled;
    protected boolean mouseInside;
    
    public MenuComponentItem(final String displayName, final float x, final float y, final float width, final float height, final int color, final boolean drawCircle, final boolean enabled) {
        super(displayName, x, y, width, height);
        this.mouseInside = false;
        this.setColor(color);
        this.drawCircle = drawCircle;
        this.enabled = enabled;
    }
    
    public int getColor() {
        return this.enabled ? (SummitStatic.COLORS.RainbowGUI.getValue() ? ClickGuiS.rainbowUtil.GetRainbowColorAt(0) : 14108489) : this.color;
    }
    
    public void setColor(final int color) {
        this.color = color;
    }
    
    @Override
    public void renderWith(final float x, final float y, final float mouseX, final float mouseY, final float partialTicks) {
        if (this.drawCircle) {
            RenderUtil.drawCircle(x + this.getX() + this.getWidth() - 5.0f, y + this.getY() + 3.5f, 2.0f, ((boolean)SummitStatic.COLORS.RainbowGUI.getValue()) ? (0xFF000000 | ClickGuiS.rainbowUtil.GetRainbowColorAt(0)) : -4830646);
        }
        if (mouseX >= x + this.getX() && mouseX < x + this.getX() + this.getWidth() && mouseY >= y + this.getY() && mouseY < y + this.getY() + this.getHeight()) {
            RenderUtil.drawRect(x + this.getX(), y + this.getY() - 4.0f, x + this.getX() + this.getWidth(), y + this.getY() + this.getHeight(), -1879048192);
            this.mouseInside = true;
        }
        else {
            this.mouseInside = false;
        }
        RenderUtil.drawStringWithShadow(this.getDisplayName(), x + 3.0f + this.getX(), y + this.getY(), this.getColor());
    }
    
    @Override
    public void clicked(final int mouseButton) {
        if (this.mouseInside && mouseButton == 0) {
            this.toggle();
        }
    }
    
    public void toggle() {
        this.enabled = !this.enabled;
    }
}
