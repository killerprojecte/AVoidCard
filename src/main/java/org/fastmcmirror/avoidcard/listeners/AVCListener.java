package org.fastmcmirror.avoidcard.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.fastmcmirror.avoidcard.AVoidCard;
import org.fastmcmirror.avoidcard.utils.AVCHolder;
import org.fastmcmirror.avoidcard.utils.AVCInventory;
import org.fastmcmirror.avoidcard.utils.Color;
import org.fastmcmirror.avoidcard.utils.PaymentCore;

public class AVCListener implements Listener {
    @EventHandler
    public void onGetDamage(EntityDamageEvent event){
        if (event.isCancelled()) return;
        if (!(event.getEntity() instanceof Player)) return;
        Player player = (Player) event.getEntity();
        if (player.getHealth()<=event.getFinalDamage()){
            event.setCancelled(true);
            player.openInventory(AVCInventory.init(event));
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if (!(event.getClickedInventory().getHolder() instanceof AVCHolder)) return;
        AVCHolder holder = (AVCHolder) event.getClickedInventory().getHolder();
        event.setCancelled(true);
        if (event.getSlot()==12){
            player.sendMessage(Color.color("&c您放弃了使用&6&l免死金牌&c!"));
            player.setHealth(0.0);
        } else if (event.getSlot()==14){
            if (PaymentCore.pay(player.getUniqueId())){
                holder.setFinish(true);
                player.closeInventory();
                player.sendMessage(Color.color("&a免死金牌已应用!"));
            } else {
                player.setHealth(0.0);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent event){
        if (!(event.getInventory().getHolder() instanceof AVCHolder)) return;
        AVCHolder holder = (AVCHolder) event.getInventory().getHolder();
        if (holder.isFinish()) return;
        if (event.getPlayer().isDead()) return;
        event.getPlayer().sendMessage(Color.color("&c您放弃了使用&6&l免死金牌&c!"));
        event.getPlayer().setHealth(0.0);
    }
}
