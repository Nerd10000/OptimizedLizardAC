package me.dragon.optimzedlizardac.managers;

import net.kyori.adventure.chat.ChatType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerVelocityEvent;

public  class MovementStruc  implements Listener {

    public static double x, y, z, lastX, lastY, lastZ, yaw, pitch, lastYaw, lastPitch, predictedY;
    //public static Location from, to;
    public static Player player;
    public static Location From, To;

    public static long lastVelocityTaken;
    public static int airTicks, groundTicks, lastGorundTicks, lastAirTicks;

    public static boolean isFalling, isBlocking, isSprinting;

    @EventHandler
    public void onMove(PlayerMoveEvent e) {


        x = e.getTo().getX();
        y = e.getTo().getY();
        z = e.getTo().getZ();
        player = e.getPlayer();
        yaw = e.getTo().getYaw();
        pitch = e.getTo().getPitch();
        From = e.getFrom();
        To = e.getTo();

        lastYaw = e.getFrom().getYaw();
        lastPitch = e.getFrom().getPitch();
        lastX = e.getFrom().getX();
        lastY = e.getFrom().getY();
        lastZ = e.getFrom().getZ();


        if (player.isSprinting()) {
            isSprinting = true;
        } else {
            isSprinting = false;
        }
        if (player.isBlocking()) {
            isBlocking = true;
        } else {
            isBlocking = false;
        }

        if (player.getFallDistance() > 0.0f) {
            isFalling = true;
        } else {
            isFalling = false;
        }
    }


    @EventHandler
    public void onVelocityHandle(PlayerVelocityEvent e) {

        if (!e.getVelocity().isZero()) {
            lastVelocityTaken = System.currentTimeMillis();
        }


    }





}
