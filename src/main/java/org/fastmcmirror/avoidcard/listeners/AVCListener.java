package org.fastmcmirror.avoidcard.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.fastmcmirror.avoidcard.utils.AVCHolder;
import org.fastmcmirror.avoidcard.utils.AVCInventory;

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
}
