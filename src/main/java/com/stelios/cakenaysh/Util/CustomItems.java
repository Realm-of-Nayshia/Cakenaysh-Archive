package com.stelios.cakenaysh.Util;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;

import java.util.ArrayList;
import java.util.Arrays;

public enum CustomItems {


    //BATTLE ITEMS
    WRATH_OF_SPARTA(new BattleItemBuilder(Material.GOLDEN_SWORD, 1,69420,45,100,90
            ,10,2,10)
            .setDisplayName(new ArrayList<>(Arrays.asList("Wrath of Sparta")),
                    new ArrayList<>(Arrays.asList(255,215,0)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("THIS IS SPARTA!")),
                    new ArrayList<>(Arrays.asList(255,215,0)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    SCYTHE_OF_INTELLIJ(new BattleItemBuilder(Material.GOLDEN_HOE, 1,60,11,120,5
            ,0,0,0)
            .setDisplayName(new ArrayList<>(Arrays.asList("Scythe of Intellij")),
                    new ArrayList<>(Arrays.asList(66,27,224)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("A divine weapon created by the ancient", "nl", "God of Intellect: Intellij")),
                    new ArrayList<>(Arrays.asList(200,200,200,200,200,200)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)))),

    TEST_ITEM(new BattleItemBuilder(Material.ACACIA_SLAB, 1,5,1,1,1
            ,0,0,0,1,4,2,6,8,3,3,4,5,1,34,5,4,1)
            .setDisplayName(new ArrayList<>(Arrays.asList("Te","st"," Item")),
                    new ArrayList<>(Arrays.asList(66,27,224,25,124,254,33,55,235)),
                    new ArrayList<>(Arrays.asList(false, false, true)),
                    new ArrayList<>(Arrays.asList(true, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, true)),
                    new ArrayList<>(Arrays.asList(false, true, false)),
                    new ArrayList<>(Arrays.asList(false, true, false)))
            .setLore(new ArrayList<>(Arrays.asList("We make", "a few new items...", "nl", "we gotta test the system")),
                    new ArrayList<>(Arrays.asList(45,26,135,200,200,200,200,200,200)),
                    new ArrayList<>(Arrays.asList(true, false, false)),
                    new ArrayList<>(Arrays.asList(false, true, false)),
                    new ArrayList<>(Arrays.asList(false, false, true)),
                    new ArrayList<>(Arrays.asList(false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false)))),


    //ITEM BUILDERS
    RPG_MENU(new ItemBuilder(Material.NETHER_STAR, 1)
            .setDisplayName(new ArrayList<>(Arrays.asList("Menu")),
                    new ArrayList<>(Arrays.asList(0,255,0)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("View all your RPG progress, including", "nl", "your stats, skills, quests, and more!")),
                    new ArrayList<>(Arrays.asList(128,128,128,128,128,128)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)))),

    PROFILE(new ItemBuilder(Material.PLAYER_HEAD, 1)
            .setDisplayName(new ArrayList<>(Arrays.asList("RPG Profile")),
                    new ArrayList<>(Arrays.asList(0,255,0)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("WIP")),
                    new ArrayList<>(Arrays.asList(128,128,128)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    STATS(new ItemBuilder(Material.DIAMOND_SWORD, 1)
            .setDisplayName(new ArrayList<>(Arrays.asList("Stats")),
                    new ArrayList<>(Arrays.asList(0,255,0)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("View and level up your stats.")),
                    new ArrayList<>(Arrays.asList(128,128,128)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .removeFlag(ItemFlag.HIDE_ATTRIBUTES)),

    SKILLS(new ItemBuilder(Material.END_CRYSTAL, 1)
            .setDisplayName(new ArrayList<>(Arrays.asList("Skills")),
                    new ArrayList<>(Arrays.asList(0,255,0)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("View and level up your skills.")),
                    new ArrayList<>(Arrays.asList(128,128,128)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    QUESTS(new ItemBuilder(Material.WRITABLE_BOOK, 1)
            .setDisplayName(new ArrayList<>(Arrays.asList("Quest Log")),
                    new ArrayList<>(Arrays.asList(0,255,0)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("View your active quests,", "nl", "progress, and rewards.")),
                    new ArrayList<>(Arrays.asList(128,128,128,128,128,128)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)))),

    RECIPE_BOOK(new ItemBuilder(Material.WRITTEN_BOOK, 1)
            .setDisplayName(new ArrayList<>(Arrays.asList("Recipe Book")),
                    new ArrayList<>(Arrays.asList(0,255,0)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("View special crating recipes", "nl", "for items you have unlocked.")),
                    new ArrayList<>(Arrays.asList(128,128,128,128,128,128)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)),
                    new ArrayList<>(Arrays.asList(false, false)))),

    CHARACTER_MANAGEMENT(new ItemBuilder(Material.NAME_TAG, 1)
            .setDisplayName(new ArrayList<>(Arrays.asList("Character Management")),
                    new ArrayList<>(Arrays.asList(0,255,0)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("You can have multiple", "nl", "characters on this server.", "nl", "nl", "View and manage them here.")),
                    new ArrayList<>(Arrays.asList(128,128,128,128,128,128,128,128,128,128,128,128)),
                    new ArrayList<>(Arrays.asList(false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false)))),

    CLOSE(new ItemBuilder(Material.BARRIER, 1)
            .setDisplayName(new ArrayList<>(Arrays.asList("Close")),
                    new ArrayList<>(Arrays.asList(255,0,0)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    SETTINGS(new ItemBuilder(Material.REDSTONE_TORCH, 1)
            .setDisplayName(new ArrayList<>(Arrays.asList("Settings")),
                    new ArrayList<>(Arrays.asList(0,255,0)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("View and edit RPG settings.")),
                    new ArrayList<>(Arrays.asList(128,128,128)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    BLANK_BLACK_PANE(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE, 1)
            .setDisplayName(new ArrayList<>(Arrays.asList("")),
                    new ArrayList<>(Arrays.asList(0,0,0)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    DIAL_OF_THE_SUN(new ItemBuilder(Material.CLOCK, 1)
            .setDisplayName(new ArrayList<>(Arrays.asList("Dial of the Sun")),
                    new ArrayList<>(Arrays.asList(255,255,0)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("This clock is an ancient relic lost to time.", "nl", "It is believed that strange events happen", "nl", "when this item is pointed towards the sky...")),
                    new ArrayList<>(Arrays.asList(75,75,75,75,75,75,75,75,75)),
                    new ArrayList<>(Arrays.asList(false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false)))),

    ;


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

    //

    //gets all the itemBuilders in the enum
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
