// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.guiclick2.components;

import com.salhack.summit.main.Summit;
import com.salhack.summit.util.render.RenderUtil;
import com.salhack.summit.guiclick2.ClickGuiS;
import com.salhack.summit.main.SummitStatic;
import java.util.ArrayList;
import java.util.List;

public class MenuListComponent extends MenuComponent
{
    private List<MenuComponent> Items;
    private boolean isDragging;
    private float topbarHeight;
    private float deltaX;
    private float deltaY;
    
    public MenuListComponent(final String displayName, final float x, final float y, final float width, final float height, final float topbarHeight) {
        super(displayName, x, y, width, height);
        this.Items = new ArrayList<MenuComponent>();
        this.isDragging = false;
        this.topbarHeight = topbarHeight;
    }
    
    public void addItem(final MenuComponent item) {
        this.Items.add(item);
    }
    
    public final List<MenuComponent> getItems() {
        return this.Items;
    }
    
    @Override
    public void onRender(final float mouseX, final float mouseY, final float partialTicks) {
        if (this.isInside(mouseX, mouseY)) {}
        if (this.isDragging) {
            this.setX(mouseX - this.deltaX);
            this.setY(mouseY - this.deltaY);
        }
        final boolean rainbow = SummitStatic.COLORS.RainbowGUI.getValue();
        final int borderColor = rainbow ? (-16777216 + ClickGuiS.rainbowUtil.GetRainbowColorAt(0)) : -3980989;
        RenderUtil.drawRect(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), -1726079458);
        RenderUtil.drawRect(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + 15.0f, borderColor);
        if (!rainbow) {
            Summit.GetFontManager().getGameFont().drawCenteredString(this.getDisplayName(), this.getX() + this.getWidth() / 2.0f, this.getY() + 4.0f, -1, true);
        }
        this.Items.forEach(item -> item.renderWith(this.getX(), this.getY() + 19.0f, mouseX, mouseY, partialTicks));
        RenderUtil.drawLine(this.getX(), this.getY(), this.getX(), this.getY() + this.getHeight(), 2.0f, borderColor);
        RenderUtil.drawLine(this.getX() + this.getWidth(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), 2.0f, borderColor);
        RenderUtil.drawLine(this.getX(), this.getY() + this.getHeight(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), 2.0f, borderColor);
        RenderUtil.drawTriangle(this.getX(), this.getY() + 8.0f, 8.0f, 180.0f, borderColor);
        RenderUtil.drawTriangle(this.getX() + this.getWidth(), this.getY() + 8.0f, 8.0f, 180.0f, borderColor);
        RenderUtil.drawLine(this.getX() - 4.0f, this.getY(), this.getX(), this.getY() + 15.0f, 3.0f, borderColor);
        RenderUtil.drawLine(this.getX() + this.getWidth(), this.getY() + 15.0f, this.getX() + this.getWidth() + 4.0f, this.getY(), 3.0f, borderColor);
    }
    
    @Override
    public void onClicked(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isInside((float)mouseX, (float)mouseY) && mouseY <= this.topbarHeight + this.getY()) {
            this.isDragging = true;
            this.deltaX = mouseX - this.getX();
            this.deltaY = mouseY - this.getY();
        }
        this.Items.forEach(item -> item.clicked(mouseButton));
    }
    
    @Override
    public void onReleased(final int mouseX, final int mouseY, final int state) {
        if (this.isDragging) {
            this.isDragging = false;
        }
        this.Items.forEach(item -> item.onReleased(mouseX, mouseY, state));
    }
}
