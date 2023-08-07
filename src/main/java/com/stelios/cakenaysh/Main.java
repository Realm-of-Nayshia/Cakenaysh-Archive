package com.stelios.cakenaysh;

import com.stelios.cakenaysh.Commands.*;
import com.stelios.cakenaysh.Commands.TabComplete.*;
import com.stelios.cakenaysh.Listeners.Entity.EntityDamagedListener;
import com.stelios.cakenaysh.Listeners.Entity.PlayerInteractListener;
import com.stelios.cakenaysh.Listeners.Entity.PlayerStatusChangeListener;
import com.stelios.cakenaysh.Listeners.Entity.SentinelDeathListener;
import com.stelios.cakenaysh.Listeners.Inventory.InventoryAlteredListener;
import com.stelios.cakenaysh.Listeners.Server.ConnectionListener;
import com.stelios.cakenaysh.Listeners.Server.ServerListPingListener;
import com.stelios.cakenaysh.Listeners.Server.ServerStartupListener;
import com.stelios.cakenaysh.Listeners.Stats.ProficiencyChangedListener;
import com.stelios.cakenaysh.Listeners.Stats.SpeedChangedListener;
import com.stelios.cakenaysh.Listeners.Stats.XpGainListener;
import com.stelios.cakenaysh.Managers.CombatManager;
import com.stelios.cakenaysh.Managers.StashManager;
import com.stelios.cakenaysh.MenuCreation.MenuListener;
import com.stelios.cakenaysh.AbilityCreation.CustomAbilities;
import com.stelios.cakenaysh.Items.CustomItems;
import com.stelios.cakenaysh.Util.*;
import com.stelios.cakenaysh.AbilityCreation.Abilities.DialOfTheSunAbility;
import com.stelios.cakenaysh.AbilityCreation.Abilities.WrathOfSpartaAbility;
import com.stelios.cakenaysh.Managers.PlayerManager;
import com.stelios.cakenaysh.Managers.StatsManager;
import com.stelios.cakenaysh.Npc.Traits.NpcStats;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;
import java.util.logging.Level;

public final class Main extends JavaPlugin {

    private Database database;
    private PlayerManager playerManager;
    private StashManager stashManager;
    private CombatManager combatManager;
    private StatsManager statsManager;

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

        //setup of important plugin info
        registerManagers();
        registerEvents();
        registerCommands();
        registerAbilities();
        registerTraits();
    }



    public void registerManagers(){
        playerManager = new PlayerManager();
        stashManager = new StashManager(this);
        combatManager = new CombatManager();
        statsManager = new StatsManager(this);
    }

    private void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ConnectionListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ServerStartupListener(this, statsManager), this);
        Bukkit.getPluginManager().registerEvents(new EntityDamagedListener(this, statsManager), this);
        Bukkit.getPluginManager().registerEvents(new PlayerStatusChangeListener(this, statsManager), this);
        Bukkit.getPluginManager().registerEvents(new InventoryAlteredListener(this, statsManager), this);
        Bukkit.getPluginManager().registerEvents(new ProficiencyChangedListener(this, statsManager), this);
        Bukkit.getPluginManager().registerEvents(new SpeedChangedListener(this, statsManager), this);
        Bukkit.getPluginManager().registerEvents(new ServerListPingListener(), this);
        Bukkit.getPluginManager().registerEvents(new XpGainListener(this), this);
        Bukkit.getPluginManager().registerEvents(new SentinelDeathListener(this), this);
        Bukkit.getPluginManager().registerEvents(new MenuListener(), this);
    }

    @SuppressWarnings("DataFlowIssue")
    private void registerCommands(){
        getCommand("heal").setExecutor(new HealCommand());
        getCommand("kill").setExecutor(new KillCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("stash").setExecutor(new StashCommand());
        getCommand("playtime").setExecutor(new PlayTimeCommand());

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

    private void registerAbilities(){
        new WrathOfSpartaAbility(CustomAbilities.SPARTAN_WRATH, CustomItems.WRATH_OF_SPARTA.getItem(), 5, 5);
        new DialOfTheSunAbility(CustomAbilities.GRADUAL_SET_DAY, CustomItems.DIAL_OF_THE_SUN.getItem(), 25, 15);
    }

    private void registerTraits(){
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(NpcStats.class).withName("npcstats"));
    }

    //manager getters
    public Database getDatabase() {return database;}
    public PlayerManager getPlayerManager() {return playerManager;}
    public StashManager getStashManager() {return stashManager;}
    public CombatManager getCombatManager() {return combatManager;}
    public StatsManager getStatsManager() {return statsManager;}


    @Override
    public void onDisable() {
        // Plugin shutdown logic

        database.disconnect();

    }

}
