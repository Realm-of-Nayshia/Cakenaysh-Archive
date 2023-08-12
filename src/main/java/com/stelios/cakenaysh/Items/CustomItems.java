package com.stelios.cakenaysh.Items;

import com.stelios.cakenaysh.Main;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public enum CustomItems {


    //GUI ITEMS
    SKILLS(new Item(Material.END_CRYSTAL, 1,false)
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

    QUESTS(new Item(Material.WRITABLE_BOOK, 1,false)
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

    RECIPE_BOOK(new Item(Material.WRITTEN_BOOK, 1,false)
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

    CHARACTER_MANAGEMENT(new Item(Material.NAME_TAG, 1,false)
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

    BACK_BUTTON(new Item(Material.PLAYER_HEAD, 1,false, "Back Button",
            "cdc9e4dcfa4221a1fadc1b5b2b11d8beeb57879af1c42362142bae1edd5")
            .setDisplayName(new ArrayList<>(Arrays.asList("Back")),
                    new ArrayList<>(Arrays.asList(255,255,255)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    NEXT_PAGE(new Item(Material.PLAYER_HEAD, 1,false, "Next Page",
            "956a3618459e43b287b22b7e235ec699594546c6fcd6dc84bfca4cf30ab9311")
            .setDisplayName(new ArrayList<>(Arrays.asList("Next Page")),
                    new ArrayList<>(Arrays.asList(255,255,255)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    PREVIOUS_PAGE(new Item(Material.PLAYER_HEAD, 1,false, "Previous Page",
            "cdc9e4dcfa4221a1fadc1b5b2b11d8beeb57879af1c42362142bae1edd5")
            .setDisplayName(new ArrayList<>(Arrays.asList("Previous Page")),
                    new ArrayList<>(Arrays.asList(255,255,255)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    EQUAL_SIGN(new Item(Material.PLAYER_HEAD, 1,false, "Equal Sign",
            "d773155306c9d2d58b149673951cbc6666aef87b8f873538fc85745f01b51")
            .setDisplayName(new ArrayList<>(Arrays.asList("")),
                    new ArrayList<>(Arrays.asList(255,255,255)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    CLOSE(new Item(Material.BARRIER, 1,false)
            .setDisplayName(new ArrayList<>(Arrays.asList("Close")),
                    new ArrayList<>(Arrays.asList(255,0,0)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    SETTINGS(new Item(Material.REDSTONE_TORCH, 1,false)
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

    BLANK_BLACK_PANE(new Item(Material.BLACK_STAINED_GLASS_PANE, 1,false)
            .setDisplayName(new ArrayList<>(Arrays.asList("")),
                    new ArrayList<>(Arrays.asList(0,0,0)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    BLANK_LIGHT_GRAY_PANE(new Item(Material.LIGHT_GRAY_STAINED_GLASS_PANE, 1,false)
            .setDisplayName(new ArrayList<>(Arrays.asList("")),
                    new ArrayList<>(Arrays.asList(0,0,0)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    LOCKED_RED_PANE(new Item(Material.RED_STAINED_GLASS_PANE, 1, false)
            .setDisplayName(new ArrayList<>(Arrays.asList("LOCKED")),
                    new ArrayList<>(Arrays.asList(255,0,0)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),





    //BATTLE ITEMS
    JAZZ_HANDS(new BattleItem(Material.GOLDEN_BOOTS, 1,false,"Jazz Hands", "armor",-50,40,0,0
            ,0,20,0,0,0,100,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0, null)
            .setDisplayName(new ArrayList<>(Arrays.asList("Jazz Hands")),
                    new ArrayList<>(Arrays.asList(255, 0, 251)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("Jazz", "Hands are a great way", "nl", "to distract your enemies.")),
                    new ArrayList<>(Arrays.asList(30, 41, 235, 141, 49, 148, 65, 26, 112)),
                    new ArrayList<>(Arrays.asList(false,true,false)),
                    new ArrayList<>(Arrays.asList(false,true,false)),
                    new ArrayList<>(Arrays.asList(false,false,false)),
                    new ArrayList<>(Arrays.asList(false,false,false)),
                    new ArrayList<>(Arrays.asList(false,false,false)))),

    SPEED_BOOTS(new BattleItem(Material.CHAINMAIL_BOOTS, 1,false,"Speed Boots", "armor",0,0,0,0
            ,0,20,0,0,0,0,100,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,0,0, null)
            .setDisplayName(new ArrayList<>(Arrays.asList("Speed Boots")),
                    new ArrayList<>(Arrays.asList(119, 218, 230)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("We do a little running...")),
                    new ArrayList<>(Arrays.asList(113, 121, 122)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    HELM_OF_SPARTA(new BattleItem(Material.GOLDEN_HELMET, 1,false,"Helmet of Sparta", "armor",0,0,0,0
            ,10,10000,0,0,0,10000,0,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,1,0, null)
            .setDisplayName(new ArrayList<>(Arrays.asList("Helmet of Sparta")),
                    new ArrayList<>(Arrays.asList(255,215,0)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("idk what to put here lol")),
                    new ArrayList<>(Arrays.asList(255,215,0)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    CHEST_OF_SPARTA(new BattleItem(Material.GOLDEN_CHESTPLATE, 1,false,"Chestplate of Sparta", "armor",0,0,0,0
            ,10,10000,0,0,0,10000,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0, null)
            .setDisplayName(new ArrayList<>(Arrays.asList("Chestplate of Sparta")),
                    new ArrayList<>(Arrays.asList(255,215,0)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("idk what to put here lol")),
                    new ArrayList<>(Arrays.asList(255,215,0)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    LEGS_OF_SPARTA(new BattleItem(Material.GOLDEN_LEGGINGS, 1,false,"Leggings of Sparta", "armor",0,40,0,0
            ,10,10000,0,0,0,10000,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,1, null)
            .setDisplayName(new ArrayList<>(Arrays.asList("Leggings of Sparta")),
                    new ArrayList<>(Arrays.asList(255,215,0)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("idk what to put here lol")),
                    new ArrayList<>(Arrays.asList(255,215,0)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    BOOTS_OF_SPARTA(new BattleItem(Material.GOLDEN_BOOTS, 1,false,"Boots of Sparta", "armor",0,0,0,0
            ,10,10000,0,0,0,10000,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0, null)
            .setDisplayName(new ArrayList<>(Arrays.asList("Boots of Sparta")),
                    new ArrayList<>(Arrays.asList(255,215,0)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("idk what to put here lol")),
                    new ArrayList<>(Arrays.asList(255,215,0)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    TEST_HELM(new BattleItem(Material.NETHERITE_HELMET, 1,false,"Test Helm", "armor",0,0,1000,0
            ,0,0,0,0,0,1000,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0, null)
            .setDisplayName(new ArrayList<>(Arrays.asList("Test Helm")),
                    new ArrayList<>(Arrays.asList(255,215,0)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("We do a little testing...")),
                    new ArrayList<>(Arrays.asList(255,215,0)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    TEST_ACCESSORY(new BattleItem(Material.BLACK_DYE, 1,true,"Test Accessory", "accessory",0,40,0,0
            ,0,100,0,0,0,0,200,0,0,0,
            0,0,0,0,0,0,0,0,
            0,0,0,0,1,0, null)
            .setDisplayName(new ArrayList<>(Arrays.asList("Test Accessory")),
                    new ArrayList<>(Arrays.asList(69, 123, 209)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("We do a little more testing...")),
                    new ArrayList<>(Arrays.asList(105, 151, 224)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    WRATH_OF_SPARTA(new BattleItem(Material.GOLDEN_SWORD, 1,false, "Wrath of Sparta", "weapon",50000,40,100,50
            ,10,2,1,1,2,10,10,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,4,0,0, null)
            .setUnbreakable()
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

    BLANK_HELMET(new BattleItem(Material.IRON_HELMET, 1,false,"Blank Helmet", "armor",0,0,0,0
            ,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0, null)
            .setDisplayName(new ArrayList<>(Arrays.asList("Blank Helmet")),
                    new ArrayList<>(Arrays.asList(255,255,255)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("full set bonus testing")),
                    new ArrayList<>(Arrays.asList(255,255,255)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    BLANK_CHESTPLATE(new BattleItem(Material.IRON_CHESTPLATE, 1,false,"Blank Chestplate", "armor",0,0,0,0
            ,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0, null)
            .setDisplayName(new ArrayList<>(Arrays.asList("Blank Chestplate")),
                    new ArrayList<>(Arrays.asList(255,255,255)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("full set bonus testing")),
                    new ArrayList<>(Arrays.asList(255,255,255)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    BLANK_LEGGINGS(new BattleItem(Material.IRON_LEGGINGS, 1,false,"Blank Leggings", "armor",0,0,0,0
            ,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0, null)
            .setDisplayName(new ArrayList<>(Arrays.asList("Blank Leggings")),
                    new ArrayList<>(Arrays.asList(255,255,255)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("full set bonus testing")),
                    new ArrayList<>(Arrays.asList(255,255,255)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    BLANK_BOOTS(new BattleItem(Material.IRON_BOOTS, 1,false,"Blank Boots", "armor",0,0,0,0
            ,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0, null)
            .setDisplayName(new ArrayList<>(Arrays.asList("Blank Boots")),
                    new ArrayList<>(Arrays.asList(255,255,255)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("full set bonus testing")),
                    new ArrayList<>(Arrays.asList(255,255,255)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    SCYTHE_OF_INTELLIJ(new BattleItem(Material.GOLDEN_HOE, 1,false,"Scythe of Intellij", "weapon", 60,20,120,5
            ,0,0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0, null)
            .setUnbreakable()
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

    TEST_ITEM(new BattleItem(Material.ACACIA_SLAB,1,true,"Test Item", "weapon",5,5,1,1
            ,2,1,1,1,1,1,2,1,4,2,
            6,8,3,3,4,5,1,34,
            5,4,1,1,1,1, null)
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

    GRUBBY_GUSTARD(new BattleItem(Material.PLAYER_HEAD, 1, false, "Grubby Gustard", "accessory",0
    ,0,0,0,0,1000,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,
            "c7e8cb57fe790e965e3cfa6c4fbc16e3226210d65f5614e8853fa9fb84074441")
            .setDisplayName(new ArrayList<>(Arrays.asList("Grubby Gustard")),
                    new ArrayList<>(Arrays.asList(81, 143, 91)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("This rotting piece of wood" , "nl", "is thriving with the moss.", "nl", "Is it any useful?")),
                    new ArrayList<>(Arrays.asList(128,128,128,128,128,128,128,128,128)),
                    new ArrayList<>(Arrays.asList(false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false)),
                    new ArrayList<>(Arrays.asList(true, true, true)),
                    new ArrayList<>(Arrays.asList(false, false, false)),
                    new ArrayList<>(Arrays.asList(false, false, false)))),


    //REGULAR ITEMS
    DIAL_OF_THE_SUN(new Item(Material.CLOCK, 1,true, "Dial of the Sun", null)
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

    GRUBULOUSLY_GRUBBY_GRUSTARD(new Item(Material.PLAYER_HEAD, 1, false, "Grubulously Grubby Grustard",
            "212a03a4c11b4d472472e7e4593d2e126a6259e33cc81f44eb05cf042d076967")
            .setDisplayName(new ArrayList<>(Arrays.asList("Grubulously Grubby Grustard")),
                    new ArrayList<>(Arrays.asList(116, 181, 126)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("Why would you want this abomination of a grub...")),
                    new ArrayList<>(Arrays.asList(128,128,128)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))),

    INGOT_OF_SPARTA(new Item(Material.GOLD_INGOT, 1 , false)
            .setDisplayName(new ArrayList<>(Arrays.asList("Ingot of Sparta")),
                    new ArrayList<>(Arrays.asList(255,215,0)),
                    new ArrayList<>(Arrays.asList(true)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)),
                    new ArrayList<>(Arrays.asList(false)))
            .setLore(new ArrayList<>(Arrays.asList("Από τις έντονες φωτιές της Σπάρτας. Αυτός ο", "nl", "χρυσός λέγεται ότι είναι ο ισχυρότερος στον κόσμο...")),
                    new ArrayList<>(Arrays.asList(128,128,128,128,128,128)),
                    new ArrayList<>(Arrays.asList(false,false)),
                    new ArrayList<>(Arrays.asList(false,false)),
                    new ArrayList<>(Arrays.asList(true,true)),
                    new ArrayList<>(Arrays.asList(false,false)),
                    new ArrayList<>(Arrays.asList(false,false)))),

    ;


    private final Item item;

    CustomItems(Item item){
        this.item = item;
    }


    //gets an items item
    //@return the Item
    public Item getItem(){
        return item;
    }

    //gets an item from the enum
    //@param name: The name of the item being picked.
    //@return the Item
    public static Item getItemFromName(String name){
        for (CustomItems item : CustomItems.values()){
            if (item.name().equalsIgnoreCase(name)){

                //if the item is unstackable, make it unstackable
                if (item.getItem().getUnstackable()){
                    item.item.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class), "uniqueID"),
                            PersistentDataType.STRING, UUID.randomUUID().toString());
                }
                return item.item;
            }
        }
        return null;
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
