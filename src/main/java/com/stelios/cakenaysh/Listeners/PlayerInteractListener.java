package com.stelios.cakenaysh.Listeners;

import com.stelios.cakenaysh.Util.CustomItems;
import com.stelios.cakenaysh.Util.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class PlayerInteractListener implements Listener {


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){

        Player player = e.getPlayer();


        if (e.getItem() != null) {

            ////MENU
            //if the player clicks on the menu item
            if (e.getItem().equals(CustomItems.RPG_MENU.getItemBuilder().build())){

                //open the menu
                Inventory menu = Bukkit.createInventory(player, 45, Component.text("RPG Menu").decoration(TextDecoration.BOLD, true));

                //creating the items in the menu
                //setting the skull owner to the player
                ItemBuilder profile = CustomItems.PROFILE.getItemBuilder();
                ItemStack profileItem = profile.build();
                SkullMeta profileMeta = (SkullMeta) profileItem.getItemMeta();
                profileMeta.setOwningPlayer(player);
                profileItem.setItemMeta(profileMeta);

                menu.setItem(4, profileItem);
                menu.setItem(20, CustomItems.STATS.getItemBuilder().build());
                menu.setItem(21, CustomItems.SKILLS.getItemBuilder().build());
                menu.setItem(23, CustomItems.QUESTS.getItemBuilder().build());
                menu.setItem(24, CustomItems.RECIPE_BOOK.getItemBuilder().build());
                menu.setItem(39, CustomItems.CHARACTER_MANAGEMENT.getItemBuilder().build());
                menu.setItem(40, CustomItems.CLOSE.getItemBuilder().build());
                menu.setItem(41, CustomItems.SETTINGS.getItemBuilder().build());

                for(int i: new int[]{0,1,2,3,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,
                        22,25,26,27,28,29,30,31,32,33,34,35,36,37,38,42,43,44}){
                    menu.setItem(i, CustomItems.BLANK_BLACK_PANE.getItemBuilder().build());
                }

                player.openInventory(menu);

            }
        }
    }
}