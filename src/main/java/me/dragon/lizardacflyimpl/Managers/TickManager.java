package me.dragon.lizardacflyimpl.Managers;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class TickManager implements Listener {

    public  static int airTicks,groundTIcks;
    @EventHandler
    public  void onMove(PlayerMoveEvent e){

        Player player = e.getPlayer();

        if (player.isOnGround()){
            groundTIcks++;
            airTicks = 0;
        }else {
           airTicks++;
           groundTIcks = 0;

        }

    }





}
