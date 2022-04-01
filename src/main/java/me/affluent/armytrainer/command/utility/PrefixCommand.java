package me.affluent.armytrainer.command.utility;

import me.affluent.armytrainer.Constants;
import me.affluent.armytrainer.entity.Player;
import me.affluent.armytrainer.entity.PrefixUser;
import me.affluent.armytrainer.language.Language;
import me.affluent.armytrainer.superclass.BotCommand;
import me.affluent.armytrainer.util.CommandEvent;
import me.affluent.armytrainer.util.system.MessageUtil;
import net.dv8tion.jda.api.entities.User;

public class PrefixCommand extends BotCommand {

    public PrefixCommand() {
        this.name = "prefix";
        this.cooldown = 1.5;
    }

    @Override
    protected void execute(CommandEvent e) {
        User u = e.getAuthor();
        String uid = u.getId();
        String[] args = e.getArgs();
        if (!Player.playerExists(uid)) {
            e.reply(Constants.PROFILE_404(uid));
            return;
        }
        if (args.length < 1) {
            String userPrefix = PrefixUser.getPrefixUser(uid).getPrefix();
            e.reply(MessageUtil.info(Language.getLocalized(uid, "usage_plain", "Usage"),
                    Language.getLocalized(uid, "usage", "Please use {command_usage}.")
                            .replace("{command_usage}", "`" + userPrefix + "prefix <new prefix>`")));
            return;
        }
        String newPrefix = args[0].toLowerCase();
        if (newPrefix.length() > 4) {
            String msg = Language.getLocalized(uid, "argument_too_long",
                            "The argument {argument} is too long!\nIt needs to be max. {max} characters!")
                    .replace("{argument}", "`<new prefix>`").replace("{max}", "`4`");
            e.reply(MessageUtil.err(Constants.ERROR(uid), msg));
            return;
        }
        PrefixUser.getPrefixUser(uid).setPrefix(newPrefix);
        e.reply(MessageUtil.success(Language.getLocalized(uid, "prefix_set", "Prefix set"),
                Language.getLocalized(uid, "prefix_set_msg", "Successfully set your prefix to {new_prefix}!")
                        .replace("{new_prefix}", "`" + newPrefix + "`")));
    }
}
