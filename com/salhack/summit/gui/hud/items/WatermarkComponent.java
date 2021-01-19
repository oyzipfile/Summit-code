// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import com.salhack.summit.managers.FontManager;
import net.minecraft.client.gui.ScaledResolution;
import com.salhack.summit.gui.hud.components.DraggableHudComponent;

public class WatermarkComponent extends DraggableHudComponent
{
    private static int stringWidth;
    
    public WatermarkComponent() {
        super("Watermark", 2.0f, 6.0f, 100.0f, 100.0f);
        this.setEnabled(true);
    }
    
    @Override
    public void onRender(final ScaledResolution res, final float mouseX, final float mouseY, final float partialTicks) {
        super.onRender(res, mouseX, mouseY, partialTicks);
        FontManager.Get().lato80.drawStringWithShadow("Summit", this.getX(), this.getY(), 16711680);
        FontManager.Get().getGameFont().drawCenteredString("beta-v2.2", this.getX() + WatermarkComponent.stringWidth / 2, this.getY() + 23.0f, -1);
        this.setWidth(83.0f);
        this.setHeight(16.0f);
    }
    
    static {
        WatermarkComponent.stringWidth = FontManager.Get().lato80.getStringWidth("Summit");
    }
}
