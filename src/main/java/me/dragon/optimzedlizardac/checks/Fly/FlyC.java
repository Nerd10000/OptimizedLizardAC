package me.dragon.optimzedlizardac.checks.Fly;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.MovementStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Player;

public class FlyC implements PacketListener {
    /*
    FlyC gravity prediction check.
     */

    private  static  double prediction,lastDeltaY,deltaY,buffer;
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {

        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){

            Player player = (Player) event.getPlayer();
            deltaY = MovementStruc.y - MovementStruc.lastY;



            prediction = (lastDeltaY - 0.08) * 0.98F;


            double diff = Math.abs(deltaY - prediction);


            boolean isInvalid = diff > 0.00001D;

            player.sendMessage("isInvalid= "+ isInvalid + " diffY= "+ diff+ " airTicks= "+ MovementStruc.airTicks + "isOnGround= "+ player.isOnGround());

        }
    }
}
