package com.stelios.cakenaysh.Util;

import com.stelios.cakenaysh.Main;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.UUID;

public class CustomPlayer {

    private Main main;


    private UUID uuid;
    private String rank;
    private String joinDate;
    private float playTime;
    private int xp;
    private int maxHealth;
    private int speed;
    private int meleeProficiency;
    private int rangedProficiency;
    private int wilsonCoin;


    private int stamina;
    private int health;

    public CustomPlayer(Main main, UUID uuid) throws SQLException{
        this.main = main;

        this.uuid = uuid;

        PreparedStatement statement = main.getDatabase().getConnection().prepareStatement(
                "SELECT RANK, JOIN_DATE, PLAY_TIME, XP, STAMINA, HEALTH, MAX_HEALTH, SPEED, MELEE_PROFICIENCY, RANGED_PROFICIENCY, WILSONCOIN" +
                        " FROM players WHERE UUID = ?;");
        statement.setString(1, uuid.toString());
        ResultSet rs = statement.executeQuery();
        if (rs.next()){
            rank = rs.getString("RANK");
            joinDate = rs.getString("JOIN_DATE");
            playTime = rs.getFloat("PLAY_TIME");
            xp = rs.getInt("XP");
            stamina = rs.getInt("STAMINA");
            health = rs.getInt("HEALTH");
            maxHealth = rs.getInt("MAX_HEALTH");
            speed = rs.getInt("SPEED");
            meleeProficiency = rs.getInt("MELEE_PROFICIENCY");
            rangedProficiency = rs.getInt("RANGED_PROFICIENCY");
            wilsonCoin = rs.getInt("WILSONCOIN");
        } else {
            rank = "GUEST";
            playTime = 0;
            xp = 0;
            stamina = 100;
            health = 100;
            maxHealth = 100;
            speed = 100;
            meleeProficiency = 0;
            rangedProficiency = 0;
            wilsonCoin = 0;
            PreparedStatement insert = main.getDatabase().getConnection().prepareStatement(
                    "INSERT INTO players (ID, UUID, RANK, JOIN_DATE, PLAY_TIME, XP, STAMINA, HEALTH, MAX_HEALTH, SPEED, MELEE_PROFICIENCY, " +
                            "RANGED_PROFICIENCY, WILSONCOIN) VALUES (" +
                            "default," +
                            "'" + uuid + "'," +
                            "'" + rank + "'," +
                            "'" + Calendar.getInstance().getTime() + "'," +
                            playTime + "," +
                            xp + "," +
                            stamina + "," +
                            health + "," +
                            maxHealth + "," +
                            speed + "," +
                            meleeProficiency + "," +
                            rangedProficiency + "," +
                            wilsonCoin +
                            ");");
            insert.executeUpdate();
        }
    }

    ////getter methods
    public String getRank() {return rank;}
    public String getJoinDate() {return joinDate;}
    public float getPlayTime() {return playTime;}
    public int getXp() {return xp;}
    public int getStamina() {return stamina;}
    public int getHealth() {return health;}
    public int getMaxHealth() {return maxHealth;}
    public int getSpeed() {return speed;}
    public int getMeleeProficiency() {return meleeProficiency;}
    public int getRangedProficiency() {return rangedProficiency;}
    public int getWilsonCoin() {return wilsonCoin;}

    //get all the attributes of the player
    //used for the /attributes command
    public String getAttributes(){
        return "Rank: " + rank + "\n" +
                "Join Date: " + joinDate + "\n" +
                "Play Time: " + playTime + "\n" +
                "XP: " + xp + "\n" +
                "Stamina: " + stamina + "\n" +
                "Health: " + health + "\n" +
                "Max Health: " + maxHealth + "\n" +
                "Speed: " + speed + "\n" +
                "Melee Proficiency: " + meleeProficiency + "\n" +
                "Ranged Proficiency: " + rangedProficiency + "\n" +
                "WilsonCoin: " + wilsonCoin;
    }

    ////setters
    //resets all the attributes of the player
    public void resetAttributes(Main main, Player player){
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setRank("Guest");
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setPlayTime(0);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setXp(0);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setStaminaDatabase(100);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setHealthDatabase(100);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setMaxHealth(100);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setSpeed(100);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setMeleeProficiency(0);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setRangedProficiency(0);
        main.getPlayerManager().getCustomPlayer(player.getUniqueId()).setWilsonCoin(0);
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

    //sets the health of the player locally within the class
    //@param health: the health of the player
    public void setHealthLocal(int health) {
        this.health = health;
    }

    //sets the health of the player in the database and locally within the class
    //@param health: the health of the player
    public void setHealthDatabase(int health) {
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

    //adds health to the player locally within the class
    //@param health: the health to add to the player
    public void addHealthLocal(int health) {
        this.health += health;
    }

    //adds health to the player in the database and locally within the class
    //@param health: the health to add to the player
    public void addHealthDatabase(int health) {
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
}
