package org.kelsidev.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.kelsidev.khub;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class playerListener implements Listener {

    JavaPlugin plugin;
    public playerListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String result = plugin.getConfig().getString("messages.player-join").replace("{player}", player.getName());
        event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', result));
    }
    @EventHandler
    public void onPlayerDisconect(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String result = plugin.getConfig().getString("messages.player-leave").replace("{player}", player.getName());
        event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', result));
    }

}
