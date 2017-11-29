package com.enforcedmc.essentials.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.enforcedmc.essentials.Main;

public class Max_Players implements Listener
{
    private Main main;
    
    public Max_Players(final Main main) {
        this.main = main;
    }
    
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        final int onlinePlayers = Bukkit.getOnlinePlayers().length;
        if (onlinePlayers > this.main.getConfig().getInt("max_players.amount") && !p.hasPermission("enf.maxplayers.bypass")) {
            p.kickPlayer(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("max_players.message")));
        }
    }
}
