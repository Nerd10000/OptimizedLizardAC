package me.dragon.lizardacflyimpl.Managers;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MovementManager implements Listener {

    public  static  double y,lastY,deltaY;

    @EventHandler
    public void onMove(PlayerMoveEvent e){

        y = e.getTo().getY();
        lastY = e.getFrom().getY();


        deltaY = Math.abs(y - lastY);


    }
}
