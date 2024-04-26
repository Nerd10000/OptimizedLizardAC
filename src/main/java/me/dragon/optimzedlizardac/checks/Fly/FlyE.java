package me.dragon.optimzedlizardac.checks.Fly;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class FlyE implements PacketListener {
    private List<Material> blocks = new ArrayList<>();
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){
            Player player = (Player) event.getPlayer();
            double radius = 1.2;
            for (double x = -radius; x <= radius; x++){
                for (double y = -radius; y <= radius; y++){
                    for (double z = -radius; z <= radius;z++){
                        Location loc = player.getLocation().clone().add(x,y,z);
                        //boolean exempt = player.getFallDistance() > 0.1f; //I will need this for the check!

                        Block block = loc.getBlock();
                        if (block.getType() !=  Material.AIR){
                            player.sendMessage("nearbyBlock= " + block.getType());

                        }


                    }
                }
            }


        }
    }
}
