package com.enforcedmc.essentials.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.enforcedmc.essentials.Main;

public class Clear_Chat implements CommandExecutor
{
    private Main main;
    
    public Clear_Chat(final Main main) {
        this.main = main;
    }
	
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (cmd.getName().equalsIgnoreCase("clearchat")) {
        for (final Player player : Bukkit.getServer().getOnlinePlayers()) {
            if (player.hasPermission("enf.clearchat.clear")) {
                for (int i = 0; i < 51; ++i) {
                    Bukkit.broadcastMessage("");
                }
            }
                else {
                	player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("chat.chat_clear.noperm")));
                }
            }
        }
        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("chat.chat_clear.message")));
        return false;
        }
    }
