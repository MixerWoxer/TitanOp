package com.mjava.mixer.titanop.titanop;

import com.mjava.mixer.titanop.titanop.commands.code;
import com.mjava.mixer.titanop.titanop.events.checkjoin;
import com.mjava.mixer.titanop.titanop.events.password;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class TitanOp extends JavaPlugin implements Listener {

    public List<String> listOperators = new ArrayList();

    public File file;
    public FileConfiguration config;

    public PluginManager pm = Bukkit.getServer().getPluginManager();

    public void File() {
        config = getConfig();
        file = new File(getDataFolder(), "config.yml");
        saveDefaultConfig();
    }

    @Override
    public void onEnable() {
        File();
        getCommand("TitanOp").setExecutor(new code(this));
        pm.registerEvents(new checkjoin(this), this);
        pm.registerEvents(new password(this), this);
        pm.registerEvents(this, this);
    }

    @EventHandler
    public void move(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable() {
            @Override
            public void run() {
                if (!(getConfig().getStringList("operators").contains(p.getName()))) {
                    if (p.isOp()) {
                        p.setOp(false);
                    }
                }
            }
        }, 20, 40);
    }

    @EventHandler
    public void join(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable() {
            @Override
            public void run() {
                if (!(getConfig().getStringList("operators").contains(p.getName()))) {
                    if (p.isOp()) {
                        p.setOp(false);
                    }
                }
            }
        }, 20, 40);
    }

    @EventHandler
    public void chat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable() {
            @Override
            public void run() {
                if (!(getConfig().getStringList("operators").contains(p.getName()))) {
                    if (p.isOp()) {
                        p.setOp(false);
                    }
                }
            }
        }, 20, 40);
    }

    @EventHandler
    public void command(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunnable() {
            @Override
            public void run() {
                if (!(getConfig().getStringList("operators").contains(p.getName()))) {
                    if (p.isOp()) {
                        p.setOp(false);
                    }
                }
            }
        }, 20, 40);
    }
}
