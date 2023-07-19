package com.stelios.cakenaysh.Util.MenuCreation.Menus;

import com.stelios.cakenaysh.Main;
import com.stelios.cakenaysh.Util.Items.CustomItems;
import com.stelios.cakenaysh.Util.CustomPlayer;
import com.stelios.cakenaysh.Util.Items.ItemBuilder;
import com.stelios.cakenaysh.Util.MenuCreation.MenuBuilder;
import com.stelios.cakenaysh.Util.MenuCreation.MenuButtonBuilder;
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

public class PlayerInfoMain extends MenuBuilder {

    public PlayerInfoMain(Player player) {

        //create the menu
        super(Component.text("Navigation", TextColor.color(0,0,0), TextDecoration.BOLD), 5);

        //set inventory consumers
        //setInventoryOpened(opened -> opened.sendMessage("Inventory opened"));
        //setInventoryClosed(closed -> closed.sendMessage("Inventory closed"));

        //getting the custom player
        CustomPlayer customPlayer = Main.getPlugin(Main.class).getPlayerManager().getCustomPlayer(player.getUniqueId());

        ////registering clickable buttons
        //profile button
        ItemBuilder profile = new ItemBuilder(Material.PLAYER_HEAD, 1,false)
                .setDisplayName(new ArrayList<>(Collections.singletonList(player.getName() + "'s Profile")),
                        new ArrayList<>(Arrays.asList(153,255,51)),
                        new ArrayList<>(Collections.singletonList(false)),
                        new ArrayList<>(Collections.singletonList(false)),
                        new ArrayList<>(Collections.singletonList(false)),
                        new ArrayList<>(Collections.singletonList(false)),
                        new ArrayList<>(Collections.singletonList(false)))
                .setLore(new ArrayList<>(Arrays.asList("nl", String.valueOf(customPlayer.getRank()), "nl",
                                "Level ", String.valueOf(customPlayer.getLevel()), "nl",
                                "Investment Points ", String.valueOf(customPlayer.getInvestmentPoints()), "nl",
                                "\uD83D\uDF9B Experience ", String.valueOf(customPlayer.getXp()), "nl", "nl",
                                "❤ Health ", String.valueOf((int) customPlayer.getHealth()), "nl",
                                "❤ Health Regen ", String.valueOf(customPlayer.getHealthRegen()), "nl",
                                "⚡ Stamina ", String.valueOf(customPlayer.getStamina()), "nl",
                                "⚡ Stamina Regen ", String.valueOf(customPlayer.getStaminaRegen()), "nl",
                                "✦ Speed ", (int) customPlayer.getSpeed() + 100 + "%"
                                )),
                        new ArrayList<>(Arrays.asList(255,255,255,
                                153,255,51, 255,255,255,
                                153,255,51, 255,255,255,
                                153,255,51, 255,255,255,
                                255,51,51, 255,255,255,
                                255,51,51, 255,255,255,
                                255,135,51, 255,255,255,
                                255,135,51, 255,255,255,
                                255,255,255, 255,255,255)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)));


        ItemStack profileItem = profile.build();
        SkullMeta profileMeta = (SkullMeta) profileItem.getItemMeta();
        profileMeta.setOwningPlayer(player);
        profileItem.setItemMeta(profileMeta);

        MenuButtonBuilder profileButton = new MenuButtonBuilder(profileItem);
        profileButton.setWhenClicked(clicked ->
                clicked.sendMessage("You clicked on your profile"));

        registerButton(profileButton, 4);


