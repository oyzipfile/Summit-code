// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.util.imgur;

import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;

public class CopyToClipboard
{
    private String copiedText;
    
    public boolean copy(final String message) {
        if (message != null) {
            final StringSelection stringSelection = new StringSelection(message);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            this.copiedText = message;
            return true;
        }
        return false;
    }
    
    public String getCopy() {
        return this.copiedText;
    }
}
