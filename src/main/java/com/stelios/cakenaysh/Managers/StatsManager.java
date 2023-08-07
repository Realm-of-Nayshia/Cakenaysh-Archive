package com.stelios.cakenaysh.Managers;

import com.stelios.cakenaysh.Main;
import com.stelios.cakenaysh.Util.CustomPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

public class StatsManager {

    private final Main main;

    public StatsManager(Main main) {
        this.main = main;
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