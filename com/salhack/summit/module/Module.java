// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module;

import com.salhack.summit.main.Summit;
import com.mojang.realmsclient.gui.ChatFormatting;
import com.salhack.summit.managers.ModuleManager;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.gui.GuiScreen;
import com.salhack.summit.managers.CommandManager;
import com.salhack.summit.events.salhack.EventSalHackModuleDisable;
import com.salhack.summit.main.SummitStatic;
import com.salhack.summit.events.salhack.EventSalHackModuleEnable;
import com.salhack.summit.SummitMod;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import com.salhack.summit.events.bus.EventListener;

public class Module implements EventListener
{
    public String displayName;
    private String[] alias;
    private String desc;
    public String key;
    private int color;
    public boolean hidden;
    private boolean enabled;
    private ModuleType type;
    private boolean m_NeedsClickGuiValueUpdate;
    protected final Minecraft mc;
    private String _metaData;
    public List<Value<?>> valueList;
    
    private Module(final String displayName, final String[] alias, final String key, final int color, final ModuleType type) {
        this.hidden = false;
        this.enabled = false;
        this.mc = Minecraft.getMinecraft();
        this._metaData = null;
        this.valueList = new ArrayList<Value<?>>();
        this.displayName = displayName;
        this.alias = alias;
        this.key = key;
        this.color = color;
        this.type = type;
    }
    
    public Module(final String displayName, final String[] alias, final String desc, final String key, final int color, final ModuleType type) {
        this(displayName, alias, key, color, type);
        this.desc = desc;
    }
    
    public void onEnable() {
        SummitMod.EVENT_BUS.subscribe(this);
        SummitMod.EVENT_BUS.post(new EventSalHackModuleEnable(this));
        if (SummitStatic.ARRAYLIST != null && !this.isHidden()) {
            SummitStatic.ARRAYLIST.addMod(this);
        }
    }
    
    public void onDisable() {
        SummitMod.EVENT_BUS.unsubscribe(this);
        SummitMod.EVENT_BUS.post(new EventSalHackModuleDisable(this));
        if (SummitStatic.ARRAYLIST != null && !this.isHidden()) {
            SummitStatic.ARRAYLIST.removeMod(this);
        }
    }
    
    public void onToggle() {
    }
    
    public void toggle() {
        this.setEnabled(!this.isEnabled());
        if (this.isEnabled()) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
        this.onToggle();
    }
    
    public void toggleNoSave() {
        this.setEnabled(!this.isEnabled());
        if (this.isEnabled()) {
            this.onEnable();
        }
        else {
            this.onDisable();
        }
        this.onToggle();
    }
    
    public void ToggleOnlySuper() {
        this.setEnabled(!this.isEnabled());
        this.onToggle();
    }
    
    public String getArrayListAddon() {
        return this._metaData;
    }
    
