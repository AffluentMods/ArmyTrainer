package me.affluent.armytrainer.command.actions;

import me.affluent.armytrainer.Constants;
import me.affluent.armytrainer.entity.Player;
import me.affluent.armytrainer.entity.PrefixUser;
import me.affluent.armytrainer.superclass.BotCommand;
import me.affluent.armytrainer.util.CommandEvent;
import net.dv8tion.jda.api.entities.User;

public class ConstructCommand extends BotCommand {

    public ConstructCommand() {
        this.name = "construct";
        this.aliases = new String[]{"build", "cons", "upgrade", "building"};
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

    }
}
