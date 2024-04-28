package me.dragon.optimzedlizardac.checks.Speed;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.MovementStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Player;

public class SpeedE implements PacketListener {

    private  int buffer;

    public boolean isInWater(Player player){

        boolean isIn = player.getLocation().getBlock().isLiquid();

        return isIn;
    }

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){
            Player player = (Player) event.getPlayer();
            if (isInWater(player)){

                double deltaX = MovementStruc.x - MovementStruc.lastX;
                double deltaZ = MovementStruc.z - MovementStruc.lastZ;

                double deltaXZ = Math.hypot(deltaX,deltaZ);
                if (!player.isSwimming() && deltaXZ > 0.08){
                   buffer++;
                   if (buffer > 9){
                       DataStruc.alert("deltaXZ= "+ deltaXZ + "buffer= " + buffer,player,
                               CheckType.SPEED, GradeEnum.E,10);
                   }
                }else {
                    buffer--;
                }

            }
        }
    }
}
