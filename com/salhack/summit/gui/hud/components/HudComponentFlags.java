// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.gui.hud.components;

public class HudComponentFlags
{
    public static int None;
    public static int OnlyVisibleInHudEditor;
    public static int IsInCornerList;
    
    static {
        HudComponentFlags.None = 0;
        HudComponentFlags.OnlyVisibleInHudEditor = 1;
        HudComponentFlags.IsInCornerList = 2;
    }
}
