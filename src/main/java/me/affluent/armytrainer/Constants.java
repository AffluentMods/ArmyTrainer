package me.affluent.armytrainer;

import me.affluent.armytrainer.language.Language;
import me.affluent.armytrainer.util.system.MessageUtil;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.Random;

public class Constants {

    public static final String PREFIX = "a.";
    public static final String main_guild = "926964804252483594";
    public static final String TAB = "\u2004\u2004\u2004\u2004";
    public static final String dbl_vote_auth = "";
    public static final String dbl_botid = "";
    public static final String dbl_token =
            "" +
                    "" +
                    "";

    public static MessageEmbed PROFILE_404(String userId) {
        return MessageUtil.err(Language.getLocalized(userId, "unknown", "Unknown"), Language.getLocalized(userId, "no_profile",
                "Lone traveler, drop your weapon if you wish to visit the last Kingdom `" + PREFIX + "start`\n\n" +
                        "[Support Server](https://discord.gg/ArmyTrainer)"));
    }

    public static String ERROR(String userId) {
        return Language.getLocalized(userId, "error", "Error");
    }

    public static String COOLDOWN(String userId) {
            return Language.getLocalized(userId, "cooldown", "Please wait **{cooldown}** my lord!");
    }
}
