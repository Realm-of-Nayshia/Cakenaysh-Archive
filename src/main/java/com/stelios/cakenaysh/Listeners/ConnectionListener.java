package com.stelios.cakenaysh.Listeners;

import com.stelios.cakenaysh.Main;
import com.stelios.cakenaysh.Util.CustomPlayer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.sql.SQLException;

public class ConnectionListener implements Listener {

    private final Main main;

    public ConnectionListener(Main main){
        this.main = main;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player player = e.getPlayer();

        //creates a new CustomPlayer for each joining player
        try {
            CustomPlayer playerData = new CustomPlayer(main, player.getUniqueId());
            main.getPlayerManager().addCustomPlayer(player.getUniqueId(), playerData);
        } catch (SQLException ex) {
            player.kick(Component.text(("An error occurred while loading your data. Please contact a server admin."),
                    TextColor.color(255,0,0)));
            ex.printStackTrace();
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){

        Player player = e.getPlayer();

        //saving the players stamina and health in the database
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setHealthDatabase(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getHealth());
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setStaminaDatabase(main.getPlayerManager().getCustomPlayer(player.getUniqueId()).getStamina());

        //remove the custom player
        main.getPlayerManager().removeCustomPlayer(player.getUniqueId());
    }

}
