package com.stelios.cakenaysh.Util.MenuCreation.Menus;

import com.stelios.cakenaysh.Main;
import com.stelios.cakenaysh.Util.CustomItems;
import com.stelios.cakenaysh.Util.CustomPlayer;
import com.stelios.cakenaysh.Util.ItemBuilder;
import com.stelios.cakenaysh.Util.MenuCreation.Menu;
import com.stelios.cakenaysh.Util.MenuCreation.MenuButton;
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

public class PlayerInfoMain extends Menu{

    public PlayerInfoMain(Player player) {

        //create the menu
        super(Component.text("Navigation", TextColor.color(0,0,0), TextDecoration.BOLD), 5);

        //set inventory consumers
        setInventoryOpened(opened -> opened.sendMessage("Inventory opened"));
        setInventoryClosed(closed -> closed.sendMessage("Inventory closed"));

        //getting the custom player
        CustomPlayer customPlayer = Main.getPlugin(Main.class).getPlayerManager().getCustomPlayer(player.getUniqueId());

        ////registering clickable buttons
        //profile button
        ItemBuilder profile = new ItemBuilder(Material.PLAYER_HEAD, 1)
                .setDisplayName(new ArrayList<>(Collections.singletonList(player.getName() + "'s Profile")),
                        new ArrayList<>(Arrays.asList(153,255,51)),
                        new ArrayList<>(Collections.singletonList(false)),
                        new ArrayList<>(Collections.singletonList(false)),
                        new ArrayList<>(Collections.singletonList(false)),
                        new ArrayList<>(Collections.singletonList(false)),
                        new ArrayList<>(Collections.singletonList(false)))
                .setLore(new ArrayList<>(Arrays.asList(String.valueOf(customPlayer.getRank()), "nl", "nl",
                                "Level ", String.valueOf(customPlayer.getLevel()), "nl",
                                "\uD83D\uDF9B Experience ", String.valueOf(customPlayer.getXp()), "nl",
                                "❤ Health ", String.valueOf((int) customPlayer.getHealth()), "nl",
                                "❤ Health Regen ", String.valueOf(customPlayer.getHealthRegen()), "nl",
                                "⚡ Stamina ", String.valueOf(customPlayer.getStamina()), "nl",
                                "⚡ Stamina Regen ", String.valueOf(customPlayer.getStaminaRegen()), "nl",
                                "✦ Speed ",String.valueOf((int) customPlayer.getSpeed() + 100)
                                )),
                        new ArrayList<>(Arrays.asList(255,255,255,
                                153,255,51, 255,255,255,
                                153,255,51, 255,255,255,
                                255,51,51, 255,255,255,
                                255,51,51, 255,255,255,
                                255,135,51, 255,255,255,
                                255,135,51, 255,255,255,
                                255,255,255, 255,255,255)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)));


        ItemStack profileItem = profile.build();
        SkullMeta profileMeta = (SkullMeta) profileItem.getItemMeta();
        profileMeta.setOwningPlayer(player);
        profileItem.setItemMeta(profileMeta);

        MenuButton profileButton = new MenuButton(profileItem);
        profileButton.setWhenClicked(clicked ->
                clicked.sendMessage("You clicked on your profile"));

        registerButton(profileButton, 4);


        //stats button
        MenuButton statsButton = new MenuButton(CustomItems.STATS.getItemBuilder().build());
        statsButton.setWhenClicked(clicked ->
                new PlayerInfoStats(player).open(clicked));

        registerButton(statsButton, 20);

        //skills button
        MenuButton skillsButton = new MenuButton(CustomItems.SKILLS.getItemBuilder().build());
        skillsButton.setWhenClicked(clicked ->
                clicked.sendMessage("You clicked on your skills"));

        registerButton(skillsButton, 21);

        //quests button
        MenuButton questsButton = new MenuButton(CustomItems.QUESTS.getItemBuilder().build());
        questsButton.setWhenClicked(clicked ->
                clicked.sendMessage("You clicked on your quests"));

        registerButton(questsButton, 23);

        //recipe book button
        MenuButton recipeBookButton = new MenuButton(CustomItems.RECIPE_BOOK.getItemBuilder().build());
        recipeBookButton.setWhenClicked(clicked ->
                clicked.sendMessage("You clicked on your recipe book"));

        registerButton(recipeBookButton, 24);

        //character management button
        MenuButton characterManagementButton = new MenuButton(CustomItems.CHARACTER_MANAGEMENT.getItemBuilder().build());
        characterManagementButton.setWhenClicked(clicked ->
                clicked.sendMessage("You clicked on your character management"));

        registerButton(characterManagementButton, 39);

        //settings button
        MenuButton settingsButton = new MenuButton(CustomItems.SETTINGS.getItemBuilder().build());
        settingsButton.setWhenClicked(clicked ->
                clicked.sendMessage("You clicked on your settings"));

        registerButton(settingsButton, 41);

        //close button
        MenuButton closeButton = new MenuButton(CustomItems.CLOSE.getItemBuilder().build());
        closeButton.setWhenClicked(clicked -> clicked.closeInventory());

        registerButton(closeButton, 40);

        //registering non-clickable buttons
        for(int i: new int[]{0,1,2,3,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,
                22,25,26,27,28,29,30,31,32,33,34,35,36,37,38,42,43,44}){
            registerButton(new MenuButton(CustomItems.BLANK_BLACK_PANE.getItemBuilder().build()), i);
        }

    }
}
