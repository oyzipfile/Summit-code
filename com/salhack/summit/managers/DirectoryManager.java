// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.managers;

import com.salhack.summit.main.Summit;
import java.io.File;
import java.io.IOException;

public class DirectoryManager
{
    public void Init() {
        try {
            this.CreateDirectory("Summit");
            this.CreateDirectory("Summit/Modules");
            this.CreateDirectory("Summit/GUI");
            this.CreateDirectory("Summit/HUD");
            this.CreateDirectory("Summit/Locater");
            this.CreateDirectory("Summit/StashFinder");
            this.CreateDirectory("Summit/Config");
            this.CreateDirectory("Summit/Capes");
            this.CreateDirectory("Summit/Music");
            this.CreateDirectory("Summit/CoordExploit");
            this.CreateDirectory("Summit/LogoutSpots");
            this.CreateDirectory("Summit/DeathSpots");
            this.CreateDirectory("Summit/Waypoints");
            this.CreateDirectory("Summit/Fonts");
            this.CreateDirectory("Summit/CustomMods");
            this.CreateDirectory("Summit/Presets");
            this.CreateDirectory("Summit/Presets/Default");
            this.CreateDirectory("Summit/Presets/Default/Modules");
            this.CreateDirectory("Summit/PacketLogger");
            this.CreateDirectory("Summit/Macros");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void CreateDirectory(final String p_Path) throws IOException {
        new File(p_Path).mkdirs();
    }
    
    public static DirectoryManager Get() {
        return Summit.GetDirectoryManager();
    }
    
    public String GetCurrentDirectory() throws IOException {
        return new File(".").getCanonicalPath();
    }
}
