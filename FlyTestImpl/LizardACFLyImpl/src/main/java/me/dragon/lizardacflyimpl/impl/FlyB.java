package me.dragon.lizardacflyimpl.impl;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.lizardacflyimpl.LizardACFLyImpl;
import me.dragon.lizardacflyimpl.Managers.MovementManager;
import me.dragon.lizardacflyimpl.Managers.TickManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class FlyB implements PacketListener {

    private  double lastDeltaY,lastAccel;


    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION || event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION){
            double deltaY = MovementManager.deltaY;
            Player player = (Player) event.getPlayer();

            double accel = MovementManager.deltaY - lastDeltaY;

            //player.sendMessage("accel" + accel + "airTicks= "+ TickManager.airTicks);
            boolean invalid = lastAccel <= 0 && accel > 0 && deltaY > 0 && !player.isInWater() && !player.isFlying() && player.getGameMode() == GameMode.SURVIVAL;
            if (TickManager.airTicks  > 11){
                if (invalid){
                    LizardACFLyImpl.flagEvent(player,'B',"accel= "+ accel + "lastAccel= "+ lastAccel + "deltaY= "+ deltaY);
                }
            }
            lastDeltaY = deltaY;
            lastAccel = accel;
        }
    }
}
