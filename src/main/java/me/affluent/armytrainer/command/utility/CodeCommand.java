package me.affluent.armytrainer.command.utility;

import me.affluent.armytrainer.Constants;
import me.affluent.armytrainer.entity.Player;
import me.affluent.armytrainer.entity.PrefixUser;
import me.affluent.armytrainer.language.Language;
import me.affluent.armytrainer.superclass.BotCommand;
import me.affluent.armytrainer.util.CommandEvent;
import me.affluent.armytrainer.util.system.MessageUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;

public class CodeCommand extends BotCommand {

    public CodeCommand() {
        this.name = "code";
        this.aliases = new String[]{"redeem"};
        this.cooldown = 1.5;
    }

    @Override
    protected void execute(CommandEvent e) {
        User u = e.getAuthor();
        String uid = u.getId();
        Player p = Player.getPlayer(uid);
        boolean isCodeRedeemed = false;
        if (!Player.playerExists(uid)) {
            e.reply(Constants.PROFILE_404(uid));
            return;
        }
        String[] args = e.getArgs();
        if (args.length < 1) {
            String userPrefix = PrefixUser.getPrefixUser(uid).getPrefix();
            e.reply(MessageUtil.info(Language.getLocalized(uid, "usage_plain", "Usage"),
                    Language.getLocalized(uid, "code_usage",
                            "Please use {command_usage}.\n\n"
                                    .replace("{command_usage}", "`" + userPrefix + "code <code>`\n" +
                                            "Example: `" + userPrefix + "code 123`"))));
            return;
        }
        String redeemed = args[0];

        if (isCodeRedeemed) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle(Language.getLocalized(uid, "redeemed_code_plain", "Redeemed Code"));
            eb.setColor(new Color(250, 58, 35));
            eb.setDescription(Language.getLocalized(uid, "code",
                    "You already redeemed `" + redeemed + "`"));
            e.getTextChannel().sendMessage(eb.build()).queue();
            return;
        }

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(Language.getLocalized(uid, "invalid_code_plain", "Invalid Code"));
        eb.setColor(new Color(250, 58, 35));
        eb.setDescription(Language.getLocalized(uid, "invalid",
                "`" + redeemed + "` is not a valid code."));
        e.getTextChannel().sendMessage(eb.build()).queue();
    }
}
