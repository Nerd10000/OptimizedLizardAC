package me.dragon.optimzedlizardac.checks.Nofall;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPosition;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.MovementStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import javax.xml.crypto.Data;

public class NoFallA implements PacketListener {

    private  double buffer;

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){
            WrapperPlayClientPlayerPosition wrapper = new WrapperPlayClientPlayerPosition(event);
            Player player = (Player) event.getPlayer();
            boolean clientGround = wrapper.isOnGround(),serverGround = (MovementStruc.y % (1 / 64)) < 0.0001;

            if(clientGround && !serverGround){
                buffer++;

                if (buffer > 1 && !player.isFlying()){

                    DataStruc.alert("Failed to GroundSproof server= "+ serverGround + "client= "+ clientGround,player,CheckType.NOFALL,GradeEnum.A,3);

                }


            }



        }
    }
}
