package com.enforcedmc.essentials.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.enforcedmc.essentials.Main;
import com.enforcedmc.essentials.commands.Chat_Toggle;

public class Chat_Listener implements Listener
{
    private Main main;
    
    public Chat_Listener(final Main main) {
        this.main = main;
    }
    
    @EventHandler
    public void onChat(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        if (!Chat_Toggle.chat && !p.hasPermission("enf.chattoggle.bypass")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("chat.chat_toggle.chat_off")));
            e.setCancelled(true);
        }
    }
}
