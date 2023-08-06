package com.stelios.cakenaysh.Listeners;

import com.stelios.cakenaysh.Events.XpChangedEvent;
import com.stelios.cakenaysh.Main;
import com.stelios.cakenaysh.Npc.Traits.NpcStats;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.NPC;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.mcmonkey.sentinel.SentinelTrait;

import java.util.HashMap;
import java.util.UUID;

public class SentinelDeathListener implements Listener {

    Main main;

    public SentinelDeathListener(Main main){
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

                    //get the players that dealt damage to the npc
                    NPC npc = CitizensAPI.getNPCRegistry().getNPC(e.getEntity());
                    NpcStats npcStats = npc.getOrAddTrait(NpcStats.class);
                    SentinelTrait sentinelTrait = npc.getOrAddTrait(SentinelTrait.class);
                    HashMap<UUID, Float> playerDamages = npcStats.getPlayerDamages();

                    //give xp to each player based on how much damage they dealt
                    for (UUID uuid : playerDamages.keySet()) {

                        Player player = Bukkit.getPlayer(uuid);

                        //if the player is offline continue
                        if(player == null) continue;

                        //calculate the xp the player will get
                        int xp = (int) (playerDamages.get(uuid) / sentinelTrait.health * npcStats.getXp());

                        //if the xp is greater than the npc's xp set it to the npc's xp
                        if(xp > npcStats.getXp()){
                            xp = (int) npcStats.getXp();
                        }

                        //add xp to the player
                        main.getPlayerManager().getCustomPlayer(uuid).addXp(xp);
                        player.sendMessage(Component.text("+" + xp + "XP", TextColor.color(0, 255, 0)));

                        //call the xp changed event
                        Bukkit.getPluginManager().callEvent(new XpChangedEvent(player, xp, npc));
                    }
                }
            }catch (NullPointerException ex){
                //do nothing
            }
        }

    }

}
