package me.dragon.optimzedlizardac.checks.NoSlow;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.MovementStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Player;


public class NoSlowA implements PacketListener {
    /*
    NoSlowDown check Type: A wrote 2024.04.19 by Nerd10000;
    This will check if the player is Sprinting while blocking.
     */
    private double buffer;
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {

        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){

            boolean isBlocking = MovementStruc.isBlocking,isSprinting= MovementStruc.isSprinting;
            boolean predictedBlocking = isSprinting && !isBlocking;
            if (isSprinting){
                //If the prediction failed:
                if (predictedBlocking == false){
                    if (buffer++ > 1){
                        DataStruc.alert("PredictedBlocking was false (player is sprinting and the player is blocking at the same time)", (Player) event.getPlayer(),
                                CheckType.NOSLOW, GradeEnum.A,3);
                    }
                }
                buffer -= 0.20;
            }

        }


    }
}
