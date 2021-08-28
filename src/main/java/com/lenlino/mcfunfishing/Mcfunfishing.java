package com.lenlino.mcfunfishing;

import org.bukkit.Bukkit;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Mcfunfishing extends JavaPlugin {
    List<Fish> fish=new ArrayList<>();
    FileConfiguration config=getConfig();
    Map<String,kouka> FishKouka=new HashMap<>();
    @Override
    public void onEnable() {
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }
        Bukkit.getPluginManager().registerEvents(new FishingEvent(this),this);
        config.options().copyDefaults(true);
        for(String key:config.getConfigurationSection("fishlist").getKeys(false)){
            fish.add(new Fish(config.getInt("fishlist."+key+".min"),config.getInt("fishlist."+key+".max"),config.getString("fishlist."+key+".name"), Material.valueOf(config.getString("fishlist."+key+".material")),config.getString("fishlist."+key+".rarity"),key,config.getString("fishlist."+key+".comment")));
        }
        addMap();
    }
    private void addMap(){
        FishKouka.put(config.getString("fishlist.test.name"),p->{
           p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,200,3));
        });
    }
    @Override
    public void onDisable() {
        saveConfig();
    }
}
