package me.dragon.optimzedlizardac.checks.Fly;

import me.dragon.optimzedlizardac.LizardAC;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;


public class FlyTickManager implements Listener {

    public static    double y,lastY,deltaY;
    public  static boolean isUsingElytra;
    public static int airTicks,groundTIcks;



    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player player = e.getPlayer();

        //player.sendMessage("FlyTick= AirTIck= "+ airTicks);
        if (player.isOnGround()){
            groundTIcks++;
            airTicks = 0;
        }else {
            airTicks++;
            groundTIcks = 0;

        }

        y = e.getTo().getY();
        lastY = e.getFrom().getY();


        deltaY = Math.abs(y - lastY);

        isUsingElytra = e.getPlayer().isGliding();

    }


}
