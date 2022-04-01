package me.affluent.armytrainer.nodes;

import me.affluent.armytrainer.entity.Player;
import me.affluent.armytrainer.enums.Material;

public class ResourceNode extends Nodes {

    public ResourceNode(String resourceName, int resourceLevel, int resourcesLeft, Player resourceGather, Material resource) {
        this(resourceName, resourceLevel, resourcesLeft, resourceGather, resource, true);
    }

    public ResourceNode(String resourceName, int resourceLevel, int resourcesLeft, Player resourceGather, Material resource, boolean addToList) {
        super(resourceName, resourceLevel, resourcesLeft, resourceGather, resource, addToList);
    }
}
