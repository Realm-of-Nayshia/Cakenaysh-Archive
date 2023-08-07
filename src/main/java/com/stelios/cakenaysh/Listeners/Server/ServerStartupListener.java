package com.stelios.cakenaysh.Listeners.Server;

import com.stelios.cakenaysh.Main;
import com.stelios.cakenaysh.Managers.StatsManager;
import com.stelios.cakenaysh.Util.CustomPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ServerStartupListener implements Listener {

    private final Main main;
    private final StatsManager statsManager;

    public ServerStartupListener(Main main, StatsManager statsManager){
        this.main = main;
        this.statsManager = statsManager;
    }

    @EventHandler
    public void onStart(ServerLoadEvent e){

        //saving player stats every 30 minutes
        new BukkitRunnable(){
            @Override
            public void run() {
                for (Player player : main.getServer().getOnlinePlayers()){
                    statsManager.updateDatabaseStats(player);

                    //add the stats back to the player's armor
                    for (ItemStack item : player.getInventory().getArmorContents()){

                        if (item != null){

                            //remove the stats from the armor
                            statsManager.addPlayerStats(player, item, "armor");
                        }
                    }

                    //add the stats back to the main hand
                    statsManager.addPlayerStats(player, player.getInventory().getItemInMainHand(), "weapon");
                }
            }
        }.runTaskTimerAsynchronously(main, 0, 20*1800);
    }


    @EventHandler
    public void onStartup(ServerLoadEvent e){

        new BukkitRunnable(){
            @Override
            public void run() {

                for (Player player : main.getServer().getOnlinePlayers()){

                    //get the custom player
                    CustomPlayer customPlayer = main.getPlayerManager().getCustomPlayer(player.getUniqueId());

                    //if the player has 0 health then don't regenerate health or stamina
                    if (customPlayer.getHealth() > 0) {

                        //randomly increase the players saturation (only if saturation is below 3)
                        if (player.getSaturation() < 3) {
                            if (Math.random() > 0.96) {
                                player.setSaturation((player.getSaturation() + 1));
                            }
                        }

                        //regenerate stamina if not at max
                        if (customPlayer.getStamina() < customPlayer.getMaxStamina()) {
                            customPlayer.addStamina(customPlayer.getStaminaRegen());
                        }

                        //regenerate health if not at max
                        if (customPlayer.getHealth() < customPlayer.getMaxHealth()) {
                            customPlayer.addHealth(customPlayer.getHealthRegen());
                        }

                        //if over max stamina, set to max stamina
                        if (customPlayer.getStamina() > customPlayer.getMaxStamina()) {
                            customPlayer.setStamina(customPlayer.getMaxStamina());
                        }

                        //if over max health, set to max health
                        if (customPlayer.getHealth() > customPlayer.getMaxHealth()) {
                            customPlayer.setHealth(customPlayer.getMaxHealth());
                        }

                        statsManager.updateHearts(player);
                        statsManager.displayActionBar(player);

                    }else{
                        //kill the player
                        statsManager.updateHearts(player);
                    }
                }
            }
        }.runTaskTimer(main,0,40);
    }

}
