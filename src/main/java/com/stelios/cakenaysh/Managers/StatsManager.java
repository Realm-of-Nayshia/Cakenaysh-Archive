package com.stelios.cakenaysh.Managers;

import com.destroystokyo.paper.event.player.PlayerArmorChangeEvent;
import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import com.stelios.cakenaysh.Events.ProficiencyChangedEvent;
import com.stelios.cakenaysh.Events.SpeedChangedEvent;
import com.stelios.cakenaysh.Main;
import com.stelios.cakenaysh.Util.CustomPlayer;
import com.stelios.cakenaysh.Npc.Traits.NpcStats;
import io.papermc.paper.event.player.PlayerInventorySlotChangeEvent;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.mcmonkey.sentinel.SentinelTrait;

import java.util.logging.Level;

public class StatsManager implements Listener {

    private final Main main;

    public StatsManager(Main main) {
        this.main = main;
    }

    @EventHandler
    public void onStart(ServerLoadEvent e){

        //saving player stats every 30 minutes
        new BukkitRunnable(){
            @Override
            public void run() {
                for (Player player : main.getServer().getOnlinePlayers()){
                    updateDatabaseStats(player);

                    //add the stats back to the player's armor
                    for (ItemStack item : player.getInventory().getArmorContents()){

                        if (item != null){

                            //remove the stats from the armor
                            addPlayerStats(player, item, "armor");
                        }
                    }

                    //add the stats back to the main hand
                    addPlayerStats(player, player.getInventory().getItemInMainHand(), "weapon");
                }
            }
        }.runTaskTimerAsynchronously(main, 0, 20*1800);
    }


