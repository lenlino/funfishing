package com.lenlino.mcfunfishing;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Mcfunfishing extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new FishingEvent(this),this);
    }

    @Override
    public void onDisable() {
        saveConfig();
    }
}
