package com.stelios.cakenaysh.Util;

import com.stelios.cakenaysh.Main;
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

        //regenerate stamina and health
        new BukkitRunnable(){
            @Override
            public void run() {

                //regenerate stamina if not at max
                if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina() < 100){
                    main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addStaminaLocal(1);
                }


                //regenerate health if not at max
                if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() < main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth()){
                    main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addHealthLocal(1);
                }


            }
        }.runTaskTimer(main,0,20);
    }


    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){




    }

}
