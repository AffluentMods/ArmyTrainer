package me.affluent.armytrainer.command.information;

import me.affluent.armytrainer.entity.PrefixUser;
import me.affluent.armytrainer.language.Language;
import me.affluent.armytrainer.superclass.BotCommand;
import me.affluent.armytrainer.util.CommandEvent;
import me.affluent.armytrainer.util.system.EmoteUtil;
import me.affluent.armytrainer.util.system.MessageUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

public class HelpCommand extends BotCommand {

    public HelpCommand() {
        this.name = "help";
        this.cooldown = 0.5;
    }

    @Override
    protected void execute(CommandEvent e) {
        User u = e.getAuthor();
        String uid = u.getId();
        String[] args = e.getArgs();
        String userPrefix = PrefixUser.getPrefixUser(uid).getPrefix();
        if (args.length < 1) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(Language.getLocalized(uid, "help_plain", "Commands"));
            eb.setThumbnail("");
            eb.setDescription(Language.getLocalized(uid, "help",
                    "**NOTE:** Army Trainer is not currently released, not everything will work, yet.\n" +
                            "For further information use: `" + userPrefix + "help <command>`\n\n" +

                            EmoteUtil.getEmoteMention("") + " **Statistical Commands** " + EmoteUtil.getEmoteMention("") + "\n" +
                            "``\n\n" +

                            EmoteUtil.getEmoteMention("") + " **Combat Commands** " + EmoteUtil.getEmoteMention("") + "\n" +
                            "``\n\n" +

                            EmoteUtil.getEmoteMention("") + " **Economy Commands** " + EmoteUtil.getEmoteMention("") + "\n" +
                            "``\n\n" +

                            EmoteUtil.getEmoteMention("") + " **Miscellaneous Commands** " + EmoteUtil.getEmoteMention("") + "\n" +
                            "`cal`, `prefix`, `ping`, `settings`\n\n" +

                            EmoteUtil.getEmoteMention("") + " **Bonus Rewards** " + EmoteUtil.getEmoteMention("") + "\n" +
                            "`redeem`, `vote`\n\n" +

                            "**Links**\n" +
                            "[Support Server](https://discord.gg/withering) **|** [Invite Link]()"));
            eb.setFooter("Hope you enjoy Army Trainer :D", "");
            e.getTextChannel().sendMessage(eb.build()).queue();
        } else {
            if (args[0].equalsIgnoreCase("prefix")) {
                e.reply(MessageUtil.info(Language.getLocalized(uid, "prefix_help", "Prefix Usage"),
                        Language.getLocalized(uid, "usage", "Please use `" + userPrefix + "prefix <new prefix>`.\n" +
                                "`<>` - Required Parameter\n`[]` - Optional Parameter\n\n" +
                                "Change the prefix to a unique prefix specific to you, and you only.")));
                return;
            }
            if (args[0].equalsIgnoreCase("ping")) {
                e.reply(MessageUtil.info(Language.getLocalized(uid, "ping_help", "Ping Usage"),
                        Language.getLocalized(uid, "usage", "Please use `" + userPrefix + "ping`.\n" +
                                "`<>` - Required Parameter\n`[]` - Optional Parameter\n\n" +
                                "Check the response rate of the gateway, and database.")));
                return;
            }
            if (args[0].equalsIgnoreCase("calendar") || (args[0].equalsIgnoreCase("cal"))) {
                e.reply(MessageUtil.info(Language.getLocalized(uid, "calendar_help", "Calendar Usage"),
                        Language.getLocalized(uid, "usage", "Please use `" + userPrefix + "calendar [month]`.\n" +
                                "`<>` - Required Parameter\n`[]` - Optional Parameter\n" +
                                "Aliases: `cal`\n\n" +
                                "The calendar command can be ran to check the current Date and Time.\n" +
                                "[Month] - You can use this argument to check what specific events on coming in whichever month.")));
                return;
            }
            if (args[0].equalsIgnoreCase("settings") || (args[0].equalsIgnoreCase("setting"))) {
                e.reply(MessageUtil.info(Language.getLocalized(uid, "settings_help", "Settings Usage"),
                        Language.getLocalized(uid, "usage", "Please use `" + userPrefix + "settings`.\n" +
                                "`<>` - Required Parameter\n`[]` - Optional Parameter\n" +
                                "Aliases: `setting`\n\n" +
                                "Settings are a great way to make Army Trainer more customized to your preferences")));
                return;
            }
            if (args[0].equalsIgnoreCase("code") || (args[0].equalsIgnoreCase("redeem"))) {
                e.reply(MessageUtil.info(Language.getLocalized(uid, "code_help", "Code Usage"),
                        Language.getLocalized(uid, "usage", "Please use `" + userPrefix + "code <code>`.\n" +
                                "`<>` - Required Parameter\n`[]` - Optional Parameter\n" +
                                "Aliases: `redeem`\n\n" +
                                "Codes are unique and can only be used once per player.\n" +
                                "They are mainly given out if Army Trainer is offline for a while, but they" +
                                "may also be given out on special occasions, like if theres a new update.\n" +
                                "Best way to stay in the loop of new codes is to be in the [Official Server](https://discord.gg/withering)")));
                return;
            }
            if (args[0].equalsIgnoreCase("daily")) {
                e.reply(MessageUtil.info(Language.getLocalized(uid, "daily_help", "Daily Usage"),
                        Language.getLocalized(uid, "usage", "Please use `" + userPrefix + "daily`.\n" +
                                "`<>` - Required Parameter\n`[]` - Optional Parameter\n\n" +
                                "Daily Rewards not set up.")));
                return;
            }
            if (args[0].equalsIgnoreCase("vote") || args[0].equalsIgnoreCase("v")) {
                e.reply(MessageUtil.info(Language.getLocalized(uid, "vote_help", "Vote Usage"),
                        Language.getLocalized(uid, "usage", "Please use `" + userPrefix + "vote`.\n" +
                                "`<>` - Required Parameter\n`[]` - Optional Parameter\n" +
                                "Aliases: `v`\n\n" +
                                "You can vote every 12 hours to")));
                return;
            }
        }
    }
}
