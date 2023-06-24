package com.stelios.cakenaysh.Util;

import com.stelios.cakenaysh.Listeners.ItemAbility;
import com.stelios.cakenaysh.Util.Abilities.TestAbility;

import java.util.ArrayList;

public enum CustomAbilities {


    TEST_ABILITY(new TestAbility(CustomItems.getItem("WRATH_OF_SPARTA"), 10));


    private ItemAbility itemAbility;

    CustomAbilities(ItemAbility itemAbility){
        this.itemAbility = itemAbility;
    }

    //gets an ability from the enum
    //@param name: The name of the ability being picked.
    //@return the ItemAbility
    public static ItemAbility getItem(String name){
        for (CustomAbilities ability : CustomAbilities.values()){
            if (ability.name().equalsIgnoreCase(name)){
                return ability.itemAbility;
            }
        }
        return null;
    }

    //returns all the names of abilities in the enum
    //@return ArrayList<String> of all the names
    public static ArrayList<String> getNames(){
        ArrayList<String> names = new ArrayList<>();
        for (CustomAbilities ability : CustomAbilities.values()){
            names.add(ability.name());
        }
        return names;
    }

}
