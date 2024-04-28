package me.dragon.optimzedlizardac.checks.Speed;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.optimzedlizardac.managers.MovementStruc;
import org.bukkit.entity.Player;

public class SpeedC implements PacketListener {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){
            Player player = (Player) event.getPlayer();
            double velocityY =  player.getVelocity().getY();
            double VelocityX = player.getVelocity().getX();
            double VelocityZ = player.getVelocity().getZ();

            double velocityXZ = Math.abs(Math.hypot(VelocityX,VelocityZ));

            double dinstance = Math.hypot(velocityXZ,velocityY) * 0.20f;

            //player.sendMessage("distance= " + dinstance);
        }
    }
}
