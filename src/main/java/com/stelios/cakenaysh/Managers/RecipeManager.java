package com.stelios.cakenaysh.Managers;

import com.stelios.cakenaysh.Items.Recipes;
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
    public ArrayList<String> getRecipes(Player player){

        ArrayList<String> recipes = (ArrayList<String>) config.getList(player.getUniqueId().toString());

        if (recipes == null){
            recipes = new ArrayList<>();
        }

        return recipes;
    }

    //add a recipe to the player's list
    public void addRecipe(Player player, String recipe){

        //current recipes
        ArrayList<String> recipes = getRecipes(player);
        recipes.add(recipe);

        config.set(player.getUniqueId().toString(), recipes);
        save();
        player.discoverRecipe(new NamespacedKey(main, recipe));
    }

    //remove a recipe from the player's list
    public void removeRecipe(Player player, String recipe){

        //current recipes
        ArrayList<String> recipes = getRecipes(player);
        recipes.remove(recipe);

        config.set(player.getUniqueId().toString(), recipes);
        save();
        player.undiscoverRecipe(new NamespacedKey(main, recipe));
    }

    //check if the player has a recipe
    public boolean hasRecipe(Player player, String recipe){
        return getRecipes(player).contains(recipe);
    }

    //add all recipes to the player
    public void addAllRecipes(Player player){

        for (Recipes recipe : Recipes.values()) {
            if (!hasRecipe(player, recipe.getKey().getKey())) {
                addRecipe(player, recipe.getKey().getKey());
            }
        }
    }

    //reset all recipes from the player
    public void resetRecipes(Player player){
        for (Recipes recipe : Recipes.values()) {
            if (hasRecipe(player, recipe.getKey().getKey())) {
                removeRecipe(player, recipe.getKey().getKey());
            }
        }
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
