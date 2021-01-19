// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.chat;

import java.util.List;
import javax.annotation.Nullable;
import java.util.Iterator;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.text.ITextComponent;
import com.salhack.summit.managers.FontManager;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.MathHelper;
import net.minecraft.entity.player.EntityPlayer;
import com.salhack.summit.events.bus.EventHandler;
import com.salhack.summit.events.render.EventRenderGuiUpdateTick;
import com.salhack.summit.events.bus.Listener;
import com.salhack.summit.events.bus.EventListener;
import com.salhack.summit.SummitMod;
import net.minecraft.client.Minecraft;
import com.salhack.summit.module.ui.ReliantChat;
import com.salhack.summit.util.BouncyValue;
import net.minecraft.client.gui.GuiNewChat;

public class SalGuiNewChat extends GuiNewChat
{
    private final BouncyValue chatBounce;
    
    private boolean isSmooth() {
        return ReliantChat.INSTANCE.SmoothChat.getValue();
    }
    
    private boolean isFancyFont() {
        return ReliantChat.INSTANCE.FancyFont.getValue();
    }
    
    public SalGuiNewChat(final Minecraft mcIn) {
        super(mcIn);
        this.chatBounce = new BouncyValue(0.5f, 0.3f);
        SummitMod.EVENT_BUS.subscribe(new EventListener() {
            @EventHandler
            private final Listener<EventRenderGuiUpdateTick> UpdateTick = new Listener<EventRenderGuiUpdateTick>(ignored -> {
                if (SalGuiNewChat.this.isSmooth()) {
                    SalGuiNewChat.this.chatBounce.update();
                }
            });
        });
    }
    
    public void drawChat(final int updateCounter) {
        final float partialTicks = this.mc.timer.renderPartialTicks;
        final float bounceDifference = this.isSmooth() ? this.chatBounce.get(partialTicks) : 0.0f;
        if (this.mc.gameSettings.chatVisibility != EntityPlayer.EnumChatVisibility.HIDDEN) {
            final int i = this.getLineCount();
            final int lineAndBounce = i + MathHelper.ceil(Math.abs(bounceDifference) / 9.0f + 0.1f);
            final int j = this.drawnChatLines.size();
            final float f = this.mc.gameSettings.chatOpacity * 0.9f + 0.1f;
            if (j > 0) {
                boolean flag = false;
                if (this.getChatOpen()) {
                    flag = true;
                }
                final float f2 = this.getChatScale();
                final int k = MathHelper.ceil(this.getChatWidth() / f2);
                GlStateManager.pushMatrix();
                GlStateManager.translate(2.0f, 8.0f - bounceDifference, 0.0f);
                GlStateManager.scale(f2, f2, 1.0f);
                int l = 0;
                for (int i2 = 0; i2 + this.scrollPos < this.drawnChatLines.size() && i2 < lineAndBounce; ++i2) {
                    final ChatLine chatline = this.drawnChatLines.get(i2 + this.scrollPos);
                    if (chatline != null) {
                        final float counter = partialTicks + updateCounter - chatline.getUpdatedCounter();
                        if (counter < 200.0f || flag) {
                            if (!flag || i2 != this.getLineCount()) {
                                double d0 = counter / 200.0;
                                double d2 = d0 * 50.0;
                                d0 = 1.0 - d0;
                                d0 *= 10.0;
                                d0 = MathHelper.clamp(d0, 0.0, 1.0);
                                d0 *= d0;
                                d2 = MathHelper.clamp(d2, 0.0, 1.0);
                                d0 *= d2;
                                if (this.isSmooth()) {
                                    float f3 = i2 + bounceDifference * 0.11111111f + 1.99f;
                                    if (f3 > i) {
                                        if ((f3 -= i) > 1.0f) {
                                            f3 = 1.0f;
                                        }
                                        d0 *= 1.0f - f3;
                                    }
                                }
                                int l2 = (int)(255.0 * d0);
                                if (flag) {
                                    l2 = 255;
                                }
                                l2 *= (int)f;
                                ++l;
                                if (l2 > 3) {
                                    final int j2 = -i2 * 10;
                                    drawRect(-2, j2 - 10, k + 4, j2, l2 / 2 << 24);
                                    final String s = chatline.getChatComponent().getFormattedText();
                                    GlStateManager.enableBlend();
                                    if (this.isFancyFont()) {
                                        FontManager.Get().getGameFont().drawStringWithShadow(s, 0.0f, (float)(j2 - 9), 16777215 + (l2 << 24));
                                    }
                                    else {
                                        this.mc.fontRenderer.drawStringWithShadow(s, 0.0f, (float)(j2 - 9), 16777215 + (l2 << 24));
                                    }
                                    GlStateManager.disableAlpha();
                                    GlStateManager.disableBlend();
                                }
                            }
                        }
                    }
                }
                if (flag) {
                    final int k2 = FontManager.Get().getGameFont().getHeight();
                    GlStateManager.translate(-3.0f, 0.0f, 0.0f);
                    final int l3 = j * k2 + j;
                    final int i3 = l * k2 + l;
                    final int j3 = this.scrollPos * i3 / j;
                    final int k3 = i3 * i3 / l3;
                    if (l3 != i3) {
                        final int k4 = (j3 > 0) ? 170 : 96;
                        final int l4 = this.isScrolled ? 13382451 : 3355562;
                        drawRect(0, -j3, 2, -j3 - k3, l4 + (k4 << 24));
                        drawRect(2, -j3, 1, -j3 - k3, 13421772 + (k4 << 24));
                    }
                }
                GlStateManager.popMatrix();
            }
        }
    }
    
