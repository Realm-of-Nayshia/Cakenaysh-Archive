package com.stelios.cakenaysh;

import com.stelios.cakenaysh.Commands.*;
import com.stelios.cakenaysh.Commands.TabComplete.*;
import com.stelios.cakenaysh.Listeners.ConnectionListener;
import com.stelios.cakenaysh.Listeners.MenuListener;
import com.stelios.cakenaysh.Util.Managers.LevelManager;
import com.stelios.cakenaysh.Listeners.PlayerInteractListener;
import com.stelios.cakenaysh.Listeners.ServerListPingListener;
import com.stelios.cakenaysh.Util.*;
import com.stelios.cakenaysh.Util.Abilities.DialOfTheSunAbility;
import com.stelios.cakenaysh.Util.Abilities.WrathOfSpartaAbility;
import com.stelios.cakenaysh.Util.Managers.PlayerManager;
import com.stelios.cakenaysh.Util.Managers.StatsManager;
import com.stelios.cakenaysh.Util.Npc.Traits.NpcStats;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.logging.Level;

public final class Main extends JavaPlugin {

    private Database database;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {

        //check if Citizens is present and enabled.
        if(getServer().getPluginManager().getPlugin("Citizens") == null || !getServer().getPluginManager().getPlugin("Citizens").isEnabled()) {
            getLogger().log(Level.SEVERE, "Citizens 2.0 not found or not enabled");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        ////plugin startup logic
        //database setup
        database = new Database();
        try {
            database.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //managers setup
        playerManager = new PlayerManager();

        //registering important plugin info
        registerEvents();
        registerCommands();
        registerAbilities();
        registerTraits();

    }

    //registering events
    private void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ServerListPingListener(), this);
        Bukkit.getPluginManager().registerEvents(new StatsManager(this), this);
        Bukkit.getPluginManager().registerEvents(new LevelManager(this), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
    }

    //registering commands
    @SuppressWarnings("DataFlowIssue")
    private void registerCommands(){
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("vanish").setExecutor(new VanishCommand());

        getCommand("giveitem").setExecutor(new GiveItemCommand());
        getCommand("giveitem").setTabCompleter(new GiveItemTabComplete());

        getCommand("setattribute").setExecutor(new SetAttributesCommand());
        getCommand("setattribute").setTabCompleter(new SetAttributesTabComplete());

        getCommand("addattribute").setExecutor(new AddAttributesCommand());
        getCommand("addattribute").setTabCompleter(new AddAttributesTabComplete());

        getCommand("getattributes").setExecutor(new GetAttributesCommand());
        getCommand("getattributes").setTabCompleter(new GetAttributesTabComplete());

        getCommand("resetattributes").setExecutor(new ResetAttributesCommand());
        getCommand("resetattributes").setTabCompleter(new ResetAttributesTabComplete());

        getCommand("resetnpcstats").setExecutor(new ResetNpcStatsCommand());
        getCommand("getnpcstats").setExecutor(new GetNpcStatsCommand());
        getCommand("setnpcstat").setExecutor(new SetNpcStatCommand());
        getCommand("setnpcstat").setTabCompleter(new SetNpcStatTabComplete());

        getCommand("menu").setExecutor(new MenuCommand());
    }

    //registering abilities
    private void registerAbilities(){
        new WrathOfSpartaAbility(CustomAbilities.SPARTAN_WRATH, CustomItems.WRATH_OF_SPARTA.getItemBuilder(), 5, 5);
        new DialOfTheSunAbility(CustomAbilities.GRADUAL_SET_DAY, CustomItems.DIAL_OF_THE_SUN.getItemBuilder(), 25, 15);
    }

    //registering traits
    private void registerTraits(){
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(NpcStats.class).withName("npcstats"));
    }

    ////getters
    //gets and returns the database
    public Database getDatabase() {return database;}

    //returns the player manager
    public PlayerManager getPlayerManager() {return playerManager;}



    @Override
    public void onDisable() {
        // Plugin shutdown logic

        database.disconnect();

    }

}
