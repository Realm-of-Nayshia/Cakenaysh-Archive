package com.stelios.cakenaysh.Util.Npc.Traits;

import com.stelios.cakenaysh.Main;
import net.citizensnpcs.api.persistence.Persist;
import net.citizensnpcs.api.trait.Trait;

public class TestTrait extends Trait {
    public TestTrait() {
        super("testtrait");
        main = Main.getPlugin(Main.class);
    }

    Main main;

    @Persist("meleeResistance") float meleeResistance = 0.0f;
    @Persist("rangedResistance") float rangedResistance = 0.0f;






}
