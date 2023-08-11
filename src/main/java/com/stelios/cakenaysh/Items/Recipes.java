package com.stelios.cakenaysh.Items;

import com.stelios.cakenaysh.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;

public enum Recipes {

    GRUBULOUSLY_GRUBBY_GRUSTARD(new ShapedRecipe(new NamespacedKey(Main.getPlugin(Main.class),
            "grubulously_grubby_grustard"), CustomItems.GRUBULOUSLY_GRUBBY_GRUSTARD.getItem().build())
            .shape("GGG",
                    "G G",
                    "GGG")
            .setIngredient('G', CustomItems.GRUBBY_GUSTARD.getItem().build())),

    ;

    private final ShapedRecipe recipe;

    Recipes(ShapedRecipe recipe) {
        this.recipe = recipe;
    }

    public ShapedRecipe getRecipe() {
        return recipe;
    }


}
