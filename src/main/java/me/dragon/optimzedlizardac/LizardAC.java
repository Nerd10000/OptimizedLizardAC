package me.dragon.optimzedlizardac;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import me.dragon.optimzedlizardac.checks.Aura.MultiAura;
import me.dragon.optimzedlizardac.checks.Speed.SpeedA;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class LizardAC extends JavaPlugin {

    private static Plugin plugin;
    public void onLoad(){
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().getSettings().bStats(false)
                .reEncodeByDefault(false)
                .checkForUpdates(false);
        PacketEvents.getAPI().load();;
    }

    @Override
    public void onEnable() {
        PacketEvents.getAPI().getEventManager().registerListener(new MultiAura(), PacketListenerPriority.NORMAL);
        PacketEvents.getAPI().getEventManager().registerListener(new SpeedA(),PacketListenerPriority.NORMAL);
        PacketEvents.getAPI().init();
        plugin = this;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return plugin;
    }
}
