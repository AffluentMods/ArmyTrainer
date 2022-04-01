package me.affluent.armytrainer.util;

import me.affluent.armytrainer.ArmyTrainer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class SettingsUtil {

    private static HashMap<String, SettingsUtil> cache = new HashMap<>();

    private final String userId;
    private String confirm;
    private String format;
    private String numberFormat;
    private String colorPlayer;
    private String colorEnemy;
    private String colorAlly;

    public static void clearCache() {
        cache.clear();
    }

    private SettingsUtil(String userId) {
        this.userId = userId;
        load();
        cache.put(userId, this);
    }

    private void load() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM settings WHERE userId=?;", userId)) {
            if (rs.next()) {
                confirm = rs.getString("confirm");
                format = rs.getString("format");
                numberFormat = rs.getString("numberFormat");
                colorPlayer = rs.getString("colorPlayer");
                colorEnemy = rs.getString("colorEnemy");
                colorAlly = rs.getString("colorAlly");
            } else {
                confirm = "on";
                format = "pc";
                numberFormat = "abbreviated";
                colorPlayer = "green";
                colorEnemy = "red";
                colorAlly = "blue";
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO settings VALUES (?, ?, ?, ?, ?, ?, ?);",
                        userId, confirm, format, numberFormat, colorPlayer, colorEnemy, colorAlly);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getUserId() {
        return userId;
    }

    public String getConfirm() {
        return confirm;
    }

    public String getFormat() {
        return format;
    }

    public String getNumberFormat() {
        return numberFormat;
    }

    public String getColorPlayer() {
        return colorPlayer;
    }

    public String getColorEnemy() {
        return colorEnemy;
    }

    public String getColorAlly() {
        return colorAlly;
    }

    public void setConfirm() {
        if (confirm.equalsIgnoreCase("off")) this.confirm = "on";
        else this.confirm = "off";
        cache();
        ArmyTrainer.getBot().getDatabase().update("UPDATE settings SET confirm=? WHERE userId=?;", confirm, userId);
    }

    public void setFormat() {
        if (format.equalsIgnoreCase("mobile")) this.format = "pc";
        else this.format = "mobile";
        cache();
        ArmyTrainer.getBot().getDatabase().update("UPDATE settings SET format=? WHERE userId=?;", format, userId);
    }

    public void setNumbersFormat() {
        if (numberFormat.equalsIgnoreCase("abbreviated")) this.numberFormat = "commas";
        else this.numberFormat = "abbreviated";
        cache();
        ArmyTrainer.getBot().getDatabase().update("UPDATE settings SET numberFormat=? WHERE userId=?;", numberFormat, userId);
    }

    public void setColorPlayer(String colorPlayer) {
        this.colorPlayer = colorPlayer;
        cache();
        ArmyTrainer.getBot().getDatabase().update("UPDATE settings SET colorPlayer=? WHERE userId=?;", colorPlayer, userId);
    }

    public void setColorEnemy(String colorEnemy) {
        this.colorEnemy = colorEnemy;
        cache();
        ArmyTrainer.getBot().getDatabase().update("UPDATE settings SET colorEnemy=? WHERE userId=?;", colorEnemy, userId);
    }

    public void setColorAlly(String colorAlly) {
        this.colorAlly = colorAlly;
        cache();
        ArmyTrainer.getBot().getDatabase().update("UPDATE settings SET colorAlly=? WHERE userId=?;", colorAlly, userId);
    }

    private void cache() {
        cache.put(userId, this);
    }

    public static SettingsUtil getSettings(String userId) {
        if (cache.containsKey(userId)) {
            return cache.get(userId);
        }
        return new SettingsUtil(userId);
    }
}
