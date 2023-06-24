package com.stelios.cakenaysh.Listeners;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.stelios.cakenaysh.Util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import com.stelios.cakenaysh.Main;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public abstract class ItemAbility implements Listener {

    private ItemBuilder item;
    private int stamina;
    private Cache<UUID, Long> cooldown;

    public ItemAbility(ItemBuilder item, long cooldown) {
        this.item = item;
        this.cooldown = CacheBuilder.newBuilder().expireAfterWrite(cooldown, TimeUnit.SECONDS).build();
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin(Main.class));
    }

    public ItemAbility(ItemBuilder item, int stamina) {
        this.item = item;
        this.stamina = stamina;
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin(Main.class));
    }

    public ItemAbility(ItemBuilder item) {
        this.item = item;
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin(Main.class));
    }

    //getters
    public ItemStack getItem() {
        return item.getItemStack();
    }



}
