package me.dragon.optimzedlizardac;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import me.dragon.optimzedlizardac.checks.Aim.AimA;
import me.dragon.optimzedlizardac.checks.Aim.AimB;
import me.dragon.optimzedlizardac.checks.Aura.KillauraA;
import me.dragon.optimzedlizardac.checks.Aura.KillauraC;
import me.dragon.optimzedlizardac.checks.Aura.MultiAura;
import me.dragon.optimzedlizardac.checks.Fly.*;

import me.dragon.optimzedlizardac.checks.Jesus.JesusA;
import me.dragon.optimzedlizardac.checks.Nofall.NoFallA;
import me.dragon.optimzedlizardac.checks.Speed.*;
import me.dragon.optimzedlizardac.checks.autoclicker.autoclickerA;
import me.dragon.optimzedlizardac.checks.autoclicker.autoclickerB;
import me.dragon.optimzedlizardac.managers.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        /*
        Config
         */

        saveDefaultConfig();
        plugin = this;
        ConfigManager.loadConfigVersion();

        /*
        Checks
         */
        registerChecks();

        // Register listeners

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Plugin getPlugin() {
        return plugin;
    }


    public void registerChecks(){
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled AuraB");
        PacketEvents.getAPI().getEventManager().registerListener(new MultiAura(), PacketListenerPriority.NORMAL);
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled AuraC");
        PacketEvents.getAPI().getEventManager().registerListener(new KillauraC(), PacketListenerPriority.NORMAL);
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled AuraA");
        PacketEvents.getAPI().getEventManager().registerListener(new KillauraA(), PacketListenerPriority.NORMAL);
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled AutoClickerA");
        PacketEvents.getAPI().getEventManager().registerListener(new autoclickerA(), PacketListenerPriority.NORMAL);
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled AutoClickerB");
        PacketEvents.getAPI().getEventManager().registerListener(new autoclickerB(), PacketListenerPriority.NORMAL);
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled AimB");
        PacketEvents.getAPI().getEventManager().registerListener(new AimB(), PacketListenerPriority.HIGH);
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled AimB");
        PacketEvents.getAPI().getEventManager().registerListener(new AimA(), PacketListenerPriority.HIGH);
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled SpeedA");
        getServer().getPluginManager().registerEvents(new SpeedA(), this);
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled SpeedC");
        PacketEvents.getAPI().getEventManager().registerListener(new SpeedC(), PacketListenerPriority.NORMAL);
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled FlyA");
        PacketEvents.getAPI().getEventManager().registerListener(new FlyA(), PacketListenerPriority.NORMAL);
        getServer().getPluginManager().registerEvents(new FlyTickManager(), this);
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled FlyTickManager(Structure)");
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled FlyB");
        PacketEvents.getAPI().getEventManager().registerListener(new FlyB(), PacketListenerPriority.NORMAL);
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled FlyC");
        PacketEvents.getAPI().getEventManager().registerListener(new FlyC(), PacketListenerPriority.NORMAL);
        //Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled NoFallA");
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled FlyD");
        //PacketEvents.getAPI().getEventManager().registerListener(new FlyD(), PacketListenerPriority.NORMAL);
        //PacketEvents.getAPI().getEventManager().registerListener(new NoFallA(), PacketListenerPriority.NORMAL);
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled SpeedD");
        PacketEvents.getAPI().getEventManager().registerListener(new SpeedD(), PacketListenerPriority.NORMAL);
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled SpeedE");
        PacketEvents.getAPI().getEventManager().registerListener(new JesusA(),PacketListenerPriority.NORMAL);
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled JesusA(Prediction)");
        PacketEvents.getAPI().getEventManager().registerListener(new SpeedE(), PacketListenerPriority.NORMAL);
        Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled MovementController (Structure)");

        // Initialize PacketEvents
        PacketEvents.getAPI().init();
    }


}
