package me.affluent.armytrainer.command.actions;

import me.affluent.armytrainer.entity.Player;
import me.affluent.armytrainer.entity.PrefixUser;
import me.affluent.armytrainer.enums.Gender;
import me.affluent.armytrainer.language.Language;
import me.affluent.armytrainer.superclass.BotCommand;
import me.affluent.armytrainer.util.CommandEvent;
import me.affluent.armytrainer.util.system.MessageUtil;
import net.dv8tion.jda.api.entities.User;

public class StartCommand extends BotCommand {

    public StartCommand() {
        this.name = "start";
        this.help = "Begin your lordship!";
        this.cooldown = 0.5;
    }

    @Override
    protected void execute(CommandEvent e) {
        User u = e.getAuthor();
        String uid = u.getId();
        String userPrefix = PrefixUser.getPrefixUser(uid).getPrefix();
        if (Player.playerExists(uid)) {
            e.reply(MessageUtil.err(Language.getLocalized(uid, "error", "Error"),
                    Language.getLocalized(uid, "start_already_started", "You already started playing!")));
            return;
        }
        String[] args = e.getArgs();
        Player p = new Player(uid, Gender.NEUTRAL);
        e.reply(MessageUtil.info(Language.getLocalized(uid, "usage_plain", "Started!"),
                Language.getLocalized(uid, "usage", """
                        Welcome to Army Trainer my lord!
                        
                        Use `""" + userPrefix + "start info` for background information\n" +
                        "Use `" + userPrefix + "tutorial` to begin the tutorial.\n" +
                        "Use `" + userPrefix + "help` for a list of commands."
                )));
        if (args[1].equalsIgnoreCase("info")) {
            e.reply(MessageUtil.info(Language.getLocalized(uid, "usage_plain", "Started!"),
                    Language.getLocalized(uid, "usage", """
                        Welcome to Army Trainer my lord!
                        Army Trainer has many features including;
                        
                        • Base Building
                        Construct many different types of buildings, like resource buildings to generate hourly resources,
                        Military buildings to train troops, and many others
                        
                        • Economy
                        Spend resources on training lot's of troops, or on research to make your troops even stronger,
                        Or perhaps spend them on upgrading your buildings.
                        
                        • Raiding
                        Need a quick pick-me-up of resources? Go raid some other players and steal their resources.
                        Not like they need them or anything.
                        
                        And More!
                        
                        Use `""" + userPrefix + "tutorial` to begin the tutorial\n" +
                            "Use `" + userPrefix + "help` for a list of commands."
                    )));
        }
    }

}
