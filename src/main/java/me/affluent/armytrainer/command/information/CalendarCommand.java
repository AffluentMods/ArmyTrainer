package me.affluent.armytrainer.command.information;

import com.mysql.cj.util.StringUtils;
import me.affluent.armytrainer.Constants;
import me.affluent.armytrainer.entity.Player;
import me.affluent.armytrainer.entity.PrefixUser;
import me.affluent.armytrainer.language.Language;
import me.affluent.armytrainer.superclass.BotCommand;
import me.affluent.armytrainer.util.CommandEvent;
import me.affluent.armytrainer.util.InGameTime;
import me.affluent.armytrainer.util.system.MessageUtil;
import net.dv8tion.jda.api.entities.User;

public class CalendarCommand extends BotCommand {

    public CalendarCommand() {
        this.name = "calendar";
        this.cooldown = 2;
        this.aliases = new String[]{"cal"};
    }

    @Override
    protected void execute(CommandEvent e) {
        User u = e.getAuthor();
        if (!(Player.playerExists(u.getId()))) {
            e.reply(Constants.PROFILE_404(u.getId()));
            return;
        }
        String uid = u.getId();
        String[] args = e.getArgs();
        String userPrefix = PrefixUser.getPrefixUser(uid).getPrefix();
        if (args.length < 1) {
            e.reply(MessageUtil.info(Language.getLocalized(uid, "calendar_title", "Calendar"),
                    Language.getLocalized(uid, "calendar",
                                    "Use {command_usage} for more information.\n\n" +
                                            "**" + InGameTime.getCurrentDate() + "**\n" +
                                            InGameTime.getCurrentTime() + "\n\n" +
                                            "Next Up; \n" +
                                            "{next_event}")
                            .replace("{next_event}", getMonthlyEvents(InGameTime.getCurrentMonth() + 2))
                            .replace("{command_usage}", "`" + userPrefix + "calendar <month>`")));
            return;
        }
        String month = args[0].toLowerCase();
        String monthlyEvent = "";
        int monthInteger;
        if (StringUtils.isStrictlyNumeric(month)) {
            monthInteger = Integer.parseInt(month);
        } else {
            monthInteger = monthString(month);
        }
        if (monthInteger > 0 && monthInteger < 13) {
            monthlyEvent = getMonthlyEvents(monthInteger);
        } else {
            e.reply(MessageUtil
                    .err(Constants.ERROR(uid), Language.getLocalized(uid, "invalid_month", "Invalid month")));
            return;
        }
        e.reply(MessageUtil.info(capitalizeFully(monthWordString(monthInteger)), monthlyEvent));
    }

    public String getMonthlyEvents(int month) {
        switch (month) {
            case 1:
                return "**Event:** Striker\n" +
                        "**Holiday Event:** New Years";
            case 2:
                return "**Event:** Striker";
            case 3:
            case 4:
                return "**Event:** Gambler";
            case 5:
            case 6:
                return "**Event:** Chest Breacher";
            case 7:
            case 8:
                return "**Event:** Golden Hand";
            case 9:
                return "**Event:** Golden Gambler";
            case 10:
                return "**Event:** Golden Gambler\n" +
                        "**Holiday Event:** Halloween";
            case 11:
                return "**Event:** Dwarven Miner";
            case 12:
                return "**Event:** Dwarven Miner\n" +
                        "**Holiday Event:** Christmas";
        }
        return null;
    }

    public static int monthString(String month) {
        switch (month) {
            case "january":
            case "jan":
                return 1;
            case "february":
            case "feb":
                return 2;
            case "march":
            case "mar":
                return 3;
            case "april":
            case "apr":
                return 4;
            case "may":
                return 5;
            case "june":
            case "jun":
                return 6;
            case "july":
            case "jul":
                return 7;
            case "august":
            case "aug":
                return 8;
            case "september":
            case "sep":
                return 9;
            case "october":
            case "oct":
                return 10;
            case "november":
            case "nov":
                return 11;
            case "december":
            case "dec":
                return 12;
        }
        return 0;
    }

    public static String monthWordString(int month) {
        switch (month) {
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

    static String capitalizeFully(String string) {
        String capitalized = "";
        for(String word : string.split(" ")) {
            capitalized += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ";
        }
        if(capitalized.endsWith(" ")) capitalized = capitalized.substring(0, capitalized.length()-1);
        return capitalized;
    }
}
