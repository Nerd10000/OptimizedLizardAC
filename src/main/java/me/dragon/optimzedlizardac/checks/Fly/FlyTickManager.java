package me.dragon.optimzedlizardac.checks.Fly;

import me.dragon.optimzedlizardac.LizardAC;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class FlyTickManager implements Listener {

    private static int currentTick = 0;
    private static double currentY, lastY, diffY;
    private static boolean isSimulateFall;

    static {
        currentTick = 0;
        currentY = 0.0;
        lastY = 0.0;
        diffY = 0.0;
        isSimulateFall = false;
    }
    //Just A TickHandling Utility;
    @EventHandler
    public void onHandleTicks(PlayerMoveEvent e) {
        currentY = e.getTo().getY();
        lastY = e.getFrom().getY();
        diffY = currentY - lastY;

        isSimulateFall = diffY < -0.1f || diffY <  0.0 ;

        currentTick++;
        if (currentTick == 20) {
            currentTick = 0;
        }
    }
    public static int getCurrentTick() {
        return currentTick;
    }

    public static double getCurrentY() {
        return currentY;
    }

    public static double getLastY() {
        return lastY;
    }

    public static double getDiffY() {
        return diffY;
    }

    public static boolean isSimulateFall() {
        return isSimulateFall;
    }
}