    @EventHandler
    public void onStartup(ServerLoadEvent e){

        new BukkitRunnable(){
            @Override
            public void run() {

                for (Player player : main.getServer().getOnlinePlayers()){

                    //get the custom player
                    CustomPlayer customPlayer = main.getPlayerManager().getCustomPlayer(player.getUniqueId());

                    //if the player has 0 health then don't regenerate health or stamina
                    if (customPlayer.getHealth() > 0) {

                        //randomly increase the players saturation (only if saturation is below 3)
                        if (player.getSaturation() < 3) {
                            if (Math.random() > 0.96) {
                                player.setSaturation((player.getSaturation() + 1));
                            }
                        }

                        //regenerate stamina if not at max
                        if (customPlayer.getStamina() < customPlayer.getMaxStamina()) {
                            customPlayer.addStamina(customPlayer.getStaminaRegen());
                        }

                        //regenerate health if not at max
                        if (customPlayer.getHealth() < customPlayer.getMaxHealth()) {
                            customPlayer.addHealth(customPlayer.getHealthRegen());
                        }

                        //if over max stamina, set to max stamina
                        if (customPlayer.getStamina() > customPlayer.getMaxStamina()) {
                            customPlayer.setStamina(customPlayer.getMaxStamina());
                        }

                        //if over max health, set to max health
                        if (customPlayer.getHealth() > customPlayer.getMaxHealth()) {
                            customPlayer.setHealth(customPlayer.getMaxHealth());
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

        //wait 1 tick to load the player's stats
        new BukkitRunnable(){
            @Override
            public void run() {
                displayActionBar(player);
                updateHearts(player);
            }
        }.runTaskLater(main, 1);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        updateDatabaseStats(e.getPlayer());
    }

    @EventHandler
    public void onDamaged(EntityDamageEvent e){

        //if the entity is a player and not a npc
        if (e.getEntity() instanceof Player && !CitizensAPI.getNPCRegistry().isNPC(e.getEntity())) {

            Player player = (Player) e.getEntity();
            CustomPlayer customPlayer = main.getPlayerManager().getCustomPlayer(player.getUniqueId());

            //if the player took fall damage
            if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                customPlayer.addHealth((int) (e.getDamage() * -5));

            //if the player took damage from hunger
            }else if (e.getCause().equals(EntityDamageEvent.DamageCause.STARVATION)){
                customPlayer.addHealth(customPlayer.getMaxHealth() /-18);

            //if the player took damage from drowning
            }else if (e.getCause().equals(EntityDamageEvent.DamageCause.DROWNING)){
                customPlayer.addHealth(customPlayer.getMaxHealth() /-18);

            //if the player took damage from poison
            }else if (e.getCause().equals(EntityDamageEvent.DamageCause.POISON)){

                //get the level of poison and reduce the health
                int poisonLevel = ((LivingEntity) e.getEntity()).getActivePotionEffects().stream().filter(potionEffect -> potionEffect.getType().equals(PotionEffectType.POISON)).findFirst().get().getAmplifier();
                customPlayer.addHealth(-3*poisonLevel);

            //if the player took damage from fire
            }else if (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE)){
                customPlayer.addHealth((int) ((customPlayer.getMaxHealth() / -20) * (1 - customPlayer.getInfernalDefense())));

            //if the player took damage from fire tick
            }else if (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)){
                customPlayer.addHealth((int) ((customPlayer.getMaxHealth() / -40) * (1 - customPlayer.getInfernalDefense())));

            //if the player took damage from lava
            }else if (e.getCause().equals(EntityDamageEvent.DamageCause.LAVA)){
                customPlayer.addHealth((int) (( customPlayer.getMaxHealth() / -12) * (1 - customPlayer.getInfernalDefense())));

            //if the player took damage from wither
            }else if (e.getCause().equals(EntityDamageEvent.DamageCause.WITHER)){

                //get the level of wither and reduce the health
                int witherLevel = ((LivingEntity) e.getEntity()).getActivePotionEffects().stream().filter(potionEffect -> potionEffect.getType().equals(PotionEffectType.WITHER)).findFirst().get().getAmplifier();
                customPlayer.addHealth(-3*witherLevel);

            //if the player took damage from the void
            }else if (e.getCause().equals(EntityDamageEvent.DamageCause.VOID)){
                    customPlayer.addHealth(customPlayer.getMaxHealth() /-10);

            }

            //negate the normal damage
            e.setDamage(0);

            //update the hearts and display the action bar
            updateHearts(player);
            displayActionBar(player);
        }
    }


    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {

        //if the defending entity is a npc
        if (CitizensAPI.getNPCRegistry().isNPC(e.getEntity())) {

            //if the defending entity is a sentinel with the NpcStats trait
            if (CitizensAPI.getNPCRegistry().getNPC(e.getEntity()).hasTrait(SentinelTrait.class) && CitizensAPI.getNPCRegistry().getNPC(e.getEntity()).hasTrait(NpcStats.class)) {

                //get the npc defender and their stats
                NPC defender = CitizensAPI.getNPCRegistry().getNPC(e.getEntity());
                NpcStats defenderNpcStats = defender.getOrAddTrait(NpcStats.class);

                float defenderThorns = defenderNpcStats.getThorns();
                float defenderStrength = defenderNpcStats.getStrength();
                float defenderDefense = defenderNpcStats.getDefense();
                float defenderInfernalDefense = defenderNpcStats.getInfernalDefense();
                float defenderUndeadDefense = defenderNpcStats.getUndeadDefense();
                float defenderAquaticDefense = defenderNpcStats.getAquaticDefense();
                float defenderAerialDefense = defenderNpcStats.getAerialDefense();
                float defenderMeleeDefense = defenderNpcStats.getMeleeDefense();
                float defenderRangedDefense = defenderNpcStats.getRangedDefense();
                float defenderMagicDefense = defenderNpcStats.getMagicDefense();

                //if the attacking entity is a NPC
                if (CitizensAPI.getNPCRegistry().isNPC(e.getDamager())) {

                    //if the attacking entity is a npc with the NpcStats trait
                    if (CitizensAPI.getNPCRegistry().getNPC(e.getDamager()).hasTrait(SentinelTrait.class) && CitizensAPI.getNPCRegistry().getNPC(e.getDamager()).hasTrait(NpcStats.class)) {

                        //get the npc attacker and their stats
                        NPC attacker = CitizensAPI.getNPCRegistry().getNPC(e.getDamager());
                        SentinelTrait attackerSentinelTrait = attacker.getOrAddTrait(SentinelTrait.class);
                        NpcStats attackerNpcStats = attacker.getOrAddTrait(NpcStats.class);

                        float attackerBaseDamage = (float) attackerSentinelTrait.getDamage();
                        float attackerCritChance = attackerNpcStats.getCritChance();
                        float attackerCritDamage = attackerNpcStats.getCritDamage();
                        float attackerStrength = attackerNpcStats.getStrength();
                        float attackerDefense = attackerNpcStats.getDefense();
                        float attackerInfernalDamage = attackerNpcStats.getInfernalDamage();
                        float attackerUndeadDamage = attackerNpcStats.getUndeadDamage();
                        float attackerAquaticDamage = attackerNpcStats.getAquaticDamage();
                        float attackerAerialDamage = attackerNpcStats.getAerialDamage();
                        float attackerMeleeDamage = attackerNpcStats.getMeleeDamage();
                        float attackerRangedDamage = attackerNpcStats.getRangedDamage();
                        float attackerMagicDamage = attackerNpcStats.getMagicDamage();

                        ////calculate the damage
                        //crit chance calculations
                        if (e.isCritical()){
                            attackerCritChance += 50;
                        }
                        if (Math.random() * 100 < attackerCritChance){
                            attackerCritChance = 100;
                        }else{
                            attackerCritChance = 0;
                        }

                        //type damage calculations
                        float typeDamage = attackerBaseDamage + attackerInfernalDamage * (1-defenderInfernalDefense/100) + attackerUndeadDamage * (1-defenderUndeadDefense/100) +
                                attackerAquaticDamage * (1-defenderAquaticDefense/100) + attackerAerialDamage * (1-defenderAerialDefense/100) +
                                attackerMeleeDamage * (1-defenderMeleeDefense/100) + attackerRangedDamage * (1-defenderRangedDefense/100) +
                                attackerMagicDamage * (1-defenderMagicDefense/100);

                        //crit damage and strength calculations
                        boolean isCritical = false;
                        if (attackerCritChance == 100){
                            typeDamage = typeDamage * (1+attackerStrength/100) * (1+attackerCritDamage/100);
                            isCritical = true;
                        }else{
                            typeDamage = typeDamage * (1+attackerStrength/100);
                        }

                        //defense calculation
                        float finalDefenderDamage = typeDamage * (1-(defenderDefense/(defenderDefense+100)));

                        //thorns calculation (10 thorns is 1% of the incoming damage, before the attacker's defense is applied)
                        float noDefAttackerDamage = defenderStrength * (defenderThorns/10);
                        float finalAttackerDamage = noDefAttackerDamage * (1-((attackerDefense+1)/(attackerDefense+101)));

                        //if the damage is negative set it to zero
                        if (finalDefenderDamage < 0){
                            finalDefenderDamage = 0;
                        }

                        //display the damage
                        displayDamage(defender.getEntity(), (int) finalDefenderDamage, isCritical, defender.getStoredLocation());

                        //deal the damage
                        e.setDamage(finalDefenderDamage);

                        //THORNS CALCULATION FOR NPC



                    } else {
                        //error: attacking entity either isn't a sentinel or doesn't have NpcStats trait
                        main.getLogger().log(Level.WARNING, "ERROR: attacking entity either isn't a sentinel or doesn't have the NpcStats trait");
                    }

                //if the attacking entity is a player
                } else if (e.getDamager() instanceof Player){

                    //get the player attacker and their stats
                    Player player = (Player) e.getDamager();
                    CustomPlayer attackerPlayer = main.getPlayerManager().getCustomPlayer(player.getUniqueId());

                    float attackerBaseDamage = attackerPlayer.getDamage();
                    float attackerCritChance = attackerPlayer.getCritChance();
                    float attackerCritDamage = attackerPlayer.getCritDamage();
                    float attackerStrength = attackerPlayer.getStrength();
                    float attackerDefense = attackerPlayer.getDefense();
                    float attackerInfernalDamage = attackerPlayer.getInfernalDamage();
                    float attackerUndeadDamage = attackerPlayer.getUndeadDamage();
                    float attackerAquaticDamage = attackerPlayer.getAquaticDamage();
                    float attackerAerialDamage = attackerPlayer.getAerialDamage();
                    float attackerMeleeDamage = attackerPlayer.getMeleeDamage();
                    float attackerRangedDamage = attackerPlayer.getRangedDamage();
                    float attackerMagicDamage = attackerPlayer.getMagicDamage();

                    ////calculate the damage
                    //crit chance calculations
                    if (e.isCritical()){
                        attackerCritChance += 50;
                    }
                    if (Math.random() * 100 < attackerCritChance){
                        attackerCritChance = 100;
                    }else{
                        attackerCritChance = 0;
                    }

                    //type damage calculations
                    float typeDamage = attackerBaseDamage + attackerInfernalDamage * (1-defenderInfernalDefense/100) + attackerUndeadDamage * (1-defenderUndeadDefense/100) +
                            attackerAquaticDamage * (1-defenderAquaticDefense/100) + attackerAerialDamage * (1-defenderAerialDefense/100) +
                            attackerMeleeDamage * (1-defenderMeleeDefense/100) + attackerRangedDamage * (1-defenderRangedDefense/100) +
                            attackerMagicDamage * (1-defenderMagicDefense/100);

                    //crit damage and strength calculations
                    boolean isCritical = false;
                    if (attackerCritChance == 100){
                        typeDamage = typeDamage * (1+attackerStrength/100) * (1+attackerCritDamage/100);
                        isCritical = true;
                    }else{
                        typeDamage = typeDamage * (1+attackerStrength/100);
                    }

                    //cooldown debuff calculations
                    float cooldownDebuff;
                    if (player.getAttackCooldown() < 0.8){
                        cooldownDebuff = player.getAttackCooldown()/1.5f;
                    }else{
                        cooldownDebuff = player.getAttackCooldown();
                    }

                    //defense calculation
                    float finalDefenderDamage = cooldownDebuff * typeDamage * (1-(defenderDefense/(defenderDefense+100)));

                    //thorns calculation (10 thorns is 1% of the incoming damage, before the attacker's defense is applied)
                    float noDefAttackerDamage = defenderStrength * (defenderThorns/10);
                    float finalAttackerDamage = noDefAttackerDamage * (1-((attackerDefense+1)/(attackerDefense+101)));

                    //if the damage is negative set it to zero
                    if (finalDefenderDamage <= 0){
                        finalDefenderDamage = 0;

                    //if the damage is greater than 0, add the damage to be tracked on the npc trait
                    } else {
                        defenderNpcStats.addPlayerDamage(player.getUniqueId(), finalDefenderDamage);
                    }

                    //display the damage
                    displayDamage(player, (int) finalDefenderDamage, isCritical, defender.getStoredLocation());

                    //deal the damage
                    e.setDamage(finalDefenderDamage);
                    attackerPlayer.setHealth((int) (attackerPlayer.getHealth() - finalAttackerDamage));

                    //update the player's health bar
                    displayActionBar(player);
                    updateHearts(player);

                }

            }
            //else do nothing: defending entity either isn't a sentinel or doesn't have NpcStats trait



        //if the defending entity is a player
        } else if (e.getEntity() instanceof Player) {

            //set the default damage to zero
            e.setDamage(0.01);

            //get the player defender and their stats
            Player playerDefend = (Player) e.getEntity();
            CustomPlayer defenderPlayer = main.getPlayerManager().getCustomPlayer(playerDefend.getUniqueId());

            float defenderThorns = defenderPlayer.getThorns();
            float defenderStrength = defenderPlayer.getStrength();
            float defenderDefense = defenderPlayer.getDefense();
            float defenderInfernalDefense = defenderPlayer.getInfernalDefense();
            float defenderUndeadDefense = defenderPlayer.getUndeadDefense();
            float defenderAquaticDefense = defenderPlayer.getAquaticDefense();
            float defenderAerialDefense = defenderPlayer.getAerialDefense();
            float defenderMeleeDefense = defenderPlayer.getMeleeDefense();
            float defenderRangedDefense = defenderPlayer.getRangedDefense();
            float defenderMagicDefense = defenderPlayer.getMagicDefense();

            //if the attacking entity is a NPC
            if (CitizensAPI.getNPCRegistry().isNPC(e.getDamager())) {

                //if the attacking entity is a npc with the NpcStats trait
                if (CitizensAPI.getNPCRegistry().getNPC(e.getDamager()).hasTrait(SentinelTrait.class) && CitizensAPI.getNPCRegistry().getNPC(e.getDamager()).hasTrait(NpcStats.class)) {

                    //get the npc attacker and their stats
                    NPC attacker = CitizensAPI.getNPCRegistry().getNPC(e.getDamager());
                    SentinelTrait attackerSentinelTrait = attacker.getOrAddTrait(SentinelTrait.class);
                    NpcStats attackerNpcStats = attacker.getOrAddTrait(NpcStats.class);

                    float attackerBaseDamage = (float) attackerSentinelTrait.getDamage();
                    float attackerCritChance = attackerNpcStats.getCritChance();
                    float attackerCritDamage = attackerNpcStats.getCritDamage();
                    float attackerStrength = attackerNpcStats.getStrength();
                    float attackerDefense = attackerNpcStats.getDefense();
                    float attackerInfernalDamage = attackerNpcStats.getInfernalDamage();
                    float attackerUndeadDamage = attackerNpcStats.getUndeadDamage();
                    float attackerAquaticDamage = attackerNpcStats.getAquaticDamage();
                    float attackerAerialDamage = attackerNpcStats.getAerialDamage();
                    float attackerMeleeDamage = attackerNpcStats.getMeleeDamage();
                    float attackerRangedDamage = attackerNpcStats.getRangedDamage();
                    float attackerMagicDamage = attackerNpcStats.getMagicDamage();

                    ////calculate the damage
                    //crit chance calculations
                    if (e.isCritical()){
                        attackerCritChance += 50;
                    }
                    if (Math.random() * 100 < attackerCritChance){
                        attackerCritChance = 100;
                    }else{
                        attackerCritChance = 0;
                    }

                    //type damage calculations
                    float typeDamage = attackerBaseDamage + attackerInfernalDamage * (1-defenderInfernalDefense/100) + attackerUndeadDamage * (1-defenderUndeadDefense/100) +
                            attackerAquaticDamage * (1-defenderAquaticDefense/100) + attackerAerialDamage * (1-defenderAerialDefense/100) +
                            attackerMeleeDamage * (1-defenderMeleeDefense/100) + attackerRangedDamage * (1-defenderRangedDefense/100) +
                            attackerMagicDamage * (1-defenderMagicDefense/100);

                    //crit damage and strength calculations
                    boolean isCritical = false;
                    if (attackerCritChance == 100){
                        typeDamage = typeDamage * (1+attackerStrength/100) * (1+attackerCritDamage/100);
                        isCritical = true;
                    }else{
                        typeDamage = typeDamage * (1+attackerStrength/100);
                    }

                    //defense calculation
                    float finalDefenderDamage = typeDamage * (1-(defenderDefense/(defenderDefense+100)));

                    //thorns calculation (10 thorns is 1% of the incoming damage, before the attacker's defense is applied)
                    float noDefAttackerDamage = defenderStrength * (defenderThorns/10);
                    float finalAttackerDamage = noDefAttackerDamage * (1-((attackerDefense+1)/(attackerDefense+101)));

                    //if the damage is negative set it to zero
                    if (finalDefenderDamage < 0){
                        finalDefenderDamage = 0;
                    }

                    //display the damage
                    displayDamage(playerDefend, (int) finalDefenderDamage, isCritical, playerDefend.getLocation());

                    //deal the damage
                    defenderPlayer.setHealth((int) (defenderPlayer.getHealth() - finalDefenderDamage));

                    //update the player's health bar
                    displayActionBar(playerDefend);
                    updateHearts(playerDefend);

                    //THORNS CALCULATION FOR NPC



                } else {
                    //error: attacking entity either isn't a sentinel or doesn't have NpcStats trait
                    main.getLogger().log(Level.WARNING, "ERROR: attacking entity either isn't a sentinel or doesn't have the NpcStats trait");
                }

            //if the attacking entity is a player
            } else if (e.getDamager() instanceof Player){

                //get the player attacker and their stats
                Player playerAttack = (Player) e.getDamager();
                CustomPlayer attackerPlayer = main.getPlayerManager().getCustomPlayer(playerAttack.getUniqueId());

                float attackerBaseDamage = attackerPlayer.getDamage();
                float attackerCritChance = attackerPlayer.getCritChance();
                float attackerCritDamage = attackerPlayer.getCritDamage();
                float attackerStrength = attackerPlayer.getStrength();
                float attackerDefense = attackerPlayer.getDefense();
                float attackerInfernalDamage = attackerPlayer.getInfernalDamage();
                float attackerUndeadDamage = attackerPlayer.getUndeadDamage();
                float attackerAquaticDamage = attackerPlayer.getAquaticDamage();
                float attackerAerialDamage = attackerPlayer.getAerialDamage();
                float attackerMeleeDamage = attackerPlayer.getMeleeDamage();
                float attackerRangedDamage = attackerPlayer.getRangedDamage();
                float attackerMagicDamage = attackerPlayer.getMagicDamage();


                ////calculate the damage
                //crit chance calculations
                if (e.isCritical()){
                    attackerCritChance += 50;
                }
                if (Math.random() * 100 < attackerCritChance){
                    attackerCritChance = 100;
                }else{
                    attackerCritChance = 0;
                }

                //type damage calculations
                float typeDamage = attackerBaseDamage + attackerInfernalDamage * (1-defenderInfernalDefense/100) + attackerUndeadDamage * (1-defenderUndeadDefense/100) +
                        attackerAquaticDamage * (1-defenderAquaticDefense/100) + attackerAerialDamage * (1-defenderAerialDefense/100) +
                        attackerMeleeDamage * (1-defenderMeleeDefense/100) + attackerRangedDamage * (1-defenderRangedDefense/100) +
                        attackerMagicDamage * (1-defenderMagicDefense/100);

                //crit damage and strength calculations
                boolean isCritical = false;
                if (attackerCritChance == 100){
                    typeDamage = typeDamage * (1+attackerStrength/100) * (1+attackerCritDamage/100);
                    isCritical = true;
                }else{
                    typeDamage = typeDamage * (1+attackerStrength/100);
                }

                //cooldown debuff calculations
                float cooldownDebuff;
                if (playerAttack.getAttackCooldown() < 0.8){
                    cooldownDebuff = playerAttack.getAttackCooldown()/1.5f;
                }else{
                    cooldownDebuff = playerAttack.getAttackCooldown();
                }

                //defense calculation
                float finalDefenderDamage = cooldownDebuff * typeDamage * (1-(defenderDefense/(defenderDefense+100)));

                //thorns calculation (10 thorns is 1% of the incoming damage, before the attacker's defense is applied)
                float noDefAttackerDamage = defenderStrength * (defenderThorns/10);
                float finalAttackerDamage = noDefAttackerDamage * (1-((attackerDefense+1)/(attackerDefense+101)));

                //if the damage is negative set it to zero
                if (finalDefenderDamage < 0){
                    finalDefenderDamage = 0;
                }

                //deal the damage
                defenderPlayer.setHealth((int) (defenderPlayer.getHealth() - finalDefenderDamage));
                playerDefend.sendMessage("You took " + finalDefenderDamage + " damage from " + playerAttack.getName());
                playerAttack.sendMessage("You dealt " + finalAttackerDamage + " damage to " + playerDefend.getName());

                //deal the thorns damage
                attackerPlayer.setHealth((int) (attackerPlayer.getHealth() - finalAttackerDamage));

                //display the damage
                displayDamage(playerDefend, (int) finalDefenderDamage, isCritical, playerDefend.getLocation());

                //update the player's health bar
                displayActionBar(playerAttack);
                updateHearts(playerAttack);
                displayActionBar(playerDefend);
                updateHearts(playerDefend);

            }
        }
    }


    @EventHandler
    public void onSpeedChanged(SpeedChangedEvent e){
        e.getPlayer().setWalkSpeed(0.2F * ((e.getSpeed() + 100) / 100));
    }

    @EventHandler
    public void onProficiencyChanged(ProficiencyChangedEvent e){
        Player player = e.getPlayer();

        //remove the player's stats before the proficiency is changed
        removePlayerStats(player, player.getInventory().getItemInMainHand(), "weapon");
        removePlayerStats(player, player.getInventory().getHelmet(), "armor");
        removePlayerStats(player, player.getInventory().getChestplate(), "armor");
        removePlayerStats(player, player.getInventory().getLeggings(), "armor");
        removePlayerStats(player, player.getInventory().getBoots(), "armor");

        //wait 1 tick to allow the proficiency to be changed
        new BukkitRunnable(){
            @Override
            public void run() {

                //recalculate the player's stats after the proficiency has been changed
                addPlayerStats(player, player.getInventory().getItemInMainHand(), "weapon");
                addPlayerStats(player, player.getInventory().getHelmet(), "armor");
                addPlayerStats(player, player.getInventory().getChestplate(), "armor");
                addPlayerStats(player, player.getInventory().getLeggings(), "armor");
                addPlayerStats(player, player.getInventory().getBoots(), "armor");

            }
        }.runTaskLater(main, 1);
    }

    @EventHandler
    public void onRespawn(PlayerPostRespawnEvent e){

        //set player configurations
        Player player = e.getPlayer();
        CustomPlayer customPlayer = main.getPlayerManager().getCustomPlayer(player.getUniqueId());
        setConfigurations(player);

        //reset player health and stamina
        customPlayer.setHealth(customPlayer.getMaxHealth());
        customPlayer.setStamina(customPlayer.getMaxStamina());

        //display the action bar
        displayActionBar(player);
    }

    //cancel vanilla health regen
    @EventHandler
    public void onVanillaHeal(EntityRegainHealthEvent e){
        e.setCancelled(true);
    }

    //disallow the player to equip items that they don't have the required stats for
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){

        //if the inventory isn't null
        if (e.getClickedInventory() != null) {

            //if the inventory is that of a players
            if (e.getClickedInventory().getType() == InventoryType.PLAYER) {

                //get the player and the item
                Player player = (Player) e.getWhoClicked();
                ItemStack item = e.getCursor();

                //if the player doesn't have the required stats for the item
                if (!meetsItemRequirements(player, item, false)) {

                    //if the item is being moved into the armor slots
                    if (e.getSlotType() == InventoryType.SlotType.ARMOR) {

                        //cancel the event
                        player.sendMessage(Component.text("You do not meet the requirements to equip this item.", TextColor.color(255, 0, 0)));
                        e.setCancelled(true);
                    }
                }
            }
        }

    }

    //remove the stats from the main hand item when any inventory is opened
    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent e){

        //get the player and their main hand item
        Player player = (Player) e.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        //remove the stats from the main hand item
        removePlayerStats(player, item, "weapon");
    }


