package com.stelios.cakenaysh.Util;

import com.stelios.cakenaysh.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BattleItemBuilder extends ItemBuilder{

    private float damage;
    private float attackSpeed;
    private float critDamage;
    private float critChance;
    private float strength;
    private float health;
    private float defense;
    private float speed;
    private float thorns;
    private float infernalDefense;
    private float infernalDamage;
    private float undeadDefense;
    private float undeadDamage;
    private float aquaticDefense;
    private float aquaticDamage;
    private float aerialDefense;
    private float aerialDamage;
    private float meleeDefense;
    private float meleeDamage;
    private float rangedDefense;
    private float rangedDamage;
    private float magicDefense;
    private float magicDamage;
    private int meleeProficiency;
    private int rangedProficiency;
    private int armorProficiency;
    private boolean isArmor;



    //@param material: The material of the item being built.
    //@param amount: The amount of the item being built.
    //@param damage: The damage of the item being built.
    //@param attackSpeed: The attack speed of the item being built.
    //@param critDamage: The crit damage of the item being built.
    //@param critChance: The crit chance of the item being built.
    //@param health: The health of the item being built.
    //@param speed: The speed of the item being built.
    //@params defense: The defense of various types for the item being built.
    //@params damage: The damage of various types for item being built.
    //@params meleeProficiency: The melee proficiency of the item being built.
    //@params rangedProficiency: The ranged proficiency of the item being built.
    //@params armorProficiency: The armor proficiency of the item being built.
    //@params isArmor: Whether the item being built is armor.
    public BattleItemBuilder(Material material, int amount, float damage, float attackSpeed, float critDamage,
                             float critChance, float strength, float health, float defense, float speed, float thorns,
                             float infernalDefense, float infernalDamage, float undeadDefense, float undeadDamage,
                             float aquaticDefense, float aquaticDamage, float aerialDefense, float aerialDamage,
                             float meleeDefense, float meleeDamage, float rangedDefense, float rangedDamage, float magicDefense,
                             float magicDamage, int meleeProficiency, int rangedProficiency, int armorProficiency,
                             boolean isArmor){
        super(material, amount);
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.critDamage = critDamage;
        this.critChance = critChance;
        this.strength = strength;
        this.health = health;
        this.defense = defense;
        this.thorns = thorns;
        this.speed = speed;
        this.infernalDefense = infernalDefense;
        this.infernalDamage = infernalDamage;
        this.undeadDefense = undeadDefense;
        this.undeadDamage = undeadDamage;
        this.aquaticDefense = aquaticDefense;
        this.aquaticDamage = aquaticDamage;
        this.aerialDefense = aerialDefense;
        this.aerialDamage = aerialDamage;
        this.meleeDefense = meleeDefense;
        this.meleeDamage = meleeDamage;
        this.rangedDefense = rangedDefense;
        this.rangedDamage = rangedDamage;
        this.magicDefense = magicDefense;
        this.magicDamage = magicDamage;
        this.meleeProficiency = meleeProficiency;
        this.rangedProficiency = rangedProficiency;
        this.armorProficiency = armorProficiency;
        this.isArmor = isArmor;

        //setting the pdc weapon type
        String itemType = "regular";
        if (isArmor){
            itemType = "armor";
        }else{
            implementAttackSpeed();
        }

        //setting pdc values for the item
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "itemType"), PersistentDataType.STRING, itemType);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "damage"), PersistentDataType.FLOAT, damage);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "attackSpeed"), PersistentDataType.FLOAT, attackSpeed);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "critDamage"), PersistentDataType.FLOAT, critDamage);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "critChance"), PersistentDataType.FLOAT, critChance);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "strength"), PersistentDataType.FLOAT, strength);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "health"), PersistentDataType.FLOAT, health);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "defense"), PersistentDataType.FLOAT, defense);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "speed"), PersistentDataType.FLOAT, speed);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "thorns"), PersistentDataType.FLOAT, thorns);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "infernalDefense"), PersistentDataType.FLOAT, infernalDefense);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "infernalDamage"), PersistentDataType.FLOAT, infernalDamage);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "undeadDefense"), PersistentDataType.FLOAT, undeadDefense);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "undeadDamage"), PersistentDataType.FLOAT, undeadDamage);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "aquaticDefense"), PersistentDataType.FLOAT, aquaticDefense);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "aquaticDamage"), PersistentDataType.FLOAT, aquaticDamage);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "aerialDefense"), PersistentDataType.FLOAT, aerialDefense);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "aerialDamage"), PersistentDataType.FLOAT, aerialDamage);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "meleeDefense"), PersistentDataType.FLOAT, meleeDefense);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "meleeDamage"), PersistentDataType.FLOAT, meleeDamage);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "rangedDefense"), PersistentDataType.FLOAT, rangedDefense);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "rangedDamage"), PersistentDataType.FLOAT, rangedDamage);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "magicDefense"), PersistentDataType.FLOAT, magicDefense);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "magicDamage"), PersistentDataType.FLOAT, magicDamage);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "meleeProficiency"), PersistentDataType.INTEGER, meleeProficiency);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "rangedProficiency"), PersistentDataType.INTEGER, rangedProficiency);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "armorProficiency"), PersistentDataType.INTEGER, armorProficiency);

        addItemFlags();
    }

    //@param material: The material of the item being built.
    //@param amount: The amount of the item being built.
    //@param damage: The damage of the item being built.
    //@param attackSpeed: The attack speed of the item being built.
    //@param critDamage: The crit damage of the item being built.
    //@param critChance: The crit chance of the item being built.
    //@param health: The health of the item being built.
    //@param speed: The speed of the item being built.
    public BattleItemBuilder(Material material, int amount, float damage, float attackSpeed, float critDamage,
                             float critChance, float strength, float health, float defense, float speed, float thorns,
                             int meleeProficiency, int rangedProficiency, int armorProficiency, boolean isArmor){
        super(material, amount);
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.critDamage = critDamage;
        this.critChance = critChance;
        this.strength = strength;
        this.health = health;
        this.defense = defense;
        this.speed = speed;
        this.thorns = thorns;
        this.meleeProficiency = meleeProficiency;
        this.rangedProficiency = rangedProficiency;
        this.armorProficiency = armorProficiency;
        this.isArmor = isArmor;

        //setting the pdc weapon type
        String itemType = "regular";
        if (isArmor){
            itemType = "armor";
        }else{
            implementAttackSpeed();
        }

        //setting pdc values for the item
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "itemType"), PersistentDataType.STRING, itemType);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "damage"), PersistentDataType.FLOAT, damage);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "attackSpeed"), PersistentDataType.FLOAT, attackSpeed);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "critDamage"), PersistentDataType.FLOAT, critDamage);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "critChance"), PersistentDataType.FLOAT, critChance);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "strength"), PersistentDataType.FLOAT, strength);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "health"), PersistentDataType.FLOAT, health);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "defense"), PersistentDataType.FLOAT, defense);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "speed"), PersistentDataType.FLOAT, speed);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "thorns"), PersistentDataType.FLOAT, thorns);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "meleeProficiency"), PersistentDataType.INTEGER, meleeProficiency);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "rangedProficiency"), PersistentDataType.INTEGER, rangedProficiency);
        this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class),
                "armorProficiency"), PersistentDataType.INTEGER, armorProficiency);

        addItemFlags();
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
            case "thorns":
                return this.thorns;
            case "infernalDefense":
                return this.infernalDefense;
            case "infernalDamage":
                return this.infernalDamage;
            case "undeadDefense":
                return this.undeadDefense;
            case "undeadDamage":
                return this.undeadDamage;
            case "aquaticDefense":
                return this.aquaticDefense;
            case "aquaticDamage":
                return this.aquaticDamage;
            case "aerialDefense":
                return this.aerialDefense;
            case "aerialDamage":
                return this.aerialDamage;
            case "meleeDefense":
                return this.meleeDefense;
            case "meleeDamage":
                return this.meleeDamage;
            case "rangedDefense":
                return this.rangedDefense;
            case "rangedDamage":
                return this.rangedDamage;
            case "magicDefense":
                return this.magicDefense;
            case "magicDamage":
                return this.magicDamage;
            case "meleeProficiency":
                return this.meleeProficiency;
            case "rangedProficiency":
                return this.rangedProficiency;
            case "armorProficiency":
                return this.armorProficiency;
        }
        return 0;
    }

    //set the attackSpeed attribute modifier depending on the vanilla attackSpeed of the item
    public void implementAttackSpeed(){

        float baseAttackSpeed = 4.0f;
        Material itemType = this.getItemStack().getType();

        //get the base attack speed of the item based on the material
        if (itemType == Material.WOODEN_SWORD || itemType == Material.STONE_SWORD
                || itemType == Material.GOLDEN_SWORD || itemType == Material.IRON_SWORD
                || itemType == Material.DIAMOND_SWORD || itemType == Material.NETHERITE_SWORD){
            baseAttackSpeed = 1.6f;
        }else if (itemType == Material.WOODEN_SHOVEL || itemType == Material.STONE_SHOVEL
                || itemType == Material.GOLDEN_SHOVEL || itemType == Material.IRON_SHOVEL
                || itemType == Material.DIAMOND_SHOVEL || itemType == Material.NETHERITE_SHOVEL){
            baseAttackSpeed = 1.0f;
        }else if (itemType == Material.WOODEN_PICKAXE || itemType == Material.STONE_PICKAXE
                || itemType == Material.GOLDEN_PICKAXE || itemType == Material.IRON_PICKAXE
                || itemType == Material.DIAMOND_PICKAXE || itemType == Material.NETHERITE_PICKAXE){
            baseAttackSpeed = 1.2f;
        }else if (itemType == Material.TRIDENT){
            baseAttackSpeed = 1.1f;
        }else if (itemType == Material.WOODEN_AXE || itemType == Material.STONE_AXE){
            baseAttackSpeed = 0.8f;
        }else if (itemType == Material.IRON_AXE){
            baseAttackSpeed = 0.9f;
        }else if (itemType == Material.GOLDEN_AXE || itemType == Material.DIAMOND_AXE
                || itemType == Material.NETHERITE_AXE || itemType == Material.WOODEN_HOE
                || itemType == Material.GOLDEN_HOE){
            baseAttackSpeed = 1.0f;
        }else if (itemType == Material.STONE_HOE){
            baseAttackSpeed = 2.0f;
        }else if (itemType == Material.IRON_HOE){
            baseAttackSpeed = 3.0f;
        }

        //setting the swing speed in the main hand and the offhand
        AttributeModifier swingSpeedMain = new AttributeModifier(UUID.randomUUID(),"generic.attackSpeed", attackSpeed * 0.1 - baseAttackSpeed,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        this.getItemMeta().addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, swingSpeedMain);

        AttributeModifier swingSpeedOffHand = new AttributeModifier(UUID.randomUUID(),"generic.attackSpeed", attackSpeed * 0.1 - baseAttackSpeed,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        this.getItemMeta().addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, swingSpeedOffHand);
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

        List<TextComponent> wordList = new ArrayList<> ();
        List<TextComponent> loreList = new ArrayList<> ();

        //variable to keep track of how many times the nl command is called
        int nlCalls = 0;

        //adding the lore to the item
        for (int i = 0; i < loreText.size(); i++){

            //go to next line by adding the current line to the loreList and clearing the wordList
            if (loreText.get(i).equals("nl")){
                TextComponent lore = Component.empty();
                for (TextComponent word : wordList){
                    lore = lore.append(word);
                }
                loreList.add(lore);
                wordList.clear();
                nlCalls++;

                //add the current word to the wordList
            }else{
                wordList.add(wordList.size(),(Component.text(loreText.get(i),
                                TextColor.color(rgbValues.get((i-nlCalls)*3), rgbValues.get((i-nlCalls)*3+1), rgbValues.get((i-nlCalls)*3+2)))
                        .decoration(TextDecoration.BOLD, isBold.get(i-nlCalls))
                        .decoration(TextDecoration.UNDERLINED, isUnderlined.get(i-nlCalls))
                        .decoration(TextDecoration.ITALIC, isItalic.get(i-nlCalls))
                        .decoration(TextDecoration.OBFUSCATED, isObfuscated.get(i-nlCalls))
                        .decoration(TextDecoration.STRIKETHROUGH, isStrikethrough.get(i-nlCalls))));
            }
        }

        //add the last line to the loreList
        TextComponent lore = Component.empty();
        for (TextComponent word : wordList){
            lore = lore.append(word);
        }
        loreList.add(lore);

        //adding the custom item attributes to the item lore
        loreList.add(0, Component.text(""));

        if (this.getStat("infernalDefense") != 0){
            //if the infernal defense is a whole number, don't add a decimal point
            if (this.getStat("infernalDefense") % 1 == 0){
                loreList.add(0, Component.text("Infernal Defense: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("infernalDefense") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Infernal Defense: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("infernalDefense") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("infernalDamage") != 0){
            //if the infernal damage is a whole number, don't add a decimal point
            if (this.getStat("infernalDamage") % 1 == 0){
                loreList.add(0, Component.text("Infernal Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("infernalDamage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Infernal Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("infernalDamage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("undeadDefense") != 0){
            //if the undead defense is a whole number, don't add a decimal point
            if (this.getStat("undeadDefense") % 1 == 0){
                loreList.add(0, Component.text("Undead Defense: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("undeadDefense") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Undead Defense: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("undeadDefense") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if(this.getStat("undeadDamage") != 0){
            //if the undead damage is a whole number, don't add a decimal point
            if (this.getStat("undeadDamage") % 1 == 0){
                loreList.add(0, Component.text("Undead Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("undeadDamage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Undead Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("undeadDamage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("aquaticDefense") != 0){
            //if the aquatic defense is a whole number, don't add a decimal point
            if (this.getStat("aquaticDefense") % 1 == 0){
                loreList.add(0, Component.text("Aquatic Defense: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("aquaticDefense") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Aquatic Defense: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("aquaticDefense") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("aquaticDamage") != 0){
            //if the aquatic damage is a whole number, don't add a decimal point
            if (this.getStat("aquaticDamage") % 1 == 0){
                loreList.add(0, Component.text("Aquatic Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("aquaticDamage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Aquatic Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("aquaticDamage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("aerialDefense") != 0){
            //if the arial defense is a whole number, don't add a decimal point
            if (this.getStat("aerialDefense") % 1 == 0){
                loreList.add(0, Component.text("Aerial Defense: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("aerialDefense") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Aerial Defense: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("aerialDefense") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("aerialDamage") != 0){
            //if the aerial damage is a whole number, don't add a decimal point
            if (this.getStat("aerialDamage") % 1 == 0){
                loreList.add(0, Component.text("Aerial Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("aerialDamage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Aerial Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("aerialDamage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("meleeDefense") != 0){
            //if the melee defense is a whole number, don't add a decimal point
            if (this.getStat("meleeDefense") % 1 == 0){
                loreList.add(0, Component.text("Melee Defense: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("meleeDefense") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Melee Defense: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("meleeDefense") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("meleeDamage") != 0){
            //if the melee damage is a whole number, don't add a decimal point
            if (this.getStat("meleeDamage") % 1 == 0){
                loreList.add(0, Component.text("Melee Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("meleeDamage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Melee Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("meleeDamage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("rangedDefense") != 0){
            //if the ranged defense is a whole number, don't add a decimal point
            if (this.getStat("rangedDefense") % 1 == 0){
                loreList.add(0, Component.text("Ranged Defense: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("rangedDefense") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Ranged Defense: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("rangedDefense") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("rangedDamage") != 0){
            //if the ranged damage is a whole number, don't add a decimal point
            if (this.getStat("rangedDamage") % 1 == 0){
                loreList.add(0, Component.text("Ranged Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("rangedDamage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Ranged Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("rangedDamage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("magicDefense") != 0){
            //if the magic defense is a whole number, don't add a decimal point
            if (this.getStat("magicDefense") % 1 == 0){
                loreList.add(0, Component.text("Magic Defense: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("magicDefense") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Magic Defense: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("magicDefense") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("magicDamage") != 0){
            //if the magic damage is a whole number, don't add a decimal point
            if (this.getStat("magicDamage") % 1 == 0){
                loreList.add(0, Component.text("Magic Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("magicDamage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Magic Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("magicDamage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("thorns") != 0){
            //if the thorns is a whole number, don't add a decimal point
            if (this.getStat("thorns") % 1 == 0){
                loreList.add(0, Component.text("Thorns: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("thorns"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Thorns: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("thorns"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("speed") != 0){
            //if the speed is a whole number, don't add a decimal point
            if (this.getStat("speed") % 1 == 0){
                loreList.add(0, Component.text("Speed: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("speed"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Speed: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("speed"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("attackSpeed") != 0){
            //if the attack speed is a whole number, don't add a decimal point
            if (this.getStat("attackSpeed") % 1 == 0){
                loreList.add(0, Component.text("Attack Speed: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("attackSpeed"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Attack Speed: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("attackSpeed"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("critDamage") != 0){
            //if the crit damage is a whole number, don't add a decimal point
            if (this.getStat("critDamage") % 1 == 0){
                loreList.add(0, Component.text("Crit Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("critDamage") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Crit Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("critDamage") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("critChance") != 0){
            //if the crit chance is a whole number, don't add a decimal point
            if (this.getStat("critChance") % 1 == 0){
                loreList.add(0, Component.text("Crit Chance: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) getStat("critChance") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Crit Chance: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("critChance") + "%", TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("defense") != 0){
            //if the defense is a whole number, don't add a decimal point
            if (this.getStat("defense") % 1 == 0){
                loreList.add(0, Component.text("Defense: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("defense"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Defense: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("defense"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("health") != 0){
            //if the health is a whole number, don't add a decimal point
            if (this.getStat("health") % 1 == 0){
                loreList.add(0, Component.text("Health: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("health"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Health: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("health"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("strength") != 0){
            //if the Strength is a whole number, don't add a decimal point
            if (this.getStat("strength") % 1 == 0){
                loreList.add(0, Component.text("Strength: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("strength"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Strength: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("strength"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("damage") != 0){
            //if the damage is a whole number, don't add a decimal point
            if (this.getStat("damage") % 1 == 0){
                loreList.add(0, Component.text("Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + (int) this.getStat("damage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }else{
                loreList.add(0, Component.text("Damage: ", TextColor.color(200, 200, 200))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text("+" + this.getStat("damage"), TextColor.color(240, 40, 50)))
                        .decoration(TextDecoration.ITALIC, false));
            }
        }

        if (this.getStat("armorProficiency") != 0){
            loreList.add(0, Component.text("Armor Proficiency: ", TextColor.color(200, 200, 200))
                    .decoration(TextDecoration.ITALIC, false)
                    .append(Component.text((int) this.getStat("armorProficiency"), TextColor.color(240, 40, 50)))
                    .decoration(TextDecoration.ITALIC, false));
        }

        if (this.getStat("rangedProficiency") != 0){
            loreList.add(0, Component.text("Ranged Proficiency: ", TextColor.color(200, 200, 200))
                    .decoration(TextDecoration.ITALIC, false)
                    .append(Component.text((int) this.getStat("rangedProficiency"), TextColor.color(240, 40, 50)))
                    .decoration(TextDecoration.ITALIC, false));
        }

        if (this.getStat("meleeProficiency") != 0){
            loreList.add(0, Component.text("Melee Proficiency: ", TextColor.color(200, 200, 200))
                    .decoration(TextDecoration.ITALIC, false)
                    .append(Component.text((int) this.getStat("meleeProficiency"), TextColor.color(240, 40, 50)))
                    .decoration(TextDecoration.ITALIC, false));
        }

        //setting the lore of the item
        super.getItemMeta().lore(loreList);
        return this;
    }

}
