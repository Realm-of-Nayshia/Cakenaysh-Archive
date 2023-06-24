package com.stelios.cakenaysh.Util;

import net.kyori.adventure.text.Component;

public enum CustomAbilities {


    TEST_ABILITY(Component.text("Test Ability"), Component.text("This is a test ability"),
            0, 1, "RIGHT_CLICK",false)



    ;


    private Component name;
    private Component description;
    private int stamina;
    private long cooldown;
    private String clickType;
    private Boolean hasSpecialCases;

    CustomAbilities(Component name, Component description, int stamina, long cooldown, String clickType, Boolean hasSpecialCases){
        this.name = name;
        this.description = description;
        this.stamina = stamina;
        this.cooldown = cooldown;
        this.clickType = clickType;
        this.hasSpecialCases = hasSpecialCases;
    }

    //getters
    public Component getName(){return this.name;}
    public Component getDescription(){return this.description;}
    public int getStamina(){return this.stamina;}
    public long getCooldown(){return this.cooldown;}
    public String getClickType(){return this.clickType;}
    public Boolean getHasSpecialCases(){return this.hasSpecialCases;}




}