    //update player stats when an item is moved into the main hand
    @EventHandler
    public void onInventorySlotChange(PlayerInventorySlotChangeEvent e){

        Player player = e.getPlayer();

        //if the item is being moved into or out of the main hand
        if (e.getSlot() == player.getInventory().getHeldItemSlot()){

            //add the stats of the new item
            addPlayerStats(player, e.getNewItemStack(), "weapon");

            //remove the stats of the old item
            removePlayerStats(player, e.getOldItemStack(), "weapon");

        //if the item is being moved into or out of the offhand
        } else if (e.getSlot() == 40){

            //remove the stats of the old item
            removePlayerStats(player, e.getOldItemStack(), "accessory");

            //if the player doesn't meet the requirements for the new item
            if (!meetsItemRequirements(player, e.getNewItemStack(), false)){

                //remove the item from the offhand
                if (player.getInventory().getItem(EquipmentSlot.OFF_HAND).equals(e.getNewItemStack())) {
                player.getInventory().setItemInOffHand(null);
                }

                //send the player a message
                player.sendMessage(Component.text("You do not meet the requirements to equip this item.", TextColor.color(255, 0, 0)));

                //wait a second
                new BukkitRunnable(){
                    @Override
                    public void run() {

                        //if the player's inventory isn't full add the item to their inventory
                        if (player.getInventory().firstEmpty() != -1) {
                            player.getInventory().addItem(e.getNewItemStack());

                        //add the item to the player's stash
                        }else{
                            main.getStashManager().addItemToStash(player, e.getNewItemStack());
                        }
                    }
                }.runTaskLater(main, 20);

            //if the player meets the requirements for the new item
            } else {
                //add the stats of the new item
                addPlayerStats(player, e.getNewItemStack(), "accessory");
            }
        }
    }

