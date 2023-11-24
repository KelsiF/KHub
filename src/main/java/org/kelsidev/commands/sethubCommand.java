package org.kelsidev.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kelsidev.khub;


public class sethubCommand implements CommandExecutor {

    private final khub plugin;

    public sethubCommand(khub plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        if (sender instanceof Player) {
            if (sender.hasPermission("khub.sethub")) {

                Location coord = player.getLocation();

                plugin.getConfig().set("hub", coord);
                plugin.saveConfig();

                player.sendMessage(ChatColor.GREEN + "Spawn point created!");
            }
            if (!sender.hasPermission("khub.sethub")) {
                player.sendMessage(ChatColor.RED + "You don't have permission to do this!");
            }
        }

        return true;
    }
}
