package com.stelios.cakenaysh.Listeners.Entity;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRecipeDiscoverEvent;

public class PlayerDiscoverRecipeEvent implements Listener {

    @EventHandler
    public void onPlayerDiscoverRecipe(PlayerRecipeDiscoverEvent e) {
        e.setCancelled(true);
    }

}
