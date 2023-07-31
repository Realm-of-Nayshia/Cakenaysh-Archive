package com.stelios.cakenaysh.Commands;

import com.stelios.cakenaysh.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class StashCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        //if the sender is a player
        if (sender instanceof Player){
            Player player = (Player) sender;

            //if there are no arguments
            if (args.length == 0){

                //get the player's stash
                //get the stash yml file
                File file = new File(Main.getPlugin(Main.class).getDataFolder(), "stashes.yml");
                YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
                String key = player.getUniqueId().toString();

                List<ItemStack> stash = (List<ItemStack>) config.getList(key);

                //if the player has nothing in the stash
                if (stash == null || stash.size() == 0){
                    player.sendMessage(Component.text("Your stash is empty.", TextColor.color(0,255,0)));
                    return false;

                //if the player has items in the stash
                }else{

                    int emptySlots = 0;

                    //if items are in the armor or offhand slots, subtract 1 from the empty slots
                    if (player.getInventory().getItem(EquipmentSlot.HEAD).getType() == Material.AIR){
                        emptySlots--;
                    }
                    if (player.getInventory().getItem(EquipmentSlot.CHEST).getType() == Material.AIR){
                        emptySlots--;
                    }
                    if (player.getInventory().getItem(EquipmentSlot.LEGS).getType() == Material.AIR){
                        emptySlots--;
                    }
                    if (player.getInventory().getItem(EquipmentSlot.FEET).getType() == Material.AIR){
                        emptySlots--;
                    }
                    if (player.getInventory().getItem(EquipmentSlot.OFF_HAND).getType() == Material.AIR){
                        emptySlots--;
                    }

                    //loop through the player's inventory and count the empty slots
                    for (ItemStack item : player.getInventory().getContents()){
                        if (item == null || item.getType().equals(Material.AIR)){
                            emptySlots++;
                        }
                    }

                    //array list of items to remove from the stash
                    ArrayList<ItemStack> itemsToRemove = new ArrayList<>();

                    //boolean that determines if the player has enough space in their inventory
                    boolean hasEnoughSpace = true;

                    //loop through the stash and give the player the items
                    for (ItemStack item : stash){
                        if (emptySlots > 0){
                            player.getInventory().addItem(item);
                            itemsToRemove.add(item);
                            emptySlots--;

                        }else{
                            hasEnoughSpace = false;
                            player.sendMessage(Component.text("Not enough space. " + (stash.size()-itemsToRemove.size()) + " items remain in your stash.", TextColor.color(255,0,0)));
                            break;
                        }
                    }

                    //loop through the list of items to remove and removes them from the stash
                    for (ItemStack item : itemsToRemove){
                        stash.remove(item);
                    }

                    //save the stash
                    config.set(key, stash);
                    try {
                        config.save(file);
                    } catch (IOException ex) {
                        Main.getPlugin(Main.class).getLogger().log(Level.SEVERE, "Could not save stash.yml");
                    }

                    if (hasEnoughSpace){
                        player.sendMessage(Component.text("Item(s) successfully retrieved from stash.", TextColor.color(0,255,0)));
                    }
                }
            }else{
                //incorrect usage
                player.sendMessage(Component.text("Incorrect usage of command. Please use /stash", TextColor.color(255,0,0)));
            }
        }

        return false;
    }
}
