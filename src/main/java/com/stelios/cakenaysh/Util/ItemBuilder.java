package com.stelios.cakenaysh.Util;

import com.stelios.cakenaysh.Main;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;


    //@param material: The material of the item being built.
    //@param amount: The amount of the item being built.
    public ItemBuilder(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
        this.itemMeta = this.itemStack.getItemMeta();
    }

    //getters
    public ItemStack getItemStack(){return this.itemStack;}
    public ItemMeta getItemMeta(){return this.itemMeta;}

    //updates the itemMeta of the item
    public void updateItemMeta(){
        this.itemStack.setItemMeta(this.itemMeta);
    }

    //sets the display name of the item
    //@param name: The name being set to the item.
    //@param red: The amount of red in the color.
    //@param greed: The amount of green in the color.
    //@param blue: The amount of blue in the color.
    //@param isBold: Makes the name bold.
    //@param isUnderlined: Makes the name underlined.
    //@param isItalic: makes the name italic.
    //@param isObfuscated: Makes the name obfuscated.
    //@param isStrikethrough: Makes the name strikethrough.
    //@return the ItemBuilder
    public ItemBuilder setDisplayName(String name, int red, int green, int blue, Boolean isBold, Boolean isUnderlined, Boolean isItalic,
                                      Boolean isObfuscated, Boolean isStrikethrough){
        this.itemMeta.displayName(Component.text(name, TextColor.color(red, green, blue)).
                decoration(TextDecoration.BOLD, isBold).
                decoration(TextDecoration.UNDERLINED, isUnderlined).
                decoration(TextDecoration.ITALIC, isItalic).
                decoration(TextDecoration.OBFUSCATED, isObfuscated).
                decoration(TextDecoration.STRIKETHROUGH, isStrikethrough));
        return this;
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
    public ItemBuilder setLore(List<String> loreText, List<Integer> rgbValues, List<Boolean> isBold, List<Boolean> isUnderlined, List<Boolean> isItalic, List<Boolean> isObfuscated, List<Boolean> isStrikethrough){

        List<TextComponent> loreList = new ArrayList<> ();

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
        this.itemMeta.lore(loreList);
        return this;
    }

    //removes an itemFlag from the item
    //@param itemFlag: The itemFlag being removed from the item.
    //@return the ItemBuilder
    public ItemBuilder removeFlag(ItemFlag itemFlag){
        this.itemMeta.removeItemFlags(itemFlag);
        return this;
    }

    //controls if the item is unbreakable
    //@param value: Is the item unbreakable?
    //@return the ItemBuilder
    public ItemBuilder setUnbreakable(Boolean value){
        this.itemMeta.setUnbreakable(value);
        return this;
    }


    //makes the item unstackable by giving it a random UUID
    //return the ItemBuilder
    public ItemBuilder makeUnstackable(){
        this.itemStack.getItemMeta().getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class), "UUID"), PersistentDataType.STRING, String.valueOf(UUID.randomUUID()));
        return this;
    }

    //return the created item stack
    public ItemStack build(){
        this.updateItemMeta();
        return this.itemStack;
    }
}
