package org.kelsidev;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.kelsidev.commands.hubCommand;
import org.kelsidev.commands.sethubCommand;
import org.kelsidev.listeners.playerListener;


public final class khub extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        getCommand("sethub").setExecutor(new sethubCommand(this));
        getCommand("hub").setExecutor(new hubCommand(this));

        getServer().getPluginManager().registerEvents(new playerListener(this), this);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
