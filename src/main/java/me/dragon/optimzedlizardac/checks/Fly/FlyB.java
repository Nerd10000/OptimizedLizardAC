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

public class FlyB implements PacketListener {

    private  double lastDeltaY,lastAccel;


    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION || event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION){

            double deltaY = FlyTickManager.deltaY;
            Player player = (Player) event.getPlayer();

            double accel = FlyTickManager.deltaY - lastDeltaY;

            //player.sendMessage("accel" + accel + "airTicks= "+ TickManager.airTicks);
            boolean invalid = lastAccel <= 0 && accel > 0 && deltaY > 0 && !player.isInWater() &&
                    !player.isFlying() && player.getGameMode() == GameMode.SURVIVAL && !FlyTickManager.isUsingElytra && player.getFallDistance() == 0.0f;
            if (FlyTickManager.airTicks > 11 ){
                if (invalid){
                    DataStruc.alert("lastAccel =" +lastAccel + " | accel=" + accel + " | airTicks= "+ FlyTickManager.airTicks,
                            player, CheckType.FLY,GradeEnum.B,5);
                }
            }
            lastDeltaY = deltaY;
            lastAccel = accel;
        }
    }
}
