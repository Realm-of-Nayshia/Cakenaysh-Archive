package com.stelios.cakenaysh.Listeners;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.stelios.cakenaysh.Util.CustomAbilities;
import com.stelios.cakenaysh.Util.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.stelios.cakenaysh.Main;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public abstract class ItemAbility implements Listener {

    private final CustomAbilities ability;
    private final ItemBuilder item;
    private final int stamina;
    private final Cache<UUID, Long> cooldown;
    private static boolean eventRegistered = false;

    public ItemAbility(CustomAbilities ability, ItemBuilder item, int stamina, long cooldown) {
        this.ability = ability;
        this.item = item;
        this.stamina = stamina;
        this.cooldown = CacheBuilder.newBuilder().expireAfterWrite(cooldown, TimeUnit.SECONDS).build();

        //check if an event is already registered
        if (!eventRegistered) {
            Bukkit.getPluginManager().registerEvents(this, Main.getPlugin(Main.class));
            eventRegistered = true;
        }
    }

    //getters
    public CustomAbilities getAbility() {return ability;}
    public ItemStack getItem() {return item.getItemStack();}
    public Boolean hasStaminaCost() {return ability.getStamina() > 0;}
    public int getStamina() {return stamina;}
    public Cache<UUID, Long> getCooldown() {return cooldown;}
    public Boolean hasCooldown() {return ability.getCooldown() > 0;}

    //abstract method for executing the ability
    public abstract void doAbility(Player player);

    //executing the ability
    public void executeAbility(Player player){
        if (canUseAbility(player)){
            doAbility(player);
        }
    }

    //requirements for the ability
    public boolean canUseAbility(Player player){

        //get the main class
        Main main = Main.getPlugin(Main.class);

        //if the stamina is above the requirement or the cooldown has expired use the ability
        if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina() >= ability.getStamina() && !cooldown.asMap().containsKey(player.getUniqueId())){

                //add the player to the cooldown cache
                cooldown.put(player.getUniqueId(), System.currentTimeMillis() + ability.getCooldown() * 1000);

                //remove the stamina
                main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setStaminaLocal(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina() - ability.getStamina());
                return true;
        }

        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_FALL, 1, 1);
        long distance = cooldown.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
        player.sendMessage(Component.text("You must wait " + TimeUnit.MILLISECONDS.toSeconds(distance) + " seconds to use this ability again!", TextColor.color(255,0,0)));
        return false;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){


        Player player = e.getPlayer();
        player.sendMessage(Component.text("TestAbility1"));

        //if the player is holding an item
        if (e.getItem() != null) {

            //only fire the event if the correct item is in the main hand
            if (e.getHand() == EquipmentSlot.HAND && e.getItem().equals(this.item.getItemStack())) {

                //if the player right clicks and the ability is a right click ability
                if (e.getAction().isRightClick() && ability.getClickType() == ClickType.RIGHT) {

                    //execute the ability
                    executeAbility(player);


                //if the player left clicks and the ability is a left click ability
                }else if (e.getAction().isLeftClick() && ability.getClickType() == ClickType.LEFT){

                    //execute the ability
                    executeAbility(player);

                }
            }
        }
    }
}
