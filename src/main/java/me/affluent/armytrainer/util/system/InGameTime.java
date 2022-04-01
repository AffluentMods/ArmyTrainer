package me.affluent.armytrainer.util;

import me.affluent.armytrainer.ArmyTrainer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class InGameTime implements Runnable {

    private static int gameYears;
    private static int gameMonths;
    private static int gameDays;
    private static int gameHours;
    private static int gameMinutes;
    private static int daysPerMonth;
    private static String event1;
    private static String event2;

    public static void load() {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase()
                .query("SELECT * FROM gameTime;")) {
            if (rs.next()) {
                gameHours = rs.getInt("hour");
                gameDays = rs.getInt("day");
                gameMonths = rs.getInt("month");
                gameYears = rs.getInt("year");
            } else {
                gameHours = 0;
                gameDays = 1;
                gameMonths = 1;
                gameYears = 0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void setTime() {
        ArmyTrainer.getBot().getDatabase().update("DELETE FROM gameTime;");
        ArmyTrainer.getBot().getDatabase().update("INSERT INTO gameTime VALUES (?, ?, ?, ?);", gameHours, gameDays, gameMonths, gameYears);
    }

    public static void setTime(int minute, int hour, int day, int month, int year) {
        gameMinutes = minute;
        gameHours = hour;
        gameDays = day;
        gameMonths = month;
        gameYears = year;
    }

    public static void updateTime() {
        ScheduledExecutorService scheduler
                = Executors.newSingleThreadScheduledExecutor();

        Runnable task = new InGameTime() {
            @Override
            public void run() {
                gameMinutes += 15;
                gameHours = (int) getCurrentHours();
                gameDays = (int) getCurrentDays();
                gameMonths = (int) getCurrentMonths();
                gameYears = (int) getCurrentYear();
                setTime();
            }
        };
        int initialDelay = 60;
        int periodicDelay = 60;
        scheduler.scheduleAtFixedRate(task, initialDelay, periodicDelay,
                TimeUnit.SECONDS);
    }

    public String getNextEvent1() {
        switch (gameMonths) {
            case 1:
                event1 = "pet_leveling";
                break;
            case 3:
                event1 = "gambler";
                break;
            case 5:
                event1 = "chest_breacher";
                break;
            case 7:
                event1 = "item_starring";
                break;
            case 9:
                event1 = "gambling";
                break;
            case 11:
                event1 = "dungeon";
                break;
        }
        return event1;
    }

    public String getNextEvent2() {
        switch (gameMonths) {
            case 1:
                event2 = "striker";
                break;
            case 3:
                event2 = "golden_gambler";
                break;
            case 5:
                event2 = "jeweler";
                break;
            case 7:
                event2 = "golden_hand";
                break;
            case 9:
                event2 = "mining_event";
                break;
            case 11:
                event2 = "boss_event";
                break;
        }
        return event2;
    }

    public String getNextEventTemp() {
        switch (gameMonths) {
            case 1:
            case 2:
                event1 = "striker";
                break;
            case 3:
            case 4:
                event1 = "gambler";
                break;
            case 5:
            case 6:
                event1 = "chest breacher";
                break;
            case 7:
            case 8:
                event1 = "golden hand";
                break;
            case 9:
            case 10:
                event1 = "golden gambler";
                break;
            case 11:
            case 12:
                event1 = "dwarven miner";
                break;
        }
        return event1;
    }

    public static long getSpecialEventEnd() {
        long days1;
        long days2;
        if (gameMonths % 2 == 0) {
            days1 = daysPerMonthSpecific(gameMonths);
            days2 = 0;
        } else {
            days1 = daysPerMonthSpecific(gameMonths);
            days2 = daysPerMonthSpecific(gameMonths + 1);
        }
        int totalDays = (int) (days1 + days2);
        return (totalDays * 96L); // real time amount of minutes (4 minutes/hour)
    }

    public static long getHolidayEventEnd() {
        long days = daysPerMonthSpecific(gameMonths);
        return (days * 96L); // real time amount of minutes (4 minutes/hour)
    }


    public long daysPerMonth() {
        switch (gameMonths) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysPerMonth = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                daysPerMonth = 30;
                break;
            case 2:
                if (((gameYears % 4 == 0) && !((gameYears % 100) == 0)) || (gameYears % 400 == 0))
                    daysPerMonth = 29;
                else
                    daysPerMonth = 28;
        }
        return daysPerMonth;
    }

    public static long daysPerMonthSpecific(int month) {
        int days = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                days = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                days = 30;
                break;
            case 2:
                if (((gameYears % 4 == 0) && !((gameYears % 100) == 0)) || (gameYears % 400 == 0))
                    days = 29;
                else
                    days = 28;
        }
        return days;
    }

    public static String getCurrentDate() {
        String year;
        year = "Year " + gameYears;
        String month = monthString(gameMonths);
        String daySuffix = daySuffix(gameDays);
        String day = gameDays + daySuffix;
        return day + " of " + month + " " + year;
    }

    public static String getCurrentTime() {
        String ampm = "";
        /*if (gameHours >= 12) {
            ampm = "PM";
        } else {
            ampm = "AM";
        } Currently only military time, add a setting to switch between military and standard time*/
        String minute;
        if (gameMinutes >= 10) {
            minute = String.valueOf(gameMinutes);
        } else {
            minute = "0" + gameMinutes;
        }

        String hour;
        if (gameHours >= 10) {
            hour = String.valueOf(gameHours);
        } else {
            hour = "0" + gameHours;
        }
        return hour + ":" + minute + " " + ampm;
    }

    public static String daySuffix(int gameDays) {
        int ten = gameDays % 10;
        int hundred = gameDays % 100;
        if (hundred - ten == 10) {
            return "th";
        }
        switch (ten) {
            case 1:
                return "st";
            case 2:
                return "nd";
            case 3:
                return "rd";
            default:
                return "th";
        }
    }

    public static String monthString(int gameMonths) {
        switch (gameMonths) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
        }
        return null;
    }

    public long getCurrentHours() {
        while (gameMinutes >= 60) {
            gameMinutes -= 60;
            gameHours++;
        }
        return gameHours;
    }

    public long getCurrentDays() {
        while (gameHours >= 24) {
            gameHours -= 24;
            gameDays++;
        }
        return gameDays;
    }

    public long getCurrentMonths() {
        if (gameDays > daysPerMonth()) {
            gameDays = 1;
            gameMonths++;
        }
        return gameMonths;
    }

    public long getCurrentYear() {
        while (gameMonths >= 13) {
            gameMonths -= 12;
            gameYears++;
        }
        return gameYears;
    }

    public static int getCurrentMonth() {
        return gameMonths;
    }
}