        //stats button
        ItemBuilder combatStats = new ItemBuilder(Material.DIAMOND_SWORD, 1,false)
                .setDisplayName(new ArrayList<>(Arrays.asList("Combat Stats")),
                        new ArrayList<>(Arrays.asList(0,255,0)),
                        new ArrayList<>(Arrays.asList(false)),
                        new ArrayList<>(Arrays.asList(false)),
                        new ArrayList<>(Arrays.asList(false)),
                        new ArrayList<>(Arrays.asList(false)),
                        new ArrayList<>(Arrays.asList(false)))
                .setLore(new ArrayList<>(Arrays.asList("View your combat stats.","nl","nl",
                                "Damage ", String.valueOf(customPlayer.getDamage()), "nl",
                                "Attack Speed ", String.valueOf(customPlayer.getAttackSpeed()), "nl",
                                "Crit Damage ", String.valueOf(customPlayer.getCritDamage()), "nl",
                                "Crit Chance ", String.valueOf(customPlayer.getCritChance()), "nl",
                                "Strength ", String.valueOf(customPlayer.getStrength()), "nl",
                                "Thorns ", String.valueOf(customPlayer.getThorns()), "nl",
                                "Defense ", String.valueOf(customPlayer.getDefense()), "nl",
                                "Infernal Defense ", String.valueOf(customPlayer.getInfernalDefense()), "nl",
                                "Infernal Damage ", String.valueOf(customPlayer.getInfernalDamage()), "nl",
                                "Undead Defense ", String.valueOf(customPlayer.getUndeadDefense()), "nl",
                                "Undead Damage ", String.valueOf(customPlayer.getUndeadDamage()), "nl",
                                "Aquatic Defense ", String.valueOf(customPlayer.getAquaticDefense()), "nl",
                                "Aquatic Damage ", String.valueOf(customPlayer.getAquaticDamage()), "nl",
                                "Aerial Defense ", String.valueOf(customPlayer.getAerialDefense()), "nl",
                                "Aerial Damage ", String.valueOf(customPlayer.getAerialDamage()), "nl",
                                "Melee Defense ", String.valueOf(customPlayer.getMeleeDefense()), "nl",
                                "Melee Damage ", String.valueOf(customPlayer.getMeleeDamage()), "nl",
                                "Ranged Defense ", String.valueOf(customPlayer.getRangedDefense()), "nl",
                                "Ranged Damage ", String.valueOf(customPlayer.getRangedDamage()), "nl",
                                "Magic Defense ", String.valueOf(customPlayer.getMagicDefense()), "nl",
                                "Magic Damage ", String.valueOf(customPlayer.getMagicDamage()), "nl")),
                        new ArrayList<>(Arrays.asList(128,128,128,
                                255,51,51, 255,255,255,
                                255,51,51, 255,255,255,
                                20,28,140, 255,255,255,
                                20,28,140, 255,255,255,
                                255,51,51, 255,255,255,
                                255,255,255, 255,255,255,
                                255,255,255, 255,255,255,
                                255,255,255, 255,255,255,
                                255,255,255, 255,255,255,
                                255,255,255, 255,255,255,
                                255,255,255, 255,255,255,
                                255,255,255, 255,255,255,
                                255,255,255, 255,255,255,
                                255,255,255, 255,255,255,
                                255,255,255, 255,255,255,
                                255,255,255, 255,255,255,
                                255,255,255, 255,255,255,
                                255,255,255, 255,255,255,
                                255,255,255, 255,255,255,
                                255,255,255, 255,255,255,
                                255,255,255, 255,255,255)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)));

        MenuButtonBuilder statsButton = new MenuButtonBuilder(combatStats.build());
        statsButton.setWhenClicked(clicked ->
                new PlayerInfoStats(player).open(clicked));

        registerButton(statsButton, 20);

        //skills button
        ItemBuilder skills = new ItemBuilder(Material.END_CRYSTAL, 1,false)
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
                        new ArrayList<>(Arrays.asList(false)));

        MenuButtonBuilder skillsButton = new MenuButtonBuilder(skills.build());
        skillsButton.setWhenClicked(clicked ->
                clicked.sendMessage("You clicked on your skills"));

        registerButton(skillsButton, 21);

        //quests button
        ItemBuilder quests = new ItemBuilder(Material.WRITABLE_BOOK, 1,false)
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
                        new ArrayList<>(Arrays.asList(false, false)));

        MenuButtonBuilder questsButton = new MenuButtonBuilder(quests.build());
        questsButton.setWhenClicked(clicked ->
                clicked.sendMessage("You clicked on your quests"));

        registerButton(questsButton, 23);

        //recipe book button
        ItemBuilder recipeBook = new ItemBuilder(Material.WRITTEN_BOOK, 1,false)
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
                        new ArrayList<>(Arrays.asList(false, false)));

        MenuButtonBuilder recipeBookButton = new MenuButtonBuilder(recipeBook.build());
        recipeBookButton.setWhenClicked(clicked ->
                clicked.sendMessage("You clicked on your recipe book"));

        registerButton(recipeBookButton, 24);

        //character management button
        ItemBuilder characterManagement = new ItemBuilder(Material.NAME_TAG, 1,false)
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
                        new ArrayList<>(Arrays.asList(false, false, false)));

        MenuButtonBuilder characterManagementButton = new MenuButtonBuilder(characterManagement.build());
        characterManagementButton.setWhenClicked(clicked ->
                clicked.sendMessage("You clicked on your character management"));

        registerButton(characterManagementButton, 39);

        //settings button
        MenuButtonBuilder settingsButton = new MenuButtonBuilder(CustomItems.SETTINGS.getItemBuilder().build());
        settingsButton.setWhenClicked(clicked ->
                clicked.sendMessage("You clicked on your settings"));

        registerButton(settingsButton, 41);

        //close button
        MenuButtonBuilder closeButton = new MenuButtonBuilder(CustomItems.CLOSE.getItemBuilder().build());
        closeButton.setWhenClicked(clicked -> clicked.closeInventory());

        registerButton(closeButton, 40);

        //registering non-clickable buttons
        for(int i: new int[]{0,1,2,3,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,
                22,25,26,27,28,29,30,31,32,33,34,35,36,37,38,42,43,44}){
            registerButton(new MenuButtonBuilder(CustomItems.BLANK_BLACK_PANE.getItemBuilder().build()), i);
        }

    }
}