    //update player stats when an item is equipped
    @EventHandler
    public void onChangedHeldItem(PlayerItemHeldEvent e){

        Player player = e.getPlayer();

        //remove the stats from the item they were holding
        removePlayerStats(player, player.getInventory().getItem(e.getPreviousSlot()), "weapon");

        //add the stats of the new item being held
        addPlayerStats(player, player.getInventory().getItem(e.getNewSlot()), "weapon");
    }

    //update player stats when armor is equipped
    @EventHandler
    public void onEquipArmor(PlayerArmorChangeEvent e){

        Player player = e.getPlayer();

        //if the player doesn't have the required proficiencies for the item, put it in their inventory
        if (!meetsItemRequirements(player, e.getNewItem(), false)){

            //remove the item from the player's armor slot
            if (player.getInventory().getItem(EquipmentSlot.HEAD).equals(e.getNewItem())) {
                player.getInventory().setHelmet(null);
            }else if (player.getInventory().getItem(EquipmentSlot.CHEST).equals(e.getNewItem())) {
                player.getInventory().setChestplate(null);
            }else if (player.getInventory().getItem(EquipmentSlot.LEGS).equals(e.getNewItem())) {
                player.getInventory().setLeggings(null);
            }else if (player.getInventory().getItem(EquipmentSlot.FEET).equals(e.getNewItem())) {
                player.getInventory().setBoots(null);
            }

            //send the player a message
            player.sendMessage(Component.text("You do not meet the requirements to equip this item.", TextColor.color(255, 0, 0)));

            //wait a second
            new BukkitRunnable(){
                @Override
                public void run() {

                    //if the player's inventory isn't full add the item to their inventory
                    if (player.getInventory().firstEmpty() != -1) {
                        player.getInventory().addItem(e.getNewItem());

                    //add the item to the player's stash
                    }else{
                        main.getStashManager().addItemToStash(player, e.getNewItem());
                    }
                }
            }.runTaskLater(main, 20);

        }else{

            addPlayerStats(player, e.getNewItem(), "armor");
        }

        removePlayerStats(player, e.getOldItem(), "armor");
    }


