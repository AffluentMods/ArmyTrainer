package me.affluent.armytrainer.command.actions;

import me.affluent.armytrainer.Constants;
import me.affluent.armytrainer.entity.Player;
import me.affluent.armytrainer.entity.PrefixUser;
import me.affluent.armytrainer.language.Language;
import me.affluent.armytrainer.superclass.BotCommand;
import me.affluent.armytrainer.util.CommandEvent;
import me.affluent.armytrainer.util.ResearchUtil;
import me.affluent.armytrainer.util.system.MessageUtil;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.util.List;

import static me.affluent.armytrainer.util.ResearchUtil.getResearchUtil;

public class ResearchCommand extends BotCommand {

    public ResearchCommand() {
        this.name = "research";
        this.aliases = new String[]{"learn", "technology", "tech", "res"};
        this.cooldown = 2;
    }

    @Override
    protected void execute(CommandEvent e) {
        User u = e.getAuthor();
        String uid = u.getId();
        String[] args = e.getArgs();
        String userPrefix = PrefixUser.getPrefixUser(uid).getPrefix();
        if (!(Player.playerExists(uid))) {
            e.reply(Constants.PROFILE_404(uid));
            return;
        }
        if (args.length < 1) {
            e.reply(MessageUtil.err(Language.getLocalized(uid, "error", "Usage"),
                    Language.getLocalized(uid, "invalid_parameter", "Please use `" + userPrefix + "research <category> [technology]`\n\n" +
                            "Categories;\n" +
                            "Army\n" +
                            "Army Training\n" +
                            "Defense\n" +
                            "Economy\n" +
                            "Wall Defense")));
            return;
        }
    }
}
