package me.affluent.armytrainer.nodes;

import me.affluent.armytrainer.enums.Rarity;

public class BanditNode extends Nodes {

    public BanditNode(String banditName, int banditLevel, int banditDamage, int banditHealth, int banditDefense, Rarity banditRarity) {
        this(banditName, banditLevel, banditDamage, banditHealth, banditDefense, banditRarity, true);
    }

    public BanditNode(String banditName, int banditLevel, int banditDamage, int banditHealth, int banditDefense, Rarity banditRarity, boolean addToList) {
        super(banditName, banditLevel, banditDamage, banditHealth, banditDefense, banditRarity, addToList);
    }
}
