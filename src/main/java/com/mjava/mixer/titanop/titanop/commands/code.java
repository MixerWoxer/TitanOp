package com.mjava.mixer.titanop.titanop.commands;

import com.mjava.mixer.titanop.titanop.TitanOp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class code implements CommandExecutor {

    private TitanOp pl;

    public code(TitanOp pl) {
        this.pl = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("No console, LOL :D !");
            return true;
        }
        Player p = (Player) sender;
        if (command.getName().equalsIgnoreCase("TitanOp")) {
            if (sender.isOp()) {
                if (args.length == 0) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3TitanOp &8| &bHelp Page"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &7- &f/TitanOp reload"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &7- &f/TitanOp othersecurity"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', " &7- &f/TitanOp developer"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                    return true;
                }
                if (args[0].equalsIgnoreCase("reload")) {
                    pl.reloadConfig();
                    pl.saveConfig();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&3TitanOp&7] &fPlugin reloaded."));
                    return true;
                }
                if (args[0].equalsIgnoreCase("developer")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[ &aMixer &7] &fhttps://www.youtube.com/watch?v=GfjkIeLJg-Q"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[ &aMixer &7] &fSkripter - Builder - JavaScript"));
                    return true;
                }
                if (args[0].equalsIgnoreCase("othersecurity")) {
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Titan Protection  &8| &bhttps://www.spigotmc.org/resources/mjava-titan-protection.84335/"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3Wexera &8| &bhttps://www.spigotmc.org/resources/wexera.84130/"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3AntiSyntaxBukkit &8| &bhttps://www.spigotmc.org/resources/antisyntaxbukkit.84232/"));
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', ""));
                    return true;
                }
            } else {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7[&3TitanOp&7] &fYou dont have a permission !"));
            }
        }
        return true;
    }
}
