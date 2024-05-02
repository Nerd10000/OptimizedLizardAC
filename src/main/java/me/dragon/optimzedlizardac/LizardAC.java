package me.dragon.optimzedlizardac;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import me.dragon.optimzedlizardac.checks.Aim.AimA;
import me.dragon.optimzedlizardac.checks.Aim.AimB;
import me.dragon.optimzedlizardac.checks.Aim.AimD;
import me.dragon.optimzedlizardac.checks.Aura.KillauraA;
import me.dragon.optimzedlizardac.checks.Aura.KillauraC;
import me.dragon.optimzedlizardac.checks.Aura.MultiAura;
import me.dragon.optimzedlizardac.checks.Fly.*;

import me.dragon.optimzedlizardac.checks.Jesus.JesusA;
import me.dragon.optimzedlizardac.checks.Motion.MotionA;
import me.dragon.optimzedlizardac.checks.NoSlow.NoSlowA;
import me.dragon.optimzedlizardac.checks.NoSlow.NoSlowB;
import me.dragon.optimzedlizardac.checks.Nofall.NoFallA;
import me.dragon.optimzedlizardac.checks.Speed.*;
import me.dragon.optimzedlizardac.checks.Strafe.StrafeA;
import me.dragon.optimzedlizardac.checks.autoclicker.autoclickerA;
import me.dragon.optimzedlizardac.checks.autoclicker.autoclickerB;
import me.dragon.optimzedlizardac.managers.ConfigManager;
import me.dragon.optimzedlizardac.managers.MovementStruc;
import net.kyori.adventure.audience.Audience;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

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
        PacketEvents.getAPI().terminate();
    }

    public static Plugin getPlugin() {
        return plugin;
    }


    public void registerChecks(){
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled AuraB");

        PacketEvents.getAPI().getEventManager().registerListener(new MultiAura(), PacketListenerPriority.NORMAL);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled AuraC");
        PacketEvents.getAPI().getEventManager().registerListener(new KillauraC(), PacketListenerPriority.NORMAL);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled AuraA");
        PacketEvents.getAPI().getEventManager().registerListener(new KillauraA(), PacketListenerPriority.NORMAL);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled AimD");
        PacketEvents.getAPI().getEventManager().registerListener(new AimD(), PacketListenerPriority.NORMAL);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled AutoclickerA");;
        PacketEvents.getAPI().getEventManager().registerListener(new autoclickerA(), PacketListenerPriority.NORMAL);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled NoSlowA");
        PacketEvents.getAPI().getEventManager().registerListener(new NoSlowA(), PacketListenerPriority.NORMAL);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled NoSlowB");
        PacketEvents.getAPI().getEventManager().registerListener(new NoSlowB(), PacketListenerPriority.NORMAL);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled AutoclickerB");
        PacketEvents.getAPI().getEventManager().registerListener(new autoclickerB(), PacketListenerPriority.NORMAL);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled AimB");
        PacketEvents.getAPI().getEventManager().registerListener(new AimB(), PacketListenerPriority.HIGH);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled AimA");
        PacketEvents.getAPI().getEventManager().registerListener(new AimA(), PacketListenerPriority.HIGH);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled SpeedA");
        getServer().getPluginManager().registerEvents(new SpeedA(), this);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled SpeedC");
        PacketEvents.getAPI().getEventManager().registerListener(new SpeedC(), PacketListenerPriority.NORMAL);
        getServer().getPluginManager().registerEvents(new FlyTickManager(), this);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled FLyTickManager(Struc)");
        Bukkit.getServer().getPluginManager().registerEvents(new MovementStruc(),this);
        //Bukkit.getLogger().info(ChatColor.GREEN + "Successfully enabled NoFallA");
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled FlyE");
        PacketEvents.getAPI().getEventManager().registerListener(new FlyE(), PacketListenerPriority.NORMAL);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled FlyD");
        PacketEvents.getAPI().getEventManager().registerListener(new FlyD(), PacketListenerPriority.NORMAL);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled FlyC");
        PacketEvents.getAPI().getEventManager().registerListener(new FlyC(),PacketListenerPriority.NORMAL);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled FlyB");
        PacketEvents.getAPI().getEventManager().registerListener(new FlyB(),PacketListenerPriority.NORMAL);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled FlyA");
        PacketEvents.getAPI().getEventManager().registerListener(new FlyA(),PacketListenerPriority.NORMAL);
        //PacketEvents.getAPI().getEventManager().registerListener(new NoFallA(), PacketListenerPriority.NORMAL);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled SpeedD");
        PacketEvents.getAPI().getEventManager().registerListener(new SpeedD(), PacketListenerPriority.NORMAL);

        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled StrafeA");
        PacketEvents.getAPI().getEventManager().registerListener(new StrafeA(), PacketListenerPriority.NORMAL);
        Bukkit.getConsoleSender().sendMessage( "[LizardAC]" + ChatColor.GREEN + "Successfully enabled MotionA");
        PacketEvents.getAPI().getEventManager().registerListener(new MotionA(),PacketListenerPriority.NORMAL);

        //PacketEvents.getAPI().getEventManager().registerListener(new JesusA(),PacketListenerPriority.NORMAL);

        // Initialize PacketEvents
        PacketEvents.getAPI().init();
    }


}
