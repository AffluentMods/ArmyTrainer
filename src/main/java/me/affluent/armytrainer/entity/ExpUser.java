package me.affluent.armytrainer.entity;

import me.affluent.armytrainer.ArmyTrainer;
import me.affluent.armytrainer.api.ExpApi;
import net.dv8tion.jda.api.entities.TextChannel;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class ExpUser {

    private static final HashMap<String, ExpUser> cache = new HashMap<>();

    private final String userId;
    private int level;
    private BigInteger experience;

    public static void clearCache() {
        cache.clear();
    }

    private ExpUser(String userId) {
        this.userId = userId;
        load();
        cache.put(userId, this);
    }

    private void load() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM exp WHERE userId=?;", userId)) {
            if (rs.next()) {
                level = rs.getInt("level");
                String expString = rs.getString("experience");
                experience = new BigInteger(expString);
            } else {
                level = 1;
                experience = BigInteger.ZERO;
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO exp VALUES (?, ?, ?);", userId, 1, "0");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getUserId() {
        return userId;
    }

    public int getLevel() {
        return level;
    }

    public BigInteger getExperience() {
        return experience;
    }

    public double maxXP() {
        return ExpApi.getNeededXP(level);
    }

    public boolean isMaxed() {
        return getLevel() >= 150;
    }


    public void addExp(long amount, TextChannel tc) {
        if (isMaxed()) {
            return;
        }
        setExperience(this.experience.add(BigInteger.valueOf(amount)));
        while (ExpApi.canLevelUp(this.experience.doubleValue(), level)) {
            double neededEXP = ExpApi.getNeededXP(level);
            double expnow = this.experience.doubleValue();
            setExperience(BigInteger.valueOf(new Double(expnow - neededEXP).longValue()));
            final int newLevel = level + 1;
            setLevel(newLevel);
            Player p = Player.getPlayer(userId);
        }
        cache();
    }

    public void setExperience(BigInteger experience) {
        this.experience = experience;
        cache();
        ArmyTrainer.getBot().getDatabase()
                .update("UPDATE exp SET experience=? WHERE userId=?;", experience.toString(), userId);
    }

    public void setLevel(int level) {
        this.level = level;
        cache();
        ArmyTrainer.getBot().getDatabase().update("UPDATE exp SET level=? WHERE userId=?;", level, userId);
    }

    private void cache() {
        cache.put(userId, this);
    }

    public static ExpUser getExpUser(String userId) {
        if (cache.containsKey(userId)) {
            return cache.get(userId);
        }
        return new ExpUser(userId);
    }
}
