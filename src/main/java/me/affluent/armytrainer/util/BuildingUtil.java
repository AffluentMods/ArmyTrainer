package me.affluent.armytrainer.util;

import me.affluent.armytrainer.ArmyTrainer;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildingUtil {

    private static HashMap<String, BuildingUtil> cache = new HashMap<>();

    private final String userId;
    private Map<Integer, String> buildings;

    private BuildingUtil(String userId) {
        this.userId = userId;
        load();
        cache.put(userId, this);
    }

    private void load() {
        buildings = new HashMap<>();
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM buildings WHERE userId=?;", userId)) {
            while (rs.next())  {
                buildings.put(rs.getInt("buildingLevel"), rs.getString("buildingName"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    private String getUserId() {
        return userId;
    }

    public Map<Integer, String> getBuildings() {
        return buildings;
    }

    public int getBuildingLevel(String name) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM buildings WHERE '" + name + "' IN (buildingName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("buildingName");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO buildings VALUES (?, ?);", userId, 0, name);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public void setBuildingLevel(String name, int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM buildings WHERE '" + name + "' IN (buildingName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE buildings SET buildingName=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO buildings VALUES (?, ?);", userId, 0, name);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void cache() {
        cache.put(userId, this);
    }

    public static BuildingUtil getBuildingUtil(String userId) {
        if (cache.containsKey(userId)) {
            return cache.get(userId);
        }
        return new BuildingUtil(userId);
    }
}
