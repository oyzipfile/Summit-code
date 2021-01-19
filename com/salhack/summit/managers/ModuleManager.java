// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.managers;

import net.minecraft.client.gui.GuiScreen;
import java.util.Iterator;
import java.util.function.Function;
import java.util.Comparator;
import java.util.ArrayList;
import java.lang.reflect.Field;
import com.salhack.summit.module.Value;
import com.salhack.summit.preset.Preset;
import com.salhack.summit.module.bot.DupeBot;
import com.salhack.summit.module.world.Weather;
import com.salhack.summit.module.world.TimerModule;
import com.salhack.summit.module.world.StashLogger;
import com.salhack.summit.module.world.StashFinder;
import com.salhack.summit.module.world.SpeedyGonzales;
import com.salhack.summit.module.world.SkyRender;
import com.salhack.summit.module.world.Scaffold;
import com.salhack.summit.module.world.Nuker;
import com.salhack.summit.module.world.NoGlitchBlocks;
import com.salhack.summit.module.world.Lawnmower;
import com.salhack.summit.module.world.FastPlace;
import com.salhack.summit.module.world.EnderChestFarmer;
import com.salhack.summit.module.world.Avoid;
import com.salhack.summit.module.world.AutoWither;
import com.salhack.summit.module.world.AutoTunnel;
import com.salhack.summit.module.world.AutoTool;
import com.salhack.summit.module.world.AutoMine;
import com.salhack.summit.module.world.AutoNameTag;
import com.salhack.summit.module.world.AutoBuilder;
import com.salhack.summit.module.ui.ReliantChat;
import com.salhack.summit.module.ui.Keybinds;
import com.salhack.summit.module.ui.Hud;
import com.salhack.summit.module.ui.HudEditor;
import com.salhack.summit.module.ui.ClickGui;
import com.salhack.summit.module.ui.Console;
import com.salhack.summit.module.ui.Commands;
import com.salhack.summit.module.ui.Colors;
import com.salhack.summit.module.render.WoWTooltips;
import com.salhack.summit.module.render.Waypoints;
import com.salhack.summit.module.render.Wallhack;
import com.salhack.summit.module.render.VoidESP;
import com.salhack.summit.module.render.ViewClip;
import com.salhack.summit.module.render.Trajectories;
import com.salhack.summit.module.render.Tracers;
import com.salhack.summit.module.render.SuperheroFX;
import com.salhack.summit.module.render.StorageESP;
import com.salhack.summit.module.render.SmallShield;
import com.salhack.summit.module.render.Skeleton;
import com.salhack.summit.module.render.ShulkerPreview;
import com.salhack.summit.module.render.Search;
import com.salhack.summit.module.render.NoRender;
import com.salhack.summit.module.render.NoBob;
import com.salhack.summit.module.render.MapTooltip;
import com.salhack.summit.module.render.Nametags;
import com.salhack.summit.module.render.ItemPhysics;
import com.salhack.summit.module.render.HoleESP;
import com.salhack.summit.module.render.Fullbright;
import com.salhack.summit.module.render.Freecam;
import com.salhack.summit.module.render.FarmESP;
import com.salhack.summit.module.movement.EntitySpeed;
import com.salhack.summit.module.render.EntityESP;
import com.salhack.summit.module.render.ContainerPreview;
import com.salhack.summit.module.render.CityESP;
import com.salhack.summit.module.render.BreakHighlight;
import com.salhack.summit.module.render.BreadCrumbs;
import com.salhack.summit.module.render.BlockHighlight;
import com.salhack.summit.module.render.AntiFog;
import com.salhack.summit.module.movement.WebSpeed;
import com.salhack.summit.module.movement.Yaw;
import com.salhack.summit.module.movement.Step;
import com.salhack.summit.module.movement.Sprint;
import com.salhack.summit.module.movement.Speed;
import com.salhack.summit.module.movement.Sneak;
import com.salhack.summit.module.movement.SafeWalk;
import com.salhack.summit.module.movement.ReverseStep;
import com.salhack.summit.module.movement.Jesus;
import com.salhack.summit.module.render.Parkour;
import com.salhack.summit.module.movement.NoSlow;
import com.salhack.summit.module.movement.NoRotate;
import com.salhack.summit.module.movement.NoFall;
import com.salhack.summit.module.movement.LevitationControl;
import com.salhack.summit.module.movement.LongJump;
import com.salhack.summit.module.movement.IceSpeed;
import com.salhack.summit.module.movement.HoleFinder;
import com.salhack.summit.module.movement.HighJump;
import com.salhack.summit.module.movement.Flight;
import com.salhack.summit.module.movement.FastSwim;
import com.salhack.summit.module.movement.EntityControl;
import com.salhack.summit.module.movement.ElytraFly;
import com.salhack.summit.module.movement.Blink;
import com.salhack.summit.module.movement.BoatFly;
import com.salhack.summit.module.movement.AutoWalk;
import com.salhack.summit.module.misc.XCarry;
import com.salhack.summit.module.misc.VisualRange;
import com.salhack.summit.module.misc.TotemPopNotifier;
import com.salhack.summit.module.misc.StopWatch;
import com.salhack.summit.module.misc.QueuePeek;
import com.salhack.summit.module.misc.PacketLogger;
import com.salhack.summit.module.misc.Notifications;
import com.salhack.summit.module.misc.MiddleClickPearl;
import com.salhack.summit.module.misc.MiddleClickFriends;
import com.salhack.summit.module.misc.HotbarCache;
import com.salhack.summit.module.misc.GlobalLocation;
import com.salhack.summit.module.misc.ImgurUploader;
import com.salhack.summit.module.misc.Friends;
import com.salhack.summit.module.misc.FakePlayer;
import com.salhack.summit.module.misc.DurabilityAlert;
import com.salhack.summit.module.misc.DiscordRPC;
import com.salhack.summit.module.misc.ChestSwap;
import com.salhack.summit.module.misc.ChorusFruitBypass;
import com.salhack.summit.module.misc.ChestStealer;
import com.salhack.summit.module.misc.ChatNotifier;
import com.salhack.summit.module.misc.ChatModifications;
import com.salhack.summit.module.misc.BuildHeight;
import com.salhack.summit.module.world.AutoTend;
import com.salhack.summit.module.misc.AutoTame;
import com.salhack.summit.module.misc.AutoSign;
import com.salhack.summit.module.misc.AutoShear;
import com.salhack.summit.module.misc.AutoReconnect;
import com.salhack.summit.module.misc.AutoMount;
import com.salhack.summit.module.misc.AutoMendArmor;
import com.salhack.summit.module.world.AutoFarmland;
import com.salhack.summit.module.misc.AutoEat;
import com.salhack.summit.module.misc.AntiShulkerPlace;
import com.salhack.summit.module.misc.AntiAFK;
import com.salhack.summit.module.misc.Annoy;
import com.salhack.summit.module.misc.Announcer;
import com.salhack.summit.module.exploit.Swing;
import com.salhack.summit.module.exploit.Reach;
import com.salhack.summit.module.exploit.PortalGodMode;
import com.salhack.summit.module.exploit.PacketFly;
import com.salhack.summit.module.exploit.PacketCanceller;
import com.salhack.summit.module.exploit.NewChunks;
import com.salhack.summit.module.exploit.NoMiningTrace;
import com.salhack.summit.module.exploit.LiquidInteract;
import com.salhack.summit.module.exploit.EntityDesync;
import com.salhack.summit.module.exploit.CrashItem;
import com.salhack.summit.module.exploit.CoordTPExploit;
import com.salhack.summit.module.exploit.AntiHunger;
import com.salhack.summit.module.combat.Velocity;
import com.salhack.summit.module.combat.Trigger;
import com.salhack.summit.module.combat.Surround;
import com.salhack.summit.module.combat.SelfWeb;
import com.salhack.summit.module.combat.SelfTrap;
import com.salhack.summit.module.combat.Offhand;
import com.salhack.summit.module.combat.HoleFiller;
import com.salhack.summit.module.combat.Criticals;
import com.salhack.summit.module.combat.BowSpam;
import com.salhack.summit.module.combat.BowAim;
import com.salhack.summit.module.combat.BlinkDetect;
import com.salhack.summit.module.combat.AutoTrapFeet;
import com.salhack.summit.module.combat.AutoTrap;
import com.salhack.summit.module.combat.AutoTotem;
import com.salhack.summit.module.combat.Aura;
import com.salhack.summit.module.combat.AutoLog;
import com.salhack.summit.module.combat.AutoCrystalBypass;
import com.salhack.summit.module.combat.AutoCrystal;
import com.salhack.summit.module.combat.AutoCity;
import com.salhack.summit.main.SummitStatic;
import com.salhack.summit.module.combat.AutoArmor;
import com.salhack.summit.module.combat.AntiGappleDisease;
import com.salhack.summit.module.combat.AntiCityBoss;
import com.salhack.summit.module.combat.AntiBowkick;
import com.salhack.summit.module.combat.AntiBots;
import com.salhack.summit.module.combat.Anchor;
import java.util.concurrent.CopyOnWriteArrayList;
import com.salhack.summit.main.Summit;
import com.salhack.summit.module.Module;
import java.util.List;

