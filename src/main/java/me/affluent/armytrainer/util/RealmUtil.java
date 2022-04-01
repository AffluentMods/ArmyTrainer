package me.affluent.armytrainer.util;

import me.affluent.armytrainer.ArmyTrainer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class GemTroveUtil {

    private static final HashMap<String, Integer> cache = new HashMap<>();


    public static int getMap() {

    }

    public static int getDiceRolls(String userId) {
        if (cache.containsKey(userId)) return cache.get(userId);
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT dice FROM items WHERE userId=?;", userId)) {
            if (rs.next()) return rs.getInt("dice");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public static void setDiceRolls(String userId, int diceRolls) {
        cache.put(userId, diceRolls);
        ArmyTrainer.getBot().getDatabase().update("UPDATE items SET dice=? WHERE userId=?;", diceRolls, userId);
    }

    public static void addDiceRolls(String userId, int diceRolls) {
        int oldDiceRolls = getDiceRolls(userId);
        setDiceRolls(userId, oldDiceRolls + diceRolls);
    }
}
