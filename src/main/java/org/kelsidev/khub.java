package org.kelsidev;

import me.clip.placeholderapi.PlaceholderAPI;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.kelsidev.commands.hubCommand;
import org.kelsidev.commands.sethubCommand;

import java.util.Objects;



public final class khub extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("sethub").setExecutor(new sethubCommand(this));
        getCommand("hub").setExecutor(new hubCommand(this));
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static boolean PAPI;

    public static String setPlaceholders(String text, Player player) {
        if (text.contains("%player%") && player != null) {
            text = text.replace("%player%", player.getName());
        }

        if (PAPI && player != null) {
            text = PlaceholderAPI.setPlaceholders(player, text);
        }

        return text;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(getConfig().getString("messages.player-join"));
        }
    }

}
