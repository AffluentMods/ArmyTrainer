package me.affluent.armytrainer.util;

import me.affluent.armytrainer.util.system.Utility;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class TimeCostUtil {

    private static List<Integer> time;
    private static long level;
    private static long powerGain;
    private static long minutes;
    private static long seconds;
    private static long medallionCost;
    private static long stoneCost;
    private static long titaniumCost;
    private static long woodCost;

    // Single Method
    public static HashMap<String, Long> getTroops(String name, int amount) {
        HashMap<String, Long> info = new HashMap<>();
        powerGain = 0;
        medallionCost = 0;
        stoneCost = 0;
        woodCost = 0;
        titaniumCost = 0;
        switch (name) {
            case "Footmen", "Archer", "Calvary" -> {
                woodCost = 50;
                titaniumCost = 50;
                powerGain = 2;
                seconds = 15;
            }
            case "Ballista" -> {
                stoneCost = 50;
                woodCost = 50;
                titaniumCost = 50;
                powerGain = 2;
                seconds = 15;
            }
            case "Gladiator", "Crossbowmen", "Cataphract" -> {
                woodCost = 100;
                titaniumCost = 100;
                medallionCost = 5;
                powerGain = 8;
                seconds = 30;
            }
            case "Catapult" -> {
                woodCost = 100;
                stoneCost = 100;
                titaniumCost = 100;
                medallionCost = 5;
                powerGain = 8;
                seconds = 30;
            }
        }
        seconds *= amount;
        woodCost *= amount;
        stoneCost *= amount;
        titaniumCost *= amount;
        medallionCost *= amount;
        powerGain *= amount;
        info.put("time", seconds);
        info.put("power", powerGain);
        if (medallionCost > 0) info.put("medallion", medallionCost);
        if (stoneCost > 0) info.put("stone", stoneCost);
        if (titaniumCost > 0) info.put("titanium", titaniumCost);
        if (woodCost > 0) info.put("wood", woodCost);
        return info;
    }

    public static HashMap<String, Long> getTraps(String name, int amount) {
        HashMap<String, Long> info = new HashMap<>();
        powerGain = 0;
        medallionCost = 0;
        stoneCost = 0;
        woodCost = 0;
        titaniumCost = 0;
        switch (name) {
            case "Trench" -> {
                stoneCost = 25;
                titaniumCost = 25;
                powerGain = 2;
                seconds = 10;
            }
            case "Tower" -> {
                woodCost = 25;
                titaniumCost = 25;
                powerGain = 2;
                seconds = 10;
            }
            case "Boulder" -> {
                stoneCost = 25;
                woodCost = 25;
                powerGain = 2;
                seconds = 10;
            }


            case "Spikes" -> {
                stoneCost = 50;
                titaniumCost = 50;
                powerGain = 3;
                seconds = 20;
            }
            case "ArcherTower" -> {
                woodCost = 50;
                titaniumCost = 50;
                powerGain = 3;
                seconds = 20;
            }
            case "SpikedBoulder" -> {
                stoneCost = 50;
                woodCost = 50;
                powerGain = 3;
                seconds = 20;
            }
        }
        seconds *= amount;
        woodCost *= amount;
        stoneCost *= amount;
        titaniumCost *= amount;
        powerGain *= amount;
        info.put("time", seconds);
        info.put("power", powerGain);
        if (stoneCost > 0) info.put("stone", stoneCost);
        if (titaniumCost > 0) info.put("titanium", titaniumCost);
        if (woodCost > 0) info.put("wood", woodCost);
        return info;
    }

    public static HashMap<String, Long> getResearch(String name, int currentLevel) {
        HashMap<String, Long> info = new HashMap<>();
        level = currentLevel + 1;
        if (level > 10) return null;
        powerGain = 0;
        medallionCost = 0;
        stoneCost = 0;
        woodCost = 0;
        titaniumCost = 0;
        switch (name) {
            case "ResearchSpeed" -> {
                stoneCost = 714;
                woodCost = 1142;
                titaniumCost = 429;
                medallionCost = 794;
                powerGain = 180;
                time = Arrays.asList(1, 8, 23, 66, 210, 360, 960, 5640, 16380, 40320);
            }
            case "Harvesting" -> {
                stoneCost = 58;
                woodCost = 58;
                titaniumCost = 42;
                medallionCost = 397;
                powerGain = 90;
                time = Arrays.asList(1, 4, 12, 34, 101, 300, 450, 2730, 8160, 20400);
            }
            case "VaultManagement" -> {
                stoneCost = 143;
                woodCost = 143;
                titaniumCost = 105;
                medallionCost = 278;
                powerGain = 63;
                time = Arrays.asList(3, 10, 22, 31, 71, 212, 630, 1920, 5700, 14280);
            }
            case "StoneProduction", "TitaniumProduction", "WoodProduction" -> {
                stoneCost = 143;
                woodCost = 143;
                titaniumCost = 105;
                medallionCost = 397;
                powerGain = 90;
                time = Arrays.asList(4, 15, 31, 43, 101, 303, 967, 2730, 8167, 20400);
            }
            case "Hauling" -> {
                stoneCost = 857;
                woodCost = 857;
                titaniumCost = 628;
                medallionCost = 476;
                powerGain = 108;
                time = Arrays.asList(4, 18, 37, 53, 121, 360, 1080, 3270, 10080, 24500);
            }
            case "ResourceHarvesting" -> {
                stoneCost = 857;
                woodCost = 857;
                titaniumCost = 628;
                medallionCost = 516;
                powerGain = 117;
                time = Arrays.asList(5, 20, 40, 57, 131, 390, 1170, 3540, 10410, 26640);
            }
            case "TrapCrafting" -> {
                stoneCost = 700;
                woodCost = 584;
                titaniumCost = 467;
                medallionCost = 756;
                powerGain = 24;
                time = List.of(1);
            }
            case "Trench", "Tower", "Boulders" -> {
                stoneCost = 11662;
                woodCost = 11662;
                titaniumCost = 8552;
                medallionCost = 18886;
                powerGain = 240;
                time = List.of(4);
            }
            case "TrapDefense", "TrapStrength", "TrapDurability" -> {
                stoneCost = 215;
                woodCost = 215;
                titaniumCost = 158;
                medallionCost = 158;
                powerGain = 36;
                time = Arrays.asList(2, 7, 11, 20, 46, 58, 420, 1230, 3690, 9240);
            }
            case "WallDurability" -> {
                stoneCost = 258;
                woodCost = 258;
                titaniumCost = 258;
                medallionCost = 315;
                powerGain = 72;
                time = Arrays.asList(3, 13, 23, 40, 93, 270, 810, 2460, 7380, 18480);
            }
            case "Spikes", "ArcherTower", "SpikedBoulder" -> {
                stoneCost = 46647;
                woodCost = 38872;
                titaniumCost = 31098;
                medallionCost = 75542;
                powerGain = 4797;
                time = List.of(260);
            }
            case "TrainingSpeed" -> {
                stoneCost = 700;
                woodCost = 700;
                titaniumCost = 520;
                medallionCost = 756;
                powerGain = 24;
                time = List.of(1);
            }
            case "SneakySpies" -> {
                stoneCost = 410;
                woodCost = 342;
                titaniumCost = 273;
                medallionCost = 360;
                powerGain = 82;
                time = Arrays.asList(3, 11, 17, 30, 89, 268, 780, 2400, 6360, 18060);
            }
            case "TravelSpeed" -> {
                stoneCost = 615;
                woodCost = 615;
                titaniumCost = 431;
                medallionCost = 360;
                powerGain = 82;
                time = Arrays.asList(3, 11, 17, 30, 89, 268, 780, 2400, 6360, 18060);
            }
            case "InfantryOffense", "SiegeOffense", "RangedOffense", "CalvaryOffense" -> {
                stoneCost = 819;
                woodCost = 819;
                titaniumCost = 601;
                medallionCost = 720;
                powerGain = 163;
                time = Arrays.asList(7, 22, 33, 60, 180, 540, 1590, 4800, 14460, 36180);
            }
            case "InfantryDefense", "SiegeDefense", "RangedDefense", "CalvaryDefense" -> {
                stoneCost = 1229;
                woodCost = 1229;
                titaniumCost = 901;
                medallionCost = 1080;
                powerGain = 245;
                time = Arrays.asList(7, 22, 33, 60, 180, 540, 1590, 4800, 14460, 36180);
            }
            case "Gladiator", "Crossbowmen", "Cataphract", "Catapult", "InfantryHealth", "SiegeHealth", "RangedHealth", "CalvaryHealth" -> {
                stoneCost = 128277;
                woodCost = 128277;
                titaniumCost = 94070;
                medallionCost = 207738;
                powerGain = 17149;
                time = List.of(3480);
            }
            case "Rebuild" -> {
                stoneCost = 3344;
                woodCost = 3344;
                titaniumCost = 2453;
                medallionCost = 527;
                powerGain = 199;
                time = Arrays.asList(17, 33, 56, 82, 124, 405, 1200, 3608, 10835, 27600);
            }
            case "Rebuild2" -> {
                stoneCost = 8360;
                woodCost = 8360;
                titaniumCost = 5016;
                medallionCost = 1317;
                powerGain = 496;
                time = Arrays.asList(41, 81, 140, 207, 310, 1020, 3000, 9020, 25980, 67680);
            }
            case "WallDefense", "WallDurability2", "WallRepair" -> {
                stoneCost = 6688;
                woodCost = 6688;
                titaniumCost = 4905;
                medallionCost = 1054;
                powerGain = 397;
                time = Arrays.asList(33, 66, 112, 165, 248, 800, 2407, 7200, 21660, 54120);
            }
            case "TrapDefense2", "TrapStrength2", "TrapDurability2" -> {
                stoneCost = 11704;
                woodCost = 11704;
                titaniumCost = 8583;
                medallionCost = 1843;
                powerGain = 695;
                time = Arrays.asList(58, 116, 200, 288, 435, 1410, 4200, 12420, 37620, 94860);
            }
            case "GatheringPriority" -> {
                stoneCost = 10898;
                woodCost = 8718;
                titaniumCost = 10898;
                medallionCost = 32119;
                powerGain = 239;
                time = List.of(10);
            }
            case "FootmenCost", "ArcherCost", "CalvaryCost", "BallistaCost" -> {
                stoneCost = 694;
                woodCost = 555;
                titaniumCost = 694;
                medallionCost = 2987;
                powerGain = 192;
                time = Arrays.asList(8, 12, 16, 24, 40, 118, 197, 465, 625, 1170);
            }
            case "GladiatorCost", "CrossbowmenCost", "CataphractCost", "CatapultCost" -> {
                stoneCost = 4332;
                woodCost = 3466;
                titaniumCost = 4332;
                medallionCost = 14933;
                powerGain = 766;
                time = Arrays.asList(31, 48, 63, 95, 160, 470, 805, 1885, 2520, 4730);
            }
            case "FieldDoctor" -> {
                stoneCost = 3639;
                woodCost = 6064;
                titaniumCost = 4852;
                medallionCost = 26132;
                powerGain = 3190;
                time = Arrays.asList(128, 189, 263, 390, 660, 1975, 3180, 7920, 10320, 19740);
            }
            case "TravelSpeed2" -> {
                stoneCost = 4332;
                woodCost = 3466;
                titaniumCost = 4332;
                medallionCost = 18666;
                powerGain = 3828;
                time = Arrays.asList(155, 240, 311, 470, 788, 2310, 3884, 9120, 12420, 23700);
            }
            case "RallyPriority" -> {
                stoneCost = 98078;
                woodCost = 98078;
                titaniumCost = 32693;
                medallionCost = 240892;
                powerGain = 6608;
                time = List.of(267);
            }
        }
        minutes = time.get(currentLevel);
        for (int i = 1; i < level; i++) {
            switch (i) {
                case 2 -> {
                    stoneCost *= 3.3;
                    woodCost *= 3.3;
                    titaniumCost *= 3.3;
                    medallionCost *= 6.6;
                    powerGain *= 2.75;
                }
                case 3 -> {
                    stoneCost *= 3;
                    woodCost *= 3;
                    titaniumCost *= 3;
                    medallionCost *= 3;
                    powerGain *= 1.8;
                }
                case 4 -> {
                    stoneCost *= 3.3;
                    woodCost *= 3.3;
                    titaniumCost *= 3.3;
                    medallionCost *= 6.6;
                    powerGain *= 3;
                }
                case 5 -> {
                    stoneCost *= 2.5;
                    woodCost *= 2.5;
                    titaniumCost *= 2.5;
                    medallionCost *= 2.5;
                    powerGain *= 3;
                }
                case 6, 8 -> {
                    stoneCost *= 2;
                    woodCost *= 2;
                    titaniumCost *= 2;
                    medallionCost *= 2;
                    powerGain *= 3;
                }
                case 7 -> {
                    stoneCost *= 5;
                    woodCost *= 5;
                    titaniumCost *= 5;
                    medallionCost *= 2.5;
                    powerGain *= 3;
                }
                case 9 -> {
                    stoneCost *= 2;
                    woodCost *= 2;
                    titaniumCost *= 2;
                    medallionCost *= 4;
                    powerGain *= 3;
                }
                case 10 -> {
                    stoneCost *= 2;
                    woodCost *= 2;
                    titaniumCost *= 2;
                    medallionCost *= 2.25;
                    powerGain *= 2.5;
                }
            }
        }
        info.put("time", minutes);
        info.put("level", level);
        info.put("power", powerGain);
        if (stoneCost > 0) info.put("stone", stoneCost);
        if (woodCost > 0) info.put("wood", woodCost);
        if (titaniumCost > 0) info.put("titanium", titaniumCost);
        if (medallionCost > 0) info.put("medallion", medallionCost);
        return info;
    }

    public static HashMap<String, Long> getTroopHealing(String troop, int amount) {
        woodCost = 0;
        stoneCost = 0;
        powerGain = 0;
        titaniumCost = 0;
        HashMap<String, Long> info = new HashMap<>();
        double healingSeconds = 0;
        switch (troop) {
            case "footmen" -> {
                woodCost = 15;
                titaniumCost = 15;
                powerGain = 2;
                healingSeconds = 0.25;
            }
            case "archer" -> {
                woodCost = 15;
                stoneCost =  15;
                powerGain = 2;
                healingSeconds = 0.25;
            }
            case "calvary" -> {
                stoneCost = 15;
                titaniumCost = 15;
                powerGain = 2;
                healingSeconds = 0.25;
            }
            case "ballista" -> {
                woodCost = 15;
                titaniumCost = 15;
                stoneCost = 15;
                powerGain = 2;
                healingSeconds = 0.25;
            }
            case "gladiator" -> {
                woodCost = 30;
                titaniumCost = 30;
                powerGain = 8;
                healingSeconds = 0.5;
            }
            case "crossbowmen" -> {
                woodCost = 30;
                stoneCost =  30;
                powerGain = 8;
                healingSeconds = 0.5;
            }
            case "cataphract" -> {
                stoneCost = 30;
                titaniumCost = 30;
                powerGain = 8;
                healingSeconds = 0.5;
            }
            case "catapult" -> {
                woodCost = 30;
                titaniumCost = 30;
                stoneCost = 30;
                powerGain = 8;
                healingSeconds = 0.5;
            }
        }
        healingSeconds *= amount;
        powerGain *= amount;
        woodCost *= amount;
        stoneCost *= amount;
        titaniumCost *= amount;
        info.put("healing", (long) healingSeconds);
        info.put("power", powerGain);
        info.put("stone", stoneCost);
        info.put("titanium", titaniumCost);
        info.put("wood", woodCost);
        return info;
    }

    public static HashMap<String, Long> getTrapHealing(String trap, int amount) {
        woodCost = 0;
        stoneCost = 0;
        powerGain = 0;
        titaniumCost = 0;
        HashMap<String, Long> info = new HashMap<>();
        double healingSeconds = 0;
        switch (trap) {
            case "trench" -> {
                stoneCost = 8;
                titaniumCost = 8;
                powerGain = 2;
                healingSeconds = 0.25;
            }
            case "tower" -> {
                woodCost = 8;
                titaniumCost = 8;
                powerGain = 2;
                healingSeconds = 0.25;
            }
            case "boulder" -> {
                woodCost = 8;
                stoneCost = 8;
                powerGain = 2;
                healingSeconds = 0.25;
            }
            case "spikes" -> {
                stoneCost = 15;
                titaniumCost = 15;
                powerGain = 3;
                healingSeconds = 0.5;
            }
            case "archerTower" -> {
                woodCost = 15;
                titaniumCost = 15;
                powerGain = 3;
                healingSeconds = 0.5;
            }
            case "spikedBoulder" -> {
                woodCost = 15;
                stoneCost = 15;
                powerGain = 3;
                healingSeconds = 0.5;
            }
        }
        woodCost *= amount;
        powerGain *= amount;
        stoneCost *= amount;
        titaniumCost *= amount;
        healingSeconds *= amount;
        info.put("healing", (long) healingSeconds);
        info.put("power", powerGain);
        info.put("stone", stoneCost);
        info.put("titanium", titaniumCost);
        info.put("wood", woodCost);
        return info;
    }

    public static HashMap<String, Long> getBuildings(String name, int currentLevel) {
        HashMap<String, Long> info = new HashMap<>();
        level = currentLevel + 1;
        if (level > 25) return null;
        powerGain = 0;
        medallionCost = 0;
        stoneCost = 0;
        woodCost = 0;
        titaniumCost = 0;
        switch (name) {
            case "WoodMill" -> {
                stoneCost = 150;
                titaniumCost = 150;
                powerGain = 5;
                minutes = 1;
            }
            case "StoneQuarry" -> {
                woodCost = 150;
                titaniumCost = 150;
                powerGain = 5;
                minutes = 1;
            }
            case "TitaniumMine" -> {
                woodCost = 150;
                stoneCost = 150;
                powerGain = 5;
                minutes = 1;
            }
            case "Mint" -> {
                stoneCost = 360;
                woodCost = 360;
                titaniumCost = 264;
                powerGain = 12;
                minutes = 1;
            }
            case "Vault" -> {
                stoneCost = 635;
                woodCost = 635;
                titaniumCost = 460;
                powerGain = 6;
                minutes = 2;
            }
            case "ResearchCenter" -> {
                stoneCost = 698;
                woodCost = 558;
                titaniumCost = 837;
                powerGain = 32;
                minutes = 2;
            }
            case "Barracks" -> {
                stoneCost = 840;
                woodCost = 525;
                titaniumCost = 315;
                powerGain = 18;
                minutes = 2;
            }
            case "Prison" -> {
                stoneCost = 1125;
                woodCost = 1125;
                titaniumCost = 1125;
                powerGain = 25;
                minutes = 2;
            }
            case "Shrine" -> {
                stoneCost = 2430;
                woodCost = 2430;
                titaniumCost = 1458;
                powerGain = 29;
            }
            case "Castle" -> {
                stoneCost = 2430;
                woodCost = 2430;
                titaniumCost = 1458;
                powerGain = 50;
                minutes = 4;
            }
            case "CastleWall" -> {
                stoneCost = 2025;
                woodCost = 1688;
                titaniumCost = 1350;
                powerGain = 40;
                minutes = 2;
            }
            case "TradingGateway" -> {
                stoneCost = 720;
                woodCost = 720;
                titaniumCost = 432;
                powerGain = 13;
                minutes = 1;
            }
            case "Hospital" -> {
                stoneCost = 150;
                woodCost = 240;
                titaniumCost = 90;
                powerGain = 12;
                minutes = 2;
            }
            case "Forge" -> {
                stoneCost = 638;
                woodCost = 638;
                titaniumCost = 638;
                powerGain = 15;
                minutes = 1;
            }
            case "WatchTower" -> {
                stoneCost = 418;
                woodCost = 495;
                titaniumCost = 743;
                powerGain = 15;
                minutes = 2;
            }
            case "Embassy" -> {
                stoneCost = 885;
                woodCost = 885;
                titaniumCost = 513;
                powerGain = 32;
                minutes = 1;
            }
            case "Tavern" -> {
                stoneCost = 745;
                woodCost = 745;
                titaniumCost = 467;
                medallionCost = 100;
                powerGain = 208;
                minutes = 5;
            }
        }
        for (int i = 0; i < level; i++) {
            if (name.equalsIgnoreCase("Tavern")) {
                if (i < 10) {
                    stoneCost *= 1.5;
                    titaniumCost *= 1.5;
                    medallionCost *= 1.5;
                    woodCost *= 1.5;
                } else {
                    stoneCost *= 2;
                    titaniumCost *= 2;
                    medallionCost *= 2;
                    woodCost *= 2;
                }
                if (Utility.isBetween(i, 0, 4)) minutes *= 2;
                else if (Utility.isBetween(i, 5, 7)) minutes *= 1.825;
                else minutes *= 1.5;
            } else {
                if (i < 25) {
                    stoneCost *= 1.5;
                    titaniumCost *= 1.5;
                    medallionCost *= 1.5;
                    woodCost *= 1.5;
                } else {
                    stoneCost *= 2;
                    titaniumCost *= 2;
                    medallionCost *= 2;
                    woodCost *= 2;
                }
                if (Utility.isBetween(i, 0, 6)) minutes *= 2;
                else if (Utility.isBetween(i, 7, 11)) minutes *= 1.825;
                else if (Utility.isBetween(i, 12, 21)) minutes *= 1.35;
                else minutes *= 1.5;
            }
            powerGain += (Math.pow(powerGain, 1.0325) * 1.078 / (Math.pow(powerGain, 0.1598234))) + 1;
        }
        info.put("time", minutes);
        info.put("level", level);
        info.put("power", powerGain);
        if (stoneCost > 0) info.put("stone", stoneCost);
        if (woodCost > 0) info.put("wood", woodCost);
        if (titaniumCost > 0) info.put("titanium", titaniumCost);
        if (medallionCost > 0) info.put("medallion", medallionCost);
        return info;
    }
}
