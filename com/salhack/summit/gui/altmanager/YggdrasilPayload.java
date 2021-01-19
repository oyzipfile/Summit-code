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

public class YggdrasilPayload
{
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
            e.printStackTrace();
            System.out.println("Failed login: " + username + ":" + password);
            return null;
        }
    }
    
    public static Session loginCrack(final String username) {
        return new Session(username, "", "", "LEGACY");
    }
}
