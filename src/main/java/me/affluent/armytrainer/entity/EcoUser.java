package me.affluent.armytrainer.entity;

import me.affluent.armytrainer.ArmyTrainer;
import me.affluent.armytrainer.util.system.FormatUtil;

import java.lang.reflect.WildcardType;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class EcoUser {

    private static HashMap<String, EcoUser> cache = new HashMap<>();

    private final String userId;
    private BigInteger medallions;
    private BigInteger wood;
    private BigInteger stone;
    private BigInteger titanium;
    private int crystals;

    public static void clearCache() {
        cache.clear();
    }

    private EcoUser(String userId) {
        this.userId = userId;
        load();
        cache.put(userId, this);
    }

    private void load() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM economy WHERE userId=?;", userId)) {
            if (rs.next()) {
                crystals = rs.getInt("crystals");
                String medallionsString = rs.getString("medallions");
                String woodString = rs.getString("wood");
                String stoneString = rs.getString("stone");
                String titaniumString = rs.getString("titanium");
                medallions = new BigInteger(medallionsString);
                wood = new BigInteger(woodString);
                stone = new BigInteger(stoneString);
                titanium = new BigInteger(titaniumString);
            } else {
                crystals = 0;
                medallions = BigInteger.ZERO;
                wood = BigInteger.ZERO;
                stone = BigInteger.ZERO;
                titanium = BigInteger.ZERO;
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO economy VALUES (?, ?, ?, ?, ?, ?);", userId, 0, "0", "0", "0", "0");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public String getUserId() {
        return userId;
    }

    public BigInteger getmedallions() {
        return medallions;
    }

    public BigInteger getWood() {
        return wood;
    }

    public BigInteger getStone() {
        return stone;
    }

    public BigInteger getTitanium() {
        return titanium;
    }

    public int getCrystals() {
        return crystals;
    }

    public void removemedallions(long amount) {
        removemedallions(new BigInteger(String.valueOf(amount)));
    }

    public void removemedallions(BigInteger amount) {
        setmedallions(this.medallions.subtract(amount));
    }

    public void addmedallions(BigInteger amount) {
        setmedallions(this.medallions.add(amount)); 
    }
    
    public void addmedallions(long amount) {
        addmedallions(new BigInteger(String.valueOf(amount)));
    }
    
    public void setmedallions(BigInteger medallions) {
        this.medallions = medallions;
        cache();
        ArmyTrainer.getBot().getDatabase().update("UPDATE economy SET medallions=? WHERE userId=?;", medallions.toString(), userId);
    }

    public void removeWood(long amount) {
        removeWood(new BigInteger(String.valueOf(amount)));
    }

    public void removeWood(BigInteger amount) {
        setWood(this.wood.subtract(amount));
    }

    public void addWood(BigInteger amount) {
        setWood(this.wood.add(amount));
    }

    public void addWood(long amount) {
        addWood(new BigInteger(String.valueOf(amount)));
    }
    
    public void setWood(BigInteger wood) {
        this.wood = wood;
        cache();
        ArmyTrainer.getBot().getDatabase().update("UPDATE economy SET wood=? WHERE userId=?;", wood.toString(), userId);
    }

    public void removeTitanium(long amount) {
        removeTitanium(new BigInteger(String.valueOf(amount)));
    }

    public void removeTitanium(BigInteger amount) {
        setTitanium(this.titanium.subtract(amount));
    }

    public void addTitanium(BigInteger amount) {
        setTitanium(this.titanium.add(amount));
    }

    public void addTitanium(long amount) {
        addTitanium(new BigInteger(String.valueOf(amount)));
    }
    
    public void setTitanium(BigInteger titanium) {
        this.titanium = titanium;
        cache();
        ArmyTrainer.getBot().getDatabase().update("UPDATE economy SET titanium=? WHERE userId=?;", titanium.toString(), userId);
    }

    public void removeStone(long amount) {
        removeStone(new BigInteger(String.valueOf(amount)));
    }

    public void removeStone(BigInteger amount) {
        setStone(this.stone.subtract(amount));
    }

    public void addStone(BigInteger amount) {
        setStone(this.stone.add(amount));
    }

    public void addStone(long amount) {
        addStone(new BigInteger(String.valueOf(amount)));
    }
    
    public void setStone(BigInteger stone) {
        this.stone = stone;
        cache();
        ArmyTrainer.getBot().getDatabase().update("UPDATE economy SET stone=? WHERE userId=?;", stone.toString(), userId);
    }

    public void removeCrystals(long amount) {
        setCrystals((int) (crystals - amount));
    }

    public void addCrystals(long amount) {
        setCrystals((int) (crystals + amount));
    }

    public void setCrystals(int crystals) {
        if (crystals < 0) crystals = 0;
        this.crystals = crystals;
        cache();
        ArmyTrainer.getBot().getDatabase().update("UPDATE economy SET crystals=? WHERE userId=?;", crystals, userId);
    }

    public String getmedallionsAbr() {
        return FormatUtil.formatAbbreviated(this.medallions.toString());
    }
    
    public String getmedallionsCom() {
        return FormatUtil.formatCommas(this.medallions.toString());
    }

    public String getWoodAbr() {
        return FormatUtil.formatAbbreviated(this.wood.toString());
    }

    public String getWoodCom() {
        return FormatUtil.formatCommas(this.wood.toString());
    }

    public String getTitaniumAbr() {
        return FormatUtil.formatAbbreviated(this.titanium.toString());
    }

    public String getTitaniumCom() {
        return FormatUtil.formatCommas(this.titanium.toString());
    }

    public String getStoneAbr() {
        return FormatUtil.formatAbbreviated(this.stone.toString());
    }

    public String getStoneCom() {
        return FormatUtil.formatCommas(this.stone.toString());
    }

    public String getCrystalsAbr() {
        return FormatUtil.formatAbbreviated(this.crystals);
    }

    public String getCrystalsCom() {
        return FormatUtil.formatCommas(this.crystals);
    }
    
    private void cache() {
        cache.put(userId, this);
    }

    public static EcoUser getEcoUser(String userId) {
        if (cache.containsKey(userId)) {
            return cache.get(userId);
        }
        return new EcoUser(userId);
    }
}
