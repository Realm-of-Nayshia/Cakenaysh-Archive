package com.stelios.cakenaysh.Util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.Arrays;

public enum CustomItems {

    //ITEM BUILDERS
    RPG_MENU(new ItemBuilder(Material.NETHER_STAR, 1)
            .setDisplayName("Menu"
                    , 0, 255, 0, false, false, false, false, false)
            .setLore(new ArrayList<>(Arrays.asList("View all your RPG progress, including", "your stats, skills, quests, and more!")),
                    new ArrayList<>(Arrays.asList(128,128,128,128,128,128)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)))),

    PROFILE(new ItemBuilder(Material.PLAYER_HEAD, 1)
            .setDisplayName("RPG Profile"
                    , 0, 255, 0, false, false, false, false, false)
            .setLore(new ArrayList<>(Arrays.asList("WIP")),
                    new ArrayList<>(Arrays.asList(128,128,128)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    STATS(new ItemBuilder(Material.DIAMOND_SWORD, 1)
            .setDisplayName("Stats"
                    , 0, 255, 0, false, false, false, false, false)
            .setLore(new ArrayList<>(Arrays.asList("View and level up your stats.")),
                    new ArrayList<>(Arrays.asList(128,128,128)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .removeFlag(ItemFlag.HIDE_ATTRIBUTES)),

    SKILLS(new ItemBuilder(Material.END_CRYSTAL, 1)
            .setDisplayName("Skills"
                    , 0, 255, 0, false, false, false, false, false)
            .setLore(new ArrayList<>(Arrays.asList("View and level up your skills.")),
                    new ArrayList<>(Arrays.asList(128,128,128)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    QUESTS(new ItemBuilder(Material.WRITABLE_BOOK, 1)
            .setDisplayName("Quest Log"
                    , 0, 255, 0, false, false, false, false, false)
            .setLore(new ArrayList<>(Arrays.asList("View your active quests,", "progress, and rewards.")),
                    new ArrayList<>(Arrays.asList(128,128,128,128,128,128)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)))),

    RECIPE_BOOK(new ItemBuilder(Material.WRITTEN_BOOK, 1)
            .setDisplayName("Recipe Book"
                    , 0, 255, 0, false, false, false, false, false)
            .setLore(new ArrayList<>(Arrays.asList("View special crafting recipes", "for items you have unlocked.")),
                    new ArrayList<>(Arrays.asList(128,128,128,128,128,128)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)))),

    CHARACTER_MANAGEMENT(new ItemBuilder(Material.NAME_TAG, 1)
            .setDisplayName("Character Management"
                    , 0, 255, 0, false, false, false, false, false)
            .setLore(new ArrayList<>(Arrays.asList("You can have multiple", "characters on this server.", " ", "View and manage them here.")),
                    new ArrayList<>(Arrays.asList(128,128,128,128,128,128,128,128,128,128,128,128)),
                    new ArrayList<>(Arrays.asList(false, false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false, false)))),

    CLOSE(new ItemBuilder(Material.BARRIER, 1)
            .setDisplayName("Close"
                    , 255, 0, 0, false, false, false, false, false)),

    SETTINGS(new ItemBuilder(Material.REDSTONE_TORCH, 1)
            .setDisplayName("Settings"
                    , 0, 255, 0, false, false, false, false, false)
            .setLore(new ArrayList<>(Arrays.asList("View and edit RPG settings.")),
                    new ArrayList<>(Arrays.asList(128,128,128)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    BLANK_BLACK_PANE(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1)
            .setDisplayName(""
                    , 0, 0, 0, false, false, false, false, false)),

    DIAL_OF_THE_SUN(new ItemBuilder(Material.CLOCK, 1)
            .setDisplayName("Dial of the Sun"
                    , 255, 255, 0, true, false, false, false, false)
            .setLore(new ArrayList<>(Arrays.asList("This clock is an ancient relic lost to time.", "It is believed that strange events happen", "when this item is pointed towards the sky...")),
                    new ArrayList<>(Arrays.asList(75,75,75,75,75,75,75,75,75)),
                    new ArrayList<>(Arrays.asList(false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false)))),



    //BATTLE ITEMS
    WRATH_OF_SPARTA(new BattleItemBuilder(Material.GOLDEN_SWORD, 1,1000,45,100,90
            ,10,2,10,1)
            .setDisplayName("Wrath of Sparta"
                    , 255, 215, 0, true, false, false, false, false)
            .setLore(new ArrayList<>(Arrays.asList("THIS IS SPARTA!")),
                    new ArrayList<>(Arrays.asList(255,215,0)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),



    SCYTHE_OF_INTELLIJ(new BattleItemBuilder(Material.GOLDEN_HOE, 1,60,11,120,5
            ,0,0,0,5)
            .setDisplayName("Scythe of Intellij"
                    , 66, 27, 224, false, false, false, false, false)
            .setLore(new ArrayList<>(Arrays.asList("A divine weapon created by the ancient", "God of Intellect: Intellij")),
                    new ArrayList<>(Arrays.asList(201,201,201,201,201,201)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false))));


    private final ItemBuilder itemBuilder;

    CustomItems(ItemBuilder itemBuilder){
        this.itemBuilder = itemBuilder;
    }


    //gets an items itemBuilder
    //@return the ItemBuilder
    public ItemBuilder getItemBuilder(){
        return itemBuilder;
    }

    //gets an item from the enum
    //@param name: The name of the item being picked.
    //@return the ItemBuilder
    public static ItemBuilder getItem(String name){
        for (CustomItems item : CustomItems.values()){
            if (item.name().equalsIgnoreCase(name)){
                return item.itemBuilder;
            }
        }
        return null;
    }

    //gets all the itembuildres in the enum
    //@return ArrayList<ItemBuilder> of all the items
public static ArrayList<ItemBuilder> getAllItems(){
        ArrayList<ItemBuilder> items = new ArrayList<>();
        for (CustomItems item : CustomItems.values()){
            items.add(item.itemBuilder);
        }
        return items;
    }

    //returns all the names of items in the enum
    //@return ArrayList<String> of all the names
    public static ArrayList<String> getNames(){
        ArrayList<String> names = new ArrayList<>();
        for (CustomItems item : CustomItems.values()){
            names.add(item.name());
        }
        return names;
    }

}
