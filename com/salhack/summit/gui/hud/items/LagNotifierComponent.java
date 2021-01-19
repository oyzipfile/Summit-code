// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import com.salhack.summit.managers.FontManager;
import com.salhack.summit.util.render.RenderUtil;
import java.text.DecimalFormat;
import net.minecraft.client.gui.ScaledResolution;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.network.EventServerPacket;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.util.Timer;
import com.salhack.summit.gui.hud.components.DraggableHudComponent;

public class LagNotifierComponent extends DraggableHudComponent
{
    private Timer timer;
    @EventHandler
    private Listener<EventServerPacket> onServerPacket;
    
    public LagNotifierComponent() {
        super("LagNotifier", 500.0f, 500.0f, 100.0f, 100.0f);
        this.timer = new Timer();
        this.onServerPacket = new Listener<EventServerPacket>(event -> this.timer.reset());
        this.setEnabled(true);
    }
    
    @Override
    public void onRender(final ScaledResolution res, final float mouseX, final float mouseY, final float partialTicks) {
        super.onRender(res, mouseX, mouseY, partialTicks);
        final float seconds = (System.currentTimeMillis() - this.timer.getTime()) / 1000.0f % 60.0f;
        if (seconds < 3.0f) {
            return;
        }
        final String delay = "Server has stopped responding for " + new DecimalFormat("#.#").format(seconds) + " seconds..";
        this.setWidth(RenderUtil.getStringWidth(delay));
        this.setHeight((float)FontManager.Get().getGameFont().getHeight());
        RenderUtil.drawStringWithShadow(delay, res.getScaledWidth() / 2 - this.getWidth() / 2.0f, 20.0f, -1);
    }
}
