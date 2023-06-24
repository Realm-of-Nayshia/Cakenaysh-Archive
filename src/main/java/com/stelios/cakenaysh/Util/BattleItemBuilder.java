package com.stelios.cakenaysh.Util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class BattleItemBuilder extends ItemBuilder{

    private float damage;
    private float attackSpeed;
    private float critDamage;
    private float critChance;
    private float strength;
    private float health;
    private float defense;
    private float speed;

    //@param material: The material of the item being built.
    //@param amount: The amount of the item being built.
    //@param damage: The damage of the item being built.
    //@param attackSpeed: The attack speed of the item being built.
    //@param critDamage: The crit damage of the item being built.
    //@param critChance: The crit chance of the item being built.
    //@param health: The health of the item being built.
    //@param defense: The defense of the item being built.
    //@param speed: The speed of the item being built.
    public BattleItemBuilder(Material material, int amount, float damage, float attackSpeed, float critDamage,
                             float critChance, float strength, float health, float defense, float speed){
        super(material, amount);
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.critDamage = critDamage;
        this.critChance = critChance;
        this.strength = strength;
        this.health = health;
        this.defense = defense;
        this.speed = speed;
    }

    //getter
    //@param Stat: The stat being retrieved.
    //@return the value of the stat.
    public float getStat(String Stat){
        switch(Stat){
            case "damage":
                return this.damage;
            case "attackSpeed":
                return this.attackSpeed;
            case "critDamage":
                return this.critDamage;
            case "critChance":
                return this.critChance;
            case "strength":
                return this.strength;
            case "health":
                return this.health;
            case "defense":
                return this.defense;
            case "speed":
                return this.speed;
        }
        System.out.println("Invalid stat");
        return 0;
    }

    //setter
    //@param Stat: The stat being set.
    //@param value: The value being set to the stat.
    public void setStat(String Stat, float value){
        switch(Stat){
            case "damage":
                this.damage = value;
                break;
            case "attackSpeed":
                this.attackSpeed = value;
                break;
            case "critDamage":
                this.critDamage = value;
                break;
            case "critChance":
                this.critChance = value;
                break;
            case "strength":
                this.strength = value;
                break;
            case "health":
                this.health = value;
                break;
            case "defense":
                this.defense = value;
                break;
            case "speed":
                this.speed = value;
                break;
            default:
                System.out.println("Invalid stat");
                break;
        }
    }

    //sets the lore of the item
    //@param loreText: The text of the lore being set to the item.
    //@param rgbValues: The rgb values of the lore being set to the item.
    //@param isBold: Makes the lore bold.
    //@param isUnderlined: Makes the lore underlined.
    //@param isItalic: makes the lore italic.
    //@param isObfuscated: Makes the lore obfuscated.
    //@param isStrikethrough: Makes the lore strikethrough.
    //@return the ItemBuilder
    public BattleItemBuilder setLore(List<String> loreText, List<Integer> rgbValues, List<Boolean> isBold, List<Boolean> isUnderlined,
                                     List<Boolean> isItalic, List<Boolean> isObfuscated, List<Boolean> isStrikethrough){

        List<TextComponent> loreList = new ArrayList<>();

        //adding the actual lore to the item
        for (int i = 0; i < loreText.size(); i++){
            loreList.add(loreList.size(),(Component.text(loreText.get(i),
                            TextColor.color(rgbValues.get(i*3), rgbValues.get(i*3+1), rgbValues.get(i*3+2)))
                    .decoration(TextDecoration.BOLD, isBold.get(i))
                    .decoration(TextDecoration.UNDERLINED, isUnderlined.get(i))
                    .decoration(TextDecoration.ITALIC, isItalic.get(i))
                    .decoration(TextDecoration.OBFUSCATED, isObfuscated.get(i))
                    .decoration(TextDecoration.STRIKETHROUGH, isStrikethrough.get(i))));
        }

        //adding the custom item attributes to the item lore
        loreList.add(0, Component.text(""));

        if (this.getStat("speed") != 0){
            //if the speed is a whole number, don't add a decimal point
            if (this.getStat("speed") % 1 == 0){
                loreList.add(0, Component.text("Speed: ", TextColor.color(184, 184, 184))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("speed"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Speed: ", TextColor.color(184, 184, 184))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("speed"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("attackSpeed") != 0){
            //if the attack speed is a whole number, don't add a decimal point
            if (this.getStat("attackSpeed") % 1 == 0){
                loreList.add(0, Component.text("Attack Speed: ", TextColor.color(184, 184, 184))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("attackSpeed"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Attack Speed: ", TextColor.color(184, 184, 184))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("attackSpeed"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("critDamage") != 0){
            //if the crit damage is a whole number, don't add a decimal point
            if (this.getStat("critDamage") % 1 == 0){
                loreList.add(0, Component.text("Crit Damage: ", TextColor.color(184, 184, 184))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("critDamage") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Crit Damage: ", TextColor.color(184, 184, 184))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("critDamage") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("critChance") != 0){
            //if the crit chance is a whole number, don't add a decimal point
            if (this.getStat("critChance") % 1 == 0){
                loreList.add(0, Component.text("Crit Chance: ", TextColor.color(184, 184, 184))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) getStat("critChance") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Crit Chance: ", TextColor.color(184, 184, 184))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("critChance") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("defense") != 0){
            //if the defense is a whole number, don't add a decimal point
            if (this.getStat("defense") % 1 == 0){
                loreList.add(0, Component.text("Defense: ", TextColor.color(184, 184, 184))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("defense"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Defense: ", TextColor.color(184, 184, 184))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("defense"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("health") != 0){
            //if the health is a whole number, don't add a decimal point
            if (this.getStat("health") % 1 == 0){
                loreList.add(0, Component.text("Health: ", TextColor.color(184, 184, 184))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("health"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Health: ", TextColor.color(184, 184, 184))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("health"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("strength") != 0){
            //if the Strength is a whole number, don't add a decimal point
            if (this.getStat("strength") % 1 == 0){
                loreList.add(0, Component.text("Strength: ", TextColor.color(184, 184, 184))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("strength"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Strength: ", TextColor.color(184, 184, 184))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("strength"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("damage") != 0){
            //if the damage is a whole number, don't add a decimal point
            if (this.getStat("damage") % 1 == 0){
                loreList.add(0, Component.text("Damage: ", TextColor.color(184, 184, 184))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("damage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Damage: ", TextColor.color(184, 184, 184))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("damage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        //setting the lore of the item
        super.getItemMeta().lore(loreList);
        return this;
    }

}
