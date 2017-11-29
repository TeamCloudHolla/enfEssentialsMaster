package com.enforcedmc.essentials;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import com.enforcedmc.essentials.commands.Chat_Toggle;
import com.enforcedmc.essentials.commands.Clear_Chat;
import com.enforcedmc.essentials.events.Disable_Commands;
import com.enforcedmc.essentials.events.Max_Players;
import com.enforcedmc.essentials.listener.Cooldown_Listener;

public class Main extends JavaPlugin implements Listener
{
    private String prefix;
    int time;
    
    public Main() {
    	
    this.time = this.getConfig().getInt("auto_restart.time") + 5;    
    }
    
    public void onEnable() {
        Bukkit.getServer().getLogger().info("EnforcedMC Essentials - By IIIKILLAIII, Creeperzombie3!");
        this.Commands();
        this.Listeners();
        this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();
        this.AutoRestart();
    }
    
    public void Listeners() {
        this.getServer().getPluginManager().registerEvents((Listener)new Disable_Commands(this), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new Max_Players(this), (Plugin)this);
        this.getServer().getPluginManager().registerEvents((Listener)new Cooldown_Listener(this), (Plugin)this);
        Bukkit.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)this);
    }
    
    public void Commands() {
        this.getCommand("clearchat").setExecutor((CommandExecutor)new Clear_Chat(this));
        this.getCommand("togglechat").setExecutor((CommandExecutor)new Chat_Toggle(this));
    }
    
    public void AutoRestart() {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask((Plugin)this, (Runnable)new Runnable() {
            @Override
            public void run() {
                final Main this$0 = Main.this;
                --this$0.time;
                if (Main.this.time == Main.this.getConfig().getInt("auto_restart.warning1.time") + 5) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.this.getConfig().getString("auto_restart.warning1.message")));
                }
                if (Main.this.time == Main.this.getConfig().getInt("auto_restart.warning2.time") + 5) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.this.getConfig().getString("auto_restart.warning2.message")));
                }
                if (Main.this.time == Main.this.getConfig().getInt("auto_restart.warning3.time") + 5) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.this.getConfig().getString("auto_restart.warning3.message")));
                }
                if (Main.this.time == Main.this.getConfig().getInt("auto_restart.warning4.time") + 5) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.this.getConfig().getString("auto_restart.warning4.message")));
                }
                if (Main.this.time == Main.this.getConfig().getInt("auto_restart.warning5.time") + 5) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.this.getConfig().getString("auto_restart.warning5.message")));
                }
                if (Main.this.time == 5) {
                    Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', Main.this.getConfig().getString("auto_restart.restarting")));
                    Player[] arrayOfPlayer;
                    for (int j = (arrayOfPlayer = Bukkit.getOnlinePlayers()).length, i = 0; i < j; ++i) {
                        final Player p = arrayOfPlayer[i];
                        p.kickPlayer(ChatColor.translateAlternateColorCodes('&', Main.this.getConfig().getString("auto_restart.kick")));
                    }
                }
                if (Main.this.time == 0) {
                    Bukkit.getServer().shutdown();
                }
            }
        }, 20L, 20L);
    }
}
