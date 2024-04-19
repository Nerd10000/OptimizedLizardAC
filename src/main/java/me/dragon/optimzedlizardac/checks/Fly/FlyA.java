package me.dragon.optimzedlizardac.checks.Fly;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.MovementStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import me.dragon.optimzedlizardac.managers.enums.lastPacket;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.xml.crypto.Data;

public class FlyA implements PacketListener {

    private float buffer;

    private lastPacket lastSent;
    private  double lastDeltaY;
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){

            Player player = (Player) event.getPlayer();
            double deltaY = MovementStruc.y - MovementStruc.lastY;
            //player.sendMessage("airTicks= " + MovementStruc.airTicks);

            if(MovementStruc.airTicks > 11 && (deltaY - lastDeltaY) > 0.01 && player.getFallDistance() == 0.0f && !player.isFlying() && lastSent != lastPacket.BLOCK_PLACE && !player.isSwimming()){
                DataStruc.alert("airTicks= "+ MovementStruc.airTicks+ "delta diff= "+ (deltaY - lastDeltaY), (Player) event.getPlayer(),CheckType.FLY,GradeEnum.A,5);
            }
            lastDeltaY = deltaY;
    }else if ( event.getPacketType() == PacketType.Play.Client.PLAYER_BLOCK_PLACEMENT){
            lastSent = lastPacket.BLOCK_PLACE;

        }
    }



}
