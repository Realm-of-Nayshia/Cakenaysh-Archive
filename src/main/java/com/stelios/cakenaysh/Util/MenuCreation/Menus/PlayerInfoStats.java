package com.stelios.cakenaysh.Util.MenuCreation.Menus;

import com.stelios.cakenaysh.Main;
import com.stelios.cakenaysh.Util.CustomItems;
import com.stelios.cakenaysh.Util.CustomPlayer;
import com.stelios.cakenaysh.Util.MenuCreation.Menu;
import com.stelios.cakenaysh.Util.MenuCreation.MenuButton;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.entity.Player;

public class PlayerInfoStats extends Menu {

    public PlayerInfoStats(Player player) {
        super(Component.text( player.getName() + "'s Stats", TextColor.color(0,0,0), TextDecoration.BOLD), 6);

        //set inventory consumers
        setInventoryOpened(opened -> opened.sendMessage("Inventory opened"));
        setInventoryClosed(closed -> closed.sendMessage("Inventory closed"));

        //getting the custom player
        CustomPlayer customPlayer = Main.getPlugin(Main.class).getPlayerManager().getCustomPlayer(player.getUniqueId());

        ////registering clickable buttons


        //registering non-clickable buttons
        for(int i: new int[]{0,1,2,3,5,6,7,8,9,17,18,26,27,35,36,41,42,43,44,45,46,47,48,49,50,51,52,53}){
            registerButton(new MenuButton(CustomItems.BLANK_BLACK_PANE.getItemBuilder().build()), i);
        }

    }

}
