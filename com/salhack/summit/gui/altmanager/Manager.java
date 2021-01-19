// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.altmanager;

import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import java.net.Proxy;
import net.minecraft.util.Session;
import java.net.HttpURLConnection;
import java.util.UUID;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Manager
{
    public static ArrayList<Alt> altList;
    public static GuiAltList altScreen;
    public static final int slotHeight = 25;
    
    public static void addAlt(final Alt paramAlt) {
        Manager.altList.add(paramAlt);
    }
    
    public static void addAlt(final String username) {
        Manager.altList.add(new Alt(username));
    }
    
    public static void addAlt(final String username, final String password) {
        Manager.altList.add(new Alt(username, password));
    }
    
    public static String makePassChar(final String regex) {
        return regex.replaceAll("(?s).", "*");
    }
    
    public static ArrayList<Alt> getList() {
        return Manager.altList;
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
    
    public static String getResponse(final String userpass) {
        final UUID token = UUID.randomUUID();
        final String json = "{\n \"agent\":{\n \"name\": \"Minecraft\",\n \"version\": \"1\"\n },\n \"username\": \"" + userpass.split(":")[0] + "\",\n \"password\": \"" + userpass.split(":")[1] + "\",\n \"clientToken\": \"" + token.toString() + "\"\n}";
        return excutePost("https://authserver.mojang.com/authenticate", json);
    }
    
    public static String excutePost2(final String targetURL, final String urlParameters) {
        HttpURLConnection connection = null;
        try {
            final URL url = new URL(targetURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", "" + Integer.toString(urlParameters.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            final DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            final InputStream is = connection.getInputStream();
            final BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            final StringBuffer response = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            return response.toString();
        }
        catch (Exception e) {
            return null;
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }
    
    public static Session loginPassword(final String username, final String password) {
        if (username == null || username.length() <= 0 || password == null || password.length() <= 0) {
            return null;
        }
        final YggdrasilAuthenticationService a = new YggdrasilAuthenticationService(Proxy.NO_PROXY, "");
        final YggdrasilUserAuthentication b = (YggdrasilUserAuthentication)a.createUserAuthentication(Agent.MINECRAFT);
        b.setUsername(username);
        b.setPassword(password);
        try {
            b.logIn();
            return new Session(b.getSelectedProfile().getName(), b.getSelectedProfile().getId().toString(), b.getAuthenticatedToken(), "LEGACY");
        }
        catch (AuthenticationException e) {
            Manager.altScreen.dispErrorString = "".concat("§cBad Login §7(").concat(username).concat(")");
            e.printStackTrace();
            return null;
        }
    }
    
    static {
        Manager.altList = new ArrayList<Alt>();
        Manager.altScreen = new GuiAltList();
    }
}