    //set player configurations
    public void setConfigurations(Player player){
        player.setMaxHealth(40);
    }

    //update database stats
    public void updateDatabaseStats(Player player){
        CustomPlayer customPlayer = main.getPlayerManager().getCustomPlayer(player.getUniqueId());

        //remove the stats from the player's armor
        for (ItemStack item : player.getInventory().getArmorContents()){

            if (item != null){

                //remove the stats from the armor
                removePlayerStats(player, item, "armor");
            }
        }

        //remove the stats from the main hand
        removePlayerStats(player, player.getInventory().getItemInMainHand(), "weapon");

        //saving the player's stats to the database
        customPlayer.saveAttributesToDatabase(player);
    }

    //returns true if the player has met the requirements to equip an item
    public boolean meetsItemRequirements(Player player, ItemStack item, Boolean sendMessage){

        try {

            CustomPlayer customPlayer = main.getPlayerManager().getCustomPlayer(player.getUniqueId());
            PersistentDataContainer itemData = item.getItemMeta().getPersistentDataContainer();

            try {
                //if the player meets the item requirements, return true
                if (itemData.get(new NamespacedKey(main, "meleeProficiency"), PersistentDataType.INTEGER) <= customPlayer.getMeleeProficiency()) {
                    if (itemData.get(new NamespacedKey(main, "rangedProficiency"), PersistentDataType.INTEGER) <= customPlayer.getRangedProficiency()) {
                        if (itemData.get(new NamespacedKey(main, "armorProficiency"), PersistentDataType.INTEGER) <= customPlayer.getArmorProficiency()) {
                            return true;
                        }
                    }
                }

            //if there is an error the item is not a battle item, return true
            } catch (NullPointerException ex) {
                return true;
            }

            //else return false
            if (sendMessage){
                player.sendMessage(Component.text("You do not meet the requirements to equip this item.", TextColor.color(255, 0, 0)));
            }
            return false;

        //if the item is null return true
        }catch (NullPointerException ex){
            return true;
        }
    }

