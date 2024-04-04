package me.dragon.optimzedlizardac.checks.Speed;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.MovementStruc;
import me.dragon.optimzedlizardac.managers.PlayerStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class SpeedB implements PacketListener {
    private int buffer;


    @Override
    public void onPacketReceive(PacketReceiveEvent event) {

        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION ||
                event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION) {

            final double deltaX, deltaZ;
            double  deltaXZ;
            final Player player = (Player) event.getPlayer();

            // Retrieve movement data from the instance of MovementStruc


            // Calculate deltaX, deltaZ, and deltaXZ
            deltaX = Math.abs(MovementStruc.x - MovementStruc.lastX);
            deltaZ = Math.abs(MovementStruc.z - MovementStruc.lastZ);
            deltaXZ = Math.hypot(deltaX, deltaZ) * 100;
            if (deltaXZ > 28. && MovementStruc.y == MovementStruc.lastY) {
                int level = SpeedA.getPotionLevel(player, PotionEffectType.SPEED);
                deltaXZ -= 6 * level;

            } else if (deltaXZ > 28. && player.getVelocity().getY() > 0 && player.getFallDistance() == 0) {
                deltaXZ /= 10.301* player.getVelocity().getY();
            } else if (deltaXZ > 28. && player.getFallDistance() > 0.0) {
                deltaXZ -= 14.502*player.getFallDistance();
            }

            if (deltaXZ > 28. && !player.isFlying()) {
                //buffer++;
                DataStruc.alert("&7(*Experimental)&8 deltaXZ= " + deltaXZ + "buffer= " + buffer, player, CheckType.SPEED, GradeEnum.B);
                if (buffer >= 2) {

                }

                //buffer -= 1;
                player.sendMessage(String.valueOf(deltaXZ));
            }
        }
    }
}
