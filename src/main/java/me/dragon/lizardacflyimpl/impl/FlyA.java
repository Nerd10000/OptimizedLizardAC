package me.dragon.lizardacflyimpl.impl;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.lizardacflyimpl.LizardACFLyImpl;
import me.dragon.lizardacflyimpl.Managers.MovementManager;
import me.dragon.lizardacflyimpl.Managers.TickManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class FlyA implements PacketListener {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION || event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION_AND_ROTATION){


            Player player = (Player) event.getPlayer();
            if (TickManager.airTicks > 12 &&player.getFallDistance() == 0.0f &&
                    !player.isInWater() && !player.isFlying() &&player.getGameMode() == GameMode.SURVIVAL &&
            !MovementManager.isUsingElytra) {
                LizardACFLyImpl.flagEvent(player,'A',"airTicks=" + TickManager.airTicks);
            }
            //player.sendMessage("airTicks= "+ TickManager.airTicks.getOrDefault(player.getUniqueId(),0) + "groundTicks= "+ TickManager.groundTicks.getOrDefault(player.getUniqueId(),0));
        }
    }
}
