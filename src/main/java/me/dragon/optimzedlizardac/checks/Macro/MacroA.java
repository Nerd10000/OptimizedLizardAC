package me.dragon.optimzedlizardac.checks.Macro;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPosition;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class MacroA implements PacketListener {
    private HashMap<UUID,Double> score = new HashMap<>(10);
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){
            WrapperPlayClientPlayerPosition wapper = new WrapperPlayClientPlayerPosition(event);
            Player player = (Player) event.getPlayer();
            double x = player.getLocation().getX();
            double z = player.getLocation().getZ();
            
        }
    }
}
