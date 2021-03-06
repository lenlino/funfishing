package com.lenlino.mcfunfishing;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Fish {
    String rarity;
    int min;
    int max;
    ItemStack item;
    String key;
    Random random=new Random();
    public Fish(int min, int max, String name, Material material,String rea,String key,String setumei,int number){
        this.max=max;
        this.min=min;
        this.item=new ItemStack(material);
        this.rarity=rea;
        ItemMeta meta=this.item.getItemMeta();
        meta.setDisplayName(name);
        List<String> list=new ArrayList<>();
        list.add(ChatColor.WHITE +rea);
        list.add(ChatColor.WHITE+setumei);
        meta.setLore(list);
        meta.setCustomModelData(number);
        this.item.setItemMeta(meta);
        this.key=key;
    }
    public ItemStack getFish(){
        ItemStack item=this.item.clone();
        ItemMeta meta=item.getItemMeta();
        List<String> list=meta.getLore();
        list.add(ChatColor.WHITE+String.valueOf(random.nextInt(this.max-this.min+1)+this.min)+"cm");
        meta.setLore(list);
        item.setItemMeta(meta);
        return item;
    }
}
