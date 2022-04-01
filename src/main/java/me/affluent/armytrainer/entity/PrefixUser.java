package me.affluent.armytrainer.entity;

import me.affluent.armytrainer.ArmyTrainer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class PrefixUser {

    private static HashMap<String, PrefixUser> cache = new HashMap<>();

    private final String userId;
    private String prefix;

    public static void clearCache() {
        cache.clear();
    }

    private PrefixUser(String userId) {
        this.userId = userId;
        load();
        cache.put(userId, this);
    }

    private void load() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase()
                .query("SELECT prefix FROM userprefix WHERE userId=?;", userId)) {
            if (rs.next()) {
                prefix = rs.getString("prefix");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO userprefix VALUES (?, ?);", userId, "a.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
        cache();
        ArmyTrainer.getBot().getDatabase().update("UPDATE userprefix SET prefix=? WHERE userId=?;", prefix, userId);
    }

    private void cache() {
        cache.put(userId, this);
    }

    public static PrefixUser getPrefixUser(String userId) {
        if (cache.containsKey(userId)) {
            return cache.get(userId);
        }
        return new PrefixUser(userId);
    }
}
