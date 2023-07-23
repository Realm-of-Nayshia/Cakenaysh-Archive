package com.stelios.cakenaysh.MenuCreation.Menus;

import com.stelios.cakenaysh.Items.CustomItems;
import com.stelios.cakenaysh.Items.ItemBuilder;
import com.stelios.cakenaysh.Main;
import com.stelios.cakenaysh.MenuCreation.MenuBuilder;
import com.stelios.cakenaysh.MenuCreation.MenuButtonBuilder;
import com.stelios.cakenaysh.Util.CustomPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PlayerInfoSkills extends MenuBuilder {

    public PlayerInfoSkills(Player player){
        super( Component.text("Your Skills", TextColor.color(0,0,0), TextDecoration.BOLD), 5);

        //set inventory consumers
        //setInventoryOpened(opened -> opened.sendMessage("Inventory opened"));
        //setInventoryClosed(closed -> closed.sendMessage("Inventory closed"));

        //getting the custom player
        CustomPlayer customPlayer = Main.getPlugin(Main.class).getPlayerManager().getCustomPlayer(player.getUniqueId());

        ////registering clickable buttons
        //back button
        MenuButtonBuilder backButton = new MenuButtonBuilder(CustomItems.BACK_BUTTON.getItemBuilder().build());
        backButton.setWhenClicked(clicked -> {
            new PlayerInfoMain(clicked).open(clicked);
        });

        registerButton(backButton, 36);

        //close button
        MenuButtonBuilder closeButton = new MenuButtonBuilder(CustomItems.CLOSE.getItemBuilder().build());
        closeButton.setWhenClicked(clicked -> clicked.closeInventory());

        registerButton(closeButton, 40);





        ////registering non-clickable buttons
        //proficiency information
        ItemBuilder proficiency = new ItemBuilder(Material.PLAYER_HEAD, 1,false)
                .setDisplayName(new ArrayList<>(Arrays.asList("Proficiency Information")),
                        new ArrayList<>(Arrays.asList(153,255,51)),
                        new ArrayList<>(Collections.singletonList(false)),
                        new ArrayList<>(Collections.singletonList(false)),
                        new ArrayList<>(Collections.singletonList(false)),
                        new ArrayList<>(Collections.singletonList(false)),
                        new ArrayList<>(Collections.singletonList(false)))
                .setLore(new ArrayList<>(Arrays.asList("nl","Current Proficiency Levels:", "nl",
                                "Melee ", String.valueOf(customPlayer.getMeleeProficiency()), "nl",
                                "Ranged ", String.valueOf(customPlayer.getRangedProficiency()), "nl",
                                "Armor ", String.valueOf(customPlayer.getArmorProficiency()), "nl", "nl",
                                "Proficiency unlocks the use of better items and abilities.", "nl",
                                "You gain three proficiency points each time you level up.", "nl",
                                "Click on the items below to level up your proficiency."
                        )),
                        new ArrayList<>(Arrays.asList(
                                255,255,255, 240,185,85,
                                255,255,255, 240,185,85,
                                255,255,255, 240,185,85,
                                255,255,255, 128,128,128,
                                128,128,128, 128,128,128
                                )),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false)));


        ItemStack profileItem = proficiency.build();
        SkullMeta profileMeta = (SkullMeta) profileItem.getItemMeta();
        profileMeta.setOwningPlayer(player);
        profileItem.setItemMeta(profileMeta);

        registerButton(new MenuButtonBuilder(profileItem), 11);

        //skills info
        registerButton(new MenuButtonBuilder(CustomItems.SKILLS.getItemBuilder().build()), 4);

        //blank panes
        for(int i: new int[]{0,1,2,3,5,6,7,8,9,10,12,13,14,15,16,17,18,
                22,23,24,25,26,27,28,30,31,32,33,34,35,37,38,39,41,42,43,44}){
            registerButton(new MenuButtonBuilder(CustomItems.BLANK_BLACK_PANE.getItemBuilder().build()), i);
        }



    }

}
