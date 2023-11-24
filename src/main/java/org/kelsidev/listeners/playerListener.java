package org.kelsidev.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Objects;


public class playerListener implements Listener {

    JavaPlugin plugin;

    public playerListener(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (plugin.getConfig().getBoolean("features.join_leave-messages")) {
            Player player = event.getPlayer();
            String result = Objects.requireNonNull(plugin.getConfig().getString("messages.player-join")).replace("{player}", player.getName());
            event.setJoinMessage(ChatColor.translateAlternateColorCodes('&', result));
            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                try {
                    online.playSound(player.getLocation(), Sound.valueOf(plugin.getConfig().getString("sounds.player-join.sound")), plugin.getConfig().getInt("sounds.player-join.volume"), plugin.getConfig().getInt("sounds.player-join.pitch"));
                } catch (Exception e) {
                    return;
                }
            }
        }
        if (plugin.getConfig().getBoolean("features.join-title")) {
            Player player = event.getPlayer();
            player.sendTitle(plugin.getConfig().getString("messages.player-join-title"), plugin.getConfig().getString("messages.player-join-subtitle"), 1, 1, 1);
        }
    }

    @EventHandler
    public void onPlayerDisconect(PlayerQuitEvent event) {
        if (plugin.getConfig().getBoolean("features.join_leave-messages")) {
            Player player = event.getPlayer();
            String result = Objects.requireNonNull(plugin.getConfig().getString("messages.player-leave")).replace("{player}", player.getName());
            event.setQuitMessage(ChatColor.translateAlternateColorCodes('&', result));
            for (Player online : Bukkit.getOnlinePlayers()) {
                try {
                    online.playSound(player.getLocation(), Sound.valueOf(plugin.getConfig().getString("sounds.player-join.sound")), plugin.getConfig().getInt("sounds.player-join.volume"), plugin.getConfig().getInt("sounds.player-join.pitch"));
                } catch (Exception e) {
                    return;
                }

            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (!plugin.getConfig().getBoolean("events.block-break")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.break-block"))));
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (!plugin.getConfig().getBoolean("events.block-place")) {
            event.setCancelled(true);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.place-block"))));
        }
    }

    @EventHandler
    public void onBlockInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("khub.prevents_events")) {
            if (!plugin.getConfig().getBoolean("events.block-interact"))
                if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    if (player.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                        event.setCancelled(true);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.interact-block"))));
                    }
                }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Player player = (Player) event.getEntity();
        if (!player.hasPermission("khub.prevents_events")) {
            if (!plugin.getConfig().getBoolean("events.fall-damage")) {
                if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    event.setCancelled(true);
                }
            }
        }

    }

    @EventHandler
    public void onPvp(EntityDamageByEntityEvent event) {
        Player player = (Player) event.getEntity();
        if (!player.hasPermission("khub.prevents_events")) {
            if (!plugin.getConfig().getBoolean("events.pvp")) {
                if (event.getDamager() instanceof Player) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onItemPickup(InventoryPickupItemEvent event) {
        if (!plugin.getConfig().getBoolean("events.item-pickup")) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("khub.prevents_events")) {
            if (!plugin.getConfig().getBoolean("events.item-drop")) {
                event.setCancelled(true);
            }
        }
    }
}
