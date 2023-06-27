package com.stelios.cakenaysh.Util;

import com.stelios.cakenaysh.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class AttributeManager implements Listener {

    private final Main main;

    public AttributeManager(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player player = e.getPlayer();

        new BukkitRunnable(){
            @Override
            public void run() {

                //player information
                int healthRegen = main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealthRegen();
                int maxHealth = main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth();
                int staminaRegen = main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStaminaRegen();
                int maxStamina = main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxStamina();

                //regenerate stamina if not at max
                if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina() < maxStamina){
                    main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addStaminaLocal(staminaRegen);
                }

                //regenerate health if not at max
                if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() < maxHealth){
                    main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addHealthLocal(healthRegen);
                }

                //if over max stamina, set to max stamina
                if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina() > maxStamina){
                    main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setStaminaLocal(maxStamina);
                }

                //if over max health, set to max health
                if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() > maxHealth){
                    main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setHealthLocal(maxHealth);
                }

                //display the action bar
                player.sendActionBar(Component.text(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() + " / " + maxHealth + " ❤     ", TextColor.color(255,0,0))
                        .append(Component.text(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina() + " / " + maxStamina + " ⚡", TextColor.color(210,125,45))));

            }
        }.runTaskTimer(main,0,40);
    }


    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){




    }

}
