package com.stelios.cakenaysh.Util;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import com.stelios.cakenaysh.Main;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.mcmonkey.sentinel.SentinelTrait;

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

            //negate the normal damage
            e.setDamage(0);

            //if the player took fall damage
            if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){

                //reduce there health, update the hearts, and display the action bar
                main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addHealthLocal((float) (e.getDamage()*-3));
                updateHearts(player);
                displayActionBar(player);

            //if the player took damage from hunger
            } else if (e.getCause().equals(EntityDamageEvent.DamageCause.STARVATION)){

                //reduce their health, update the hearts, and display the action bar
                main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addHealthLocal((float) main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth() /-18);
                updateHearts(player);
                displayActionBar(player);

            //if the player took damage from drowning
            }else if (e.getCause().equals(EntityDamageEvent.DamageCause.DROWNING)){

                //reduce their health, update the hearts, and display the action bar
                main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addHealthLocal((float) main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth() /-18);
                updateHearts(player);
                displayActionBar(player);

            //if the player took damage from poison
            }else if (e.getCause().equals(EntityDamageEvent.DamageCause.POISON)){

                //get the level of poison
                int poisonLevel = ((LivingEntity) e.getEntity()).getActivePotionEffects().stream().filter(potionEffect -> potionEffect.getType().equals(PotionEffectType.POISON)).findFirst().get().getAmplifier();

                //reduce their health, update the hearts, and display the action bar
                main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addHealthLocal(-3*poisonLevel);
                updateHearts(player);
                displayActionBar(player);

            //if the player took damage from fire
            }else if (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE)){

                //reduce their health, update the hearts, and display the action bar
                main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addHealthLocal((float) main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth() /-20);
                updateHearts(player);
                displayActionBar(player);

            //if the player took damage from fire tick
            }else if (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)){


            //if the player took damage from lava
            }else if (e.getCause().equals(EntityDamageEvent.DamageCause.LAVA)){


            //if the player took damage from wither
            }else if (e.getCause().equals(EntityDamageEvent.DamageCause.WITHER)){

                //get the level of wither
                int witherLevel = ((LivingEntity) e.getEntity()).getActivePotionEffects().stream().filter(potionEffect -> potionEffect.getType().equals(PotionEffectType.WITHER)).findFirst().get().getAmplifier();

                //reduce their health, update the hearts, and display the action bar
                main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addHealthLocal(-3*witherLevel);
                updateHearts(player);
                displayActionBar(player);

            }

        }
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {

        //if the defending entity is a player or npc
        if (e.getEntity() instanceof Player) {

            //if the attacking entity is a sentinel
            try {
                if (CitizensAPI.getNPCRegistry().getNPC(e.getDamager()).hasTrait(SentinelTrait.class)) {

                    //get the attacking npc
                    NPC npc = CitizensAPI.getNPCRegistry().getNPC(e.getDamager());
                    SentinelTrait sentinel = npc.getOrAddTrait(SentinelTrait.class);

                    //calculate the damage
                    double damage = sentinel.getDamage();

                    //if the defender is a npc, reduce their health
                   // if (CitizensAPI.getNPCRegistry().getNPC(e.getEntity()).hasTrait(SentinelTrait.class)) {

                        //deal the damage to the npc
                    //    e.setDamage(damage);


                    //if the defender is a player, reduce their health, update the hearts, and display the action bar
                    //}else{
                        Player player = (Player) e.getEntity();
                        e.setDamage(0);

                        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addHealthLocal((float) -damage);
                        updateHearts(player);
                        displayActionBar(player);
                    //}

                    return;

                }

            }catch (NullPointerException ex){
                //attack was not from a sentinel
            }


            //if the attacking entity is a player
            if (e.getDamager() instanceof Player) {

                //get the attacking player
                Player player = (Player) e.getDamager();

                ////calculate the damage
                //default damage is 1
                double damage = 1.0;

                //get the held item
                ItemStack item = player.getInventory().getItemInMainHand();

                //if the player is holding an item
                if (item.getItemMeta() != null){

                    //calculate the damage if the item is a battle item
                    if (item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(main, "itemType"), PersistentDataType.STRING).equals("battleItem")) {

                        //noinspection DataFlowIssue
                        damage = item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(main, "damage"), PersistentDataType.FLOAT);

                    }
                }

                //if the defender is a npc, reduce their health
                if (CitizensAPI.getNPCRegistry().getNPC(e.getEntity()).hasTrait(SentinelTrait.class)) {

                    //deal the damage to the npc
                    e.setDamage(damage);
                    player.sendMessage("npc damaged by: " + damage);

                //if the defender is a player, reduce their health, update the hearts, and display the action bar
                }else{
                    e.setDamage(0);
                    main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addHealthLocal((float) -damage);
                    updateHearts(player);
                    displayActionBar(player);
                }


            }
        }
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
        player.sendActionBar(Component.text(Math.round(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth()) + " / " + main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth() + " ❤     ", TextColor.color(255,0,0))
                .append(Component.text(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina() + " / " + main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxStamina() + " ⚡", TextColor.color(210,125,45))));

    }

    //update the player's hearts
    public void updateHearts(Player player){

        //if the player has no health, kill them
        if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() <= 0) {
            player.setHealth(0);

        //if the player has super low health, set the health to 1/2 a heart
        } else if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() / main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth() > 0.0001 && main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() / main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth() < 0.02){
            player.setHealth(1);

        //else scale the hearts normally
        }else{
            player.setHealth(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth() / main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getMaxHealth() * 40);
        }
    }
}
