package com.stelios.cakenaysh.Listeners.Server;

import com.stelios.cakenaysh.Main;
import com.stelios.cakenaysh.Util.CustomPlayer;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class ConnectionListener implements Listener {

    private final Main main;

    public ConnectionListener(Main main){
        this.main = main;
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e){

        Player player = e.getPlayer();

        //inject the player
        main.getPacketManager().inject(player);

        //creates a new CustomPlayer for each joining player
        CustomPlayer playerData = new CustomPlayer(main, player.getUniqueId());
        main.getPlayerManager().addCustomPlayer(player.getUniqueId(), playerData);

        //create a stash for new players
        main.getStashManager().createStash(player);

        //get the recipes yml file
        File file = new File(main.getDataFolder(), "recipes.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        String key = player.getUniqueId().toString();

        List<String> recipes = (List<String>) config.getList(key);

        //if the player isn't in the yml file
        if (recipes == null) {

            //add the player to the recipe file
            recipes = new ArrayList<String>();
            config.set(key, recipes);

            //reset the player's recipes
            for (NamespacedKey recipe : player.getDiscoveredRecipes()) {
                player.undiscoverRecipe(recipe);
            }

            //save the file
            try {
                config.save(file);
            } catch (IOException ex) {
                main.getLogger().log(Level.SEVERE, "Could not save recipes.yml");
            }
        }

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){

        Player player = e.getPlayer();

        //unInject the player
        main.getPacketManager().unInject(player);

        //remove the custom player after waiting 1 tick
        main.getServer().getScheduler().runTaskLater(main, () -> {
            main.getPlayerManager().removeCustomPlayer(player.getUniqueId());
        }, 10);
    }

}
