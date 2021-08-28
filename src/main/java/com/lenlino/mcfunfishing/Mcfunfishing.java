package com.lenlino.mcfunfishing;

import org.bukkit.Bukkit;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Mcfunfishing extends JavaPlugin {
    List<Fish> fish=new ArrayList<>();
    FileConfiguration config=getConfig();
    @Override
    public void onEnable() {
        if(!getDataFolder().exists()){
            getDataFolder().mkdir();
        }
        Bukkit.getPluginManager().registerEvents(new FishingEvent(this),this);
        config.options().copyDefaults(true);
        for(String key:config.getConfigurationSection("fishlist").getKeys(false)){
            fish.add(new Fish(config.getInt("fishlist."+key+".min"),config.getInt("fishlist."+key+".max"),config.getString("fishlist."+key+".name"), Material.valueOf(config.getString("fishlist."+key+".material")),config.getString("fishlist."+key+".rarity")));
        }
    }
    @Override
    public void onDisable() {
        saveConfig();
    }
}
