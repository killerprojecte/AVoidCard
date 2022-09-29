package org.fastmcmirror.avoidcard;

import net.milkbowl.vault.economy.Economy;
import org.black_ixx.playerpoints.PlayerPoints;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.fastmcmirror.avoidcard.listeners.AVCListener;
import org.fastmcmirror.avoidcard.utils.PaymentCore;

public final class AVoidCard extends JavaPlugin {

    public static AVoidCard instance;
    public static boolean hasVault = true;
    public static boolean hasPoints = true;

    @Override
    public void onEnable() {
        instance = this;
        setupEconomy();
        setupPoints();
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new AVCListener(),this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean setupPoints() {
        if (Bukkit.getPluginManager().isPluginEnabled("PlayerPoints")) {
            PaymentCore.points = ((PlayerPoints) Bukkit.getPluginManager().getPlugin("PlayerPoints")).getAPI();
            return true;
        } else {
            return false;
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null || !Bukkit.getPluginManager().isPluginEnabled("Vault")) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        PaymentCore.econ = rsp.getProvider();
        return PaymentCore.econ != null;
    }

}
