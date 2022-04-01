package me.affluent.armytrainer.command.information;

import me.affluent.armytrainer.ArmyTrainer;
import me.affluent.armytrainer.database.Database;
import me.affluent.armytrainer.language.Language;
import me.affluent.armytrainer.superclass.BotCommand;
import me.affluent.armytrainer.util.CommandEvent;
import me.affluent.armytrainer.util.system.MessageUtil;
import net.dv8tion.jda.api.entities.User;

public class PingCommand extends BotCommand {

    public PingCommand() {
        this.name = "ping";
        this.cooldown = 1.5;
    }

    @Override
    protected void execute(CommandEvent e) {
        User u = e.getAuthor();
        String uid = u.getId();
        String dcping = String.valueOf(ArmyTrainer.getBot().getShardManager().getAverageGatewayPing());
        if (dcping.length() > 7) dcping = dcping.substring(0, 7);
        String discordPing = "`" + dcping + "`";
        String dbPing = "`" + Database.ping + "`";
        e.reply(MessageUtil.info(Language.getLocalized(uid, "ping_title", "Ping"), Language.getLocalized(uid, "ping",
                        "{user_mention}  The bot's ping to discord's gateway is (avg.): {discord_ping}ms\nLast ping to the " +
                                "database server: {db_ping}ms.")
                .replace("{user_mention}", u.getAsMention().replace("`", "").replace("*", "").replace("||", "").replace("||", ">")).replace("{discord_ping}", discordPing)
                .replace("{db_ping}", dbPing)));
    }
}
