// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.mixin.client;

import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import java.util.Iterator;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import com.salhack.summit.main.Wrapper;
import net.minecraft.util.math.MathHelper;
import com.salhack.summit.main.SummitStatic;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.util.text.ITextComponent;
import com.google.common.collect.Lists;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Shadow;
import net.minecraft.client.gui.ChatLine;
import java.util.List;
import net.minecraft.client.gui.GuiNewChat;
import org.spongepowered.asm.mixin.Mixin;

@Mixin({ GuiNewChat.class })
public class MixinGuiNewChat
{
    @Shadow
    @Final
    public final List<ChatLine> chatLines;
    @Shadow
    @Final
    public final List<ChatLine> drawnChatLines;
    @Shadow
    public int scrollPos;
    @Shadow
    public boolean isScrolled;
    
    public MixinGuiNewChat() {
        this.chatLines = (List<ChatLine>)Lists.newArrayList();
        this.drawnChatLines = (List<ChatLine>)Lists.newArrayList();
    }
    
    @Inject(method = { "setChatLine" }, at = { @At("HEAD") }, cancellable = true)
    private void setChatLine(final ITextComponent chatComponent, final int chatLineId, final int updateCounter, final boolean displayOnly, final CallbackInfo info) {
        if (SummitStatic.CHATMODIFICATIONS != null) {
            info.cancel();
            final int maxLines = (SummitStatic.CHATMODIFICATIONS.ChatLength.getValue() == -1) ? 16777215 : SummitStatic.CHATMODIFICATIONS.ChatLength.getValue();
            final GuiNewChat guiNewChat = (GuiNewChat)this;
            if (chatLineId != 0) {
                guiNewChat.deleteChatLine(chatLineId);
            }
            final int i = MathHelper.floor(guiNewChat.getChatWidth() / guiNewChat.getChatScale());
            final List<ITextComponent> list = (List<ITextComponent>)GuiUtilRenderComponents.splitText(chatComponent, i, Wrapper.GetMC().fontRenderer, false, false);
            final boolean flag = guiNewChat.getChatOpen();
            for (final ITextComponent itextcomponent : list) {
                if (flag && this.scrollPos > 0) {
                    this.isScrolled = true;
                    guiNewChat.scroll(1);
                }
                this.drawnChatLines.add(0, new ChatLine(updateCounter, itextcomponent, chatLineId));
            }
            while (this.drawnChatLines.size() > maxLines) {
                this.drawnChatLines.remove(this.drawnChatLines.size() - 1);
            }
            if (!displayOnly) {
                this.chatLines.add(0, new ChatLine(updateCounter, chatComponent, chatLineId));
                while (this.chatLines.size() > maxLines) {
                    this.chatLines.remove(this.chatLines.size() - 1);
                }
            }
        }
    }
}
