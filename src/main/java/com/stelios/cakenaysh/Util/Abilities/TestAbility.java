package com.stelios.cakenaysh.Util.Abilities;

import com.stelios.cakenaysh.Listeners.ItemAbility;
import com.stelios.cakenaysh.Util.ItemBuilder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class TestAbility extends ItemAbility {

    public TestAbility(ItemBuilder item, long cooldown) {
        super(item, cooldown);
    }

    //executing the ability
    public void doAbility(){
        System.out.println("TestAbility");
    }

    //conditions for the ability to be executed
    @EventHandler
    public void onClick(PlayerInteractEvent e){

        //only fire the event if the correct item is in the main hand
        if (e.getHand() == EquipmentSlot.HAND && e.getItem() == this.getItem()) {


            //fire the ability
            this.doAbility();


        }
    }


}
