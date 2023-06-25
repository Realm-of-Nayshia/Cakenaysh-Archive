package com.stelios.cakenaysh.Listeners;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.stelios.cakenaysh.Util.CustomAbilities;
import com.stelios.cakenaysh.Util.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
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

    private CustomAbilities ability;
    private ItemBuilder item;
    private int stamina;
    private Cache<UUID, Long> cooldown;

    public ItemAbility(CustomAbilities ability, ItemBuilder item, int stamina, long cooldown) {
        this.ability = ability;
        this.item = item;
        this.stamina = stamina;
        this.cooldown = CacheBuilder.newBuilder().expireAfterWrite(cooldown, TimeUnit.SECONDS).build();
        Bukkit.getPluginManager().registerEvents(this, Main.getPlugin(Main.class));
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

        player.playSound(player.getLocation(), "minecraft:block.note_block.bass", 1, 1);
        long distance = cooldown.asMap().get(player.getUniqueId()) - System.currentTimeMillis();
        player.sendMessage(Component.text("You must wait " + TimeUnit.MILLISECONDS.toSeconds(distance) + " seconds to use this ability again!", TextColor.color(255,0,0)));
        return false;
    }

    //unregistering the listener
    public void unregister(){
        PlayerInteractEvent.getHandlerList().unregister(this);
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
