package com.lenlino.mcfunfishing;

import org.bukkit.Bukkit;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

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
            fish.add(new Fish(config.getInt("fishlist."+key+".min"),config.getInt("fishlist."+key+".max"),config.getString("fishlist."+key+".name"), Material.valueOf(config.getString("fishlist."+key+".material")),config.getString("fishlist."+key+".rarity"),key,config.getString("fishlist."+key+".comment"),config.getInt("fishlist."+key+".modelData")));
        }
        addMap();
    }
    private void addMap(){
        FishKouka.put(config.getString("fishlist.tuna.name"),(p,item)->{
           p.sendMessage("§eうまかった");
        });
        FishKouka.put(config.getString("fishlist.whale.name"),(p,item)->{
            p.damage(999);
            p.sendMessage("§eでかすぎてのどに詰まらせてしまった...");
        });
        FishKouka.put(config.getString("fishlist.creeperFish.name"),(p,item)->{
           p.getWorld().createExplosion(p.getLocation(),3);
        });
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equals("mcfget")){
            if(config.getConfigurationSection("fishlist").contains(args[0])){
                if(sender instanceof Player){
                    ((Player)sender).getInventory().addItem(new Fish(config.getInt("fishlist."+args[0]+".min"),config.getInt("fishlist."+args[0]+".max"),config.getString("fishlist."+args[0]+".name"), Material.valueOf(config.getString("fishlist."+args[0]+".material")),config.getString("fishlist."+args[0]+".rarity"),args[0],config.getString("fishlist."+args[0]+".comment"),config.getInt("fishlist."+args[0]+".modelData")).getFish());
                    return true;
                }else{
                    sender.sendMessage("プレーヤーが実行してください");
                }
            }else{
                sender.sendMessage("魚が見つかりませんでした");
            }
        }
        return false;
    }
    @Override
    public void onDisable() {
        saveConfig();
    }
}
