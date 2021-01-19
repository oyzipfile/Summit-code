// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin.client.entity;

import com.salhack.summit.managers.CommandManager;
import com.salhack.summit.module.ui.Commands;
import com.salhack.summit.main.SummitStatic;
import com.salhack.summit.events.player.EventPlayerIsHandActive;
import com.salhack.summit.events.player.EventPlayerStartRiding;
import net.minecraft.entity.Entity;
import com.salhack.summit.events.player.EventPlayerJump;
import com.salhack.summit.events.player.EventPlayerSendChatMessage;
import com.salhack.summit.events.player.EventPlayerPushOutOfBlocks;
import com.salhack.summit.events.player.EventPlayerSwingArm;
import net.minecraft.util.EnumHand;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.world.GameType;
import com.salhack.summit.util.CameraUtils;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import com.salhack.summit.events.player.EventPlayerUpdate;
import com.salhack.summit.util.entity.PlayerUtil;
import com.salhack.summit.events.player.EventPlayerMotionUpdateCancelled;
import com.salhack.summit.events.player.EventPlayerMove;
import com.salhack.summit.events.MinecraftEvent;
import com.salhack.summit.main.Wrapper;
import net.minecraft.entity.MoverType;
import org.spongepowered.asm.mixin.injection.Inject;
import com.salhack.summit.events.player.EventPlayerLivingUpdate;
import com.salhack.summit.SummitMod;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import com.salhack.summit.events.player.EventPlayerMotionUpdate;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.util.MovementInput;
import net.minecraft.client.entity.EntityPlayerSP;
import org.spongepowered.asm.mixin.Mixin;
import com.salhack.summit.mixin.client.MixinAbstractClientPlayer;

@Mixin({ EntityPlayerSP.class })
public abstract class MixinEntityPlayerSP extends MixinAbstractClientPlayer
{
    @Shadow
    public MovementInput movementInput;
    private EventPlayerMotionUpdate _event;
    
    @Shadow
    protected abstract boolean isCurrentViewEntity();
    
