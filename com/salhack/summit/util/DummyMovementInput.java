// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.util;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.MovementInputFromOptions;

public class DummyMovementInput extends MovementInputFromOptions
{
    public DummyMovementInput(final GameSettings gameSettingsIn) {
        super(gameSettingsIn);
    }
    
    public void updatePlayerMoveState() {
    }
}
