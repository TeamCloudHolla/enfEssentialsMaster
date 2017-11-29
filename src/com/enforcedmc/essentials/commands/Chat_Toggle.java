package com.enforcedmc.essentials.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.enforcedmc.essentials.Main;

public class Chat_Toggle implements CommandExecutor
{
    public static boolean chat;
    
    static {
        Chat_Toggle.chat = true;
    }
    
    private Main main;
    
    public Chat_Toggle(final Main main) {
        this.main = main;
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String lable, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (cmd.getName().equalsIgnoreCase("togglechat")) {
                if (p.hasPermission("enf.chattoggle")) {
                    if (Chat_Toggle.chat) {
                        Chat_Toggle.chat = false;
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("chat.chat_toggle.message_off")).replace("%player%", p.getName()));
                    }
                    else if (!Chat_Toggle.chat) {
                        Chat_Toggle.chat = true;
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("chat.chat_toggle.message_on")).replace("%player%", p.getName()));
                    }
                }
                else {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("chat.chat_toggle.noperm")));
                }
            }
        }
        else {
            sender.sendMessage("§c§lYou must be a player to run this command!");
        }
        return true;
    }
}
