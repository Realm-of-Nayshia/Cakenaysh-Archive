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

                //if over max stamina, set to max stamina
                if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina() > main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxStamina()){
                    main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setStaminaLocal(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxStamina());
                }

                //if over max health, set to max health
                if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() > main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth()){
                    main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setHealthLocal(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth());
                }

                //regenerate stamina if not at max
                if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina() < main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxStamina()){
                    main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addStaminaLocal(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStaminaRegen());

                    //if over max stamina, set to max stamina
                    if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina() > main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxStamina()){
                        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setStaminaLocal(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxStamina());
                    }
                }

                //regenerate health if not at max
                if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() < main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth()){
                    main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addHealthLocal(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealthRegen());

                    //if over max health, set to max health
                    if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() > main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth()){
                        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setHealthLocal(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth());
                    }
                }

                //display the action bar
                int health = main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth();
                int maxHealth = main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth();
                int stamina = main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina();

                player.sendActionBar(Component.text(health + " / " + maxHealth + " ❤     ", TextColor.color(255,0,0))
                        .append(Component.text(stamina + " / 100 ⚡", TextColor.color(210,125,45))));

            }
        }.runTaskTimer(main,0,40);
    }


    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){




    }

}
