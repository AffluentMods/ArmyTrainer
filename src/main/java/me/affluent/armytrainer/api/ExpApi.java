package me.affluent.armytrainer.api;

public class ExpApi {

    public static boolean canLevelUp(double xp, int level) {
        if (level >= 65) return false;
        return xp >= getNeededXP(level);
    }

    public static double getNeededXP(int level) {
        double neededXP = 0;
        if (level >= 65) return -1;
        for (int i = 1; i < level + 1; i++) {
            if (i < 11) neededXP += 500;
            if (i >= 11 && i < 28) neededXP += 2000;
            if (i >= 28 && i < 38) neededXP += 5000;
            if (i >= 38 && i < 51) neededXP += 10000;
            if (i >= 51 && i < 56) neededXP += 550000;
            if (i >= 56 && i < 64) neededXP += 2000000;
            if (i == 64) neededXP *= 2;
        }
        return neededXP;
    }

}
