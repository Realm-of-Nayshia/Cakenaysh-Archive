package com.stelios.cakenaysh.MenuCreation.Menus;

import com.stelios.cakenaysh.Main;
import com.stelios.cakenaysh.Items.CustomItems;
import com.stelios.cakenaysh.Util.CustomPlayer;
import com.stelios.cakenaysh.Items.ItemBuilder;
import com.stelios.cakenaysh.MenuCreation.MenuBuilder;
import com.stelios.cakenaysh.MenuCreation.MenuButtonBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PlayerInfoStats extends MenuBuilder {

    public PlayerInfoStats(Player player) {
        super(Component.text( "Your Combat Stats", TextColor.color(0,0,0), TextDecoration.BOLD), 6);

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

        registerButton(backButton, 45);

        //close button
        MenuButtonBuilder closeButton = new MenuButtonBuilder(CustomItems.CLOSE.getItemBuilder().build());
        closeButton.setWhenClicked(clicked -> clicked.closeInventory());

        registerButton(closeButton, 49);





        ////registering non-clickable buttons
        //combat stats
        ItemBuilder combatStats = new ItemBuilder(Material.DIAMOND_SWORD, 1,false)
                .setDisplayName(new ArrayList<>(Collections.singletonList("Combat Stats")),
                        new ArrayList<>(Arrays.asList(0,255,0)),
                        new ArrayList<>(Collections.singletonList(false)),
                        new ArrayList<>(Collections.singletonList(false)),
                        new ArrayList<>(Collections.singletonList(false)),
                        new ArrayList<>(Collections.singletonList(false)),
                        new ArrayList<>(Collections.singletonList(false)))
                .setLore(new ArrayList<>(Arrays.asList("View your combat stats.","nl","nl",
                                "Damage ", String.valueOf((int) customPlayer.getDamage()), "nl",
                                "Attack Speed ", String.valueOf((int) customPlayer.getAttackSpeed()), "nl",
                                "Crit Damage ", customPlayer.getCritDamage() + "%", "nl",
                                "Crit Chance ", customPlayer.getCritChance() + "%", "nl",
                                "Strength ", String.valueOf((int) customPlayer.getStrength()), "nl",
                                "Thorns ", String.valueOf((int) customPlayer.getThorns()), "nl",
                                "Defense ", String.valueOf((int) customPlayer.getDefense()), "nl",
                                "Infernal Defense ", customPlayer.getInfernalDefense() + "%", "nl",
                                "Infernal Damage ", String.valueOf((int) customPlayer.getInfernalDamage()), "nl",
                                "Undead Defense ", customPlayer.getUndeadDefense() + "%", "nl",
                                "Undead Damage ", String.valueOf((int) customPlayer.getUndeadDamage()), "nl",
                                "Aquatic Defense ", customPlayer.getAquaticDefense() + "%", "nl",
                                "Aquatic Damage ", String.valueOf((int) customPlayer.getAquaticDamage()), "nl",
                                "Aerial Defense ", customPlayer.getAerialDefense() + "%", "nl",
                                "Aerial Damage ", String.valueOf((int) customPlayer.getAerialDamage()), "nl",
                                "Melee Defense ", customPlayer.getMeleeDefense() + "%", "nl",
                                "Melee Damage ", String.valueOf((int) customPlayer.getMeleeDamage()), "nl",
                                "Ranged Defense ", customPlayer.getRangedDefense() + "%", "nl",
                                "Ranged Damage ", String.valueOf((int) customPlayer.getRangedDamage()), "nl",
                                "Magic Defense ", customPlayer.getMagicDefense() + "%", "nl",
                                "Magic Damage ", String.valueOf((int) customPlayer.getMagicDamage()))),
                        new ArrayList<>(Arrays.asList(128,128,128,
                                255,51,51, 255,255,255,
                                255,51,51, 255,255,255,
                                66,64,219, 255,255,255,
                                66,64,219, 255,255,255,
                                255,51,51, 255,255,255,
                                90,122,80, 255,255,255,
                                72,163,44, 255,255,255,
                                214,90,2, 255,255,255,
                                214,90,2, 255,255,255,
                                60,71,58, 255,255,255,
                                60,71,58, 255,255,255,
                                91,97,199, 255,255,255,
                                91,97,199, 255,255,255,
                                196,196,196, 255,255,255,
                                196,196,196, 255,255,255,
                                214,88,88, 255,255,255,
                                214,88,88, 255,255,255,
                                240,185,85, 255,255,255,
                                240,185,85, 255,255,255,
                                174,56,217, 255,255,255,
                                174,56,217, 255,255,255)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)),
                        new ArrayList<>(Arrays.asList(false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false)));

        registerButton(new MenuButtonBuilder(combatStats.build()), 4);

        //blank panes
        for(int i: new int[]{0,1,2,3,5,6,7,8,9,17,18,26,27,35,36,41,42,43,44,46,47,48,50,51,52,53}){
            registerButton(new MenuButtonBuilder(CustomItems.BLANK_BLACK_PANE.getItemBuilder().build()), i);
        }

    }

}
