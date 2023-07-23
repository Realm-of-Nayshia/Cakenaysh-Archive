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
                                66,64,219, 255,255,255,
                                66,64,219, 255,255,255,
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

        registerButton(new MenuButtonBuilder(combatStats.build()), 4);

        //blank panes
        for(int i: new int[]{0,1,2,3,5,6,7,8,9,17,18,26,27,35,36,41,42,43,44,46,47,48,50,51,52,53}){
            registerButton(new MenuButtonBuilder(CustomItems.BLANK_BLACK_PANE.getItemBuilder().build()), i);
        }

    }

}
