package com.stelios.cakenaysh.Util;

import net.kyori.adventure.text.Component;
import org.bukkit.event.inventory.ClickType;

public enum CustomAbilities {


    SPARTAN_WRATH(Component.text("Test Ability"), Component.text("This is a test ability"),
            0, 1, ClickType.RIGHT,false);


    private final Component name;
    private final Component description;
    private final int stamina;
    private final long cooldown;
    private final ClickType clickType;
    private final Boolean hasSpecialCases;

    CustomAbilities(Component name, Component description, int stamina, long cooldown, ClickType clickType, Boolean hasSpecialCases){
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
    public ClickType getClickType(){return this.clickType;}
    public Boolean getHasSpecialCases(){return this.hasSpecialCases;}




}
