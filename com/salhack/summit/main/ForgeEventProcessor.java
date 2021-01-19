// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.main;

import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import com.salhack.summit.events.render.EventRenderGetFOVModifier;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.client.event.PlayerSPPushOutOfBlocksEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import org.lwjgl.input.Mouse;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import com.salhack.summit.events.minecraft.EventKeyInput;
import com.salhack.summit.managers.MacroManager;
import com.salhack.summit.managers.ModuleManager;
import org.lwjgl.input.Keyboard;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import com.salhack.summit.events.client.EventClientTick;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import com.salhack.summit.util.render.AWTFontRenderer;
import com.salhack.summit.events.render.RenderEvent;
import com.salhack.summit.SummitMod;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import java.awt.image.BufferedImage;
import net.minecraftforge.client.event.ScreenshotEvent;
import com.salhack.summit.util.imgur.ImgurUploader;

public class ForgeEventProcessor
{
    private ImgurUploader imgurUploader;
    
    public ForgeEventProcessor() {
        this.imgurUploader = new ImgurUploader();
    }
    
    @SubscribeEvent
    public void onScreenshot(final ScreenshotEvent event) {
        if (SummitStatic.IMGURUPLOADER != null && SummitStatic.IMGURUPLOADER.isEnabled()) {
            final BufferedImage screenshot = event.getImage();
            this.imgurUploader.uploadImage(screenshot);
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        if (event.isCanceled()) {
            return;
        }
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GlStateManager.disableDepth();
        GlStateManager.glLineWidth(1.0f);
        SummitMod.EVENT_BUS.post(new RenderEvent(event.getPartialTicks()));
        GlStateManager.glLineWidth(1.0f);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        AWTFontRenderer.garbageCollectionTick();
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        if (Wrapper.GetMC().player == null) {
            return;
        }
        SummitMod.EVENT_BUS.post(new EventClientTick());
    }
    
    @SubscribeEvent
    public void onEntitySpawn(final EntityJoinWorldEvent event) {
        if (event.isCanceled()) {
            return;
        }
        SummitMod.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(final InputEvent.KeyInputEvent event) {
        if (Keyboard.getEventKeyState()) {
            final String key = Keyboard.getKeyName(Keyboard.getEventKey());
            ModuleManager.Get().OnKeyPress(key);
            MacroManager.Get().OnKeyPress(key);
            if (!key.equals("NONE") && !key.isEmpty()) {
                SummitMod.EVENT_BUS.post(new EventKeyInput(key));
            }
        }
    }
    
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(final InputEvent.MouseInputEvent event) {
        if (Mouse.getEventButtonState()) {
            final String button = Mouse.getButtonName(Mouse.getEventButton());
            ModuleManager.Get().OnKeyPress(button);
            MacroManager.Get().OnKeyPress(button);
            SummitMod.EVENT_BUS.post(event);
        }
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPlayerDrawn(final RenderPlayerEvent.Pre event) {
        SummitMod.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onPlayerDrawn(final RenderPlayerEvent.Post event) {
        SummitMod.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onChunkLoaded(final ChunkEvent.Load event) {
        SummitMod.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onChunkUnLoaded(final ChunkEvent.Unload event) {
        SummitMod.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onInputUpdate(final InputUpdateEvent event) {
        SummitMod.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onLivingEntityUseItemEventTick(final LivingEntityUseItemEvent.Start entityUseItemEvent) {
        SummitMod.EVENT_BUS.post(entityUseItemEvent);
    }
    
    @SubscribeEvent
    public void onLivingDamageEvent(final LivingDamageEvent event) {
        SummitMod.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onEntityJoinWorldEvent(final EntityJoinWorldEvent entityJoinWorldEvent) {
        SummitMod.EVENT_BUS.post(entityJoinWorldEvent);
    }
    
    @SubscribeEvent
    public void onPlayerPush(final PlayerSPPushOutOfBlocksEvent event) {
        SummitMod.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onLeftClickBlock(final PlayerInteractEvent.LeftClickBlock event) {
        SummitMod.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onAttackEntity(final AttackEntityEvent entityEvent) {
        SummitMod.EVENT_BUS.post(entityEvent);
    }
    
    @SubscribeEvent
    public void onRenderBlockOverlay(final RenderBlockOverlayEvent event) {
        SummitMod.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void onClientChat(final ClientChatReceivedEvent event) {
        SummitMod.EVENT_BUS.post(event);
    }
    
    @SubscribeEvent
    public void getFOVModifier(final EntityViewRenderEvent.FOVModifier p_Event) {
        final EventRenderGetFOVModifier l_Event = new EventRenderGetFOVModifier((float)p_Event.getRenderPartialTicks(), true);
        SummitMod.EVENT_BUS.post(l_Event);
        if (l_Event.isCancelled()) {
            p_Event.setFOV(l_Event.GetFOV());
        }
    }
    
    @SubscribeEvent
    public void LivingAttackEvent(final LivingAttackEvent p_Event) {
        SummitMod.EVENT_BUS.post(p_Event);
    }
    
    @SubscribeEvent
    public void OnWorldChange(final WorldEvent p_Event) {
        SummitMod.EVENT_BUS.post(p_Event);
    }
}
