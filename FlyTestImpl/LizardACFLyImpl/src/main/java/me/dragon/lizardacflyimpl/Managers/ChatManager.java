package me.dragon.lizardacflyimpl.Managers;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.protocol.player.User;
import com.github.retrooper.packetevents.util.StringUtil;
import io.github.retrooper.packetevents.util.SpigotReflectionUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Arrays;

import static org.bukkit.Bukkit.getPlayer;
import static org.bukkit.Bukkit.getServer;

public class ChatManager {
    public ChatManager(){

    }

    public void onSendDebug(String info,String prefix){
        StringBuilder builder = new StringBuilder(info);



        getServer().getConsoleSender().sendMessage( prefix + " " + ChatColor.AQUA + builder.toString());
    }
    /*
    public void AdventureLibAlert(Player player,char type,String other){
        for (Player admins : Bukkit.getOnlinePlayers()){
            if (admins.hasPermission("Lizard.alerts")){
                User user = PacketEvents.getAPI().getPlayerManager().getUser(Bukkit.getPlayer(admins.getUniqueId()));
                Component raw = (Component) Component.text()
                        .content("&a[&2Fly&a] &aLizard detected &2" + player.getName() + "&aType: &a"  + type)
                        .hoverEvent(HoverEvent.showText(Component.text()
                                .content("Player Name: " + player.getName() + Component.newline().content() +
                                        "")));
                //Component sendTO = GsonComponentSerializer.gson().deserialize(raw);
            }
        }
    }



     */
}
