// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.ui;

import net.minecraft.client.gui.GuiNewChat;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraft.client.gui.GuiIngame;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.world.EventLoadWorld;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.gui.chat.SalGuiNewChat;
import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public class ReliantChat extends Module
{
    public final Value<Boolean> FancyFont;
    public final Value<Boolean> SmoothChat;
    public static ReliantChat INSTANCE;
    private SalGuiNewChat m_Chat;
    @EventHandler
    private final Listener<EventLoadWorld> LoadWorldListener;
    
    public ReliantChat() {
        super("ReliantChat", new String[] { "CustomChat" }, "TTF font rendering for chat", "NONE", -1, ModuleType.UI);
        this.FancyFont = new Value<Boolean>("FancyFont", new String[0], "Enables the fancy TTF font rendering", true);
        this.SmoothChat = new Value<Boolean>("SmoothChat", new String[0], "Enables the smooth chat animation", false);
        this.m_Chat = null;
        this.LoadWorldListener = new Listener<EventLoadWorld>(ignored -> this.Activate());
        ReliantChat.INSTANCE = this;
    }
    
    public void Activate() {
        if (this.mc.ingameGUI == null) {
            return;
        }
        if (this.m_Chat == null) {
            this.m_Chat = new SalGuiNewChat(this.mc);
        }
        ObfuscationReflectionHelper.setPrivateValue((Class)GuiIngame.class, (Object)this.mc.ingameGUI, (Object)this.m_Chat, new String[] { "field_73840_e" });
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        this.Activate();
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
        if (this.mc.ingameGUI == null) {
            return;
        }
        ObfuscationReflectionHelper.setPrivateValue((Class)GuiIngame.class, (Object)this.mc.ingameGUI, (Object)new GuiNewChat(this.mc), new String[] { "field_73840_e" });
    }
}
