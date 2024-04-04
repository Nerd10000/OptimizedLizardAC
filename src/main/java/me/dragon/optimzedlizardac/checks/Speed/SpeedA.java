package me.dragon.optimzedlizardac.checks.Speed;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.packettype.PacketTypeCommon;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.MovementStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import javax.xml.crypto.Data;
import java.util.Locale;

public class SpeedA implements Listener {

    private Vector pastVelocity;
    private int buffer;
    @EventHandler
    public void onRun(PlayerMoveEvent event) {
        Player user = event.getPlayer();
        double distance = event.getFrom().distance(event.getTo());
        Vector currentVelocity = user.getPlayer().getVelocity();
        final double knockbackThreshold = 1.0;

        // Check if pastVelocity is not null before using it
        if (pastVelocity != null) {
            final double velocityChange = currentVelocity.distance(pastVelocity);

            // Adjust distance based on player conditions
            if (user.getPlayer().hasPotionEffect(PotionEffectType.SPEED) && !user.getPlayer().isFlying() && user.getPlayer().getFallDistance() == 0.0) {
                int speedLevel = getPotionLevel(user.getPlayer(), PotionEffectType.SPEED);
                distance -= 0.33 * speedLevel; // Adjust distance based on speed potion level
            } else if (user.getPlayer().getVelocity().getY() > 0.0 && !user.getPlayer().isFlying() && user.getPlayer().getFallDistance() != 0.0) {
                distance -= 0.711 * user.getPlayer().getFallDistance(); // Adjust distance if player is jumping
            }

            // Check if distance exceeds threshold
            if (distance > 0.423 && !user.getPlayer().isFlying() && user.getPlayer().getFallDistance() == 0.0 && user.getPlayer().getVehicle() == null && !isJumpingOnIce(user)) {
                // Increment buffer and check for violations
                buffer++;
                if (buffer > 5) {
                    // Log violation
                    DataStruc.alert("distance= " + distance, event.getPlayer(), CheckType.SPEED, GradeEnum.A);
                }
            } else {
                // Reset buffer if conditions are not met
                buffer = 0;
            }

            if (user.getPlayer().getVelocity().getY() > 0.0 && user.getPlayer().getFallDistance() > 0.0) {
                distance -= 0.46 * user.getPlayer().getVelocity().getY();
            } else if (velocityChange >= knockbackThreshold) {
                distance -= 0.4865 * velocityChange;
            }

            // Adjust distance if the player is on ice
            if (isOnIce(user)) {
                distance /= 0.64; // Adjusted factor
            }
        }

        pastVelocity = currentVelocity;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        // Reset buffer and pastVelocity when player quits
        buffer = 0;
        pastVelocity = null;
    }

    // Method to check if the player is on ice
    private boolean isOnIce(Player user) {
        Block block = user.getPlayer().getLocation().subtract(0, 1, 0).getBlock();
        Material blockType = block.getType();
        return blockType == Material.ICE || blockType == Material.PACKED_ICE || blockType == Material.FROSTED_ICE || blockType == Material.BLUE_ICE;
    }

    // Method to check if the player is jumping on ice
    private boolean isJumpingOnIce(Player user) {
        return user.getPlayer().getVelocity().getY() > 0.0 && isOnIce(user);
    }
    public static int getPotionLevel(Player player, PotionEffectType effect) {
        final int effectId = effect.getId();

        if (!player.hasPotionEffect(effect)) return 0;

        return player.getActivePotionEffects().stream().filter(potionEffect -> potionEffect.getType().getId() == effectId).map(PotionEffect::getAmplifier).findAny().orElse(0) + 1;
    }
}
