package me.dragon.lizardacflyimpl;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import me.dragon.lizardacflyimpl.Managers.MovementManager;
import me.dragon.lizardacflyimpl.Managers.TickManager;
import me.dragon.lizardacflyimpl.impl.*;
import me.dragon.lizardacflyimpl.impl.commands.Test;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class LizardACFLyImpl extends JavaPlugin {
    public void onLoad(){
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().getSettings().checkForUpdates(false).reEncodeByDefault(false)
                .bStats(false);

        PacketEvents.getAPI().load();
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Test(),this);
        getServer().getPluginManager().registerEvents(new TickManager(),this);
        getServer().getPluginManager().registerEvents(new MovementManager(),this);
        PacketEvents.getAPI().getEventManager().registerListener(new FlyA(), PacketListenerPriority.NORMAL);
        PacketEvents.getAPI().getEventManager().registerListener(new FlyB(), PacketListenerPriority.NORMAL);
        PacketEvents.getAPI().getEventManager().registerListener(new FlyC(), PacketListenerPriority.NORMAL);
        PacketEvents.getAPI().getEventManager().registerListener(new FlyD(),PacketListenerPriority.NORMAL);
        PacketEvents.getAPI().init();




    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        PacketEvents.getAPI().terminate();
    }

    public static  void flagEvent(Player player,char type,String other){
        for (Player admins : Bukkit.getOnlinePlayers()){
            if (admins.hasPermission("lizard.Fly.alert")){
                admins.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a[&2Fly&a] &aLizard detected &2" + player.getName()
                + "&awith Fly &cType: &f" + type + "&8 | &7 "+ other));
            }
        }

    }
    public static  void flagAdventureEvent(Player player,char type,String other){
        for (Player admins : Bukkit.getOnlinePlayers()){
            if (admins.hasPermission("lizard.Fly.alert")){
                admins.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a[&2Fly&a] &aLizard detected &2" + player.getName()
                        + "&awith Fly &cType: &f" + type + "&8 | &7 "+ other));
            }
        }

    }
}
