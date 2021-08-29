package com.lenlino.mcfunfishing;

import org.bukkit.entity.Item;
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
    }
    @EventHandler
    public void GetFishEvent(PlayerFishEvent e){
        if(e.getState()== PlayerFishEvent.State.CAUGHT_FISH){
            if(e.getCaught()!=null){
                if(e.getCaught() instanceof Item&&random.nextBoolean()){
                    ((Item)e.getCaught()).setItemStack(plugin.fish.get(random.nextInt(plugin.fish.size())).getFish());
                }
            }
        }
    }
}
