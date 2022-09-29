package org.fastmcmirror.avoidcard.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.fastmcmirror.avoidcard.AVoidCard;

import java.util.ArrayList;
import java.util.List;

public class AVCInventory {
    public static Inventory init(EntityDamageEvent event){
        Inventory inventory = Bukkit.createInventory(new AVCHolder(event),3*9,Color.color("&8[&6&l免死金牌&8]"));
        ItemStack i1 = new ItemStack(Material.BARRIER);
        ItemMeta m1 = i1.getItemMeta();
        m1.setDisplayName(Color.color("&c&l关闭"));
        List<String> l1 = new ArrayList<>();
        l1.add(Color.color("&8- 点击关闭界面"));
        m1.setLore(l1);
        i1.setItemMeta(m1);
        ItemStack i2 = new ItemStack(Material.EMERALD_BLOCK);
        ItemMeta m2 = i2.getItemMeta();
        m2.setDisplayName(Color.color("&a&l使用金牌"));
        List<String> l2 = new ArrayList<>();
        l2.add(Color.color("&8- 点击扣除&b" + AVoidCard.instance.getConfig().getDouble("payment.cost") + "金币 &8并激活免死金牌"));
        m2.setLore(l2);
        i2.setItemMeta(m2);
        inventory.setItem(12,i1);
        inventory.setItem(14,i2);
        return inventory;
    }
}
