package me.dragon.optimzedlizardac.checks.Fly;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPosition;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.MovementStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Player;

public class FlyD implements PacketListener {

    private  double buffer;

    private  boolean clientGround  = true,posGround = true;
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {

        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){

            boolean posGround = MovementStruc.y % 0.015625 == 0;
            WrapperPlayClientPlayerPosition wrapper = new WrapperPlayClientPlayerPosition(event);

            boolean clientGround = wrapper.isOnGround();

            Player player = (Player) event.getPlayer();
            if (clientGround != posGround ){

                //Tried to spoof ground
                buffer++;

                if (buffer > 5){
                    DataStruc.alert("Tried to SproofGround (Nofall) client=" + clientGround + " posGround/server= "+ posGround, player,
                            CheckType.FLY, GradeEnum.D,3);
                }
            }

            buffer-= 0.76;
        }
    }
}
