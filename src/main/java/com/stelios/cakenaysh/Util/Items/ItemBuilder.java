package com.stelios.cakenaysh.Util.Items;

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
    private final boolean unstackable;


    //@param material: The material of the item being built.
    //@param amount: The amount of the item being built.
    public ItemBuilder(Material material, int amount, boolean unstackable) {
        this.itemStack = new ItemStack(material, amount);
        this.itemMeta = this.itemStack.getItemMeta();
        this.unstackable = unstackable;

        this.itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class), "itemType"),
                PersistentDataType.STRING, "regularItem");

        addItemFlags();

        //if the item is unstackable, add a unique identifier to the item
        if (unstackable){
            this.itemMeta.getPersistentDataContainer().set(new NamespacedKey(Main.getPlugin(Main.class), "uniqueID"),
                    PersistentDataType.STRING, UUID.randomUUID().toString());
        }
    }

    //getters
    public ItemStack getItemStack(){return this.itemStack;}
    public ItemMeta getItemMeta(){return this.itemMeta;}
    public boolean getUnstackable(){return this.unstackable;}

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
    public ItemBuilder setDisplayName(List<String> nameText, List<Integer> rgbValues, List<Boolean> isBold,
                                      List<Boolean> isUnderlined, List<Boolean> isItalic, List<Boolean> isObfuscated,
                                      List<Boolean> isStrikethrough){

        //list to store every word of the item name
        List<TextComponent> nameList = new ArrayList<> ();

        //loop through the nameText, apply the stated colors/decorations, and add each word to the nameList
        for (int i = 0; i < nameText.size(); i++){
            nameList.add(nameList.size(),(Component.text(nameText.get(i),
                            TextColor.color(rgbValues.get(i*3), rgbValues.get(i*3+1), rgbValues.get(i*3+2)))
                    .decoration(TextDecoration.BOLD, isBold.get(i))
                    .decoration(TextDecoration.UNDERLINED, isUnderlined.get(i))
                    .decoration(TextDecoration.ITALIC, isItalic.get(i))
                    .decoration(TextDecoration.OBFUSCATED, isObfuscated.get(i))
                    .decoration(TextDecoration.STRIKETHROUGH, isStrikethrough.get(i))));
        }

        //loop through the nameList and concatenate each word to one textcomponent
        TextComponent displayName = Component.empty();
        for (TextComponent word : nameList){
            displayName = displayName.append(word);
        }

        this.itemMeta.displayName(displayName);
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
    public ItemBuilder setLore(List<String> loreText, List<Integer> rgbValues, List<Boolean> isBold,
                               List<Boolean> isUnderlined, List<Boolean> isItalic, List<Boolean> isObfuscated,
                               List<Boolean> isStrikethrough){

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

        //add the last line to the loreList
        this.itemMeta.lore(loreList);
        return this;
    }

    //removes an itemFlag from the item
    //@param itemFlag: The itemFlag being removed from the item.
    //@return the ItemBuilder
    public void addItemFlags(){
        this.itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    }

    //controls if the item is unbreakable
    //@param value: Is the item unbreakable?
    //@return the ItemBuilder
    public ItemBuilder setUnbreakable(){
        this.itemMeta.setUnbreakable(true);
        return this;
    }

    //return the created item stack
    public ItemStack build(){
        this.updateItemMeta();
        return this.itemStack;
    }
}
