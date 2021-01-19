// 
// Decompiled by Procyon v0.5.36
// 

package com.salhack.summit.communication;

import com.salhack.summit.main.SummitStatic;
import java.util.function.Consumer;

public class ServerHandlers
{
    private Client _client;
    Consumer<Packet> OnStartDupe;
    Consumer<Packet> OnStopDupe;
    Consumer<Packet> OnRideDonkey;
    Consumer<Packet> OnEndOfRoad;
    Consumer<Packet> OnChunksLoaded;
    Consumer<Packet> OnRemountDesync;
    Consumer<Packet> OnShutdown;
    Consumer<Packet> OnPong;
    Consumer<Packet> OnReady;
    Consumer<Packet> OnDismountedDonkey;
    Consumer<Packet> OnTestOpcode;
    Consumer<Packet> OnInvalidOpcode;
    
    public ServerHandlers(final Client client) {
        this.OnStartDupe = (packet -> SummitStatic.DUPEBOT.OnStartDupe());
        this.OnStopDupe = (packet -> SummitStatic.DUPEBOT.OnStopDupe());
        this.OnRideDonkey = (packet -> SummitStatic.DUPEBOT.OnRideDonkey());
        this.OnEndOfRoad = (packet -> SummitStatic.DUPEBOT.OnEndOfRoad());
        this.OnChunksLoaded = (packet -> SummitStatic.DUPEBOT.OnChunksLoad());
        this.OnRemountDesync = (packet -> SummitStatic.DUPEBOT.OnRemountDesync());
        this.OnShutdown = (packet -> SummitStatic.DUPEBOT.OnShutdown());
        this.OnPong = (packet -> this._client.SendOpcodeSafe(new Packet(ClientOpcodes.CMSG_PING)));
        this.OnReady = (packet -> SummitStatic.DUPEBOT.OnReady(packet));
        this.OnDismountedDonkey = (packet -> SummitStatic.DUPEBOT.OnDismountedDonkey());
        this.OnTestOpcode = (packet -> {});
        this.OnInvalidOpcode = (packet -> System.out.println("Recieved invalid opcode..."));
        this._client = client;
    }
}
