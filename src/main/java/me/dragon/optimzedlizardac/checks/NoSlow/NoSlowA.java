package me.dragon.optimzedlizardac.checks.NoSlow;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;

public class NoSlowA implements PacketListener {
    /*
    NoSlowDown check Type: A wrote 2024.04.19 by Nerd10000;
    This will check if the player is Sprinting while blocking.
     */
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {

        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){
            
        }


    }
}
