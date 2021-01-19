// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.altmanager;

import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import com.salhack.summit.main.Wrapper;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import org.lwjgl.input.Keyboard;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.GuiScreen;

public class GuiDirectLogin extends GuiScreen
{
    public GuiScreen parent;
    public GuiTextField usernameBox;
    public GuiPasswordField passwordBox;
    
    public GuiDirectLogin(final GuiScreen paramScreen) {
        this.parent = paramScreen;
    }
    
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, this.height / 4 + 96 + 12, "Login"));
        this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height / 4 + 96 + 36, "Back"));
        this.usernameBox = new GuiTextField(0, this.fontRenderer, this.width / 2 - 100, 51, 200, 20);
        this.passwordBox = new GuiPasswordField(2, this.fontRenderer, this.width / 2 - 100, 91, 200, 20);
        this.usernameBox.setMaxStringLength(200);
        this.passwordBox.setMaxStringLength(200);
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }
    
    public void updateScreen() {
        this.usernameBox.updateCursorCounter();
        this.passwordBox.updateCursorCounter();
    }
    
    public void mouseClicked(final int x, final int y, final int b) throws IOException {
        this.usernameBox.mouseClicked(x, y, b);
        this.passwordBox.mouseClicked(x, y, b);
        super.mouseClicked(x, y, b);
    }
    
    public void actionPerformed(final GuiButton button) {
        if (button.id == 1) {
            final YggdrasilAuthenticator auth = new YggdrasilAuthenticator(this.usernameBox.getText(), this.passwordBox.getText());
            if (auth.login()) {
                Wrapper.GetMC().session = auth.getSession();
            }
        }
        else if (button.id == 2) {
            Wrapper.GetMC().displayGuiScreen(this.parent);
        }
    }
    
    public static String excutePost(final String s, final String s1) {
        HttpsURLConnection httpsurlconnection = null;
        try {
            final URL url = new URL(s);
            httpsurlconnection = (HttpsURLConnection)url.openConnection();
            httpsurlconnection.setRequestMethod("POST");
            httpsurlconnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpsurlconnection.setRequestProperty("Content-Type", Integer.toString(s1.getBytes().length));
            httpsurlconnection.setRequestProperty("Content-Language", "en-US");
            httpsurlconnection.setUseCaches(false);
            httpsurlconnection.setDoInput(true);
            httpsurlconnection.setDoOutput(true);
            httpsurlconnection.connect();
            final DataOutputStream dataoutputstream = new DataOutputStream(httpsurlconnection.getOutputStream());
            dataoutputstream.writeBytes(s1);
            dataoutputstream.flush();
            dataoutputstream.close();
            final InputStream inputstream = httpsurlconnection.getInputStream();
            final BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
            final StringBuffer stringbuffer = new StringBuffer();
            String s2;
            while ((s2 = bufferedreader.readLine()) != null) {
                stringbuffer.append(s2);
                stringbuffer.append('\r');
            }
            bufferedreader.close();
            final String s4;
            final String s3 = s4 = stringbuffer.toString();
            return s3;
        }
        catch (Exception exception) {
            exception.printStackTrace();
            return null;
        }
        finally {
            if (httpsurlconnection != null) {
                httpsurlconnection.disconnect();
            }
        }
    }
    
    protected void keyTyped(final char c, final int i) {
        this.usernameBox.textboxKeyTyped(c, i);
        this.passwordBox.textboxKeyTyped(c, i);
        if (c == '\t') {
            if (this.usernameBox.isFocused()) {
                this.usernameBox.setFocused(false);
                this.passwordBox.setFocused(true);
            }
            else if (this.passwordBox.isFocused()) {
                this.usernameBox.setFocused(false);
                this.passwordBox.setFocused(false);
            }
        }
        if (c == '\r') {
            this.actionPerformed(this.buttonList.get(0));
        }
    }
    
    public void drawScreen(final int x, final int y, final float f) {
        this.drawDefaultBackground();
        this.drawString(this.fontRenderer, "Username", this.width / 2 - 100, 38, 10526880);
        this.drawString(this.fontRenderer, "§4*", this.width / 2 - 106, 38, 10526880);
        this.drawString(this.fontRenderer, "Password", this.width / 2 - 100, 79, 10526880);
        try {
            this.usernameBox.drawTextBox();
            this.passwordBox.drawTextBox();
        }
        catch (Exception err) {
            err.printStackTrace();
        }
        super.drawScreen(x, y, f);
    }
}
