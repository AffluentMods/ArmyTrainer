package me.affluent.armytrainer.nodes;

import me.affluent.armytrainer.ArmyTrainer;
import me.affluent.armytrainer.entity.Player;
import me.affluent.armytrainer.util.system.Utility;

import java.awt.geom.Point2D;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CoordinatesUtil {

    private static HashMap<String, CoordinatesUtil> cache = new HashMap<>();

    private final String userId;
    private Point2D coordinates;
    private final HashMap<String, Point2D> playerCoordinates = new HashMap<>();
    private final HashMap<Point2D, Integer> impCoordinates = new HashMap<>();
    private final HashMap<Point2D, Integer> resourceCoordinates = new HashMap<>();
    private List<Point2D> allCoordinates = new ArrayList<>();

    public static void clearCache() {
        cache.clear();
    }

    private CoordinatesUtil(String userId) {
        this.userId = userId;
        load();
        cache.put(userId, this);
    }

    private void load() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM coordinates WHERE userId=?;", userId)) {
            if (rs.next()) {
                Point2D spawn = new Point2D.Double(rs.getInt("x"), rs.getInt("y"));
                coordinates.setLocation(spawn);
                allCoordinates.add(spawn);
            } else {
                System.out.println("[ERROR] NO COORDINATES SELECTED");
                Point2D spawn = getRandomCoordinates();
                setCoordinates(spawn);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM coordinates")) {
            while (rs.next()) {
                Point2D spawn = new Point2D.Double(rs.getInt("x"), rs.getInt("y"));
                playerCoordinates.put(rs.getString("userId"), spawn);
                allCoordinates.add(spawn);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    private String getUserId() {
        return userId;
    }

    public Point2D getCoordinates() {
        return coordinates;
    }

    public HashMap<Point2D, Integer> getImpCoordinates() {
        return impCoordinates;
    }

    public HashMap<Point2D, Integer> getResourceCoordinates() {
        return impCoordinates;
    }

    public void newPlayer() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM coordinates WHERE userId=?;", userId)) {
            if (!rs.next()) {
                Point2D spawn = getRandomCoordinates();
                coordinates.setLocation(spawn);
                playerCoordinates.put(userId, spawn);
                allCoordinates.add(spawn);
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO coordinates VALUES (?, ?, ?);", userId, spawn.getX(), spawn.getY());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setCoordinates(Integer x, Integer y) {
        Point2D spawn = new Point2D.Double(x, y);
        coordinates.setLocation(spawn);
        playerCoordinates.remove(userId);
        playerCoordinates.put(userId, spawn);
        allCoordinates.remove(spawn);
        allCoordinates.add(spawn);
        ArmyTrainer.getBot().getDatabase().update("DELETE FROM coordinates WHERE userId=?", userId);
        ArmyTrainer.getBot().getDatabase().update("INSERT INTO coordinates VALUES (?, ?, ?);", userId, spawn.getX(), spawn.getY());
    }

    public void setCoordinates(Point2D newCoordinates) {
        coordinates.setLocation(newCoordinates);
        playerCoordinates.remove(userId);
        playerCoordinates.put(userId, newCoordinates);
        allCoordinates.remove(newCoordinates);
        allCoordinates.add(newCoordinates);
        ArmyTrainer.getBot().getDatabase().update("DELETE FROM coordinates WHERE userId=?", userId);
        ArmyTrainer.getBot().getDatabase().update("INSERT INTO coordinates VALUES (?, ?, ?);", userId, newCoordinates.getX(), newCoordinates.getY());
    }

    public Object getRandomResourceNode() {
        int type = new Random().nextInt(5);
        int level = new Random().nextInt(1000);
        String resourceType = "";
        String resourceLevel = "";
        switch (type) {
            case 1 -> {
                resourceType = "Forest";
            }
            case 2 -> {
                resourceType = "Mountain";
            }
            case 3 -> {
                resourceType = "Rich Vein";
            }
            case 4 -> {
                resourceType = "Treasury";
            }
        }
        if (Utility.isBetween(level, 0, 224)) resourceLevel = "1";
        else if (Utility.isBetween(level, 225, 524)) resourceLevel = "2";
        else if (Utility.isBetween(level, 525, 749)) resourceLevel = "3";
        else if (Utility.isBetween(level, 750, 924)) resourceLevel = "4";
        else if (Utility.isBetween(level, 925, 999)) resourceLevel = "5";
        return ResourceNode.getResourceNode(resourceType + " " + resourceLevel);
    }

    public Point2D getRandomCoordinates() {
        int x = new Random().nextInt(1000);
        int y = new Random().nextInt(1000);
        Point2D spawn = new Point2D.Double(x, y);
        while (!possibleSpawn(spawn)) {
            x = new Random().nextInt(1000);
            y = new Random().nextInt(1000);
            spawn = new Point2D.Double(x, y);
        }
        return spawn;
    }

    public boolean possibleSpawn(Point2D coordinates) {
        return allCoordinates.contains(coordinates);
    }

    private void cache() {
        cache.put(userId, this);
    }

    public static CoordinatesUtil getCoordinatesUtil(String userId) {
        if (cache.containsKey(userId)) {
            return cache.get(userId);
        }
        return new CoordinatesUtil(userId);
    }
}
