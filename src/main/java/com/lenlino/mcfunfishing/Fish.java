package com.lenlino.mcfunfishing;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Fish {
    int min;
    int max;
    ItemStack item;
    Random random=new Random();
    public Fish(int min, int max, String name, Material material,String rea){
        this.max=max;
        this.min=min;
        this.item=new ItemStack(material);
        ItemMeta meta=this.item.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(new ArrayList<String>(Collections.singleton(rea)));
        this.item.setItemMeta(meta);
    }
    public ItemStack getFish(){
        ItemStack item=this.item.clone();
        ItemMeta meta=item.getItemMeta();
        List<String> list=meta.getLore();
        list.add(String.valueOf(random.nextInt(this.max-this.min+1)+this.min));
        meta.setLore(list);
        item.setItemMeta(meta);
        return item;
    }
}
