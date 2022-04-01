package me.affluent.armytrainer.manager;

import me.affluent.armytrainer.event.*;
import me.affluent.armytrainer.listener.EventListener;

import java.util.ArrayList;
import java.util.List;

public class EventManager {

    private static List<EventListener> eventListeners = new ArrayList<>();

    public static void registerListener(EventListener eventListener) {
        if (eventListeners.contains(eventListener)) {
            System.out.println(
                    "[FATAL ERROR] !!! Tried to add event listener [name=" + eventListener.getClass().getName() +
                    "] which is already added !!!");
            return;
        }
        eventListeners.add(eventListener);
    }

    public static void callEvent(Event event) {
        for (EventListener eventListener : eventListeners) {
            //if (event instanceof AttackEvent) eventListener.onAttackEvent((AttackEvent) event);
            //else eventListener.onEvent(event);
        }
    }
}