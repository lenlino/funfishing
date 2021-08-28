package com.lenlino.mcfunfishing;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;

public class FishingEvent implements Listener {
    Mcfunfishing plugin;
    public FishingEvent(Mcfunfishing plugin){
        this.plugin=plugin;
    }
    @EventHandler
    public void EatEvent(PlayerItemConsumeEvent e){
        if(e.getItem()!=null) {
            if(e.getItem().hasItemMeta()) {
                if (plugin.FishKouka.containsKey(e.getItem().getItemMeta().getDisplayName())) {
                    plugin.FishKouka.get(e.getItem().getItemMeta().getDisplayName()).Eatfish(e.getPlayer());
                }
            }
        }
    }
}
