package org.kelsidev.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class reloadConfig implements CommandExecutor {

    JavaPlugin plugin;

    public reloadConfig(JavaPlugin plugin) {
        this.plugin = plugin;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
            if (sender.hasPermission("khub.reload")) {
                plugin.reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "KHub has reloaded!");
            }
        return false;
    }
}
