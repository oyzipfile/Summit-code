// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.guiclick2.components;

import com.salhack.summit.managers.FontManager;
import com.salhack.summit.util.render.RenderUtil;

public class MenuComponentEditorItem extends MenuComponent
{
    private boolean withRect;
    protected boolean hovered;
    protected boolean toggled;
    protected boolean displayRect;
    protected boolean outlineRectInstead;
    protected float sliderWidth;
    
    public MenuComponentEditorItem(final String displayName, final float x, final float y, final float width, final float height, final boolean withRect) {
        super(displayName, x, y, width, height);
        this.withRect = withRect;
    }
    
    @Override
    public void renderWith(final float x, final float y, final float mouseX, final float mouseY, final float partialTicks) {
        if (mouseX >= x + this.getX() && mouseX < x + this.getX() + this.getWidth() && mouseY >= y + this.getY() && mouseY < y + this.getY() + this.getHeight()) {
            RenderUtil.drawRect(x + this.getX(), y + this.getY() - 4.0f, x + this.getX() + this.getWidth(), y + this.getY() + this.getHeight(), -1879048192);
            this.hovered = true;
        }
        else {
            this.hovered = false;
        }
        if (this.withRect) {
            if (this.outlineRectInstead) {
                RenderUtil.drawLine(x + this.getX(), y + this.getY(), x + this.getX() + this.getWidth(), y + this.getY(), 1.0f, -3980989);
                RenderUtil.drawLine(x + this.getX(), y + this.getY() + this.getHeight(), x + this.getX() + this.getWidth(), y + this.getY() + this.getHeight(), 1.0f, -3980989);
                RenderUtil.drawLine(x + this.getX(), y + this.getY(), x + this.getX(), y + this.getY() + this.getHeight(), 1.0f, -3980989);
                RenderUtil.drawLine(x + this.getX() + this.getWidth(), y + this.getY(), x + this.getX() + this.getWidth(), y + this.getY() + this.getHeight(), 1.0f, -3980989);
                RenderUtil.drawRect(x + this.getX(), y + this.getY(), x + this.getX() + this.sliderWidth, y + this.getY() + this.getHeight(), -3980989);
            }
            else {
                int color = -3980989;
                if (this.displayRect) {
                    color = -12646906;
                }
                RenderUtil.drawRect(x + this.getX(), y + this.getY(), x + this.getX() + this.getWidth(), y + this.getY() + this.getHeight(), color);
            }
        }
        FontManager.Get().getGameFont().drawStringWithShadow(this.getDisplayName(), x + 3.0f + this.getX(), y + this.getY() + 4.5f, -1);
    }
    
    @Override
    public void clicked(final int mouseButton) {
        if (mouseButton == 0 && this.hovered) {
            this.toggle();
        }
    }
    
    public void toggle() {
        this.toggled = !this.toggled;
    }
}
