package com.mjava.mixer.titanop.titanop.events;

import com.mjava.mixer.titanop.titanop.TitanOp;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class password implements Listener {
    public List<UUID> list = new ArrayList<>();

    public static TitanOp plugin;

    @SuppressWarnings("static-access")
    public password(TitanOp plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!plugin.getConfig().getBoolean("askPassword")) {
            return;
        }
        if (!(p.isOp())) {
            return;
        } else {
            p.setGameMode(GameMode.ADVENTURE);
            p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 99999, 255));
            this.list.add(p.getUniqueId());
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.sendMessage(plugin.getConfig().getString("messages.doLogin").replace("&", "§"));
                }
            }.runTaskLater(plugin, 40L);
        }
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String msg = e.getMessage();
        if (!plugin.getConfig().getBoolean("askPassword")) {
            return;
        }
        if (!(p.isOp())) {
            return;
        } else {
            if (this.list.contains(p.getUniqueId())) {
                if (msg.equals(plugin.getConfig().getString("password"))) {
                    e.setCancelled(true);
                    this.list.remove(p.getUniqueId());
                    p.sendMessage(plugin.getConfig().getString("messages.loginSucces").replace("&", "§"));
                    for (PotionEffect effect : p.getActivePotionEffects()) {
                        p.removePotionEffect(effect.getType());
                    }
                } else {
                    e.setCancelled(true);
                    e.getPlayer().sendMessage(plugin.getConfig().getString("messages.wrongPassword").replace("&", "§"));
                }
            }
        }
    }

    @EventHandler
    public void onUseCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if (!plugin.getConfig().getBoolean("askPassword")) {
            return;
        }
        if (!(p.isOp())) {
            return;
        } else {
            if (this.list.contains(p.getUniqueId())) {
                e.setCancelled(true);
                p.sendMessage(plugin.getConfig().getString("messages.doLogin").replace("&", "§"));
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (!plugin.getConfig().getBoolean("askPassword")) {
            return;
        }
        if (!(p.isOp())) {
            return;
        } else {
            if (this.list.contains(p.getUniqueId())) {
                e.setCancelled(true);
            } else {
                e.setCancelled(false);
            }
        }
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        if (!plugin.getConfig().getBoolean("askPassword")) {
            return;
        }
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        Player p = (Player) e.getEntity();
        if (!(p.isOp())) {
            return;
        } else {
            if (this.list.contains(p.getUniqueId())) {
                e.setCancelled(true);
            } else {
                e.setCancelled(false);
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if (!plugin.getConfig().getBoolean("askPassword")) {
            return;
        }
        Player p = e.getPlayer();
        if (!(p.isOp())) {
            return;
        } else {
            if (this.list.contains(p.getUniqueId())) {
                e.setCancelled(true);
            }
        }
    }

}
