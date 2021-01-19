// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.misc;

import com.salhack.summit.util.PlayerAlert;
import com.salhack.summit.util.RebaneGetter;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.client.EventClientTick;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.util.Timer;
import com.salhack.summit.module.Module;

public class QueuePeek extends Module
{
    private Timer timer;
    @EventHandler
    private Listener<EventClientTick> onTick;
    
    public QueuePeek() {
        super("QueuePeek", new String[] { "QP" }, "Notifies you when someone enters the queue", "NONE", -1, ModuleType.MISC);
        this.timer = new Timer();
        this.onTick = new Listener<EventClientTick>(event -> {
            if (this.timer.passed(12000.0)) {
                this.timer.reset();
                new Thread(() -> {
                    RebaneGetter.init();
                    PlayerAlert.updateQueuePositions();
                }).start();
            }
        });
    }
}
