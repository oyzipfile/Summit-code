// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.items;

import java.util.Date;
import java.text.SimpleDateFormat;
import com.salhack.summit.module.Value;
import com.salhack.summit.gui.hud.components.OptionalListHudComponent;

public class TimeComponent extends OptionalListHudComponent
{
    public final Value<Boolean> Date;
    SimpleDateFormat _formatter;
    
    public TimeComponent() {
        super("Time", 2.0f, 110.0f, 0.0f, 0.0f);
        this.Date = new Value<Boolean>("Date", new String[] { "" }, "Show current date", false);
        this._formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.setEnabled(true);
    }
    
    @Override
    public void onUpdate() {
        final Date now = new Date();
        String time = new SimpleDateFormat("h:mm a").format(now);
        if (this.Date.getValue()) {
            time = time + " " + this._formatter.format(now);
        }
        this.cornerItem.setName(time);
    }
}
