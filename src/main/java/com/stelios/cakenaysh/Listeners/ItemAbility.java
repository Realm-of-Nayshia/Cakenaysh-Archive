package com.stelios.cakenaysh.Listeners;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.stelios.cakenaysh.Util.CustomAbilities;
import com.stelios.cakenaysh.Util.ItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import com.stelios.cakenaysh.Main;
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
    public ItemStack getItem() {return item.getItemStack();}
    public int getStamina() {return stamina;}


    //requirements for the ability
    public boolean canUseAbility(Player player){

        //get the main class
        Main main = Main.getPlugin(Main.class);

        //if the stamina is above the requirement or the cooldown has expired use the ability
        if (main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina() >= this.stamina && !cooldown.asMap().containsKey(player.getUniqueId())){

                //add the player to the cooldown cache
                cooldown.put(player.getUniqueId(), System.currentTimeMillis());

                //remove the stamina
                main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setStaminaLocal(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina() - this.stamina);
                return true;
        }

        return false;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){

        e.getPlayer().sendMessage(Component.text("TestAbility"));

        //if the player is holding an item
        if (e.getItem() != null) {

            //only fire the event if the correct item is in the main hand
            if (e.getHand() == EquipmentSlot.HAND && e.getItem().equals(this.item.getItemStack())) {

                //if the player right clicks
                if (e.getAction().isRightClick()){


                    e.getPlayer().sendMessage(Component.text("TestAbility"));
                    //fire the ability
                    //CustomAbilities.TEST_ABILITY.;

                }




            }
        }

    }



}
