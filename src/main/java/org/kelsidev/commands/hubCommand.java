package org.kelsidev.commands;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.kelsidev.khub;

public class hubCommand implements CommandExecutor {

    private final khub plugin;

    public hubCommand(khub plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            Location coord = plugin.getConfig().getLocation("hub");

            if (coord != null) {
                player.teleport(coord);

                player.sendMessage(ChatColor.GREEN + "You have been teleported to the hub!");

            }else{
                player.sendMessage(ChatColor.RED + "The administration did not create a spawn point");
                System.out.println("Admins, pls make spawn point in your hub");
            }

        }

        return true;
    }
}