    //display a hologram for the damage
    public void displayDamage(Entity entity, int damage, boolean isCritical, Location location){

        //save the y location
        double y = location.getY();

        //move the location super high up before spawning the hologram
        location.setY(2);

        //slightly offset the location
        location.add(((Math.random()*-2)+1)/2, ((Math.random()*-2)+1)/2 - 1, ((Math.random()*-2)+1)/2);

        //create the damage component
        Component damageComponent;
        if (isCritical){
            damageComponent = Component.text("⚔" + damage, TextColor.color(255,51,51));
        }else{
            damageComponent = Component.text(damage, TextColor.color(204,0,0));
        }

        //spawn the hologram
        ArmorStand armorStand = (ArmorStand) entity.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setInvisible(true);
        armorStand.setGravity(false);
        armorStand.setInvulnerable(true);
        armorStand.setCustomNameVisible(true);
        armorStand.customName(damageComponent);

        //move the location back to the original y location
        armorStand.teleport(new Location(location.getWorld(), location.getX(), location.getY() + y - 2, location.getZ()));

        //despawn the armor stand after 1.5 seconds
        new BukkitRunnable(){
            @Override
            public void run() {
                armorStand.remove();
            }
        }.runTaskLater(main, 30);

    }

    //manages the health of the player when equipping different items
    public void manageHealthAndStamina(CustomPlayer customPlayer, float healthBefore, float maxHealthBefore, float staminaBefore, float maxStaminaBefore){
        //if health is greater than max health, set health to max health
        if (customPlayer.getHealth() > customPlayer.getMaxHealth()) {
            customPlayer.setHealth(customPlayer.getMaxHealth());
        }

        //if the player was at maximum health, set health to max health
        if (healthBefore >= maxHealthBefore) {
            customPlayer.setHealth(customPlayer.getMaxHealth());
        }

        //if the stamina is greater than max stamina, set the stamina to max stamina
        if (customPlayer.getStamina() > customPlayer.getMaxStamina()) {
            customPlayer.setStamina(customPlayer.getMaxStamina());
        }

        //if the player was at maximum stamina, set stamina to max stamina
        if (staminaBefore >= maxStaminaBefore) {
            customPlayer.setStamina(customPlayer.getMaxStamina());
        }
    }

    //display the action bar
    public void displayActionBar(Player player){
        CustomPlayer customPlayer = main.getPlayerManager().getCustomPlayer(player.getUniqueId());

        player.sendActionBar(Component.text(customPlayer.getHealth() + " / " + customPlayer.getMaxHealth() + " ❤     ", TextColor.color(255,51,51))
                .append(Component.text(customPlayer.getStamina() + " / " + customPlayer.getMaxStamina() + " ⚡", TextColor.color(255,135,51))));
    }

    //update the player's hearts
    public void updateHearts(Player player){

        CustomPlayer customPlayer = main.getPlayerManager().getCustomPlayer(player.getUniqueId());

        //if the player has no health, kill them
        if (customPlayer.getHealth() <= 0) {
            player.setHealth(0);

        //if the player has super low health, set the health to 1/2 a heart
        } else if ((double) customPlayer.getHealth() / customPlayer.getMaxHealth() > 0.0001 && (double) customPlayer.getHealth() / customPlayer.getMaxHealth() < 0.02){
            player.setHealth(1);

        //else scale the hearts normally
        }else{
            player.setHealth((double) customPlayer.getHealth() / customPlayer.getMaxHealth() * 40);
        }
    }




