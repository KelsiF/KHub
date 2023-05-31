package org.kelsidev;

import org.bukkit.plugin.java.JavaPlugin;
import org.kelsidev.commands.hubCommand;
import org.kelsidev.commands.sethubCommand;

public final class khub extends JavaPlugin {

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
}
