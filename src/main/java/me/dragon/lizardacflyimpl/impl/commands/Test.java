package me.dragon.lizardacflyimpl.impl.commands;

import com.github.retrooper.packetevents.PacketEvents;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class Test implements Listener {

    @EventHandler
    public void onPlayerChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        Location location = player.getLocation();

        Component message = Component.text("Your message: ", NamedTextColor.DARK_GREEN)
                .append(Component.text(event.getMessage(), NamedTextColor.GREEN)
                        .hoverEvent(HoverEvent.showText(Component.text("Your coordinates: ", NamedTextColor.DARK_GREEN)
                                .append(Component.text(location.getBlockX() + ", " + location.getBlockY() + ", " + location.getBlockZ(), NamedTextColor.GREEN)))));

        PacketEvents.getAPI().getPlayerManager().getUser(player);
    }
}

