package me.dragon.optimzedlizardac.managers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RestoreManager {

    public void restoreServer(Player player, Entity target){



        if (target instanceof  Player){
            Bukkit.getLogger().info(" Restoring target(s) inventory begins...Finished!");
            double heart = ((Player) target).getHealth();
            Location pos = target.getLocation();
            ItemStack[] inventory = ((Player) target).getInventory().getContents().clone();

            if (target.isDead() ||((Player) target).getHealth() <= 3){
                ((Player) target).setHealth(heart);
                target.teleport(pos);
                ((Player) target).getInventory().setContents(inventory);

            }
        }
    }
    public  void restoreXrayerInventory(Player xrayer,ItemStack[] lastInv){
        xrayer.getInventory().setContents(lastInv);
        Bukkit.getLogger().info(" Restoring x-rayer inventory...please wait!");
    }
}
