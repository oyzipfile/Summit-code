// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.events.bus;

public interface EventBus
{
    void post(final Object p0);
    
    void subscribe(final EventListener p0);
    
    void unsubscribe(final EventListener p0);
}
