// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.main;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import com.mojang.realmsclient.gui.ChatFormatting;
import com.salhack.summit.util.RebaneGetter;
import com.salhack.summit.util.render.RenderUtil;
import com.salhack.summit.SummitMod;
import com.salhack.summit.util.Timer;
import com.salhack.summit.communication.Client;
import com.salhack.summit.util.RotationUtils;
import com.salhack.summit.managers.MacroManager;
import com.salhack.summit.managers.RotationManager;
import com.salhack.summit.managers.UUIDManager;
import com.salhack.summit.managers.PresetsManager;
import com.salhack.summit.managers.CapeManager;
import com.salhack.summit.waypoints.WaypointManager;
import com.salhack.summit.managers.NotificationManager;
import com.salhack.summit.managers.TickRateManager;
import com.salhack.summit.managers.CommandManager;
import com.salhack.summit.managers.DirectoryManager;
import com.salhack.summit.managers.DiscordManager;
import com.salhack.summit.managers.FriendManager;
import com.salhack.summit.managers.HudManager;
import com.salhack.summit.managers.FontManager;
import com.salhack.summit.managers.ImageManager;
import com.salhack.summit.managers.ModuleManager;

public class Summit
{
    private static ModuleManager m_ModuleManager;
    private static ImageManager m_ImageManager;
    private static FontManager m_FontManager;
    private static HudManager m_HudManager;
    private static FriendManager m_FriendManager;
    private static DiscordManager m_DiscordManager;
    private static DirectoryManager m_DirectoryManager;
    private static CommandManager m_CommandManager;
    private static TickRateManager m_TickRateManager;
    private static NotificationManager m_NotificationManager;
    private static WaypointManager m_WaypointManager;
    private static CapeManager m_CapeManager;
    private static AlwaysEnabledModule m_AlwaysEnabledMod;
    private static PresetsManager m_PresetsManager;
    private static UUIDManager m_UUIDManager;
    private static RotationManager m_RotationManager;
    private static MacroManager m_MacroManager;
    private static RotationUtils m_RotationUtils;
    private static Client m_Client;
    private static Timer updateTimer;
    
    public static void Init() {
        SummitMod.log.info("initalizing salhack object (all static fields)");
        RenderUtil.init();
        Summit.m_DirectoryManager.Init();
        Summit.m_FontManager.Load();
        Summit.m_CapeManager.Init();
        try {
            Summit.m_PresetsManager.LoadPresets();
            Summit.m_ModuleManager.Init();
            Summit.m_HudManager.Init();
            Summit.m_CommandManager.InitializeCommands();
            Summit.m_ImageManager.Load();
            Summit.m_FriendManager.Load();
            Summit.m_MacroManager.Load();
            RebaneGetter.init();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            while (true) {
                if (!Summit.updateTimer.passed(50.0)) {
                    continue;
                }
                else {
                    Summit.updateTimer.reset();
                    try {
                        Summit.m_PresetsManager.getActivePreset().onUpdate();
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }).start();
        (Summit.m_AlwaysEnabledMod = new AlwaysEnabledModule()).init();
        Summit.m_RotationManager.init();
        new Thread(() -> (Summit.m_Client = new Client("127.0.0.1", 5056)).Update()).start();
    }
    
    public static ModuleManager GetModuleManager() {
        return Summit.m_ModuleManager;
    }
    
    public static ImageManager GetImageManager() {
        return Summit.m_ImageManager;
    }
    
    public static FontManager GetFontManager() {
        return Summit.m_FontManager;
    }
    
    public static void SendMessage(final String string) {
        if (Wrapper.GetMC().ingameGUI != null || Wrapper.GetPlayer() == null) {
            Wrapper.GetMC().ingameGUI.getChatGUI().printChatMessage((ITextComponent)new TextComponentString(ChatFormatting.RED + "[Summit]: " + string));
        }
    }
    
    public static HudManager GetHudManager() {
        return Summit.m_HudManager;
    }
    
    public static FriendManager GetFriendManager() {
        return Summit.m_FriendManager;
    }
    
    public static DiscordManager GetDiscordManager() {
        return Summit.m_DiscordManager;
    }
    
    public static DirectoryManager GetDirectoryManager() {
        return Summit.m_DirectoryManager;
    }
    
    public static CommandManager GetCommandManager() {
        return Summit.m_CommandManager;
    }
    
    public static TickRateManager GetTickRateManager() {
        return Summit.m_TickRateManager;
    }
    
    public static NotificationManager GetNotificationManager() {
        return Summit.m_NotificationManager;
    }
    
    public static WaypointManager GetWaypointManager() {
        return Summit.m_WaypointManager;
    }
    
    public static CapeManager GetCapeManager() {
        return Summit.m_CapeManager;
    }
    
    public static PresetsManager GetPresetsManager() {
        return Summit.m_PresetsManager;
    }
    
    public static UUIDManager GetUUIDManager() {
        return Summit.m_UUIDManager;
    }
    
    public static Client GetClient() {
        return Summit.m_Client;
    }
    
    public static RotationManager GetRotationManager() {
        return Summit.m_RotationManager;
    }
    
    public static void SendMessage(final TextComponentString portalTextComponent) {
        Wrapper.GetMC().ingameGUI.getChatGUI().printChatMessage((ITextComponent)portalTextComponent);
    }
    
    public static MacroManager GetMacroManager() {
        return Summit.m_MacroManager;
    }
    
    public static RotationUtils GetRotationUtils() {
        return Summit.m_RotationUtils;
    }
    
    static {
        Summit.m_ModuleManager = new ModuleManager();
        Summit.m_ImageManager = new ImageManager();
        Summit.m_FontManager = new FontManager();
        Summit.m_HudManager = new HudManager();
        Summit.m_FriendManager = new FriendManager();
        Summit.m_DiscordManager = new DiscordManager();
        Summit.m_DirectoryManager = new DirectoryManager();
        Summit.m_CommandManager = new CommandManager();
        Summit.m_TickRateManager = new TickRateManager();
        Summit.m_NotificationManager = new NotificationManager();
        Summit.m_WaypointManager = new WaypointManager();
        Summit.m_CapeManager = new CapeManager();
        Summit.m_PresetsManager = new PresetsManager();
        Summit.m_UUIDManager = new UUIDManager();
        Summit.m_RotationManager = new RotationManager();
        Summit.m_MacroManager = new MacroManager();
        Summit.m_RotationUtils = new RotationUtils();
        Summit.updateTimer = new Timer();
    }
}
