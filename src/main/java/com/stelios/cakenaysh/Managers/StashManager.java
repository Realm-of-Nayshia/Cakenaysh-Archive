package com.stelios.cakenaysh.Managers;

import com.stelios.cakenaysh.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class StashManager {

    Main main;

    public StashManager(Main main) {
        this.main = main;
    }

    //adds an item to the player's stash
    public void addItemToStash(Player player, ItemStack item){

        //get the stash yml file
        File file = new File(main.getDataFolder(), "stashes.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        String key = player.getUniqueId().toString();

        List<ItemStack> stash = (List<ItemStack>) config.getList(key);

        //if the player doesn't have a stash create one
        if (stash == null) {
            stash = new ArrayList<ItemStack>();
        }

        //add each item to the stash
        stash.add(item);
        config.set(key, stash);

        player.sendMessage(Component.text("Your inventory is full. "+ stash.size() + " items are in your stash.\nType /stash to collect your items.", TextColor.color(255, 0, 0)));

        try {
            config.save(file);
        } catch (IOException ex) {
            main.getLogger().log(Level.SEVERE, "Could not save stash.yml");
        }
    }

    //adds multiple items to the player's stash
    public void addItemsToStash(Player player, ArrayList<ItemStack> items){

        //get the stash yml file
        File file = new File(main.getDataFolder(), "stashes.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        String key = player.getUniqueId().toString();

        List<ItemStack> stash = (List<ItemStack>) config.getList(key);

        //if the player doesn't have a stash create one
        if (stash == null) {
            stash = new ArrayList<ItemStack>();
        }

        //add each item to the stash
        stash.addAll(items);
        config.set(key, stash);

        player.sendMessage(Component.text("Your inventory is full. "+ stash.size() + " items are in your stash.\nType /stash to collect your items.", TextColor.color(255, 0, 0)));

        try {
            config.save(file);
        } catch (IOException ex) {
            main.getLogger().log(Level.SEVERE, "Could not save stash.yml");
        }
    }

}
