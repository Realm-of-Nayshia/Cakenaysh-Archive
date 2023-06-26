package com.stelios.cakenaysh;

import com.stelios.cakenaysh.Commands.*;
import com.stelios.cakenaysh.Commands.TabComplete.*;
import com.stelios.cakenaysh.Listeners.ConnectionListener;
import com.stelios.cakenaysh.Listeners.PlayerInteractListener;
import com.stelios.cakenaysh.Listeners.ServerListPingListener;
import com.stelios.cakenaysh.Util.Abilities.DialOfTheSunAbility;
import com.stelios.cakenaysh.Util.Abilities.TestAbility;
import com.stelios.cakenaysh.Util.CustomAbilities;
import com.stelios.cakenaysh.Util.CustomItems;
import com.stelios.cakenaysh.Util.Database;
import com.stelios.cakenaysh.Util.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class Main extends JavaPlugin {

    private Database database;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {

        ////plugin startup logic
        //database setup
        database = new Database();
        try {
            database.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //player manager setup
        playerManager = new PlayerManager();

        //registering important plugin info
        registerEvents();
        registerCommands();
        registerAbilities();

    }

    //registering events
    private void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), this);
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ServerListPingListener(), this);
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
    }

    //registering abilities
    private void registerAbilities(){
        new TestAbility(CustomAbilities.SPARTAN_WRATH, CustomItems.WRATH_OF_SPARTA.getItemBuilder(), 2, 5);
        new DialOfTheSunAbility(CustomAbilities.GRADUAL_SET_DAY, CustomItems.DIAL_OF_THE_SUN.getItemBuilder(), 1, 15);
    }

    ////getters
    //gets and returns the database
    public Database getDatabase() {return database;}

    //gets and returns the player manager
    public PlayerManager getPlayerManager() {return playerManager;}



    @Override
    public void onDisable() {
        // Plugin shutdown logic

        database.disconnect();

    }

}
