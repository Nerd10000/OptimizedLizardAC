package me.dragon.optimzedlizardac.managers;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementStruc  implements Listener {

    public static double x, y, z,lastX,lastY,lastZ;
    //public static Location from, to;
    public static Player player;

    public static int airTicks, groundTicks, lastGorundTicks, lastAirTicks;


    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        x = e.getTo().getX();
        y = e.getTo().getY();
        z = e.getTo().getZ();
        player = e.getPlayer();
      lastX  = e.getFrom().getX();
      lastY = e.getFrom().getY();
      lastZ = e.getFrom().getZ();

        if (player.isOnGround()) {
            groundTicks++;
            airTicks = 0;
        } else {
            airTicks++;
            groundTicks = 0;
        }


        lastAirTicks = airTicks;
        lastGorundTicks = groundTicks;

    }

}