    //add the stats of the item the player is holding
    public void addPlayerStats(Player player, ItemStack item, String itemType){

        CustomPlayer customPlayer = main.getPlayerManager().getCustomPlayer(player.getUniqueId());

        //get the player's health and stamina beforehand
        float healthBefore = customPlayer.getHealth();
        float maxHealthBefore = customPlayer.getMaxHealth();
        float staminaBefore = customPlayer.getStamina();
        float maxStaminaBefore = customPlayer.getMaxStamina();

        try {

            //get the item's data
            ItemMeta itemMeta = item.getItemMeta();
            PersistentDataContainer itemData = itemMeta.getPersistentDataContainer();

            //if the item is of the correct itemType
            if (itemData.get(new NamespacedKey(main, "itemType"), PersistentDataType.STRING).equalsIgnoreCase(itemType)) {

                //if the player meets the item requirements
                if (meetsItemRequirements(player, item, true)) {

                    //add the item's stats to the player's stats
                    customPlayer.setDamage(customPlayer.getDamage() + itemData.get(new NamespacedKey(main, "damage"), PersistentDataType.FLOAT));
                    customPlayer.setAttackSpeed(customPlayer.getAttackSpeed() + itemData.get(new NamespacedKey(main, "attackSpeed"), PersistentDataType.FLOAT));
                    customPlayer.setCritChance(customPlayer.getCritChance() + itemData.get(new NamespacedKey(main, "critChance"), PersistentDataType.FLOAT));
                    customPlayer.setCritDamage(customPlayer.getCritDamage() + itemData.get(new NamespacedKey(main, "critDamage"), PersistentDataType.FLOAT));
                    customPlayer.setStrength(customPlayer.getStrength() + itemData.get(new NamespacedKey(main, "strength"), PersistentDataType.FLOAT));
                    customPlayer.setThorns(customPlayer.getThorns() + itemData.get(new NamespacedKey(main, "thorns"), PersistentDataType.FLOAT));
                    customPlayer.setMaxHealth((int) (customPlayer.getMaxHealth() + itemData.get(new NamespacedKey(main, "health"), PersistentDataType.FLOAT)));
                    customPlayer.setHealthRegen((int) (customPlayer.getHealthRegen() + itemData.get(new NamespacedKey(main, "healthRegen"), PersistentDataType.FLOAT)));
                    customPlayer.setMaxStamina((int) (customPlayer.getMaxStamina() + itemData.get(new NamespacedKey(main, "stamina"), PersistentDataType.FLOAT)));
                    customPlayer.setStaminaRegen((int) (customPlayer.getStaminaRegen() + itemData.get(new NamespacedKey(main, "staminaRegen"), PersistentDataType.FLOAT)));
                    customPlayer.setDefense(customPlayer.getDefense() + itemData.get(new NamespacedKey(main, "defense"), PersistentDataType.FLOAT));
                    customPlayer.setSpeed(customPlayer.getSpeed() + itemData.get(new NamespacedKey(main, "speed"), PersistentDataType.FLOAT));
                    customPlayer.setInfernalDefense(customPlayer.getInfernalDefense() + itemData.get(new NamespacedKey(main, "infernalDefense"), PersistentDataType.FLOAT));
                    customPlayer.setInfernalDamage(customPlayer.getInfernalDamage() + itemData.get(new NamespacedKey(main, "infernalDamage"), PersistentDataType.FLOAT));
                    customPlayer.setUndeadDefense(customPlayer.getUndeadDefense() + itemData.get(new NamespacedKey(main, "undeadDefense"), PersistentDataType.FLOAT));
                    customPlayer.setUndeadDamage(customPlayer.getUndeadDamage() + itemData.get(new NamespacedKey(main, "undeadDamage"), PersistentDataType.FLOAT));
                    customPlayer.setAquaticDefense(customPlayer.getAquaticDefense() + itemData.get(new NamespacedKey(main, "aquaticDefense"), PersistentDataType.FLOAT));
                    customPlayer.setAquaticDamage(customPlayer.getAquaticDamage() + itemData.get(new NamespacedKey(main, "aquaticDamage"), PersistentDataType.FLOAT));
                    customPlayer.setAerialDefense(customPlayer.getAerialDefense() + itemData.get(new NamespacedKey(main, "aerialDefense"), PersistentDataType.FLOAT));
                    customPlayer.setAerialDamage(customPlayer.getAerialDamage() + itemData.get(new NamespacedKey(main, "aerialDamage"), PersistentDataType.FLOAT));
                    customPlayer.setMeleeDefense(customPlayer.getMeleeDefense() + itemData.get(new NamespacedKey(main, "meleeDefense"), PersistentDataType.FLOAT));
                    customPlayer.setMeleeDamage(customPlayer.getMeleeDamage() + itemData.get(new NamespacedKey(main, "meleeDamage"), PersistentDataType.FLOAT));
                    customPlayer.setRangedDefense(customPlayer.getRangedDefense() + itemData.get(new NamespacedKey(main, "rangedDefense"), PersistentDataType.FLOAT));
                    customPlayer.setRangedDamage(customPlayer.getRangedDamage() + itemData.get(new NamespacedKey(main, "rangedDamage"), PersistentDataType.FLOAT));
                    customPlayer.setMagicDefense(customPlayer.getMagicDefense() + itemData.get(new NamespacedKey(main, "magicDefense"), PersistentDataType.FLOAT));
                    customPlayer.setMagicDamage(customPlayer.getMagicDamage() + itemData.get(new NamespacedKey(main, "magicDamage"), PersistentDataType.FLOAT));
                }
            }
        } catch (NullPointerException e) {
            //do nothing
        }

        //manage the player's health and stamina while updating the player's action bar and hearts
        manageHealthAndStamina(customPlayer, healthBefore, maxHealthBefore, staminaBefore, maxStaminaBefore);
        displayActionBar(player);
        updateHearts(player);
    }


