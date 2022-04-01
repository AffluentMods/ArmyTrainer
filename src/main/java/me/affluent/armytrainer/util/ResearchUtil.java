package me.affluent.armytrainer.util;

import me.affluent.armytrainer.ArmyTrainer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResearchUtil {

    private static HashMap<String, ResearchUtil> cache = new HashMap<>();

    private final String userId;
    private Map<String, Integer> researches;

    public static void clearCache() {
        cache.clear();
    }

    private ResearchUtil(String userId) {
        this.userId = userId;
        load();
        cache.put(userId, this);
    }

    private void load() {
        researches = new HashMap<>();
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE userId=?;", userId)) {
            while (rs.next()) {
                researches.put(rs.getString("researchName"), rs.getInt("researchLevel"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    private String getUserId() {
        return userId;
    }

    public Map<String, Integer> getResearches() {
        return researches;
    }

    public Map<String, Integer> getEconomyResearches() {
        String str = "economy";
        Map<String, Integer> list = this.researches;
        for (String name : list.keySet()) {
            if (str.contains(name)) {

            }
        }
    }

    public int getResearchLevel(String name) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE '" + name + "' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchName");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, name);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public void setResearchLevel(String name, int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE '" + name + "' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchName=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, name);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public int getEconomyResearchSpeedLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'economyResearchSpeedLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "economyResearchSpeedLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setEconomyResearchSpeedLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'economyResearchSpeedLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "economyResearchSpeedLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getEconomyHarvestingLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'economyHarvestingLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "economyHarvestingLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setEconomyHarvestingLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'economyHarvestingLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "economyHarvestingLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getEconomyVaultManagementLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'economyVaultManagementLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "economyVaultManagementLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setEconomyVaultManagementLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'economyVaultManagementLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "economyVaultManagementLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getEconomyStoneProductionLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'economyStoneProductionLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "economyStoneProductionLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setEconomyStoneProductionLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'economyStoneProductionLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "economyStoneProductionLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getEconomyWoodProductionLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'economyWoodProductionLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "economyWoodProductionLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setEconomyWoodProductionLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'economyWoodProductionLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "economyWoodProductionLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getEconomyTitaniumProductionLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'economyTitaniumProductionLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "economyTitaniumProductionLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setEconomyTitaniumProductionLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'economyTitaniumProductionLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "economyTitaniumProductionLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getEconomyHaulingLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'economyHaulingLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "economyHaulingLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setEconomyHaulingLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'economyHaulingLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "economyHaulingLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getEconomyResourceHarvestingLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'economyResourceHarvestingLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "economyResourceHarvestingLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setEconomyResourceHarvestingLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'economyResourceHarvestingLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "economyResourceHarvestingLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }
    
    public int getDefenseTrapCraftingLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseTrapCraftingLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseTrapCraftingLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setDefenseTrapCraftingLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseTrapCraftingLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseTrapCraftingLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getDefenseTrenchLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseTrenchLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseTrenchLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setDefenseTrenchLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseTrenchLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseTrenchLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getDefenseTowerLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseTowerLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseTowerLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setDefenseTowerLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseTowerLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseTowerLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getDefenseBouldersLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseBouldersLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseBouldersLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setDefenseBouldersLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseBouldersLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseBouldersLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getDefenseTrapDefenseLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseTrapDefenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseTrapDefenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setDefenseTrapDefenseLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseTrapDefenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseTrapDefenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getDefenseTrapStrengthLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseTrapStrengthLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseTrapStrengthLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setDefenseTrapStrengthLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseTrapStrengthLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseTrapStrengthLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getDefenseTrapDurabilityLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseTrapDurabilityLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseTrapDurabilityLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setDefenseTrapDurabilityLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseTrapDurabilityLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseTrapDurabilityLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getDefenseWallDurabilityLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseWallDurabilityLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseWallDurabilityLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setDefenseWallDurabilityLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseWallDurabilityLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseWallDurabilityLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getDefenseSpikesLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseSpikesLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseSpikesLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setDefenseSpikesLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseSpikesLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseSpikesLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getDefenseArcherTowerLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseArcherTowerLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseArcherTowerLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setDefenseArcherTowerLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseArcherTowerLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseArcherTowerLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getDefenseSpikedBoulderLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseSpikedBoulderLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseSpikedBoulderLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setDefenseSpikedBoulderLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'defenseSpikedBoulderLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "defenseSpikedBoulderLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyTrainingSpeedLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingSpeedLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingSpeedLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyTrainingSpeedLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingSpeedLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingSpeedLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmySneakySpiesLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armySneakySpiesLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armySneakySpiesLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmySneakySpiesLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armySneakySpiesLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armySneakySpiesLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmySwiftnessLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armySwiftnessLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armySwiftnessLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmySwiftnessLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armySwiftnessLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armySwiftnessLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyInfantryOffenseLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyInfantryOffenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyInfantryOffenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyInfantryOffenseLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyInfantryOffenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyInfantryOffenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmySiegeOffenseLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armySiegeOffenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armySiegeOffenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmySiegeOffenseLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armySiegeOffenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armySiegeOffenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyRangedOffenseLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyRangedOffenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyRangedOffenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyRangedOffenseLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyRangedOffenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyRangedOffenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyCalvaryOffenseLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyCalvaryOffenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyCalvaryOffenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyCalvaryOffenseLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyCalvaryOffenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyCalvaryOffenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyInfantryDefenseLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyInfantryDefenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyInfantryDefenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyInfantryDefenseLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyInfantryDefenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyInfantryDefenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmySiegeDefenseLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armySiegeDefenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armySiegeDefenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmySiegeDefenseLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armySiegeDefenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armySiegeDefenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyRangedDefenseLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyRangedDefenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyRangedDefenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyRangedDefenseLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyRangedDefenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyRangedDefenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyCalvaryDefenseLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyCalvaryDefenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyCalvaryDefenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyCalvaryDefenseLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyCalvaryDefenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyCalvaryDefenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyGladiatorLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyGladiatorLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyGladiatorLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyGladiatorLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyGladiatorLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyGladiatorLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyCrossbowmenLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyCrossbowmenLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyCrossbowmenLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyCrossbowmenLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyCrossbowmenLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyCrossbowmenLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyCataphractLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyCataphractLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyCataphractLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyCataphractLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyCataphractLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyCataphractLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyCatapultLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyCatapultLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyCatapultLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyCatapultLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyCatapultLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyCatapultLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyInfantryHealthLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyInfantryHealthLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyInfantryHealthLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyInfantryHealthLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyInfantryHealthLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyInfantryHealthLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmySiegeHealthLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armySiegeHealthLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armySiegeHealthLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmySiegeHealthLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armySiegeHealthLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armySiegeHealthLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyRangedHealthLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyRangedHealthLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyRangedHealthLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyRangedHealthLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyRangedHealthLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyRangedHealthLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyCalvaryHealthLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyCalvaryHealthLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyCalvaryHealthLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyCalvaryHealthLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyCalvaryHealthLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyCalvaryHealthLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getWallDefensesRebuildLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'wallDefensesRebuildLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "wallDefensesRebuildLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setWallDefensesRebuildLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'wallDefensesRebuildLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "wallDefensesRebuildLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getWallDefensesWallDefenseLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'wallDefensesWallDefenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "wallDefensesWallDefenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setWallDefensesWallDefenseLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'wallDefensesWallDefenseLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "wallDefensesWallDefenseLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getWallDefenseWallDurabilityLevel2() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'wallDefenseWallDurabilityLevel2' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "wallDefenseWallDurabilityLevel2");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setWallDefenseWallDurabilityLevel2(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'wallDefenseWallDurabilityLevel2' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "wallDefenseWallDurabilityLevel2");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getWallDefenseWallRepairLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'wallDefenseWallRepairLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "wallDefenseWallRepairLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setWallDefenseWallRepairLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'wallDefenseWallRepairLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "wallDefenseWallRepairLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getWallDefenseRebuildLevel2() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'wallDefenseRebuildLevel2' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "wallDefenseRebuildLevel2");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setWallDefenseRebuildLevel2(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'wallDefenseRebuildLevel2' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "wallDefenseRebuildLevel2");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getWallDefenseTrapDefenseLevel2() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'wallDefenseTrapDefenseLevel2' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "wallDefenseTrapDefenseLevel2");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setWallDefenseTrapDefenseLevel2(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'wallDefenseTrapDefenseLevel2' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "wallDefenseTrapDefenseLevel2");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getWallDefenseTrapStrengthLevel2() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'wallDefenseTrapStrengthLevel2' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "wallDefenseTrapStrengthLevel2");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setWallDefenseTrapStrengthLevel2(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'wallDefenseTrapStrengthLevel2' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "wallDefenseTrapStrengthLevel2");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getWallDefenseTrapDurabilityLevel2() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'wallDefenseTrapDurabilityLevel2' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "wallDefenseTrapDurabilityLevel2");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setWallDefenseTrapDurabilityLevel2(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'wallDefenseTrapDurabilityLevel2' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "wallDefenseTrapDurabilityLevel2");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyTrainingGatheringPriorityLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingGatheringPriorityLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingGatheringPriorityLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyTrainingGatheringPriorityLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingGatheringPriorityLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingGatheringPriorityLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyTrainingFootmenCostLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingFootmenCostLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingFootmenCostLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyTrainingFootmenCostLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingFootmenCostLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingFootmenCostLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyTrainingArcherCostLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingArcherCostLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingArcherCostLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyTrainingArcherCostLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingArcherCostLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingArcherCostLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyTrainingCalvaryCostLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingCalvaryCostLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingCalvaryCostLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyTrainingCalvaryCostLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingCalvaryCostLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingCalvaryCostLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyTrainingBallistaCostLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingBallistaCostLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingBallistaCostLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyTrainingBallistaCostLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingBallistaCostLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingBallistaCostLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyTrainingGladiatorCostLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingGladiatorCostLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingGladiatorCostLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyTrainingGladiatorCostLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingGladiatorCostLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingGladiatorCostLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyTrainingCrossbowmenCostLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingCrossbowmenCostLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingCrossbowmenCostLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyTrainingCrossbowmenCostLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingCrossbowmenCostLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingCrossbowmenCostLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyTrainingCataphractCostLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingCataphractCostLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingCataphractCostLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyTrainingCataphractCostLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingCataphractCostLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingCataphractCostLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyTrainingCatapultCostLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingCatapultCostLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingCatapultCostLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyTrainingCatapultCostLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingCatapultCostLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingCatapultCostLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyTrainingFieldDoctorLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingFieldDoctorLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingFieldDoctorLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyTrainingFieldDoctorLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingFieldDoctorLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingFieldDoctorLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyTrainingSwiftnessLevel2() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingSwiftnessLevel2' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingSwiftnessLevel2");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyTrainingSwiftnessLevel2(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingSwiftnessLevel2' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingSwiftnessLevel2");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }

    public int getArmyTrainingRallyPriorityLevel1() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingRallyPriorityLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                return rs.getInt("researchLevel");
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingRallyPriorityLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
        return 0;
    }

    public void setArmyTrainingRallyPriorityLevel1(int level) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM researches WHERE 'armyTrainingRallyPriorityLevel1' IN (researchName) AND userId=?;", userId)) {
            if (rs.next()) {
                ArmyTrainer.getBot().getDatabase().update("UPDATE researches SET researchLevel=? WHERE userId=?", level, userId);
            } else {
                ArmyTrainer.getBot().getDatabase().update("INSERT INTO researches VALUES (?, ?, ?);", userId, 0, "armyTrainingRallyPriorityLevel1");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        cache();
    }
    
    private void cache() {
        cache.put(userId, this);
    }

    public static ResearchUtil getResearchUtil(String userId) {
        if (cache.containsKey(userId)) {
            return cache.get(userId);
        }
        return new ResearchUtil(userId);
    }
}