    public void unload() {
        this.valueList.clear();
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public void setDisplayName(final String displayName) {
        this.displayName = displayName;
        CommandManager.Get().Reload();
    }
    
    public String[] getAlias() {
        return this.alias;
    }
    
    public void setAlias(final String[] alias) {
        this.alias = alias;
    }
    
    public String getDesc() {
        return this.desc;
    }
    
    public void setDesc(final String desc) {
        this.desc = desc;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public boolean IsKeyPressed(final String p_KeyCode) {
        if (GuiScreen.isAltKeyDown() || GuiScreen.isCtrlKeyDown() || GuiScreen.isShiftKeyDown()) {
            if (this.key.contains(" + ")) {
                if (GuiScreen.isAltKeyDown() && this.key.contains("MENU")) {
                    final String l_Result = this.key.replace(Keyboard.isKeyDown(56) ? "LMENU + " : "RMENU + ", "");
                    return l_Result.equals(p_KeyCode);
                }
                if (GuiScreen.isCtrlKeyDown() && this.key.contains("CONTROL")) {
                    String l_CtrlKey = "";
                    if (Minecraft.IS_RUNNING_ON_MAC) {
                        l_CtrlKey = (Keyboard.isKeyDown(219) ? "LCONTROL" : "RCONTROL");
                    }
                    else {
                        l_CtrlKey = (Keyboard.isKeyDown(29) ? "LCONTROL" : "RCONTROL");
                    }
                    final String l_Result2 = this.key.replace(l_CtrlKey + " + ", "");
                    return l_Result2.equals(p_KeyCode);
                }
                if (GuiScreen.isShiftKeyDown() && this.key.contains("SHIFT")) {
                    final String l_Result = this.key.replace((Keyboard.isKeyDown(42) ? "LSHIFT" : "RSHIFT") + " + ", "");
                    return l_Result.equals(p_KeyCode);
                }
            }
            if (!ModuleManager.Get().IgnoreStrictKeybinds()) {
                return (p_KeyCode.contains("SHIFT") || p_KeyCode.contains("CONTROL") || p_KeyCode.contains("MENU")) && this.key.equals(p_KeyCode);
            }
        }
        return this.key.equals(p_KeyCode);
    }
    
    public void setKey(final String key) {
        this.key = key;
    }
    
    public int getColor() {
        return this.color;
    }
    
    public void setColor(final int color) {
        this.color = color;
    }
    
    public boolean isHidden() {
        return this.hidden;
    }
    
    public void setHidden(final boolean hidden) {
        this.hidden = hidden;
        if (!this.isEnabled()) {
            return;
        }
        if (hidden) {
            if (SummitStatic.ARRAYLIST != null) {
                SummitStatic.ARRAYLIST.removeMod(this);
            }
        }
        else if (SummitStatic.ARRAYLIST != null) {
            SummitStatic.ARRAYLIST.addMod(this);
        }
    }
    
    public boolean isEnabled() {
        return this.enabled;
    }
    
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }
    
    public ModuleType getType() {
        return this.type;
    }
    
    public void setType(final ModuleType type) {
        this.type = type;
    }
    
    public List<Value<?>> getValueList() {
        return this.valueList;
    }
    
    public void SignalEnumChange() {
    }
    
    public void SignalValueChange(final Value<?> p_Val) {
    }
    
    public List<Value<?>> GetVisibleValueList() {
        return this.valueList;
    }
    
    public void SetClickGuiValueUpdate(final boolean p_Val) {
        this.m_NeedsClickGuiValueUpdate = p_Val;
    }
    
    public boolean NeedsClickGuiValueUpdate() {
        return this.m_NeedsClickGuiValueUpdate;
    }
    
    public String GetNextStringValue(final Value<String> p_Val, final boolean p_Recursive) {
        return null;
    }
    
    public String GetArrayListDisplayName() {
        return this.getDisplayName();
    }
    
    public String GetFullArrayListDisplayName() {
        return this.getDisplayName() + ((this.getArrayListAddon() != null) ? (" " + ChatFormatting.GRAY + this.getArrayListAddon()) : "");
    }
    
    protected void SendMessage(final String p_Message) {
        if (this.mc.player != null) {
            Summit.SendMessage(ChatFormatting.AQUA + "[" + this.GetArrayListDisplayName() + "]: " + ChatFormatting.RESET + p_Message);
        }
        else {
            System.out.println("[" + this.GetArrayListDisplayName() + "]: " + p_Message);
        }
    }
    
    public void init() {
    }
    
    public void setMetaData(final String meta) {
        this._metaData = meta;
        if (SummitStatic.ARRAYLIST != null && !this.isHidden() && SummitStatic.ARRAYLIST.getCurrentCornerList() != null) {
            SummitStatic.ARRAYLIST.getCurrentCornerList().setModMetaData(this.getDisplayName(), this.getMetaData());
        }
    }
    
    public String getMetaData() {
        return this._metaData;
    }
    
    public enum ModuleType
    {
        COMBAT, 
        EXPLOIT, 
        MOVEMENT, 
        RENDER, 
        WORLD, 
        MISC, 
        HIDDEN, 
        UI, 
        BOT;
    }
}
