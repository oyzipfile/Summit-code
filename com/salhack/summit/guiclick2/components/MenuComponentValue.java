// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.guiclick2.components;

import java.math.BigDecimal;
import com.salhack.summit.module.Value;

public class MenuComponentValue extends MenuComponentEditorItem
{
    private Value value;
    private boolean canSlide;
    private boolean isSliding;
    
    public MenuComponentValue(final Value<?> val, final float x, final float y, final float width, final float height) {
        super(val.getName(), x, y, width, height, needRectDisplay(val));
        this.value = val;
        this.outlineRectInstead = (val.getValue() instanceof Number);
        this.sliderWidth = this.calculateSliderWidth();
        this.toggled = this.isToggled();
        if (val.getValue() instanceof Boolean) {
            this.displayRect = !this.toggled;
        }
        this.canSlide = (val.getValue() instanceof Number);
    }
    
    private float calculateSliderWidth() {
        if (!(this.value.getValue() instanceof Number)) {
            return 0.0f;
        }
        final float minX = this.getX();
        final float maxX = this.getX() + this.getWidth();
        if (this.value.getMax() == null) {
            return minX;
        }
        final Number numberVal = this.value.getValue();
        final Number max = this.value.getMax();
        return Math.min(this.getWidth(), (maxX - minX) * (numberVal.floatValue() / max.floatValue()));
    }
    
    private boolean isToggled() {
        return !(this.value.getValue() instanceof Boolean) || this.value.getValue();
    }
    
    private static boolean needRectDisplay(final Value<?> val) {
        return true;
    }
    
    @Override
    public String getDisplayName() {
        if (this.value.getValue() instanceof Number || this.value.getValue() instanceof Enum || this.value.getValue() instanceof String) {
            return super.getDisplayName() + " " + this.value.getValue();
        }
        return super.getDisplayName();
    }
    
    @Override
    public void renderWith(final float x, final float y, final float mouseX, final float mouseY, final float partialTicks) {
        super.renderWith(x, y, mouseX, mouseY, partialTicks);
        if (this.isSliding) {
            this.handleSlide(x, y, mouseX, mouseY);
        }
    }
    
    @Override
    public void clicked(final int mouseButton) {
        super.clicked(mouseButton);
        if (this.hovered) {
            if (this.canSlide && mouseButton == 0) {
                this.isSliding = true;
            }
            if (this.value.getValue() instanceof String) {
                this.value.setValue(this.value.getNextStringValue(mouseButton == 1));
            }
        }
    }
    
    @Override
    public void toggle() {
        super.toggle();
        if (this.value.getValue() instanceof Boolean) {
            this.value.setValue(!this.value.getValue());
            this.displayRect = !this.displayRect;
        }
    }
    
    public void handleSlide(final float currX, final float currY, final float mouseX, final float mouseY) {
        float x = currX + this.getX();
        if (mouseX >= x && mouseX <= currX + this.getX() + this.getWidth()) {
            x = mouseX;
        }
        if (mouseX > currX + this.getX() + this.getWidth()) {
            x = currX + this.getX() + this.getWidth();
        }
        x -= currX;
        this.sliderWidth = x - this.getX();
        final float l_Pct = (x - this.getX()) / this.getWidth();
        if (this.value.getValue().getClass() == Float.class) {
            final BigDecimal l_Decimal = new BigDecimal(((this.value.getMax().getClass() == Float.class) ? this.value.getMax() : ((this.value.getMax().getClass() == Double.class) ? this.value.getMax() : ((double)(int)this.value.getMax()))) * (double)l_Pct);
            this.value.setValue(l_Decimal.setScale(2, 6).floatValue());
        }
        else if (this.value.getValue().getClass() == Double.class) {
            final BigDecimal l_Decimal = new BigDecimal(((this.value.getMax().getClass() == Double.class) ? this.value.getMax() : ((this.value.getMax().getClass() == Float.class) ? this.value.getMax() : ((double)(int)this.value.getMax()))) * (double)l_Pct);
            this.value.setValue(l_Decimal.setScale(2, 6).doubleValue());
        }
        else if (this.value.getValue().getClass() == Integer.class) {
            this.value.setValue((int)(this.value.getMax() * l_Pct));
        }
    }
    
    @Override
    public void onReleased(final int mouseX, final int mouseY, final int state) {
        this.isSliding = false;
    }
}
