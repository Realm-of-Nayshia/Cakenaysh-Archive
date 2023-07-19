package com.stelios.cakenaysh.Util.AbilityCreation.Abilities;

import com.stelios.cakenaysh.Util.AbilityCreation.ItemAbility;
import com.stelios.cakenaysh.Util.AbilityCreation.CustomAbilities;
import com.stelios.cakenaysh.Util.Items.ItemBuilder;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class WrathOfSpartaAbility extends ItemAbility {

    public WrathOfSpartaAbility(CustomAbilities ability, ItemBuilder item, int stamina, long cooldown) {
        super(ability, item, stamina, cooldown);
    }

    public void doAbility(Player player){

        //get the main class
        //Main main = Main.getPlugin(Main.class);

        player.sendMessage(Component.text("The spark within you ignites... Rage takes over...",
                TextColor.color(255,0,0)));
        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 15, 15);
        //main.getPlayerManager().getCustomPlayer(player.getUniqueId()).add

    }
}
