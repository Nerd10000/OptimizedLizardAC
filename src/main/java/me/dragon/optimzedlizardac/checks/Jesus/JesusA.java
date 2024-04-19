package me.dragon.optimzedlizardac.checks.Jesus;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;

public class JesusA implements PacketListener {

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION) {
            Player player = (Player) event.getPlayer();
            if (isUsingJesusHack(player) && !isNearBoats(player)) {
                DataStruc.alert("NearSolidBlocks= null", player, CheckType.JESUS, GradeEnum.A, 10);
            }

        }
    }

    public static boolean isUsingJesusHack(Player player) {
        // Get the block the player is standing on
        Block blockBelowFeet = player.getLocation().subtract(0, 1, 0).getBlock();

        if (player.getFallDistance() != 0.0){
            return false;
        }

        // Check if the block below player's feet is water
        if (blockBelowFeet.getType() == Material.WATER || blockBelowFeet.getType() == Material.WATER) {
            // Check if the player is not swimming and is not affected by the water
            if (!player.isSwimming() && !player.getLocation().getBlock().isLiquid()) {
                // Check nearby blocks for solid blocks (excluding water and boats)
                for (int xOffset = -1; xOffset <= 1; xOffset++) {
                    for (int zOffset = -1; zOffset <= 1; zOffset++) {
                        Block nearbyBlock = player.getLocation().add(xOffset, -1, zOffset).getBlock();
                        Material nearbyBlockType = nearbyBlock.getType();
                        if ((xOffset != 0 || zOffset != 0) && nearbyBlockType.isSolid() && nearbyBlockType != Material.WATER && nearbyBlockType != Material.LEGACY_WATER) {
                            // Exclude boats from being flagged as solid blocks
                            if (!(nearbyBlock.getState() instanceof Vehicle && ((Vehicle) nearbyBlock.getState()).getPassengers().contains(player))) {
                                // If there's a solid block nearby (excluding boats), player is likely standing on it
                                return false;
                            }
                        }
                    }
                }
                // If no solid blocks nearby, player may be using Jesus hack
                return true;
            }
        }
        // Player is not standing in water or is affected by it
        return false;
    }
    public boolean isNearBoats(Player player){
        return player.getNearbyEntities(3,0,3).contains(EntityType.BOAT);
    }
}



