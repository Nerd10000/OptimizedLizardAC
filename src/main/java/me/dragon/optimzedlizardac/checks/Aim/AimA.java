package me.dragon.optimzedlizardac.checks.Aim;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.optimzedlizardac.managers.MovementStruc;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class
AimA implements PacketListener {



    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION) {
            double deltaYaw = Math.abs(MovementStruc.yaw - MovementStruc.lastYaw);
            Player player = (Player) event.getPlayer();


            player.sendMessage("DeltaYaw=" + deltaYaw);



        }
    }


}

