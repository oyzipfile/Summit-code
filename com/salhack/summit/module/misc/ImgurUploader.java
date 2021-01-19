// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.module.misc;

import com.salhack.summit.module.Value;
import com.salhack.summit.module.Module;

public class ImgurUploader extends Module
{
    public static final Value<Boolean> CopyToClipboard;
    
    public ImgurUploader() {
        super("ImgurUploader", new String[] { "ImgUpload" }, "Automatically uploads images taken with F2 to imgur.", "NONE", -1, ModuleType.MISC);
    }
    
    static {
        CopyToClipboard = new Value<Boolean>("Copy", new String[] { "Clipboard" }, "Copies the link to the clipboard", true);
    }
}