public class ModuleManager
{
    public List<Module> Mods;
    
    public static ModuleManager Get() {
        return Summit.GetModuleManager();
    }
    
    public ModuleManager() {
        this.Mods = new CopyOnWriteArrayList<Module>();
    }
    
    public void Init() {
        this.Add(new Anchor());
        this.Add(new AntiBots());
        this.Add(new AntiBowkick());
        this.Add(new AntiCityBoss());
        this.Add(new AntiGappleDisease());
        this.Add(SummitStatic.AUTOARMOR = new AutoArmor());
        this.Add(SummitStatic.AUTOCITY = new AutoCity());
        this.Add(SummitStatic.AUTOCRYSTAL = new AutoCrystal());
        this.Add(new AutoCrystalBypass());
        this.Add(new AutoLog());
        this.Add(SummitStatic.AURA = new Aura());
        this.Add(SummitStatic.AUTOTOTEM = new AutoTotem());
        this.Add(SummitStatic.AUTOTRAP = new AutoTrap());
        this.Add(SummitStatic.AUTOTRAPFEET = new AutoTrapFeet());
        this.Add(new BlinkDetect());
        this.Add(new BowAim());
        this.Add(new BowSpam());
        this.Add(SummitStatic.CRITICALS = new Criticals());
        this.Add(SummitStatic.HOLEFILLER = new HoleFiller());
        this.Add(SummitStatic.OFFHAND = new Offhand());
        this.Add(SummitStatic.SELFTRAP = new SelfTrap());
        this.Add(new SelfWeb());
        this.Add(SummitStatic.SURROUND = new Surround());
        this.Add(new Trigger());
        this.Add(new Velocity());
        this.Add(new AntiHunger());
        this.Add(new CoordTPExploit());
        this.Add(new CrashItem());
        this.Add(SummitStatic.ENTITYDESYNC = new EntityDesync());
        this.Add(SummitStatic.LIQUIDINTERACT = new LiquidInteract());
        this.Add(new NoMiningTrace());
        this.Add(new NewChunks());
        this.Add(SummitStatic.PACKETCANCELLER = new PacketCanceller());
        this.Add(SummitStatic.PACKETFLY = new PacketFly());
        this.Add(new PortalGodMode());
        this.Add(SummitStatic.REACH = new Reach());
        this.Add(new Swing());
        this.Add(new Announcer());
        this.Add(SummitStatic.ANNOY = new Annoy());
        this.Add(SummitStatic.ANTIAFK = new AntiAFK());
        this.Add(new AntiShulkerPlace());
        this.Add(new AutoEat());
        this.Add(new AutoFarmland());
        this.Add(SummitStatic.AUTOMEND = new AutoMendArmor());
        this.Add(new AutoMount());
        this.Add(SummitStatic.AUTORECONNECT = new AutoReconnect());
        this.Add(new AutoShear());
        this.Add(new AutoSign());
        this.Add(new AutoTame());
        this.Add(new AutoTend());
        this.Add(new BuildHeight());
        this.Add(SummitStatic.CHATMODIFICATIONS = new ChatModifications());
        this.Add(new ChatNotifier());
        this.Add(SummitStatic.CHESTSTEALER = new ChestStealer());
        this.Add(SummitStatic.CHORUSFRUITBYPASS = new ChorusFruitBypass());
        this.Add(new ChestSwap());
        this.Add(SummitStatic.DISCORDRPC = new DiscordRPC());
        this.Add(new DurabilityAlert());
        this.Add(new FakePlayer());
        this.Add(SummitStatic.FRIENDS = new Friends());
        this.Add(SummitStatic.IMGURUPLOADER = new ImgurUploader());
        this.Add(new GlobalLocation());
        this.Add(new HotbarCache());
        this.Add(new MiddleClickFriends());
        this.Add(new MiddleClickPearl());
        this.Add(new Notifications());
        this.Add(new PacketLogger());
        this.Add(new QueuePeek());
        this.Add(SummitStatic.STOPWATCH = new StopWatch());
        this.Add(new TotemPopNotifier());
        this.Add(new VisualRange());
        this.Add(new XCarry());
        this.Add(SummitStatic.AUTOWALK = new AutoWalk());
        this.Add(SummitStatic.BOATFLY = new BoatFly());
        this.Add(SummitStatic.BLINK = new Blink());
        this.Add(SummitStatic.ELYTRAFLY = new ElytraFly());
        this.Add(new EntityControl());
        this.Add(new FastSwim());
        this.Add(SummitStatic.FLIGHT = new Flight());
        this.Add(new HighJump());
        this.Add(SummitStatic.HOLEFINDER = new HoleFinder());
        this.Add(new IceSpeed());
        this.Add(new LongJump());
        this.Add(new LevitationControl());
        this.Add(new NoFall());
        this.Add(SummitStatic.NOROTATE = new NoRotate());
        this.Add(SummitStatic.NOSLOW = new NoSlow());
        this.Add(new Parkour());
        this.Add(new Jesus());
        this.Add(new ReverseStep());
        this.Add(new SafeWalk());
        this.Add(new Sneak());
        this.Add(SummitStatic.SPEED = new Speed());
        this.Add(new Sprint());
        this.Add(new Step());
        this.Add(new Yaw());
        this.Add(new WebSpeed());
        this.Add(new AntiFog());
        this.Add(new BlockHighlight());
        this.Add(new BreadCrumbs());
        this.Add(new BreakHighlight());
        this.Add(new CityESP());
        this.Add(new ContainerPreview());
        this.Add(SummitStatic.ENTITYESP = new EntityESP());
        this.Add(new EntitySpeed());
        this.Add(new FarmESP());
        this.Add(SummitStatic.FREECAM = new Freecam());
        this.Add(new Fullbright());
        this.Add(new HoleESP());
        this.Add(SummitStatic.ITEMPHYSICS = new ItemPhysics());
        this.Add(new Nametags());
        this.Add(new MapTooltip());
        this.Add(new NoBob());
        this.Add(SummitStatic.NORENDER = new NoRender());
        this.Add(new Search());
        this.Add(new ShulkerPreview());
        this.Add(new Skeleton());
        this.Add(new SmallShield());
        this.Add(SummitStatic.STORAGEESP = new StorageESP());
        this.Add(new SuperheroFX());
        this.Add(new Tracers());
        this.Add(new Trajectories());
        this.Add(SummitStatic.VIEWCLIP = new ViewClip());
        this.Add(new VoidESP());
        this.Add(SummitStatic.WALLHACK = new Wallhack());
        this.Add(new Waypoints());
        this.Add(new WoWTooltips());
        this.Add(SummitStatic.COLORS = new Colors());
        this.Add(new Commands());
        this.Add(new Console());
        this.Add(SummitStatic.CLICKGUI = new ClickGui());
        this.Add(new HudEditor());
        this.Add(SummitStatic.HUD = new Hud());
        this.Add(SummitStatic.KEYBINDS = new Keybinds());
        this.Add(SummitStatic.RELIANTCHAT = new ReliantChat());
        this.Add(new AutoBuilder());
        this.Add(new AutoNameTag());
        this.Add(new AutoMine());
        this.Add(new AutoTool());
        this.Add(SummitStatic.AUTOTUNNEL = new AutoTunnel());
        this.Add(new AutoWither());
        this.Add(new Avoid());
        this.Add(new EnderChestFarmer());
        this.Add(new FastPlace());
        this.Add(new Lawnmower());
        this.Add(SummitStatic.NOGLITCHBLOCKS = new NoGlitchBlocks());
        this.Add(new Nuker());
        this.Add(new Scaffold());
        this.Add(new SkyRender());
        this.Add(new SpeedyGonzales());
        this.Add(SummitStatic.STASHFINDER = new StashFinder());
        this.Add(SummitStatic.STASHLOGGER = new StashLogger());
        this.Add(SummitStatic.TIMER = new TimerModule());
        this.Add(new Weather());
        this.Add(SummitStatic.DUPEBOT = new DupeBot());
        this.Mods.sort((p_Mod1, p_Mod2) -> p_Mod1.getDisplayName().compareTo(p_Mod2.getDisplayName()));
        final Preset preset = PresetsManager.Get().getActivePreset();
        this.Mods.forEach(mod -> preset.initValuesForMod(mod));
        this.Mods.forEach(mod -> mod.init());
    }
    
