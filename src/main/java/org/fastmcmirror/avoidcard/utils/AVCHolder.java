package org.fastmcmirror.avoidcard.utils;

import org.bukkit.Bukkit;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class AVCHolder implements InventoryHolder {
    @Override
    public Inventory getInventory() {
        return Bukkit.createInventory(null,0);
    }

    public AVCHolder(EntityDamageEvent event){
        this.event = event;
    }

    private final EntityDamageEvent event;

    public EntityDamageEvent getEvent() {
        return event;
    }
}
