package com.stelios.cakenaysh.Items;

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
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BattleItem extends Item {

    private float damage;
    private float attackSpeed;
    private float critDamage;
    private float critChance;
    private float strength;
    private float health;
    private float healthRegen;
    private float stamina;
    private float staminaRegen;
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
    private final int meleeProficiency;
    private final int rangedProficiency;
    private final int armorProficiency;



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
    public BattleItem(Material material, int amount, boolean unstackable, float damage, float attackSpeed, float critDamage, float critChance,
                      float strength, float health, float healthRegen, float stamina, float staminaRegen, float defense, float speed, float thorns,
                      float infernalDefense, float infernalDamage, float undeadDefense, float undeadDamage,
                      float aquaticDefense, float aquaticDamage, float aerialDefense, float aerialDamage,
                      float meleeDefense, float meleeDamage, float rangedDefense, float rangedDamage, float magicDefense,
                      float magicDamage, int meleeProficiency, int rangedProficiency, int armorProficiency,
                      boolean isArmor){
        super(material, amount, unstackable);
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.critDamage = critDamage;
        this.critChance = critChance;
        this.strength = strength;
        this.health = health;
        this.healthRegen = healthRegen;
        this.stamina = stamina;
        this.staminaRegen = staminaRegen;
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

        //if the item is unstackable, add a unique identifier to the item
        if (unstackable){
            this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class), "uniqueID"),
                    PersistentDataType.STRING, UUID.randomUUID().toString());
        }

        //setting the pdc weapon type
        String itemType = "regular";
        if (isArmor){
            itemType = "armor";
        }else{
            implementAttackSpeed();
        }

        //setting pdc values for the item
        PersistentDataContainer pdc = this.getItemMeta().getPersistentDataContainer();
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "itemType"), PersistentDataType.STRING, itemType);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "damage"), PersistentDataType.FLOAT, damage);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "attackSpeed"), PersistentDataType.FLOAT, attackSpeed);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "critDamage"), PersistentDataType.FLOAT, critDamage);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "critChance"), PersistentDataType.FLOAT, critChance);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "strength"), PersistentDataType.FLOAT, strength);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "health"), PersistentDataType.FLOAT, health);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "healthRegen"), PersistentDataType.FLOAT, healthRegen);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "stamina"), PersistentDataType.FLOAT, stamina);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "staminaRegen"), PersistentDataType.FLOAT, staminaRegen);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "defense"), PersistentDataType.FLOAT, defense);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "speed"), PersistentDataType.FLOAT, speed);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "thorns"), PersistentDataType.FLOAT, thorns);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "infernalDefense"), PersistentDataType.FLOAT, infernalDefense);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "infernalDamage"), PersistentDataType.FLOAT, infernalDamage);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "undeadDefense"), PersistentDataType.FLOAT, undeadDefense);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "undeadDamage"), PersistentDataType.FLOAT, undeadDamage);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "aquaticDefense"), PersistentDataType.FLOAT, aquaticDefense);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "aquaticDamage"), PersistentDataType.FLOAT, aquaticDamage);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "aerialDefense"), PersistentDataType.FLOAT, aerialDefense);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "aerialDamage"), PersistentDataType.FLOAT, aerialDamage);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "meleeDefense"), PersistentDataType.FLOAT, meleeDefense);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "meleeDamage"), PersistentDataType.FLOAT, meleeDamage);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "rangedDefense"), PersistentDataType.FLOAT, rangedDefense);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "rangedDamage"), PersistentDataType.FLOAT, rangedDamage);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "magicDefense"), PersistentDataType.FLOAT, magicDefense);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "magicDamage"), PersistentDataType.FLOAT, magicDamage);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "meleeProficiency"), PersistentDataType.INTEGER, meleeProficiency);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "rangedProficiency"), PersistentDataType.INTEGER, rangedProficiency);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "armorProficiency"), PersistentDataType.INTEGER, armorProficiency);

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
    public BattleItem(Material material, int amount, boolean unstackable, float damage, float attackSpeed, float critDamage,
                      float critChance, float strength, float health, float healthRegen, float stamina, float staminaRegen,
                      float defense, float speed, float thorns, int meleeProficiency, int rangedProficiency, int armorProficiency,
                      boolean isArmor){
        super(material, amount, unstackable);
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.critDamage = critDamage;
        this.critChance = critChance;
        this.strength = strength;
        this.health = health;
        this.healthRegen = healthRegen;
        this.stamina = stamina;
        this.staminaRegen = staminaRegen;
        this.defense = defense;
        this.speed = speed;
        this.thorns = thorns;
        this.meleeProficiency = meleeProficiency;
        this.rangedProficiency = rangedProficiency;
        this.armorProficiency = armorProficiency;

        //if the item is unstackable, add a unique identifier to the item
        if (unstackable){
            this.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class), "uniqueID"),
                    PersistentDataType.STRING, UUID.randomUUID().toString());
        }

        //setting the pdc weapon type
        String itemType = "regular";
        if (isArmor){
            itemType = "armor";
        }else{
            implementAttackSpeed();
        }

        //setting pdc values for the item
        PersistentDataContainer pdc = this.getItemMeta().getPersistentDataContainer();
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "itemType"), PersistentDataType.STRING, itemType);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "damage"), PersistentDataType.FLOAT, damage);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "attackSpeed"), PersistentDataType.FLOAT, attackSpeed);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "critDamage"), PersistentDataType.FLOAT, critDamage);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "critChance"), PersistentDataType.FLOAT, critChance);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "strength"), PersistentDataType.FLOAT, strength);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "health"), PersistentDataType.FLOAT, health);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "healthRegen"), PersistentDataType.FLOAT, healthRegen);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "stamina"), PersistentDataType.FLOAT, stamina);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "staminaRegen"), PersistentDataType.FLOAT, staminaRegen);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "defense"), PersistentDataType.FLOAT, defense);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "speed"), PersistentDataType.FLOAT, speed);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "thorns"), PersistentDataType.FLOAT, thorns);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "meleeProficiency"), PersistentDataType.INTEGER, meleeProficiency);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "rangedProficiency"), PersistentDataType.INTEGER, rangedProficiency);
        pdc.set(new NamespacedKey(Main.getPlugin(Main.class), "armorProficiency"), PersistentDataType.INTEGER, armorProficiency);

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
            case "healthRegen":
                return this.healthRegen;
            case "stamina":
                return this.stamina;
            case "staminaRegen":
                return this.staminaRegen;
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

        //setting the swing speed in the main hand
        AttributeModifier swingSpeedMain = new AttributeModifier(UUID.randomUUID(),"generic.attackSpeed", attackSpeed * 0.1 - baseAttackSpeed,
                AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        this.getItemMeta().addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, swingSpeedMain);
    }



    //sets the lore of the item
    //@param loreText: The text of the lore being set to the item.
    //@param rgbValues: The rgb values of the lore being set to the item.
    //@param isBold: Makes the lore bold.
    //@param isUnderlined: Makes the lore underlined.
    //@param isItalic: makes the lore italic.
    //@param isObfuscated: Makes the lore obfuscated.
    //@param isStrikethrough: Makes the lore strikethrough.
    //@return the Item
    public BattleItem setLore(List<String> loreText, List<Integer> rgbValues, List<Boolean> isBold, List<Boolean> isUnderlined,
                              List<Boolean> isItalic, List<Boolean> isObfuscated, List<Boolean> isStrikethrough) {

        List<TextComponent> wordList = new ArrayList<>();
        List<TextComponent> loreList = new ArrayList<>();

        //variable to keep track of how many times the nl command is called
        int nlCalls = 0;

        //adding the lore to the item
        for (int i = 0; i < loreText.size(); i++) {

            //go to next line by adding the current line to the loreList and clearing the wordList
            if (loreText.get(i).equals("nl")) {
                TextComponent lore = Component.empty();
                for (TextComponent word : wordList) {
                    lore = lore.append(word);
                }
                loreList.add(lore);
                wordList.clear();
                nlCalls++;

                //add the current word to the wordList
            } else {
                wordList.add(wordList.size(), (Component.text(loreText.get(i),
                                TextColor.color(rgbValues.get((i - nlCalls) * 3), rgbValues.get((i - nlCalls) * 3 + 1), rgbValues.get((i - nlCalls) * 3 + 2)))
                        .decoration(TextDecoration.BOLD, isBold.get(i - nlCalls))
                        .decoration(TextDecoration.UNDERLINED, isUnderlined.get(i - nlCalls))
                        .decoration(TextDecoration.ITALIC, isItalic.get(i - nlCalls))
                        .decoration(TextDecoration.OBFUSCATED, isObfuscated.get(i - nlCalls))
                        .decoration(TextDecoration.STRIKETHROUGH, isStrikethrough.get(i - nlCalls))));
            }
        }

        //add the last line to the loreList
        TextComponent lore = Component.empty();
        for (TextComponent word : wordList) {
            lore = lore.append(word);
        }
        loreList.add(lore);

        //adding the custom item attributes to the item lore
        loreList.add(0, Component.text(""));
        makeLoreLine(loreList, "Infernal Defense: ", this.getStat("infernalDefense"), true, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Infernal Damage: ", this.getStat("infernalDamage"), false, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Undead Defense: ", this.getStat("undeadDefense"), true, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Undead Damage: ", this.getStat("undeadDamage"), false, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Aquatic Defense: ", this.getStat("aquaticDefense"), true, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Aquatic Damage: ", this.getStat("aquaticDamage"), false, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Aerial Defense: ", this.getStat("aerialDefense"), true, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Aerial Damage: ", this.getStat("aerialDamage"), false, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Melee Defense: ", this.getStat("meleeDefense"), true, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Melee Damage: ", this.getStat("meleeDamage"), false, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Ranged Defense: ", this.getStat("rangedDefense"), true, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Ranged Damage: ", this.getStat("rangedDamage"), false, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Magic Defense: ", this.getStat("magicDefense"), true, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Magic Damage: ", this.getStat("magicDamage"), false, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Thorns: ", this.getStat("thorns"), false, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Speed: ", this.getStat("speed"), true, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Attack Speed: ", this.getStat("attackSpeed"), false, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Crit Damage: ", this.getStat("critDamage"), true, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Crit Chance: ", this.getStat("critChance"), true, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Defense: ", this.getStat("defense"), false, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Stamina Regen: ", this.getStat("staminaRegen"), false, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Stamina: ", this.getStat("stamina"), false, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Health Regen: ", this.getStat("healthRegen"), false, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Health: ", this.getStat("health"), false, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Strength: ", this.getStat("strength"), false, false, 240, 40, 50, 200, 200, 200);
        makeLoreLine(loreList, "Damage: ", this.getStat("damage"), false, false, 240, 40, 50, 200, 200, 200);

        if (this.getStat("meleeProficiency") != 0 || this.getStat("rangedProficiency") != 0 || this.getStat("armorProficiency") != 0){
            loreList.add(0, Component.text(""));
        }

        makeLoreLine(loreList, "Melee Proficiency: ", this.getStat("armorProficiency"), false, true, 77,85,92, 200, 200, 200);
        makeLoreLine(loreList, "Ranged Proficiency: ", this.getStat("rangedProficiency"), false, true, 240, 185, 85, 200, 200, 200);
        makeLoreLine(loreList, "Armor Proficiency: ", this.getStat("meleeProficiency"), false, true, 214,88,88, 200, 200, 200);

        //setting the lore of the item
        super.getItemMeta().lore(loreList);
        return this;
    }

    //makes a lore line
    //@param loreList: The list of lore lines
    //@param statName: The name of the stat
    //@param spacedStatName: The name of the stat with spaces
    //@param isPercent: If the stat needs a percent sign
    //@param red,green,blue(number): the rgb color of the number itself
    //@param red,green,blue(stat): the rgb color of the stat itself
    public void makeLoreLine(List<TextComponent> loreList, String spacedStatName, float statValue, boolean isPercent, boolean isProficiency,
                             int redNumber, int greenNumber, int blueNumber, int redStat, int greenStat, int blueStat) {

        //if the stat value is zero do nothing
        if (statValue != 0) {

            //create the to be returned text component
            TextComponent loreLine;

            //set the sign
            String sign = "";
            if (statValue > 0) {
                sign = "+";
            }

            //if a stat is a proficiency, remove the + or - sign
            if (!isProficiency) {

                //if the stat needs a percent sign
                if (isPercent) {

                    //if the stat is a whole number, don't add a decimal point
                    if (statValue % 1 == 0) {
                        loreLine = Component.text(spacedStatName, TextColor.color(redStat, greenStat, blueStat))
                                .decoration(TextDecoration.ITALIC, false)
                                .append(Component.text(sign + (int) statValue + "%", TextColor.color(redNumber, greenNumber, blueNumber)))
                                .decoration(TextDecoration.ITALIC, false);
                    } else {
                        loreLine = Component.text(spacedStatName, TextColor.color(redStat, greenStat, blueStat))
                                .decoration(TextDecoration.ITALIC, false)
                                .append(Component.text(sign + statValue + "%", TextColor.color(redNumber, greenNumber, blueNumber)))
                                .decoration(TextDecoration.ITALIC, false);
                    }
                } else {

                    //if the stat is a whole number, don't add a decimal point
                    if (statValue % 1 == 0) {
                        loreLine = Component.text(spacedStatName, TextColor.color(redStat, greenStat, blueStat))
                                .decoration(TextDecoration.ITALIC, false)
                                .append(Component.text(sign + (int) statValue, TextColor.color(redNumber, greenNumber, blueNumber)))
                                .decoration(TextDecoration.ITALIC, false);
                    } else {
                        loreLine = Component.text(spacedStatName, TextColor.color(redStat, greenStat, blueStat))
                                .decoration(TextDecoration.ITALIC, false)
                                .append(Component.text(sign + statValue, TextColor.color(redNumber, greenNumber, blueNumber)))
                                .decoration(TextDecoration.ITALIC, false);
                    }
                }
            }else{
                loreLine = Component.text(spacedStatName, TextColor.color(redStat, greenStat, blueStat))
                        .decoration(TextDecoration.ITALIC, false)
                        .append(Component.text((int) statValue, TextColor.color(redNumber, greenNumber, blueNumber)))
                        .decoration(TextDecoration.ITALIC, false);
            }

            loreList.add(0, loreLine);
        }
    }

}
