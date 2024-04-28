package me.dragon.optimzedlizardac.managers;

import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.util.adventure.AdventureSerializer;
import me.dragon.optimzedlizardac.LizardAC;
import me.dragon.optimzedlizardac.managers.enums.CheckType;
import me.dragon.optimzedlizardac.managers.enums.GradeEnum;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;

public class DataStruc implements Listener {

    private static int vl;

    public static final void alert(String verbose, Player player, CheckType type, GradeEnum grade, int max) {
        String format = LizardAC.getPlugin().getConfig().getString("Format");
        String prefix = LizardAC.getPlugin().getConfig().getString("Prefix");
        String punishMode = LizardAC.getPlugin().getConfig().getString("punish-mode");
        String kickPunishMsg = ChatColor.translateAlternateColorCodes('&', LizardAC.getPlugin().getConfig().getString("punish-msg.KICK"));

        String message = format;

        message = message.replace("%prefix%", prefix)
                .replace("%player%", player.getName())
                .replace("%check%", type.name())
                .replace("%grade%", grade.name())
                .replace("%info%", verbose)
                .replace("%vl%", String.valueOf(vl));


        for (Player admin : Bukkit.getOnlinePlayers()){
            if (admin.hasPermission("Lizard.alerts")){


                admin.sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&',message));
            }
        }


        Bukkit.getConsoleSender().sendMessage(net.md_5.bungee.api.ChatColor.translateAlternateColorCodes('&',message));

        if (vl >= max && LizardAC.getPlugin().getConfig().getBoolean("auto-punish?")) {
            if ("KICK".equals(punishMode)) {
                Bukkit.getScheduler().runTask(LizardAC.getPlugin(), () -> {
                    player.kickPlayer(kickPunishMsg);
                    vl = 0;
                });
            }
        }
    }

    @EventHandler
    public void onLeave() {
        vl = 0;
    }
}
