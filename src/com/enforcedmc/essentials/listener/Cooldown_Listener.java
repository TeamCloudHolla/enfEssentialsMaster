package com.enforcedmc.essentials.listener;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.enforcedmc.essentials.Main;

public class Cooldown_Listener extends JavaPlugin implements Listener
{
    private Main main;
    
    public Cooldown_Listener(final Main main) {
        this.main = main;
    }
	
    public static ArrayList<String> commandsl;
    public static HashMap<String, Integer> commands;
    public static HashMap<String, Long> player;
    
    static {
        Cooldown_Listener.commandsl = new ArrayList<String>();
        Cooldown_Listener.commands = new HashMap<String, Integer>();
        Cooldown_Listener.player = new HashMap<String, Long>();
        
        
    }

    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPreprocess(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        final String uuid = p.getUniqueId().toString();
        if (e.getMessage().startsWith("/")) {
            final String cmd = e.getMessage().replaceFirst("/", "");
            boolean ready = false;
            if (!p.hasPermission("enf.cooldown.bypass") && !p.isOp()) {
                for (final String command : Cooldown_Listener.commandsl) {
                    if (!ready && cmd.startsWith(command)) {
                        ready = true;
                        final Long time = System.currentTimeMillis();
                        if (Cooldown_Listener.player.containsKey(String.valueOf(uuid) + ";" + command)) {
                            final double cooldown = Cooldown_Listener.commands.get(command) * 1000;
                            if (Cooldown_Listener.player.get(String.valueOf(uuid) + ";" + command) + cooldown <= time) {
                                Cooldown_Listener.player.remove(String.valueOf(uuid) + ";" + command);
                                Cooldown_Listener.player.put(String.valueOf(uuid) + ";" + command, (long)time);
                            }
                            else {
                                e.setCancelled(true);
                                double timeleft = (Cooldown_Listener.player.get(String.valueOf(uuid) + ";" + command) + cooldown - time) / 1000.0;
                                timeleft = Math.round(timeleft * 100.0) / 100.0;
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', this.main.getConfig().getString("Cooldowns.message")).replace("%cooldown%", String.valueOf(timeleft)));
                            }
                        }
                        else {
                            Cooldown_Listener.player.put(String.valueOf(uuid) + ";" + command, (long)time);
                        }
                    }
                }
            }
        }
    }
}
