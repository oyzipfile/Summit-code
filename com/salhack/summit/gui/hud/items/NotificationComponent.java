// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import java.util.Iterator;
import com.salhack.summit.util.render.RenderUtil;
import com.salhack.summit.managers.NotificationManager;
import com.salhack.summit.gui.hud.GuiHudEditor;
import net.minecraft.client.gui.ScaledResolution;
import com.salhack.summit.gui.hud.components.DraggableHudComponent;

public class NotificationComponent extends DraggableHudComponent
{
    public NotificationComponent() {
        super("Notifications", 500.0f, 500.0f, 100.0f, 100.0f);
        this.setEnabled(true);
    }
    
    @Override
    public void onRender(final ScaledResolution res, final float p_MouseX, final float p_MouseY, final float p_PartialTicks) {
        super.onRender(res, p_MouseX, p_MouseY, p_PartialTicks);
        if (this.mc.currentScreen instanceof GuiHudEditor && NotificationManager.Get().Notifications.isEmpty()) {
            final String placeholder = "Notifications";
            this.setWidth(RenderUtil.getStringWidth("Notifications"));
            this.setHeight(RenderUtil.getStringHeight("Notifications"));
            RenderUtil.drawStringWithShadow("Notifications", this.getX(), this.getY(), 16777215);
            return;
        }
        final Iterator<NotificationManager.Notification> l_Itr = NotificationManager.Get().Notifications.iterator();
        float l_Y = this.getY();
        float l_MaxWidth = 0.0f;
        while (l_Itr.hasNext()) {
            final NotificationManager.Notification l_Notification = l_Itr.next();
            if (l_Notification.IsDecayed()) {
                NotificationManager.Get().Notifications.remove(l_Notification);
            }
            final float l_Width = RenderUtil.getStringWidth(l_Notification.GetDescription()) + 1.5f;
            RenderUtil.drawRect(this.getX() - 1.5f, l_Y, this.getX() + l_Width, l_Y + 13.0f, 1963986960);
            RenderUtil.drawStringWithShadow(l_Notification.GetDescription(), this.getX(), l_Y + l_Notification.GetY(), 16777215);
            if (l_Width >= l_MaxWidth) {
                l_MaxWidth = l_Width;
            }
            l_Y -= 13.0f;
        }
        this.setHeight(10.0f);
        this.setWidth(l_MaxWidth);
    }
}
