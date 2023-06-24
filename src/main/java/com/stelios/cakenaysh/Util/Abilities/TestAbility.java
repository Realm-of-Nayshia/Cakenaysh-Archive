package com.stelios.cakenaysh.Util.Abilities;

import com.stelios.cakenaysh.Listeners.ItemAbility;
import com.stelios.cakenaysh.Util.CustomAbilities;
import com.stelios.cakenaysh.Util.ItemBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class TestAbility extends ItemAbility {

    public TestAbility(CustomAbilities ability, ItemBuilder item, int stamina, long cooldown) {
        super(ability, item, stamina, cooldown);
    }

    //executing the ability
    public void doAbility(Player player){
        System.out.println("TestAbility");
        player.sendMessage(Component.text("TestAbility"));
    }


}
