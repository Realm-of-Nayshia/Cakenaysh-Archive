package com.stelios.cakenaysh.Listeners;

import com.stelios.cakenaysh.Main;
import com.stelios.cakenaysh.Util.CustomItems;
import com.stelios.cakenaysh.Util.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerInteractListener implements Listener {


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){

        Player player = e.getPlayer();


        if (e.getItem() != null) {

            ////MENU
            //if the player clicks on the menu item
            if (e.getItem().equals(CustomItems.getItem("RPG_MENU").build())){

                //open the menu
                Inventory menu = Bukkit.createInventory(player, 45, Component.text("RPG Menu").decoration(TextDecoration.BOLD, true));

                //creating the items in the menu
                //setting the skull owner to the player
                ItemBuilder profile = CustomItems.getItem("PROFILE");
                ItemStack profileItem = profile.build();
                SkullMeta profileMeta = (SkullMeta) profileItem.getItemMeta();
                profileMeta.setOwningPlayer(player);
                profileItem.setItemMeta(profileMeta);

                menu.setItem(4, profileItem);
                menu.setItem(20, CustomItems.getItem("STATS").build());
                menu.setItem(21, CustomItems.getItem("SKILLS").build());
                menu.setItem(23, CustomItems.getItem("QUESTS").build());
                menu.setItem(24, CustomItems.getItem("RECIPE_BOOK").build());
                menu.setItem(39, CustomItems.getItem("CHARACTER_MANAGEMENT").build());
                menu.setItem(40, CustomItems.getItem("CLOSE").build());
                menu.setItem(41, CustomItems.getItem("SETTINGS").build());

                for(int i: new int[]{0,1,2,3,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,
                        22,25,26,27,28,29,30,31,32,33,34,35,36,37,38,42,43,44}){
                    menu.setItem(i, CustomItems.getItem("BLANK_BLACK_PANE").build());
                }

                player.openInventory(menu);





            }

            ////DIAL_OF_THE_SUN
            //if the player right clicks on air with the DIAL_OF_THE_SUN in hand
            if (e.getItem().equals(CustomItems.getItem("DIAL_OF_THE_SUN").build())){
                if (e.getAction().isRightClick() && e.getClickedBlock() == null){

                    //take away the dial and play a sound
                    e.getItem().setAmount(e.getItem().getAmount() - 1);
                    player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 1, 100);

                    //gradually set the time to dawn
                    new BukkitRunnable() {
                        long time = player.getWorld().getTime();
                        @Override
                        public void run() {
                            if (time > 23800) {
                                this.cancel();
                                return;
                            }
                            time += 100;
                            player.getWorld().setTime(time);
                        }
                    }.runTaskTimer(Main.getPlugin(Main.class), 0, 1);
                }
            }




        }
    }
}