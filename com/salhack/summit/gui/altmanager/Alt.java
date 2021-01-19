// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.altmanager;

public class Alt
{
    private String aUserName;
    private String aPassword;
    private boolean premium;
    
    public Alt(final String username, final String password) {
        this.premium = true;
        this.aUserName = username;
        this.aPassword = password;
    }
    
    public Alt(final String username) {
        this.premium = false;
        this.aUserName = username;
        this.aPassword = "N/A";
    }
    
    public String getFileLine() {
        if (this.premium) {
            return this.aUserName.concat(":").concat(this.aPassword);
        }
        return this.aUserName;
    }
    
    public String getUsername() {
        return this.aUserName;
    }
    
    public String getPassword() throws AccountManagementException {
        if (this.premium) {
            return this.aPassword;
        }
        throw new AccountManagementException("Non-Premium accounts do not have passwords!");
    }
    
    public boolean isPremium() {
        return this.premium;
    }
}
