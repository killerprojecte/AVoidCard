package org.fastmcmirror.avoidcard.utils;

import net.milkbowl.vault.economy.Economy;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.fastmcmirror.avoidcard.AVoidCard;

import java.util.UUID;

public class PaymentCore {
    public static Economy econ = null;
    public static PlayerPointsAPI points = null;

    public static boolean pay(UUID player) {
        OfflinePlayer ofp = Bukkit.getOfflinePlayer(player);
        Player p = Bukkit.getPlayer(player);
        if (AVoidCard.instance.getConfig().getString("payment.mode").equalsIgnoreCase("money")) {
            if (!AVoidCard.hasVault) {
                System.err.println("[AVoidCard-Payment][WARNING] 现在正在尝试进行Vault经济操作 但是您的服务器并没有安装Vault 经济行为已被跳过");
                return true;
            }
            if (econ.has(ofp, AVoidCard.instance.getConfig().getDouble("payment.cost"))) {
                econ.withdrawPlayer(ofp, AVoidCard.instance.getConfig().getDouble("payment.cost"));
                p.sendMessage(Color.color(AVoidCard.instance.getConfig().getString("payment.passed").replace("%cost%", String.valueOf(AVoidCard.instance.getConfig().getDouble("payment.cost")))));
                return true;
            } else {
                p.sendMessage(Color.color(AVoidCard.instance.getConfig().getString("payment.error").replace("%cost%", String.valueOf(AVoidCard.instance.getConfig().getDouble("payment.cost")))));
                return false;
            }
        } else {
            if (!AVoidCard.hasPoints) {
                System.err.println("[AVoidCard-Payment][WARNING] 现在正在尝试进行PlayerPoints经济操作 但是您的服务器并没有安装PlayerPoints 经济行为已被跳过");
                return true;
            }
            if (points.look(ofp.getUniqueId()) >= AVoidCard.instance.getConfig().getDouble("payment.cost")) {
                points.take(ofp.getUniqueId(), (int) AVoidCard.instance.getConfig().getDouble("payment.cost"));
                p.sendMessage(Color.color(AVoidCard.instance.getConfig().getString("payment.passed").replace("%cost%", String.valueOf((int) AVoidCard.instance.getConfig().getDouble("payment.cost")))));
                return true;
            } else {
                p.sendMessage(Color.color(AVoidCard.instance.getConfig().getString("payment.error").replace("%cost%", String.valueOf((int) AVoidCard.instance.getConfig().getDouble("payment.cost")))));
                return false;
            }
        }
    }
}
