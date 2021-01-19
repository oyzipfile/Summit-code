// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit;

import com.salhack.summit.events.bus.EventManager;
import org.apache.logging.log4j.LogManager;
import com.salhack.summit.main.ForgeEventProcessor;
import net.minecraftforge.common.MinecraftForge;
import com.salhack.summit.main.Summit;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import com.salhack.summit.events.bus.EventBus;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;

@Mod(modid = "summit", name = "Summit", version = "beta-v2.2")
public final class SummitMod
{
    public static final String NAME = "Summit";
    public static final String VERSION = "beta-v2.2";
    public static final String WATERMARK = "Summit beta-v2.2";
    public static final Logger log;
    public static final EventBus EVENT_BUS;
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        SummitMod.log.info("init salhack v: beta-v2.2");
        Summit.Init();
        MinecraftForge.EVENT_BUS.register((Object)new ForgeEventProcessor());
    }
    
    static {
        log = LogManager.getLogger("sal");
        EVENT_BUS = new EventManager();
    }
}
