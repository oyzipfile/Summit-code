// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin.client;

import com.salhack.summit.events.world.EventLoadWorld;
import com.salhack.summit.SummitMod;
import com.salhack.summit.util.CrystalUtils2;
import javax.annotation.Nullable;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.salhack.summit.gui.ingame.SalGuiIngame;
import com.salhack.summit.main.Wrapper;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ Minecraft.class })
public class MixinMinecraft
{
    @Shadow
    WorldClient world;
    @Shadow
    EntityPlayerSP player;
    @Shadow
    GuiScreen currentScreen;
    @Shadow
    GameSettings gameSettings;
    @Shadow
    GuiIngame ingameGUI;
    @Shadow
    boolean skipRenderWorld;
    @Shadow
    SoundHandler soundHandler;
    @Shadow
    private int leftClickCounter;
    @Shadow
    public ParticleManager effectRenderer;
    @Shadow
    public RayTraceResult objectMouseOver;
    @Shadow
    public PlayerControllerMP playerController;
    
    @Inject(method = { "init" }, at = { @At("RETURN") })
    private void init(final CallbackInfo callbackInfo) {
        this.ingameGUI = (GuiIngame)new SalGuiIngame(Wrapper.GetMC());
    }
    
    @Inject(method = { "loadWorld" }, at = { @At("HEAD") })
    private void loadWorld(@Nullable final WorldClient worldClientIn, final CallbackInfo info) {
        CrystalUtils2.loadWorld();
        SummitMod.EVENT_BUS.post(new EventLoadWorld());
    }
}
