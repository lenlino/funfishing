package com.lenlino.mcfunfishing;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.NPC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;

import java.util.Random;

public class FishingEvent implements Listener {
    Mcfunfishing plugin;
    Random random=new Random();
    public FishingEvent(Mcfunfishing plugin){
        this.plugin=plugin;
    }
    @EventHandler
    public void EatEvent(PlayerItemConsumeEvent e){
        if(e.getItem()!=null) {
            if(e.getItem().hasItemMeta()) {
                if (plugin.FishKouka.containsKey(e.getItem().getItemMeta().getDisplayName())) {
                    plugin.FishKouka.get(e.getItem().getItemMeta().getDisplayName()).Eatfish(e.getPlayer(),e.getItem());
                }
            }
        }
        e.getPlayer().getWorld().spawnEntity(e.getPlayer().getLocation(), EntityType.PLAYER);
    }
    @EventHandler
    public void GetFishEvent(PlayerFishEvent e){
        if(e.getState()== PlayerFishEvent.State.CAUGHT_FISH&&e.getCaught()!=null && e.getCaught() instanceof Item){
            if(random.nextInt(100)<50){
                ((Item)e.getCaught()).setItemStack(plugin.star1.get(random.nextInt(plugin.star1.size())).getFish());
            } else if (random.nextInt(100)<40) {
                ((Item)e.getCaught()).setItemStack(plugin.star2.get(random.nextInt(plugin.star2.size())).getFish());
            } else if (random.nextInt(100)<30) {
                ((Item)e.getCaught()).setItemStack(plugin.star3.get(random.nextInt(plugin.star3.size())).getFish());
            } else if (random.nextInt(100)<20) {
                ((Item)e.getCaught()).setItemStack(plugin.star4.get(random.nextInt(plugin.star4.size())).getFish());
            } else {
                ((Item)e.getCaught()).setItemStack(plugin.star5.get(random.nextInt(plugin.star5.size())).getFish());
            }
        }
    }
}
