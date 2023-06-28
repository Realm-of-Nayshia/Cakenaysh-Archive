package com.stelios.cakenaysh.Util;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import com.stelios.cakenaysh.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class AttributeManager implements Listener {

    private final Main main;

    public AttributeManager(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onStartup(ServerLoadEvent e){

        new BukkitRunnable(){
            @Override
            public void run() {

                for (Player player : main.getServer().getOnlinePlayers()){

                    //if the player has 0 health then don't regenerate health or stamina
                    if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() > 0) {

                        //randomly increase the players saturation
                        if (Math.random() > 0.96) {
                            player.sendMessage("Saturation increased");
                            player.setSaturation((player.getSaturation() + 1));
                        }

                        //player information
                        int healthRegen = main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealthRegen();
                        int maxHealth = main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth();
                        int staminaRegen = main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStaminaRegen();
                        int maxStamina = main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxStamina();

                        //regenerate stamina if not at max
                        if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina() < maxStamina) {
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addStaminaLocal(staminaRegen);
                        }

                        //regenerate health if not at max
                        if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() < maxHealth) {
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addHealthLocal(healthRegen);
                        }

                        //if over max stamina, set to max stamina
                        if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina() > maxStamina) {
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setStaminaLocal(maxStamina);
                        }

                        //if over max health, set to max health
                        if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() > maxHealth) {
                            main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setHealthLocal(maxHealth);
                        }

                        updateHearts(player);
                        displayActionBar(player);

                    }else{
                        //kill the player
                        updateHearts(player);
                    }
                }
            }
        }.runTaskTimer(main,0,40);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        //set player configurations
        Player player = e.getPlayer();
        setConfigurations(player);
        updateHearts(player);
        displayActionBar(player);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();

        //saving the players stamina and health to the database
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setHealthDatabase(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth());
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setStaminaDatabase(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina());
    }

    @EventHandler
    public void onDamaged(EntityDamageEvent e){

        //if the entity is a player
        if (e.getEntity() instanceof Player){

            Player player = (Player) e.getEntity();

            //if the player took fall damage
            if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){

                //reduce there health, update the hearts, and display the action bar
                main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addHealthLocal((int) Math.round(e.getDamage()*-2.5));
                updateHearts(player);
                displayActionBar(player);

                //negate the normal damage
                e.setDamage(0);


            //if the player took damage from hunger
            } else if (e.getCause().equals(EntityDamageEvent.DamageCause.STARVATION)){

                //reduce there health, update the hearts, and display the action bar
                main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addHealthLocal(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth()/-20);
                updateHearts(player);
                displayActionBar(player);

                //negate the normal damage
                e.setDamage(0);

            }


        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e){

    }

    @EventHandler
    public void onRespawn(PlayerPostRespawnEvent e){

        //set player configurations
        Player player = e.getPlayer();
        setConfigurations(player);

        //reset player health and stamina
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setHealthLocal(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth());
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setStaminaLocal(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxStamina());

        //display the action bar
        displayActionBar(player);
    }


    //cancel vanilla health regen
    @EventHandler
    public void onVanillaHeal(EntityRegainHealthEvent e){
        e.setCancelled(true);
    }





    //set player configurations
    public void setConfigurations(Player player){
        player.setMaxHealth(40);
    }

    //display the action bar
    public void displayActionBar(Player player){
        player.sendActionBar(Component.text(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() + " / " + main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth() + " ❤     ", TextColor.color(255,0,0))
                .append(Component.text(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina() + " / " + main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxStamina() + " ⚡", TextColor.color(210,125,45))));

    }

    //update the player's hearts
    public void updateHearts(Player player){

        //if the player has no health, kill them
        if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() <= 0) {
            player.setHealth(0);

        //if the player has super low health, set the health to 1/2 a heart
        } else if ((double) main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() / main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth() > 0.0001 && (double) main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() / main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth() < 0.02){
            player.setHealth(1);

        //else scale the hearts normally
        }else{
            player.setHealth((double) main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() / main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth() * 40);
        }
    }
}
