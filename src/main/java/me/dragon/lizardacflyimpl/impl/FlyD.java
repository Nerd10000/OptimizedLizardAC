package me.dragon.lizardacflyimpl.impl;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPosition;
import me.dragon.lizardacflyimpl.LizardACFLyImpl;
import me.dragon.lizardacflyimpl.Managers.ChatManager;
import org.bukkit.entity.Player;

public class FlyD implements PacketListener {
    private  double buffer;
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){


            WrapperPlayClientPlayerPosition wrapper = new WrapperPlayClientPlayerPosition(event);

            boolean client = wrapper.isOnGround(),server = wrapper.getPosition().y % 0.015625 < 0.0001; //The result will be 0.015625 if it is correct;

            //ServerUtils serverUtils = new ServerUtils();
            ChatManager chat = new ChatManager();
            Player player = (Player) event.getPlayer();
            if (client != server ){
                LizardACFLyImpl.flagEvent((Player) event.getPlayer(),'D',"client= " + client + "| server= " + server);
                chat.onSendDebug("playerName: " + player.getName() + " clientGround= " + client + " | serverGround= " + server,"[LizardDebug]");
            }


           //chat.onSendDebug("airTicks= " + TickManager.airTicks);
        }
    }
}
