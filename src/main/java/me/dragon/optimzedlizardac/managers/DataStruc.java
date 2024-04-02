package me.dragon.optimzedlizardac.managers;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import me.dragon.optimzedlizardac.LizardAC;
import me.dragon.optimzedlizardac.checks.Aura.MultiAura;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.time.Duration;
import java.util.Date;

public class DataStruc {


    private static CheckType type;

    private static GradeEnum grade;

    private  static  int vl;



    public static void alert(String verbose,Player player,CheckType type,GradeEnum grade) {
        vl++;
        for (Player admin : Bukkit.getOnlinePlayers()){

            if (admin.hasPermission("lizard.alerts")){
                admin.sendMessage(ChatColor.translateAlternateColorCodes('&',LizardAC.getPlugin().getConfig().getString("Format")
                        .replace("%prefix%",LizardAC.getPlugin().getConfig().getString("Prefix"))
                        .replace("%player%",player.getName())
                        .replace("%check%",type.name())
                        .replace("%grade%",grade.name())
                        .replace("%info%",verbose)
                        .replace("%vl%",String.valueOf(vl))));
                if (vl > 20 && LizardAC.getPlugin().getConfig().getBoolean("auto-punish?") ){
                       switch (LizardAC.getPlugin().getConfig().getString("punish-mode")){
                           case "KICK":
                               player.kickPlayer(LizardAC.getPlugin().getConfig().getString("punish-msg.KICK"));
                                break;
                       }
                }
            }
        }

    }


}
