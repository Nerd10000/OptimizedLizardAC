package me.dragon.optimzedlizardac.managers;

import me.dragon.optimzedlizardac.LizardAC;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DataStruc implements Listener {

    private static int vl;

    public static void alert(String verbose, Player player, CheckType type, GradeEnum grade, int max) {
        vl++;

        for (Player admin : Bukkit.getOnlinePlayers()) {
            if (admin.hasPermission("lizard.alerts")) {
                admin.sendMessage(ChatColor.translateAlternateColorCodes('&', LizardAC.getPlugin().getConfig().getString("Format")
                        .replace("%prefix%", LizardAC.getPlugin().getConfig().getString("Prefix"))
                        .replace("%player%", player.getName())
                        .replace("%check%", type.name())
                        .replace("%grade%", grade.name())
                        .replace("%info%", verbose)
                        .replace("%vl%", String.valueOf(vl))));
            }
        }
        Bukkit.getLogger().info(LizardAC.getPlugin().getConfig().getString("Format")
                .replace("%prefix%", LizardAC.getPlugin().getConfig().getString("Prefix"))
                .replace("%player%", player.getName())
                .replace("%check%", type.name())
                .replace("%grade%", grade.name())
                .replace("%info%", verbose)
                .replace("%vl%", String.valueOf(vl)));


        if (vl >= max && LizardAC.getPlugin().getConfig().getBoolean("auto-punish?")) {
            String punishMode = LizardAC.getPlugin().getConfig().getString("punish-mode");
            if ("KICK".equals(punishMode)) {
                Bukkit.getScheduler().runTask(LizardAC.getPlugin(), new Runnable() {
                    @Override
                    public void run() {
                        player.kickPlayer(ChatColor.translateAlternateColorCodes('&',LizardAC.getPlugin().getConfig().getString("punish-msg.KICK")));
                        vl = 0;
                    }
                });
            }
            // Add more if statements for other punishment modes if needed
        }
    }
    @EventHandler
    public  void onLeave(){
        vl = 0;
    }

}