    public void Add(final Module mod) {
        try {
            for (final Field field : mod.getClass().getDeclaredFields()) {
                if (Value.class.isAssignableFrom(field.getType())) {
                    if (!field.isAccessible()) {
                        field.setAccessible(true);
                    }
                    final Value<?> val = (Value<?>)field.get(mod);
                    val.InitalizeMod(mod);
                    mod.getValueList().add(val);
                }
            }
            this.Mods.add(mod);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public final List<Module> GetModuleList(final Module.ModuleType p_Type) {
        final List<Module> list = new ArrayList<Module>();
        for (final Module module : this.Mods) {
            if (module.getType().equals(p_Type)) {
                list.add(module);
            }
        }
        list.sort(Comparator.comparing((Function<? super Module, ? extends Comparable>)Module::getDisplayName));
        return list;
    }
    
    public final List<Module> GetModuleList() {
        return this.Mods;
    }
    
    public void OnKeyPress(final String string) {
        if (string == null || string.isEmpty() || string.equalsIgnoreCase("NONE")) {
            return;
        }
        this.Mods.forEach(p_Mod -> {
            if (p_Mod.IsKeyPressed(string)) {
                p_Mod.toggle();
            }
        });
    }
    
    public Module GetModLike(final String p_String) {
        for (final Module l_Mod : this.Mods) {
            if (l_Mod.GetArrayListDisplayName().toLowerCase().startsWith(p_String.toLowerCase())) {
                return l_Mod;
            }
        }
        return null;
    }
    
    public boolean IgnoreStrictKeybinds() {
        return (GuiScreen.isAltKeyDown() && !SummitStatic.KEYBINDS.Alt.getValue()) || (GuiScreen.isCtrlKeyDown() && !SummitStatic.KEYBINDS.Ctrl.getValue()) || (GuiScreen.isShiftKeyDown() && !SummitStatic.KEYBINDS.Shift.getValue());
    }
    
    public int GetTotalModsOfCategory(final Module.ModuleType type) {
        int total = 0;
        for (final Module mod : this.Mods) {
            if (mod.getType() == type) {
                ++total;
            }
        }
        return total;
    }
}
