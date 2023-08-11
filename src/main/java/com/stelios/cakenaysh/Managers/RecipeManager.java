package com.stelios.cakenaysh.Managers;

import com.stelios.cakenaysh.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;

public class RecipeManager {

    private final Main main = Main.getPlugin(Main.class);
    private final File file = new File(main.getDataFolder(), "recipes.yml");
    private final YamlConfiguration config = YamlConfiguration.loadConfiguration(file);


    //get the player's list of recipes
    public ArrayList<NamespacedKey> getRecipes(Player player){
        return (ArrayList<NamespacedKey>) config.getList(player.getUniqueId().toString());
    }

    //add a recipe to the player's list
    public void addRecipe(Player player, NamespacedKey recipe){

        //current recipes
        ArrayList<NamespacedKey> recipes = getRecipes(player);
        recipes.add(recipe);

        config.set(player.getUniqueId().toString(), recipes);
        save();
        player.discoverRecipe(recipe);
    }

    //remove a recipe from the player's list
    public void removeRecipe(Player player, NamespacedKey recipe){

        //current recipes
        ArrayList<NamespacedKey> recipes = getRecipes(player);
        recipes.remove(recipe);

        config.set(player.getUniqueId().toString(), recipes);
        save();
        player.undiscoverRecipe(recipe);
    }

    //check if the player has a recipe
    public boolean hasRecipe(Player player, NamespacedKey recipe){
        return getRecipes(player).contains(recipe);
    }

    //save the file
    public void save(){
        try {
            config.save(file);
        } catch (IOException ex) {
            main.getLogger().log(Level.SEVERE, "Could not save recipes.yml");
        }
    }

}
