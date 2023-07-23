package com.stelios.cakenaysh.Managers;

import com.stelios.cakenaysh.Events.XpChangedEvent;
import com.stelios.cakenaysh.Main;
import com.stelios.cakenaysh.Util.CustomPlayer;
import com.stelios.cakenaysh.Npc.Traits.NpcStats;
import net.citizensnpcs.api.CitizensAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.mcmonkey.sentinel.SentinelTrait;

public class LevelManager implements Listener {

    Main main;

    public LevelManager(Main main){
        this.main = main;
    }

    //giving xp to the player that killed the npc
    @EventHandler
    public void onPlayerDeath(EntityDeathEvent e){

        //if the player is a npc with the sentinel and npc stats trait return
        if(CitizensAPI.getNPCRegistry().isNPC(e.getEntity()) && CitizensAPI.getNPCRegistry().getNPC(e.getEntity()).hasTrait(SentinelTrait.class) && CitizensAPI.getNPCRegistry().getNPC(e.getEntity()).hasTrait(NpcStats.class)){

            try {
                //if the killer is a player and not a npc
                if (e.getEntity().getKiller() instanceof Player && !CitizensAPI.getNPCRegistry().isNPC(e.getEntity().getKiller())) {

                    //get the player
                    Player player = e.getEntity().getKiller();

                    //add xp to the player
                    main.getPlayerManager().getCustomPlayer(player.getUniqueId()).addXp((int) CitizensAPI.getNPCRegistry().getNPC(e.getEntity()).getOrAddTrait(NpcStats.class).getXp());
                    player.sendMessage(Component.text("+" + (int) CitizensAPI.getNPCRegistry().getNPC(e.getEntity()).getOrAddTrait(NpcStats.class).getXp() + "XP", TextColor.color(0, 255, 0)));

                    //call the xp gain event
                    Bukkit.getPluginManager().callEvent(new XpChangedEvent(player, (int) CitizensAPI.getNPCRegistry().getNPC(e.getEntity()).getOrAddTrait(NpcStats.class).getXp(), CitizensAPI.getNPCRegistry().getNPC(e.getEntity())));

                }
            }catch (NullPointerException ex){
                //do nothing
            }
        }

    }

    //checking if the player has enough xp to level up
    @EventHandler
    public void onXpGain(XpChangedEvent e){

        //get the player and custom player
        Player player = e.getPlayer();
        CustomPlayer customPlayer = main.getPlayerManager().getCustomPlayer(player.getUniqueId());

        //if the player has enough xp to level up
        if (customPlayer.howManyLevelUps() > 0){

            int levelUps = customPlayer.howManyLevelUps();

            //level up the player
            customPlayer.addLevels(levelUps);
            player.sendMessage(Component.text("You leveled up to level " + customPlayer.getLevel() + " !", TextColor.color(0, 255, 0)));
            player.showTitle(Title.title(Component.text(" LEVEL UP! ", TextColor.color(0, 255, 0)), Component.text(customPlayer.getLevel()-levelUps + " -----> " + customPlayer.getLevel(), TextColor.color(0, 255, 0))));
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
            customPlayer.addInvestmentPoints(levelUps);
        }

    }


}