    @Nullable
    public ITextComponent getChatComponent(final int mouseX, final int mouseY) {
        if (this.getChatOpen()) {
            final ScaledResolution scaledresolution = new ScaledResolution(this.mc);
            final int i = scaledresolution.getScaleFactor();
            final float f = this.getChatScale();
            int j = mouseX / i - 2;
            int k = mouseY / i - 40;
            j = MathHelper.floor(j / f);
            k = MathHelper.floor(k / f);
            if (j >= 0 && k >= 0) {
                final int l = Math.min(this.getLineCount(), this.drawnChatLines.size());
                final int height = this.isFancyFont() ? FontManager.Get().getGameFont().getHeight() : this.mc.fontRenderer.FONT_HEIGHT;
                if (j <= MathHelper.floor(this.getChatWidth() / this.getChatScale()) && k < height * l + l) {
                    final int i2 = k / height + this.scrollPos;
                    if (i2 >= 0 && i2 < this.drawnChatLines.size()) {
                        final ChatLine chatline = this.drawnChatLines.get(i2);
                        int j2 = 0;
                        for (final ITextComponent itextcomponent : chatline.getChatComponent()) {
                            if (itextcomponent instanceof TextComponentString) {
                                final String s = GuiUtilRenderComponents.removeTextColorsIfConfigured(((TextComponentString)itextcomponent).getText(), false);
                                final int width = this.isFancyFont() ? FontManager.Get().getGameFont().getStringWidth(s) : this.mc.fontRenderer.getStringWidth(s);
                                j2 += width;
                                if (j2 > j) {
                                    return itextcomponent;
                                }
                                continue;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    
    public void printChatMessageWithOptionalDeletion(final ITextComponent chatComponent, final int chatLineId) {
        this.setChatLine(chatComponent, chatLineId, this.mc.ingameGUI.getUpdateCounter(), false);
        SalGuiNewChat.LOGGER.info("[CHAT] {}", (Object)chatComponent.getUnformattedText().replaceAll("\r", "\\\\r").replaceAll("\n", "\\\\n"));
    }
    
    public void refreshChat() {
        this.drawnChatLines.clear();
        this.resetScroll();
        for (int i = this.chatLines.size() - 1; i >= 0; --i) {
            final ChatLine chatline = this.chatLines.get(i);
            this.setChatLine(chatline.getChatComponent(), chatline.getChatLineID(), chatline.getUpdatedCounter(), true);
        }
        this.chatBounce.reset();
    }
    
    private void setChatLine(final ITextComponent chatComponent, final int chatLineId, final int updateCounter, final boolean displayOnly) {
        if (chatLineId != 0) {
            this.deleteChatLine(chatLineId);
        }
        final int i = MathHelper.floor(this.getChatWidth() / this.getChatScale());
        final List<ITextComponent> list = (List<ITextComponent>)GuiUtilRenderComponents.splitText(chatComponent, i, this.mc.fontRenderer, false, false);
        final boolean flag = this.getChatOpen();
        for (final ITextComponent itextcomponent : list) {
            if (flag && this.scrollPos > 0) {
                this.isScrolled = true;
                this.scroll(1);
            }
            if (!this.isScrolled) {
                this.chatBounce.subtract(9.0f);
            }
            this.drawnChatLines.add(0, new ChatLine(updateCounter, itextcomponent, chatLineId));
        }
        while (this.drawnChatLines.size() > 100) {
            this.drawnChatLines.remove(this.drawnChatLines.size() - 1);
        }
        if (!displayOnly) {
            this.chatLines.add(0, new ChatLine(updateCounter, chatComponent, chatLineId));
            while (this.chatLines.size() > 100) {
                this.chatLines.remove(this.chatLines.size() - 1);
            }
        }
    }
    
    public void deleteChatLine(final int id) {
        Iterator<ChatLine> iterator = this.drawnChatLines.iterator();
        while (iterator.hasNext()) {
            final ChatLine chatline = iterator.next();
            if (chatline.getChatLineID() == id) {
                iterator.remove();
                this.chatBounce.subtract(-9.0f);
            }
        }
        iterator = this.chatLines.iterator();
        while (iterator.hasNext()) {
            final ChatLine chatline2 = iterator.next();
            if (chatline2.getChatLineID() == id) {
                iterator.remove();
                break;
            }
        }
    }
}
