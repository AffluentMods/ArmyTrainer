package me.affluent.armytrainer.nodes;

public class ImpNode extends Nodes {

    public ImpNode(String impName, int impLevel, int impDamage, int impHealth, int impDefense) {
        this(impName, impLevel, impDamage, impHealth, impDefense, true);
    }

    public ImpNode(String impName, int impLevel, int impDamage, int impHealth, int impDefense, boolean addToList) {
        super(impName, impLevel, impDamage, impHealth, impDefense, addToList);
    }
}
