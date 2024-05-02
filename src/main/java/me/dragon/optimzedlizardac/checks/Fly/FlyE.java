package me.dragon.optimzedlizardac.checks.Fly;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerFlying;
import me.dragon.optimzedlizardac.LizardAC;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

public class FlyE implements PacketListener {
    double buffer;
    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        Player player = (Player) event.getPlayer();

        if (WrapperPlayClientPlayerFlying.isFlying(event.getPacketType()) && !player.isFlying()) {
            boolean invalid = true;
            double radius = 1;

            for (double x = -1; x <= 0 && invalid; x++) {
                for (double y = -1; y <= 0 && invalid; y++) {
                    for (double z = -1; z <= 0 && invalid; z++) {
                        Location loc = player.getLocation().clone().add(x, y, z);
                        Block block = loc.getBlock();

                        if (block.getType().isAir()) {
                            continue;
                        }

                        BoundingBox blockBox = player.getWorld().getBlockAt(loc).getBoundingBox();
                        BoundingBox playerBox = player.getBoundingBox();


                        // If the player's expanded hitbox overlaps with a non-air block, set invalid to false
                        if (playerBox.expand(1e-9).overlaps(blockBox)) {
                            invalid = false;
                            break; // No need to continue the loop if invalid is false
                        }
                    }
                }
            }

            if (invalid && !player.isGliding() && player.getFallDistance() == 0.0f) {
                // Notify the player about the suspected fly cheatű
                buffer++;
                if (buffer > 3){
                    DataStruc.alert("Block is not found..", player, CheckType.FLY, GradeEnum.E, 10);
                    buffer -= 0.20;
                }
                buffer -= 0.86;
            }
        }
    }
}
