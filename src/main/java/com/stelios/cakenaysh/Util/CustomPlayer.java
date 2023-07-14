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
                "SELECT RANK, JOIN_DATE, PLAY_TIME, XP, STAMINA_REGEN, STAMINA, MAX_STAMINA, HEALTH_REGEN, " +
                        "HEALTH, MAX_HEALTH, MELEE_PROFICIENCY, RANGED_PROFICIENCY, ARMOR_PROFICIENCY, WILSONCOIN, PIETY, " +
                        "CHARISMA, DECEPTION, AGILITY, LUCK, STEALTH" +
                        " FROM players WHERE UUID = ?;");
        statement.setString(1, uuid.toString());
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            rank = rs.getString("RANK");
            joinDate = rs.getString("JOIN_DATE");
            playTime = rs.getFloat("PLAY_TIME");
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
                    "INSERT INTO players (ID, UUID, RANK, JOIN_DATE, PLAY_TIME, XP, STAMINA_REGEN, STAMINA, MAX_STAMINA, HEALTH_REGEN," +
                            "HEALTH, MAX_HEALTH, MELEE_PROFICIENCY, RANGED_PROFICIENCY, ARMOR_PROFICIENCY, WILSONCOIN, PIETY," +
                            "CHARISMA, DECEPTION, AGILITY, LUCK, STEALTH) VALUES (" +
                            "default," +
                            "'" + uuid + "'," +
                            "'" + rank + "'," +
                            "'" + Calendar.getInstance().getTime() + "'," +
                            playTime + "," +
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
    public float getThorns() {
        return thorns;
    }
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
        return "Rank: " + rank +
                "   Play Time: " + playTime + "\n" +
                "Join Date: " + joinDate + "\n" + "\n" +
                "XP: " + xp + "\n" + "\n" +
                "Stamina Regen: " + staminaRegen +
                "   Stamina: " + stamina +
                "   Max Stamina: " + maxStamina + "\n" +
                "Health Regen: " + healthRegen +
                "   Health: " + health +
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


    //resets all the attributes of the player
    public void resetAttributes(Main main, Player player) {
        CustomPlayer customPlayer = main.getPlayerManager().getCustomPlayer(player.getUniqueId());
        customPlayer.setRank("Guest");
        customPlayer.setPlayTime(0);
        customPlayer.setXp(0);
        customPlayer.setStaminaRegen(1);
        customPlayer.setStaminaDatabase(100);
        customPlayer.setMaxStamina(100);
        customPlayer.setHealthRegen(1);
        customPlayer.setHealthDatabase(100);
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


    //sets the rank of the player
    //@param rank: the rank of the player
    public void setRank(String rank) {
        this.rank = rank;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET RANK = '" + rank + "' WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sets the join date of the player
    //@param joinDate: the join date of the player
    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET JOIN_DATE = '" + joinDate + "' WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sets the play time of the player
    //@param playTime: the play time of the player
    public void setPlayTime(float playTime) {
        this.playTime = playTime;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET PLAY_TIME = " + playTime + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sets the xp of the player
    //@param xp: the xp of the player
    public void setXp(int xp) {
        this.xp = xp;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET XP = " + xp + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sets the stamina regen of the player
    //@param staminaRegen: the stamina regen of the player
    public void setStaminaRegen(int staminaRegen) {
        this.staminaRegen = staminaRegen;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET STAMINA_REGEN = " + staminaRegen + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sets the stamina of the player locally within the class
    //@param stamina: the stamina of the player
    public void setStaminaLocal(int stamina) {
        this.stamina = stamina;
    }

    //sets the stamina of the player in the database and locally within the class
    //@param stamina: the stamina of the player
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

    //sets the max stamina of the player
    //@param maxStamina: the max stamina of the player
    public void setMaxStamina(int maxStamina) {
        this.maxStamina = maxStamina;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET MAX_STAMINA = " + maxStamina + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sets the health regen of the player
    //@param healthRegen: the health regen of the player
    public void setHealthRegen(int healthRegen) {
        this.healthRegen = healthRegen;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET HEALTH_REGEN = " + healthRegen + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sets the health of the player locally within the class
    //@param health: the health of the player
    public void setHealthLocal(float health) {
        this.health = health;
    }

    //sets the health of the player in the database and locally within the class
    //@param health: the health of the player
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

    //sets the max health of the player
    //@param maxHealth: the max health of the player
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET MAX_HEALTH = " + maxHealth + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sets the melee proficiency of the player
    //@param meleeProficiency: the melee proficiency of the player
    public void setMeleeProficiency(int meleeProficiency) {
        this.meleeProficiency = meleeProficiency;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET MELEE_PROFICIENCY = " + meleeProficiency + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sets the ranged proficiency of the player
    //@param rangedProficiency: the ranged proficiency of the player
    public void setRangedProficiency(int rangedProficiency) {
        this.rangedProficiency = rangedProficiency;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET RANGED_PROFICIENCY = " + rangedProficiency + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sets the armor proficiency of the player
    //@param armorProficiency: the armor proficiency of the player
    public void setArmorProficiency(int armorProficiency) {
        this.armorProficiency = armorProficiency;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET ARMOR_PROFICIENCY = " + armorProficiency + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sets the wilsoncoin of the player
    //@param wilsonCoin: the wilson coin of the player
    public void setWilsonCoin(int wilsonCoin) {
        this.wilsonCoin = wilsonCoin;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET WILSONCOIN = " + wilsonCoin + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sets the piety of the player
    //@param piety: the piety of the player
    public void setPiety(int piety) {
        this.piety = piety;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET PIETY = " + piety + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sets the charisma of the player
    //@param charisma: the charisma of the player
    public void setCharisma(int charisma) {
        this.charisma = charisma;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET CHARISMA = " + charisma + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sets the deception of the player
    //@param deception: the deception of the player
    public void setDeception(int deception) {
        this.deception = deception;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET DECEPTION = " + deception + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sets the agility of the player
    //@param agility: the agility of the player
    public void setAgility(int agility) {
        this.agility = agility;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET AGILITY = " + agility + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sets the luck of the player
    //@param luck: the luck of the player
    public void setLuck(int luck) {
        this.luck = luck;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET LUCK = " + luck + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //sets the stealth of the player
    //@param stealth: the stealth of the player
    public void setStealth(int stealth) {
        this.stealth = stealth;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET STEALTH = " + stealth + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //adds play time to the player
    //@param playTime: the play time to add to the player
    public void addPlayTime(float playTime) {
        this.playTime += playTime;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET PLAY_TIME = " + this.playTime + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adds xp to the player
    //@param xp: the xp to add to the player
    public void addXp(int xp) {
        this.xp += xp;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET XP = " + this.xp + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adds stamina regen to the player
    //@param staminaRegen: the stamina regen to add to the player
    public void addStaminaRegen(int staminaRegen) {
        this.staminaRegen += staminaRegen;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET STAMINA_REGEN = " + this.staminaRegen + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adds stamina to the player locally within the class
    //@param stamina: the stamina to add to the player
    public void addStaminaLocal(int stamina) {
        this.stamina += stamina;
    }

    //adds the stamina of the player in the database and locally within the class
    //@param stamina: the stamina to add to the player
    public void addStaminaDatabase(int stamina) {
        this.stamina += stamina;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET STAMINA = " + this.stamina + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adds max stamina to the player
    //@param maxStamina: the max stamina to add to the player
    public void addMaxStamina(int maxStamina) {
        this.maxStamina += maxStamina;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET MAX_STAMINA = " + this.maxStamina + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adds health regen to the player
    //@param healthRegen: the health regen to add to the player
    public void addHealthRegen(int healthRegen) {
        this.healthRegen += healthRegen;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET HEALTH_REGEN = " + this.healthRegen + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adds health to the player locally within the class
    //@param health: the health to add to the player
    public void addHealthLocal(float health) {
        this.health += health;
    }

    //adds health to the player in the database and locally within the class
    //@param health: the health to add to the player
    public void addHealthDatabase(float health) {
        this.health += health;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET HEALTH = " + this.health + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adds max health to the player
    //@param maxHealth: the max health to add to the player
    public void addMaxHealth(int maxHealth) {
        this.maxHealth += maxHealth;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET MAX_HEALTH = " + this.maxHealth + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adds melee proficiency to the player
    //@param meleeProficiency: the melee proficiency to add to the player
    public void addMeleeProficiency(int meleeProficiency) {
        this.meleeProficiency += meleeProficiency;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET MELEE_PROFICIENCY = " + this.meleeProficiency + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adds ranged proficiency to the player
    //@param rangedProficiency: the ranged proficiency to add to the player
    public void addRangedProficiency(int rangedProficiency) {
        this.rangedProficiency += rangedProficiency;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET RANGED_PROFICIENCY = " + this.rangedProficiency + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adds armor proficiency to the player
    //@param armorProficiency: the armor proficiency to add to the player
    public void addArmorProficiency(int armorProficiency) {
        this.armorProficiency += armorProficiency;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET ARMOR_PROFICIENCY = " + this.armorProficiency + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adds WilsonCoin to the player
    //@param wilsonCoin: the WilsonCoin to add to the player
    public void addWilsonCoin(int wilsonCoin) {
        this.wilsonCoin += wilsonCoin;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET WILSONCOIN = " + this.wilsonCoin + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adds piety to the player
    //@param piety: the piety to add to the player
    public void addPiety(int piety) {
        this.piety += piety;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET PIETY = " + this.piety + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adds charisma to the player
    //@param charisma: the charisma to add to the player
    public void addCharisma(int charisma) {
        this.charisma += charisma;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET CHARISMA = " + this.charisma + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adds deception to the player
    //@param deception: the deception to add to the player
    public void addDeception(int deception) {
        this.deception += deception;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET DECEPTION = " + this.deception + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adds agility to the player
    //@param agility: the agility to add to the player
    public void addAgility(int agility) {
        this.agility += agility;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET AGILITY = " + this.agility + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adds luck to the player
    //@param luck: the luck to add to the player
    public void addLuck(int luck) {
        this.luck += luck;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET LUCK = " + this.luck + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //adds stealth to the player
    //@param stealth: the stealth to add to the player
    public void addStealth(int stealth) {
        this.stealth += stealth;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET STEALTH = " + this.stealth + " WHERE UUID = '" + uuid + "';");
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
