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
    private int speed;
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


    public CustomPlayer(Main main, UUID uuid) throws SQLException {
        this.main = main;

        this.uuid = uuid;

        PreparedStatement statement = main.getDatabase().getConnection().prepareStatement(
                "SELECT RANK, JOIN_DATE, PLAY_TIME, XP, STAMINA_REGEN, STAMINA, MAX_STAMINA, HEALTH_REGEN, " +
                        "HEALTH, MAX_HEALTH, SPEED, MELEE_PROFICIENCY, RANGED_PROFICIENCY, ARMOR_PROFICIENCY, WILSONCOIN, PIETY, " +
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
            speed = rs.getInt("SPEED");
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
            speed = 100;
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
                            "HEALTH, MAX_HEALTH, SPEED, MELEE_PROFICIENCY, RANGED_PROFICIENCY, ARMOR_PROFICIENCY, WILSONCOIN, PIETY," +
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
                            speed + "," +
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

    public int getSpeed() {
        return speed;
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
                "   Max Health: " + maxHealth + "\n" +
                "Speed: " + speed + "\n" + "\n" +
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

    ////setters
    //resets all the attributes of the player
    public void resetAttributes(Main main, Player player) {
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setRank("Guest");
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setPlayTime(0);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setXp(0);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setStaminaRegen(1);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setStaminaDatabase(100);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setMaxStamina(100);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setHealthRegen(1);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setHealthDatabase(100);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setMaxHealth(100);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setSpeed(100);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setMeleeProficiency(0);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setRangedProficiency(0);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setArmorProficiency(0);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setWilsonCoin(0);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setPiety(0);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setCharisma(0);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setDeception(0);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setAgility(0);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setLuck(0);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setStealth(0);
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

    //sets the speed of the player
    //@param speed: the speed of the player
    public void setSpeed(int speed) {
        this.speed = speed;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET SPEED = " + speed + " WHERE UUID = '" + uuid + "';");
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

    //adds speed to the player
    //@param speed: the speed to add to the player
    public void addSpeed(int speed) {
        this.speed += speed;
        try {
            PreparedStatement statement = main.getDatabase().getConnection().prepareStatement
                    ("UPDATE players SET SPEED = " + this.speed + " WHERE UUID = '" + uuid + "';");
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