    //remove the stats of the item the player is holding
    public void removePlayerStats(Player player, ItemStack item, String itemType) {

        CustomPlayer customPlayer = main.getPlayerManager().getCustomPlayer(player.getUniqueId());

        //get the player's health beforehand
        float healthBefore = customPlayer.getHealth();
        float maxHealthBefore = customPlayer.getMaxHealth();
        float staminaBefore = customPlayer.getStamina();
        float maxStaminaBefore = customPlayer.getMaxStamina();

        try {

            //get the item's data
            ItemMeta itemMeta = item.getItemMeta();
            PersistentDataContainer itemData = itemMeta.getPersistentDataContainer();

            //if the item is of the correct itemType
            if (itemData.get(new NamespacedKey(main, "itemType"), PersistentDataType.STRING).equalsIgnoreCase(itemType)) {

                //if the player meets the item requirements
                if (meetsItemRequirements(player, item, false)) {

                    //remove the item's stats from the player's stats
                    customPlayer.setDamage(customPlayer.getDamage() - itemData.get(new NamespacedKey(main, "damage"), PersistentDataType.FLOAT));
                    customPlayer.setAttackSpeed(customPlayer.getAttackSpeed() - itemData.get(new NamespacedKey(main, "attackSpeed"), PersistentDataType.FLOAT));
                    customPlayer.setCritChance(customPlayer.getCritChance() - itemData.get(new NamespacedKey(main, "critChance"), PersistentDataType.FLOAT));
                    customPlayer.setCritDamage(customPlayer.getCritDamage() - itemData.get(new NamespacedKey(main, "critDamage"), PersistentDataType.FLOAT));
                    customPlayer.setStrength(customPlayer.getStrength() - itemData.get(new NamespacedKey(main, "strength"), PersistentDataType.FLOAT));
                    customPlayer.setThorns(customPlayer.getThorns() - itemData.get(new NamespacedKey(main, "thorns"), PersistentDataType.FLOAT));
                    customPlayer.setMaxHealth((int) (customPlayer.getMaxHealth() - itemData.get(new NamespacedKey(main, "health"), PersistentDataType.FLOAT)));
                    customPlayer.setHealthRegen((int) (customPlayer.getHealthRegen() - itemData.get(new NamespacedKey(main, "healthRegen"), PersistentDataType.FLOAT)));
                    customPlayer.setMaxStamina((int) (customPlayer.getMaxStamina() - itemData.get(new NamespacedKey(main, "stamina"), PersistentDataType.FLOAT)));
                    customPlayer.setStaminaRegen((int) (customPlayer.getStaminaRegen() - itemData.get(new NamespacedKey(main, "staminaRegen"), PersistentDataType.FLOAT)));
                    customPlayer.setDefense(customPlayer.getDefense() - itemData.get(new NamespacedKey(main, "defense"), PersistentDataType.FLOAT));
                    customPlayer.setSpeed(customPlayer.getSpeed() - itemData.get(new NamespacedKey(main, "speed"), PersistentDataType.FLOAT));
                    customPlayer.setInfernalDefense(customPlayer.getInfernalDefense() - itemData.get(new NamespacedKey(main, "infernalDefense"), PersistentDataType.FLOAT));
                    customPlayer.setInfernalDamage(customPlayer.getInfernalDamage() - itemData.get(new NamespacedKey(main, "infernalDamage"), PersistentDataType.FLOAT));
                    customPlayer.setUndeadDefense(customPlayer.getUndeadDefense() - itemData.get(new NamespacedKey(main, "undeadDefense"), PersistentDataType.FLOAT));
                    customPlayer.setUndeadDamage(customPlayer.getUndeadDamage() - itemData.get(new NamespacedKey(main, "undeadDamage"), PersistentDataType.FLOAT));
                    customPlayer.setAquaticDefense(customPlayer.getAquaticDefense() - itemData.get(new NamespacedKey(main, "aquaticDefense"), PersistentDataType.FLOAT));
                    customPlayer.setAquaticDamage(customPlayer.getAquaticDamage() - itemData.get(new NamespacedKey(main, "aquaticDamage"), PersistentDataType.FLOAT));
                    customPlayer.setAerialDefense(customPlayer.getAerialDefense() - itemData.get(new NamespacedKey(main, "aerialDefense"), PersistentDataType.FLOAT));
                    customPlayer.setAerialDamage(customPlayer.getAerialDamage() - itemData.get(new NamespacedKey(main, "aerialDamage"), PersistentDataType.FLOAT));
                    customPlayer.setMeleeDefense(customPlayer.getMeleeDefense() - itemData.get(new NamespacedKey(main, "meleeDefense"), PersistentDataType.FLOAT));
                    customPlayer.setMeleeDamage(customPlayer.getMeleeDamage() - itemData.get(new NamespacedKey(main, "meleeDamage"), PersistentDataType.FLOAT));
                    customPlayer.setRangedDefense(customPlayer.getRangedDefense() - itemData.get(new NamespacedKey(main, "rangedDefense"), PersistentDataType.FLOAT));
                    customPlayer.setRangedDamage(customPlayer.getRangedDamage() - itemData.get(new NamespacedKey(main, "rangedDamage"), PersistentDataType.FLOAT));
                    customPlayer.setMagicDefense(customPlayer.getMagicDefense() - itemData.get(new NamespacedKey(main, "magicDefense"), PersistentDataType.FLOAT));
                    customPlayer.setMagicDamage(customPlayer.getMagicDamage() - itemData.get(new NamespacedKey(main, "magicDamage"), PersistentDataType.FLOAT));

                }
            }
        } catch (NullPointerException e) {
            //do nothing
        }

        //manage the player's health and stamina while updating the player's action bar and hearts
        manageHealthAndStamina(customPlayer, healthBefore, maxHealthBefore, staminaBefore, maxStaminaBefore);
        displayActionBar(player);
        updateHearts(player);
    }

}