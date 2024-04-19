package me.dragon.optimzedlizardac.checks.NoSlow;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.optimzedlizardac.managers.MovementStruc;
import org.bukkit.entity.Player;

public class NoSlowB implements PacketListener {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){
            double deltaX = Math.abs(MovementStruc.x - MovementStruc.lastX);
            double deltaZ = Math.abs(MovementStruc.z - MovementStruc.lastZ);

            double deltaXZ = Math.hypot(deltaX,deltaZ);

            Player player = (Player) event.getPlayer();

            if (MovementStruc.isBlocking){
                player.sendMessage("deltaXZ= "+ deltaXZ);
            }
        }
    }
}
