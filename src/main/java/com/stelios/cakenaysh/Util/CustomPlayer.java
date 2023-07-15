package com.stelios.cakenaysh.Util;

import com.stelios.cakenaysh.Main;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.UUID;

public class CustomPlayer {

    private final Main main;


    //info from database
    private final UUID uuid;
    private String rank;
    private String joinDate;
    private float playTime;
    private int level;
    private int xp;
    private int staminaRegen;
    private int stamina;
    private int maxStamina;
    private int healthRegen;
    private float health;
    private int maxHealth;
    private int meleeProficiency;
    private int rangedProficiency;
    private int armorProficiency;
    private int wilsonCoin;
    private int piety;
    private int charisma;
    private int deception;
    private int agility;
    private int luck;
    private int stealth;

    //info from items
    private float damage;
    private float attackSpeed;
    private float critDamage;
    private float critChance;
    private float strength;
    private float defense;
    private float speed;
    private float thorns;
    private float infernalDefense;
    private float infernalDamage;
    private float undeadDefense;
    private float undeadDamage;
    private float aquaticDefense;
    private float aquaticDamage;
    private float aerialDefense;
    private float aerialDamage;
    private float meleeDefense;
    private float meleeDamage;
    private float rangedDefense;
    private float rangedDamage;
    private float magicDefense;
    private float magicDamage;


