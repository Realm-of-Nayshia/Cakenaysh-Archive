package com.stelios.cakenaysh.Util;

import com.stelios.cakenaysh.Listeners.ItemAbility;
import com.stelios.cakenaysh.Util.Abilities.TestAbility;
import net.kyori.adventure.text.Component;

import java.util.ArrayList;

public enum CustomAbilities {


    TEST_ABILITY(Component.text("Test Ability"), Component.text("This is a test ability"),
            0, 1, "RIGHT_CLICK")



    ;


    private Component name;
    private Component description;
    private int stamina;
    private long cooldown;
    private String clickType;

    CustomAbilities(Component name, Component description, int stamina, long cooldown, String clickType){
    }

    //getters
    public Component getName(){return this.name;}
    public Component getDescription(){return this.description;}
    public int getStamina(){return this.stamina;}
    public long getCooldown(){return this.cooldown;}
    public String getClickType(){return this.clickType;}




}
