package me.dragon.optimzedlizardac.checks.Fly;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPosition;

import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Player;

import javax.xml.crypto.Data;

public class FlyD implements PacketListener {
    private  double buffer;
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){


            WrapperPlayClientPlayerPosition wrapper = new WrapperPlayClientPlayerPosition(event);

            boolean client = wrapper.isOnGround(),server = wrapper.getPosition().y % 0.015625 < 0.0001; //The result will be 0.015625 if it is correct;

            //ServerUtils serverUtils = new ServerUtils();ChatManager chat = new ChatManager();

            Player player = (Player) event.getPlayer();
            if (client != server ){
                DataStruc.alert("clientGround=" + client + " | serverGround= "+ server,player, CheckType.FLY, GradeEnum.D,2);

            }


           //chat.onSendDebug("airTicks= " + TickManager.airTicks);
        }
    }
}
