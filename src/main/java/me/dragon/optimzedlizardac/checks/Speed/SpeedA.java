package me.dragon.optimzedlizardac.checks.Speed;

import com.github.retrooper.packetevents.event.PacketListener;
import com.github.retrooper.packetevents.event.PacketReceiveEvent;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.world.Location;
import com.github.retrooper.packetevents.wrapper.play.client.WrapperPlayClientPlayerPosition;
import me.dragon.optimzedlizardac.managers.DataStruc;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;


public class SpeedA implements PacketListener {

    private  com.github.retrooper.packetevents.protocol.world.Location to;
    private com.github.retrooper.packetevents.protocol.world.Location from;
    private Vector toVelo,FromVelo;
    private  float buffer;

    @Override
    public void onPacketReceive(PacketReceiveEvent event) {
        if (event.getPacketType() == PacketType.Play.Client.PLAYER_POSITION){
            WrapperPlayClientPlayerPosition position = new WrapperPlayClientPlayerPosition(event);
            to = position.getLocation();
            Player player = (Player) event.getPlayer();
            double distance = from.getPosition().distance(to.getPosition());
            toVelo = player.getVelocity();
            final double threshold = 1.0;

            if (FromVelo != null){
                final  double VeloDiff = FromVelo.distance(toVelo);
                if (player.hasPotionEffect(PotionEffectType.SPEED) && !player.isFlying() && player.getFallDistance() == 0.0){
                    int  level = getPotionLevel(player,PotionEffectType.SPEED);

                    distance -= 0.33*level;
                } else if (player.getVelocity().getY() > 0.0 &&
                player.getPlayer().isFlying() && player.getFallDistance() != 0.0 ) {
                    distance -= 0.711f * player.getFallDistance();
                }
                if (distance > 0.432 && !player.isFlying() && player.getFallDistance() == 0.0) {
                    buffer++;
                    if (buffer > 5){
                        DataStruc.alert("distance= " + distance + " from= " + from + " to=" + to,player, CheckType.AURA, GradeEnum.A);
                    }
                }else {
                    buffer-= 0.9;
                }
                if (player.getVelocity().getY() > 0.0 && player.getFallDistance() > 0.0){
                    distance -= 0.46*player.getVelocity().getY();
                } else if (VeloDiff >= threshold) {
                    distance -= 0.4865 * VeloDiff;
                } else if (isOnIce(player)) {
                    distance /= 0.64;
                }


            }
            this.FromVelo = toVelo;
            this.from = to;
        } else if (event.getPacketType() == PacketType.Play.Server.DISCONNECT) {
            FromVelo = null;
            buffer = 0;
        }
    }

    public int getPotionLevel(Player player,PotionEffectType effectType){
        final  int id = effectType.getId();

        if (!player.hasPotionEffect(effectType)) return 0;

        return player.getActivePotionEffects().stream().filter(potion -> potion.getType().getId() == id)
                .map(PotionEffect::getAmplifier).findAny().orElse(0) +1;
    }
    private boolean isOnIce(Player user) {
        Block block = user.getLocation().subtract(0, 1, 0).getBlock();
        Material blockType = block.getType();
        return blockType == Material.ICE || blockType == Material.PACKED_ICE || blockType == Material.FROSTED_ICE || blockType == Material.BLUE_ICE;
    }

    // Method to check if the player is jumping on ice
    private boolean isJumpingOnIce(Player user) {
        return user.getPlayer().getVelocity().getY() > 0.0 && isOnIce(user);
    }
}

