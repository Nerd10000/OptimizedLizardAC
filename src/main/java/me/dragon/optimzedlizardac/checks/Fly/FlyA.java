package me.dragon.optimzedlizardac.checks.Fly;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;

import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import javax.xml.crypto.Data;

public class FlyA implements PacketListener {

    private double buffer;

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION || event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION){


            Player player = (Player) event.getPlayer();
            //player.sendMessage("AirTicks= " + FlyTickManager.airTicks);
            if (FlyTickManager.airTicks > 12 &&player.getFallDistance() == 0.0f &&
                    !player.isInWater() && !player.isFlying() &&player.getGameMode() == GameMode.SURVIVAL &&
            !FlyTickManager.isUsingElytra) {
               buffer++;
                if (buffer > 3){
                    DataStruc.alert("airTicks= "+ FlyTickManager.airTicks,player, CheckType.FLY, GradeEnum.A,10);
                    buffer -= 0.20;
                }
                buffer -= 0.97;
            }
            //player.sendMessage("airTicks= "+ TickManager.airTicks.getOrDefault(player.getUniqueId(),0) + "groundTicks= "+ TickManager.groundTicks.getOrDefault(player.getUniqueId(),0));
        }
    }
}