    @Redirect(method = { "onLivingUpdate" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/entity/EntityPlayerSP;closeScreen()V"))
    public void closeScreen(final EntityPlayerSP entityPlayerSP) {
    }
    
    @Redirect(method = { "onLivingUpdate" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V"))
    public void closeScreen(final Minecraft minecraft, final GuiScreen screen) {
    }
    
    @Inject(method = { "onLivingUpdate" }, at = { @At("HEAD") })
    public void onLivingUpdate(final CallbackInfo info) {
        SummitMod.EVENT_BUS.post(new EventPlayerLivingUpdate());
    }
    
    @Inject(method = { "move" }, at = { @At("HEAD") }, cancellable = true)
    public void move(final MoverType type, final double x, final double y, final double z, final CallbackInfo p_Info) {
        if ((EntityPlayerSP)this == Wrapper.GetMC().player) {
            final EventPlayerMove event = new EventPlayerMove(MinecraftEvent.Stage.Pre, type, x, y, z);
            SummitMod.EVENT_BUS.post(event);
            if (event.isCancelled()) {
                super.move(type, event.X, event.Y, event.Z);
                p_Info.cancel();
            }
        }
    }
    
    @Inject(method = { "move" }, at = { @At("RETURN") })
    public void movePost(final MoverType type, final double x, final double y, final double z, final CallbackInfo p_Info) {
        if ((EntityPlayerSP)this == Wrapper.GetMC().player) {
            SummitMod.EVENT_BUS.post(new EventPlayerMove(MinecraftEvent.Stage.Post, type, x, y, z));
        }
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("HEAD") }, cancellable = true)
    public void OnPreUpdateWalkingPlayer(final CallbackInfo info) {
        this._event = new EventPlayerMotionUpdate(MinecraftEvent.Stage.Pre, this.posX, this.getEntityBoundingBox().minY, this.posZ, this.onGround);
        SummitMod.EVENT_BUS.post(this._event);
        if (this._event.isCancelled()) {
            SummitMod.EVENT_BUS.post(new EventPlayerMotionUpdateCancelled(MinecraftEvent.Stage.Pre, this._event.getPitch(), this._event.getYaw()));
            info.cancel();
            PlayerUtil.sendMovementPackets(this._event);
            this.postWalkingUpdate();
        }
        if (this._event.isForceCancelled()) {
            info.cancel();
        }
    }
    
    private void postWalkingUpdate() {
        if (this._event.getFunc() != null) {
            this._event.getFunc().accept((EntityPlayerSP)this);
        }
        this._event.setEra(MinecraftEvent.Stage.Post);
        SummitMod.EVENT_BUS.post(this._event);
        if (this._event.isCancelled()) {
            SummitMod.EVENT_BUS.post(new EventPlayerMotionUpdateCancelled(MinecraftEvent.Stage.Pre, this._event.getPitch(), this._event.getYaw()));
        }
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("RETURN") })
    public void OnPostUpdateWalkingPlayer(final CallbackInfo info) {
        this.postWalkingUpdate();
    }
    
    @Inject(method = { "onUpdate" }, at = { @At("HEAD") })
    public void onUpdate(final CallbackInfo info) {
        SummitMod.EVENT_BUS.post(new EventPlayerUpdate());
    }
    
    @Inject(method = { "isCurrentViewEntity" }, at = { @At("HEAD") }, cancellable = true)
    private void allowPlayerMovementInFreeCameraMode(final CallbackInfoReturnable<Boolean> cir) {
        if (CameraUtils.freecamEnabled()) {
            cir.setReturnValue((Object)true);
        }
    }
    
    private boolean isSpec() {
        final NetworkPlayerInfo networkplayerinfo = Minecraft.getMinecraft().getConnection().getPlayerInfo(Wrapper.GetPlayer().getGameProfile().getId());
        return networkplayerinfo != null && networkplayerinfo.getGameType() == GameType.SPECTATOR;
    }
    
    public boolean isSpectator() {
        return this.isSpec() || CameraUtils.getFreeCameraSpectator();
    }
    
    @Inject(method = { "swingArm" }, at = { @At("HEAD") }, cancellable = true)
    public void swingArm(final EnumHand p_Hand, final CallbackInfo p_Info) {
        final EventPlayerSwingArm l_Event = new EventPlayerSwingArm(p_Hand);
        SummitMod.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            p_Info.cancel();
        }
    }
    
    @Inject(method = { "pushOutOfBlocks(DDD)Z" }, at = { @At("HEAD") }, cancellable = true)
    public void pushOutOfBlocks(final double x, final double y, final double z, final CallbackInfoReturnable<Boolean> callbackInfo) {
        final EventPlayerPushOutOfBlocks l_Event = new EventPlayerPushOutOfBlocks(x, y, z);
        SummitMod.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            callbackInfo.setReturnValue((Object)false);
        }
    }
    
    @Inject(method = { "sendChatMessage" }, at = { @At("HEAD") }, cancellable = true)
    public void swingArm(final String p_Message, final CallbackInfo p_Info) {
        final EventPlayerSendChatMessage l_Event = new EventPlayerSendChatMessage(p_Message);
        SummitMod.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            p_Info.cancel();
        }
    }
    
    @Override
    public void jump() {
        try {
            final EventPlayerJump event = new EventPlayerJump(this.motionX, this.motionZ);
            SummitMod.EVENT_BUS.post(event);
            if (!event.isCancelled()) {
                super.jump();
            }
        }
        catch (Exception v3) {
            v3.printStackTrace();
        }
    }
    
    @Inject(method = { "startRiding" }, at = { @At("HEAD") }, cancellable = true)
    public void startRiding(final Entity e, final boolean force, final CallbackInfoReturnable<Boolean> info) {
        SummitMod.EVENT_BUS.post(new EventPlayerStartRiding());
    }
    
    @Inject(method = { "isHandActive" }, at = { @At("HEAD") }, cancellable = true)
    public void isHandActive(final CallbackInfoReturnable<Boolean> info) {
        final EventPlayerIsHandActive event = new EventPlayerIsHandActive();
        SummitMod.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            info.cancel();
            info.setReturnValue((Object)false);
        }
    }
    
    @Override
    public boolean isElytraFlying() {
        return (SummitStatic.ELYTRAFLY == null || !SummitStatic.ELYTRAFLY.isEnabled() || !SummitStatic.ELYTRAFLY.Mode.getValue().equals("Packet")) && this.getFlag(7);
    }
    
    @Inject(method = { "sendChatMessage" }, at = { @At("HEAD") }, cancellable = true)
    public void sendChatMessage(final String msg, final CallbackInfo info) {
        if (msg.startsWith(Commands.Prefix.getValue())) {
            CommandManager.Get().processCommand(msg.substring(1));
            info.cancel();
        }
    }
}
