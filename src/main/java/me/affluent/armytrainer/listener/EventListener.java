package me.affluent.armytrainer.listener;

import me.affluent.armytrainer.event.*;

public interface EventListener {

    default void onEvent(Event event) {}

}