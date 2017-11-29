package com.enforcedmc.essentials.events;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.enforcedmc.essentials.Main;

public class Disable_Commands implements Listener
{
    private Main main;
    
    public Disable_Commands(final Main main) {
        this.main = main;
    }
    
    @EventHandler
    public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        final String message = e.getMessage();
        final List<String> list = (List<String>)this.main.getConfig().getStringList("disable_commands.commands");
        for (final String s : list) {
            if (message.equalsIgnoreCase(s) && !p.hasPermission("enf.disabledcmds.bypass")) {
                e.setCancelled(true);
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("disable_commands.message")).replace("%command%", message.toLowerCase()));
            }
        }
    }
}
