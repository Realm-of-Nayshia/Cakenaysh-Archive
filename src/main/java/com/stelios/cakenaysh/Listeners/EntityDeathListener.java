package com.stelios.cakenaysh.Listeners;

import com.stelios.cakenaysh.Main;
import com.stelios.cakenaysh.Util.Npc.Traits.NpcStats;
import net.citizensnpcs.api.CitizensAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.mcmonkey.sentinel.SentinelTrait;

public class EntityDeathListener implements Listener {

    Main main;

    public EntityDeathListener(Main main){
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
                }
            }catch (NullPointerException ex){
                //do nothing
            }
        }

    }

}
