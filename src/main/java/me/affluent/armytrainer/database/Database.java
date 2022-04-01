package me.affluent.armytrainer.database;

import java.sql.*;

public class Database {

    private Connection con;
    public static long updates = 0;
    public static long queries = 0;

    public Database() {
        mySQL();
    }

    private void mySQL() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String password = "YgbWq/6uxF]](Znd";
            String database = "ArmyTrainer";
            String ip = "localhost";
            con = DriverManager
                    .getConnection("jdbc:mysql://" + ip + ":3306/" + database + "?autoReconnect=true", "scriptuser",
                            password);
            System.out.println("[INTERN INFO] Database connection initialized to " + ip + "/" + database);
            setup();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void ct(String tn, String ts) {
        update("CREATE TABLE IF NOT EXISTS " + tn + " (" + ts + ");");
    }

    private void setup() {
        ct("profiles",
                "ID INT PRIMARY KEY AUTO_INCREMENT, userId VARCHAR(64) NOT NULL, prefix VARCHAR(4) NOT NULL, language" +
                " VARCHAR(6) NOT NULL, gender int(3) NOT NULL, clan int NOT NULL");
        ct("exp", "userId VARCHAR(64) NOT NULL, level bigint(64) NOT NULL, experience VARCHAR(128) NOT NULL");
        ct("economy", "userId VARCHAR(64) NOT NULL, crystals int NOT NULL, medallions VARCHAR(128) NOT NULL, " +
                "stone VARCHAR(128) NOT NULL, titanium VARCHAR(128) NOT NULL, wood VARCHAR(128) NOT NULL");
        ct("buildings", "userId VARCHAR(64) NOT NULL, buildingLevel int NOT NULL, buildingName VARCHAR(64) NOT NULL");
        ct("researches", "userId VARCHAR(64) NOT NULL, researchLevel int NOT NULL, researchName VARCHAR(64) NOT NULL");
        ct("cooldowns",
                "userId VARCHAR(64) NOT NULL, cooldownName VARCHAR(64) NOT NULL, cooldownEnd VARCHAR(48) NOT NULL");
        ct("userprefix", "userId VARCHAR(64) NOT NULL, prefix VARCHAR(4) NOT NULL");
        ct("userbadges", "userId VARCHAR(64) NOT NULL, badge VARCHAR(16) NOT NULL");
        ct("codes",
                "userId VARCHAR(64) NOT NULL, code VARCHAR(16) NOT NULL");
        ct("power",
                "userId VARCHAR(64) NOT NULL, totalPower INT NOT NULL");
        //
        ct("settings",
                "userId VARCHAR(64) NOT NULL, confirm VARCHAR(16) NOT NULL, format VARCHAR(16) NOT NULL, numberFormat VARCHAR(16) NOT NULL, " +
                        "colorPlayer VARCHAR(16) NOT NULL, colorEnemy VARCHAR(16) NOT NULL, colorAlly VARCHAR(16) NOT NULL");
        ct("tutorial",
                "userId VARCHAR(64) NOT NULL, initialReward VARCHAR(16) NOT NULL, finalReward VARCHAR(16) NOT NULL");
        ct("bans", "userId VARCHAR(64) NOT NULL, todate VARCHAR(64) NOT NULL");
        ct("votes", "userId VARCHAR(64) NOT NULL, until bigint(24) NOT NULL");
        ct("gameTime", "hour VARCHAR(8) NOT NULL, day VARCHAR(8) NOT NULL, month VARCHAR(8) NOT NULL, year VARCHAR(8) NOT NULL");
        ct("lang_messages",
                "ID INT PRIMARY KEY AUTO_INCREMENT, msgID VARCHAR(32) NOT NULL, langCode VARCHAR(6) NOT NULL, message" +
                        " VARCHAR(2048) NOT NULL");
    }

    public static long ping = -1L;

    private void updatePing(long now) {
        ping = System.currentTimeMillis() - now;
    }

    public ResultSet query(String sql, Object... parameterObjects) {
        queries++;
        try {
            long now = System.currentTimeMillis();
            PreparedStatement ps = con.prepareStatement(sql);
            int parameterIndex = 1;
            for (Object o : parameterObjects) {
                ps.setObject(parameterIndex, o);
                parameterIndex++;
            }
            ResultSet rs = ps.executeQuery();
            updatePing(now);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void update(String sql, Object... parameterObjects) {
        updates++;
        try {
            long now = System.currentTimeMillis();
            PreparedStatement ps = con.prepareStatement(sql);
            int parameterIndex = 1;
            for (Object o : parameterObjects) {
                ps.setObject(parameterIndex, o);
                parameterIndex++;
            }
            ps.executeUpdate();
            updatePing(now);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Connection getCon() {
        return con;
    }
}