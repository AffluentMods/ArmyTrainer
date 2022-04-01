package me.affluent.armytrainer.nodes;

import me.affluent.armytrainer.entity.Player;
import me.affluent.armytrainer.enums.Material;
import me.affluent.armytrainer.enums.Rarity;

import java.util.HashMap;

public abstract class Nodes {

    private final String resourceName;
    private final int resourceLevel;
    private final int resourceAmount;
    private int resourcesLeft;
    private Player resourceGather;
    private final Material resource;

    private final String impName;
    private final int impLevel;
    private final int impDamage;
    private final int impHealth;
    private final int impDefense;

    private final String banditName;
    private final int banditLevel;
    private final Rarity banditRarity;
    private final int banditDamage;
    private final int banditHealth;
    private final int banditDefense;

    private static final HashMap<String, Nodes> resourceNodes = new HashMap<>();
    private static final HashMap<String, Nodes> impNodes = new HashMap<>();
    private static final HashMap<String, Nodes> banditNodes = new HashMap<>();

    public Nodes(String resourceName, int resourceLevel, int resourcesLeft, Player resourceGather, Material resource, boolean addToList) {
        this.resourceName = resourceName;
        this.resourceLevel = resourceLevel;
        this.resourcesLeft = resourcesLeft;
        this.resourceGather = resourceGather;
        this.resourceAmount = getResources(resourceName, resourceLevel);
        this.resource = resource;
        if (addToList) resourceNodes.put(resourceName.toLowerCase(), this);
    }

    public Nodes(String banditName, int banditLevel, int banditDamage, int banditHealth, int banditDefense, Rarity banditRarity, boolean addToList) {
        this.banditName = banditName;
        this.banditLevel = banditLevel;
        this.banditDamage = banditDamage;
        this.banditHealth = banditHealth;
        this.banditDefense = banditDefense;
        this.banditRarity = banditRarity;
        if (addToList) banditNodes.put(banditName.toLowerCase(), this);
    }

    public Nodes(String impName, int impLevel, int impDamage, int impHealth, int impDefense, boolean addToList) {
        this.impName = impName;
        this.impLevel = impLevel;
        this.impDamage = impDamage;
        this.impHealth = impHealth;
        this.impDefense = impDefense;
        if (addToList) impNodes.put(impName.toLowerCase(), this);
    }

    public String getImpName() {
        return impName;
    }

    public int getImpLevel() {
        return impLevel;
    }

    public int getImpDamage() {
        return impDamage;
    }

    public int getImpDefense() {
        return impDefense;
    }

    public int getImpHealth() {
        return impHealth;
    }

    public int getResourcesLeft() {
        return resourcesLeft;
    }

    public Player getResourceGather() {
        return resourceGather;
    }

    public int getResourceAmount() {
        return resourceAmount;
    }

    public Material getResource() {
        return resource;
    }

    public int getResourceLevel() {
        return resourceLevel;
    }

    public String getResourceName() {
        return resourceName;
    }

    public int getBanditDamage() {
        return banditDamage;
    }

    public int getBanditDefense() {
        return banditDefense;
    }

    public int getBanditHealth() {
        return banditHealth;
    }

    public int getBanditLevel() {
        return banditLevel;
    }

    public Rarity getBanditRarity() {
        return banditRarity;
    }

    public String getBanditName() {
        return banditName;
    }

    public int getResources(String name, int level) {
        int resources = 0;
        switch (name) {
            case "mountain", "forest" -> {
                switch (level) {
                    case 1 -> {
                        resources = 175000;
                    }
                    case 2 -> {
                        resources = 325000;
                    }
                    case 3 -> {
                        resources = 725000;
                    }
                    case 4 -> {
                        resources = 1250000;
                    }
                    case 5 -> {
                        resources = 3500000;
                    }
                }
            }
            case "rich vein" -> {
                switch (level) {
                    case 1 -> {
                        resources = 125000;
                    }
                    case 2 -> {
                        resources = 250000;
                    }
                    case 3 -> {
                        resources = 550000;
                    }
                    case 4 -> {
                        resources = 950000;
                    }
                    case 5 -> {
                        resources = 2500000;
                    }
                }
            }
            case "treasury" -> {
                switch (level) {
                    case 1 -> {
                        resources = 67500;
                    }
                    case 2 -> {
                        resources = 125000;
                    }
                    case 3 -> {
                        resources = 275000;
                    }
                    case 4 -> {
                        resources = 475000;
                    }
                    case 5 -> {
                        resources = 1500000;
                    }
                }
            }
        }
        return resources;
    }

    public static Nodes getResourceNode(String name) {
        if (name.equalsIgnoreCase("")) return null;
        return resourceNodes.get(name.toLowerCase());
    }

    public static Nodes getImpNode(String name) {
        if (name.equalsIgnoreCase("")) return null;
        return impNodes.get(name.toLowerCase());
    }

    public static Nodes getBanditNode(String name) {
        if (name.equalsIgnoreCase("")) return null;
        return banditNodes.get(name.toLowerCase());
    }
}