    public CustomPlayer(Main main, UUID uuid) throws SQLException {
        this.main = main;

        this.uuid = uuid;

        PreparedStatement statement = main.getDatabase().getConnection().prepareStatement(
                "SELECT RANK, JOIN_DATE, PLAY_TIME, LEVEL, XP, STAMINA_REGEN, STAMINA, MAX_STAMINA, HEALTH_REGEN, " +
                        "HEALTH, MAX_HEALTH, MELEE_PROFICIENCY, RANGED_PROFICIENCY, ARMOR_PROFICIENCY, WILSONCOIN, PIETY, " +
                        "CHARISMA, DECEPTION, AGILITY, LUCK, STEALTH" +
                        " FROM players WHERE UUID = ?;");
        statement.setString(1, uuid.toString());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            rank = rs.getString("RANK");
            joinDate = rs.getString("JOIN_DATE");
            playTime = rs.getFloat("PLAY_TIME");
            level = rs.getInt("LEVEL");
            xp = rs.getInt("XP");
            staminaRegen = rs.getInt("STAMINA_REGEN");
            stamina = rs.getInt("STAMINA");
            maxStamina = rs.getInt("MAX_STAMINA");
            healthRegen = rs.getInt("HEALTH_REGEN");
            health = rs.getFloat("HEALTH");
            maxHealth = rs.getInt("MAX_HEALTH");
            meleeProficiency = rs.getInt("MELEE_PROFICIENCY");
            rangedProficiency = rs.getInt("RANGED_PROFICIENCY");
            armorProficiency = rs.getInt("ARMOR_PROFICIENCY");
            wilsonCoin = rs.getInt("WILSONCOIN");
            piety = rs.getInt("PIETY");
            charisma = rs.getInt("CHARISMA");
            deception = rs.getInt("DECEPTION");
            agility = rs.getInt("AGILITY");
            luck = rs.getInt("LUCK");
            stealth = rs.getInt("STEALTH");
        } else {
            rank = "GUEST";
            playTime = 0;
            level = 0;
            xp = 0;
            staminaRegen = 1;
            stamina = 100;
            maxStamina = 100;
            healthRegen = 1;
            health = 100;
            maxHealth = 100;
            meleeProficiency = 0;
            rangedProficiency = 0;
            armorProficiency = 0;
            wilsonCoin = 0;
            piety = 0;
            charisma = 0;
            deception = 0;
            agility = 0;
            luck = 0;
            stealth = 0;
            PreparedStatement insert = main.getDatabase().getConnection().prepareStatement(
                    "INSERT INTO players (ID, UUID, RANK, JOIN_DATE, PLAY_TIME, LEVEL, XP, STAMINA_REGEN, STAMINA, MAX_STAMINA, HEALTH_REGEN," +
                            "HEALTH, MAX_HEALTH, MELEE_PROFICIENCY, RANGED_PROFICIENCY, ARMOR_PROFICIENCY, WILSONCOIN, PIETY," +
                            "CHARISMA, DECEPTION, AGILITY, LUCK, STEALTH) VALUES (" +
                            "default," +
                            "'" + uuid + "'," +
                            "'" + rank + "'," +
                            "'" + Calendar.getInstance().getTime() + "'," +
                            playTime + "," +
                            level + "," +
                            xp + "," +
                            staminaRegen + "," +
                            stamina + "," +
                            maxStamina + "," +
                            healthRegen + "," +
                            health + "," +
                            maxHealth + "," +
                            meleeProficiency + "," +
                            rangedProficiency + "," +
                            armorProficiency + "," +
                            wilsonCoin + "," +
                            piety + "," +
                            charisma + "," +
                            deception + "," +
                            agility + "," +
                            luck + "," +
                            stealth +
                            ");");
            insert.executeUpdate();
        }
    }

    ////getter methods
    public String getRank() {
        return rank;
    }
    public String getJoinDate() {
        return joinDate;
    }
    public float getPlayTime() {
        return playTime;
    }
    public int getLevel() {
        return level;
    }
    public int getXp() {
        return xp;
    }
    public int getStaminaRegen() {
        return staminaRegen;
    }
    public int getStamina() {
        return stamina;
    }
    public int getMaxStamina() {
        return maxStamina;
    }
    public int getHealthRegen() {
        return healthRegen;
    }
    public float getHealth() {
        return health;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public int getMeleeProficiency() {
        return meleeProficiency;
    }
    public int getRangedProficiency() {
        return rangedProficiency;
    }
    public int getArmorProficiency() {
        return armorProficiency;
    }
    public int getWilsonCoin() {
        return wilsonCoin;
    }
    public int getPiety() {
        return piety;
    }
    public int getCharisma() {
        return charisma;
    }
    public int getDeception() {
        return deception;
    }
    public int getAgility() {
        return agility;
    }
    public int getLuck() {
        return luck;
    }
    public int getStealth() {
        return stealth;
    }


    public float getDamage() {
        return damage;
    }
    public float getAttackSpeed() {
        return attackSpeed;
    }
    public float getCritChance() {
        return critChance;
    }
    public float getCritDamage() {
        return critDamage;
    }
    public float getStrength() {
        return strength;
    }
    public float getDefense() {
        return defense;
    }
    public float getSpeed() {
        return speed;
    }
    public float getThorns() { return thorns; }
    public float getInfernalDefense() {
        return infernalDefense;
    }
    public float getInfernalDamage() {
        return infernalDamage;
    }
    public float getUndeadDefense() {
        return undeadDefense;
    }
    public float getUndeadDamage() {
        return undeadDamage;
    }
    public float getAquaticDefense() {
        return aquaticDefense;
    }
    public float getAquaticDamage() {
        return aquaticDamage;
    }
    public float getAerialDefense() {
        return aerialDefense;
    }
    public float getAerialDamage() {
        return aerialDamage;
    }
    public float getMeleeDefense() {
        return meleeDefense;
    }
    public float getMeleeDamage() {
        return meleeDamage;
    }
    public float getRangedDefense() {
        return rangedDefense;
    }
    public float getRangedDamage() {
        return rangedDamage;
    }
    public float getMagicDefense() {
        return magicDefense;
    }
    public float getMagicDamage() {
        return magicDamage;
    }


    //get all the attributes of the player
    //used for the /attributes command
    public String getAttributes() {
        return "\n" + "Rank: " + rank +
                "   Play Time: " + playTime + "\n" +
                "Join Date: " + joinDate + "\n" +
                "Level: " + level +
                "   XP: " + xp + "\n" + "\n" +
                "Stamina Regen: " + staminaRegen +
                "   Stamina: " + stamina +
                "   Max Stamina: " + maxStamina + "\n" +
                "Health Regen: " + healthRegen +
                "   Health: " + (int) health +
                "   Max Health: " + maxHealth + "\n" + "\n" +
                "Proficiencies \n" +
                "Melee: " + meleeProficiency +
                "   Ranged: " + rangedProficiency +
                "   Armor: " + armorProficiency + "\n" + "\n" +
                "WilsonCoin: " + wilsonCoin + "\n" + "\n" +
                "Piety: " + piety +
                "   Charisma: " + charisma +
                "   Deception: " + deception + "\n" +
                "Agility: " + agility +
                "   Luck: " + luck +
                "   Stealth: " + stealth + "\n";
    }


    //resets all the database attributes of the player
    public void resetAttributes(Player player) {
        CustomPlayer customPlayer = main.getPlayerManager().getCustomPlayer(player.getUniqueId());
        customPlayer.setRank("Guest");
        customPlayer.setPlayTime(0);
        customPlayer.setLevel(0);
        customPlayer.setXp(0);
        customPlayer.setStaminaRegen(1);
        customPlayer.setStamina(100);
        customPlayer.setMaxStamina(100);
        customPlayer.setHealthRegen(1);
        customPlayer.setHealth(100);
        customPlayer.setMaxHealth(100);
        customPlayer.setMeleeProficiency(0);
        customPlayer.setRangedProficiency(0);
        customPlayer.setArmorProficiency(0);
        customPlayer.setWilsonCoin(0);
        customPlayer.setPiety(0);
        customPlayer.setCharisma(0);
        customPlayer.setDeception(0);
        customPlayer.setAgility(0);
        customPlayer.setLuck(0);
        customPlayer.setStealth(0);
    }

    //saves all the attributes to the database
    public void saveAttributesToDatabase(Player player){
        CustomPlayer customPlayer = main.getPlayerManager().getCustomPlayer(player.getUniqueId());
        customPlayer.setRankDatabase(customPlayer.getRank());
        customPlayer.setJoinDateDatabase(customPlayer.getJoinDate());
        customPlayer.setPlayTimeDatabase(customPlayer.getPlayTime());
        customPlayer.setLevelDatabase(customPlayer.getLevel());
        customPlayer.setXpDatabase(customPlayer.getXp());
        customPlayer.setStaminaRegenDatabase(customPlayer.getStaminaRegen());
        customPlayer.setStaminaDatabase(customPlayer.getStamina());
        customPlayer.setMaxStaminaDatabase(customPlayer.getMaxStamina());
        customPlayer.setHealthRegenDatabase(customPlayer.getHealthRegen());
        customPlayer.setHealthDatabase(customPlayer.getHealth());
        customPlayer.setMaxHealthDatabase(customPlayer.getMaxHealth());
        customPlayer.setMeleeProficiencyDatabase(customPlayer.getMeleeProficiency());
        customPlayer.setRangedProficiencyDatabase(customPlayer.getRangedProficiency());
        customPlayer.setArmorProficiencyDatabase(customPlayer.getArmorProficiency());
        customPlayer.setWilsonCoinDatabase(customPlayer.getWilsonCoin());
        customPlayer.setPietyDatabase(customPlayer.getPiety());
        customPlayer.setCharismaDatabase(customPlayer.getCharisma());
        customPlayer.setDeceptionDatabase(customPlayer.getDeception());
        customPlayer.setAgilityDatabase(customPlayer.getAgility());
        customPlayer.setLuckDatabase(customPlayer.getLuck());
        customPlayer.setStealthDatabase(customPlayer.getStealth());
    }

    ////setters
    public void setDamage(float damage){
        this.damage = damage;
    }
    public void setAttackSpeed(float attackSpeed){
        this.attackSpeed = attackSpeed;
    }
    public void setCritChance(float critChance){
        this.critChance = critChance;
    }
    public void setCritDamage(float critDamage){
        this.critDamage = critDamage;
    }
    public void setStrength(float strength){
        this.strength = strength;
    }
    public void setDefense(float defense){
        this.defense = defense;
    }
    public void setSpeed(float speed){
        this.speed = speed;
    }
    public void setThorns(float thorns){
        this.thorns = thorns;
    }
    public void setInfernalDefense(float infernalDefense){
        this.infernalDefense = infernalDefense;
    }
    public void setInfernalDamage(float infernalDamage){
        this.infernalDamage = infernalDamage;
    }
    public void setUndeadDefense(float undeadDefense){
        this.undeadDefense = undeadDefense;
    }
    public void setUndeadDamage(float undeadDamage){
        this.undeadDamage = undeadDamage;
    }
    public void setAquaticDefense(float aquaticDefense){
        this.aquaticDefense = aquaticDefense;
    }
    public void setAquaticDamage(float aquaticDamage){
        this.aquaticDamage = aquaticDamage;
    }
    public void setAerialDefense(float aerialDefense){
        this.aerialDefense = aerialDefense;
    }
    public void setAerialDamage(float aerialDamage){
        this.aerialDamage = aerialDamage;
    }
    public void setMeleeDefense(float meleeDefense){
        this.meleeDefense = meleeDefense;
    }
    public void setMeleeDamage(float meleeDamage){
        this.meleeDamage = meleeDamage;
    }
    public void setRangedDefense(float rangedDefense){
        this.rangedDefense = rangedDefense;
    }
    public void setRangedDamage(float rangedDamage){
        this.rangedDamage = rangedDamage;
    }
    public void setMagicDefense(float magicDefense){
        this.magicDefense = magicDefense;
    }
    public void setMagicDamage(float magicDamage){
        this.magicDamage = magicDamage;
    }


    //setting player stats in the database and locally within the class
    public void setRankDatabase(String rank) {
        this.rank = rank;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET RANK = '" + rank + "' WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setJoinDateDatabase(String joinDate) {
        this.joinDate = joinDate;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET JOIN_DATE = '" + joinDate + "' WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPlayTimeDatabase(float playTime) {
        this.playTime = playTime;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET PLAY_TIME = " + playTime + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLevelDatabase(int level){
        this.level = level;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET LEVEL = " + level + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setXpDatabase(int xp) {
        this.xp = xp;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET XP = " + xp + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setStaminaRegenDatabase(int staminaRegen) {
        this.staminaRegen = staminaRegen;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET STAMINA_REGEN = " + staminaRegen + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setStaminaDatabase(int stamina) {
        this.stamina = stamina;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET STAMINA = " + stamina + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMaxStaminaDatabase(int maxStamina) {
        this.maxStamina = maxStamina;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET MAX_STAMINA = " + maxStamina + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setHealthRegenDatabase(int healthRegen) {
        this.healthRegen = healthRegen;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET HEALTH_REGEN = " + healthRegen + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setHealthDatabase(float health) {
        this.health = health;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET HEALTH = " + health + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMaxHealthDatabase(int maxHealth) {
        this.maxHealth = maxHealth;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET MAX_HEALTH = " + maxHealth + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setMeleeProficiencyDatabase(int meleeProficiency) {
        this.meleeProficiency = meleeProficiency;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET MELEE_PROFICIENCY = " + meleeProficiency + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setRangedProficiencyDatabase(int rangedProficiency) {
        this.rangedProficiency = rangedProficiency;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET RANGED_PROFICIENCY = " + rangedProficiency + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setArmorProficiencyDatabase(int armorProficiency) {
        this.armorProficiency = armorProficiency;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET ARMOR_PROFICIENCY = " + armorProficiency + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setWilsonCoinDatabase(int wilsonCoin) {
        this.wilsonCoin = wilsonCoin;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET WILSONCOIN = " + wilsonCoin + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPietyDatabase(int piety) {
        this.piety = piety;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET PIETY = " + piety + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setCharismaDatabase(int charisma) {
        this.charisma = charisma;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET CHARISMA = " + charisma + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setDeceptionDatabase(int deception) {
        this.deception = deception;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET DECEPTION = " + deception + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setAgilityDatabase(int agility) {
        this.agility = agility;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET AGILITY = " + agility + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLuckDatabase(int luck) {
        this.luck = luck;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET LUCK = " + luck + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setStealthDatabase(int stealth) {
        this.stealth = stealth;
        try{
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET STEALTH = " + stealth + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //setting player stats locally within the class
    public void setRank(String rank) {
        this.rank = rank;
    }
    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
    public void setPlayTime(float playTime) {
        this.playTime = playTime;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
    public void setStaminaRegen(int staminaRegen) {
        this.staminaRegen = staminaRegen;
    }
    public void setStamina(int stamina) {
        this.stamina = stamina;
    }
    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
    }
    public void setHealthRegen(int healthRegen) {
        this.healthRegen = healthRegen;
    }
    public void setHealth(float health) {
        this.health = health;
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
    public void setMeleeProficiency(int meleeProficiency) {
        this.meleeProficiency = meleeProficiency;
    }
    public void setRangedProficiency(int rangedProficiency) {
        this.rangedProficiency = rangedProficiency;
    }
    public void setArmorProficiency(int armorProficiency) {
        this.armorProficiency = armorProficiency;
    }
    public void setWilsonCoin(int wilsonCoin) {
        this.wilsonCoin = wilsonCoin;
    }
    public void setPiety(int piety) {
        this.piety = piety;
    }
    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }
    public void setDeception(int deception) {
        this.deception = deception;
    }
    public void setAgility(int agility) {
        this.agility = agility;
    }
    public void setLuck(int luck) {
        this.luck = luck;
    }
    public void setStealth(int stealth) {
        this.stealth = stealth;
    }


    //adding stats to the player locally within the class
    public void addPlayTime(float playTime) {
        this.playTime += playTime;
    }
    public void addLevel(int level) {
        this.level += level;
    }
    public void addXp(int xp) {
        this.xp += xp;
    }
    public void addStaminaRegen(int staminaRegen) {
        this.staminaRegen += staminaRegen;
    }
    public void addStamina(int stamina) {
        this.stamina += stamina;
    }
    public void addMaxStamina(int maxStamina) {
        this.maxStamina += maxStamina;
    }
    public void addHealthRegen(int healthRegen) {
        this.healthRegen += healthRegen;
    }
    public void addHealth(float health) {
        this.health += health;
    }
    public void addMaxHealth(int maxHealth) {
        this.maxHealth += maxHealth;
    }
    public void addMeleeProficiency(int meleeProficiency) {
        this.meleeProficiency += meleeProficiency;
    }
    public void addRangedProficiency(int rangedProficiency) {
        this.rangedProficiency += rangedProficiency;
    }
    public void addArmorProficiency(int armorProficiency) {
        this.armorProficiency += armorProficiency;
    }
    public void addWilsonCoin(int wilsonCoin) {
        this.wilsonCoin += wilsonCoin;
    }
    public void addPiety(int piety) {
        this.piety += piety;
    }
    public void addCharisma(int charisma) {
        this.charisma += charisma;
    }
    public void addDeception(int deception) {
        this.deception += deception;
    }
    public void addAgility(int agility) {
        this.agility += agility;
    }
    public void addLuck(int luck) {
        this.luck += luck;
    }
    public void addStealth(int stealth) {
        this.stealth += stealth;
    }


}
