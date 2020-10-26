package com.mjava.mixer.titanop.titanop.events;

import com.mjava.mixer.titanop.titanop.TitanOp;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class checkjoin implements Listener {

    public static TitanOp pl;

    @SuppressWarnings("static-access")
    public checkjoin(TitanOp pl) {
        this.pl = pl;
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) {
        ConfigurationSection Nick = pl.getConfig().getConfigurationSection("checkIp.name-ipAdress");
        String Joiner = e.getPlayer().getAddress().getHostName();
        Player p = e.getPlayer();
        if (p.isOp()) {
            if (pl.getConfig().getConfigurationSection("checkIp.name-ipAdress").contains(e.getPlayer().getName())) {
                if (Joiner.equals(Nick.get(e.getPlayer().getName()))) {
                    pl.getLogger().warning(" Player " + e.getPlayer().getName() + " join a normal ip adress");
                }
            } else {
                p.kickPlayer(ChatColor.translateAlternateColorCodes('&', pl.getConfig().getString("messages.wrongIp-Kick")));
                pl.getLogger().warning(" Player " + p + " join the wrong ip.");

            }

        }
    }
}
