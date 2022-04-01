package me.affluent.armytrainer.util;

import me.affluent.armytrainer.ArmyTrainer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class VIPUtil {

    private static final HashMap<String, Integer> level = new HashMap<>();
    private static final HashMap<String, Integer> exp = new HashMap<>();

    public static int getVipLevel(String userId) {
        if (level.containsKey(userId)) return level.get(userId);
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT level FROM vip WHERE userId=?", userId)) {
            if (rs.next()) return rs.getInt("level");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static int getVipExp(String userId) {
        if (exp.containsKey(userId)) return exp.get(userId);
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT exp FROM vip WHERE userId=?", userId)) {
            if (rs.next()) return rs.getInt("exp");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static boolean isMaxed(String userId) {
        return getVipLevel(userId) >= 20;
    }

    public static void addVipLevel(String userId, int levelAmount) {

    }

    public static void addVipExp(String userId, int expAmount) {
        if (isMaxed(userId)) {
            return;
        }
        setVipExp();
    }

    public static void setVipLevel(String userId, int newLevel) {
        level.put(userId, newLevel);
        ArmyTrainer.getBot().getDatabase().update("UPDATE vip SET level=? WHERE userId=?", newLevel, userId);
    }

    public static void setVipExp(String userId, int newExp) {
        exp.put(userId, newExp);
        ArmyTrainer.getBot().getDatabase().update("UPDATE vip SET exp=? WHERE userId=?", newExp, userId);
    }
}
