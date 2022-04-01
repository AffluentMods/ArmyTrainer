package me.affluent.armytrainer.command.actions;

import me.affluent.armytrainer.Constants;
import me.affluent.armytrainer.entity.Player;
import me.affluent.armytrainer.entity.PrefixUser;
import me.affluent.armytrainer.language.Language;
import me.affluent.armytrainer.superclass.BotCommand;
import me.affluent.armytrainer.util.CommandEvent;
import me.affluent.armytrainer.util.CostUtil;
import me.affluent.armytrainer.util.system.MessageUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.SelectionMenuEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;

import java.util.ArrayList;

public class RaidCommand extends BotCommand {

    public RaidCommand() {
        this.name = "raid";
        this.aliases = new String[]{"attack", "pvp", "siege"};
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
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Siege");
            eb.setDescription("Please use `" + userPrefix + "siege <coords>` to attack\n" +
                    "Valid `coords` parameter;\n" +
                    "`123x123y`\n" +
                    "`123x,123y`\n" +
                    "`123x 123y`\n" +
                    "`123x, 123y`");
            e.getTextChannel().sendMessage(eb.build()).queue();
            return;
        }
        String coords = "";
        if (args.length > 1) {
            for (String arg : args) {
                coords += arg + " ";
            }
        } else {
            coords = args[0];
        }
        String xcoords;
        String ycoords;
        if (coords.contains("x") && (coords.contains("y"))) {
            coords = coords.replaceAll(",", "").replaceAll(" ", "");
            xcoords = coords.substring(0, coords.indexOf("x"));
            ycoords = coords.substring(coords.indexOf("x") + 1, coords.indexOf("y"));
            System.out.println(xcoords + " x");
            System.out.println(ycoords + " y");
        } else {
            e.reply(MessageUtil.err(Language.getLocalized(uid, "error", "Error"),
                    Language.getLocalized(uid, "invalid_parameter", "Invalid Parameter.")));
            return;
        }
        if (!xcoords.equalsIgnoreCase("") && !ycoords.equalsIgnoreCase("")) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Siege");
            eb.setDescription("""
                    """);
            e.getTextChannel().sendMessage(eb.build())
                    .setActionRows(
                            ActionRow.of(
                                    Button.primary("info", "Troop Info"),
                                    Button.success("attack", "Siege Castle"),
                                    Button.danger("cancel", "Cancel Siege"),
                                    Button.primary("scout", "Scout")),
                            ActionRow.of(
                                    SelectionMenu.create("troopType").setPlaceholder("Select all troops you wish to bring").setMaxValues(8).setMaxValues(1)
                                            .addOption("[T1] - Footman", "t1-footman").addOption("[T1] - Archer", "t1-archer")
                                            .addOption("[T1] - Cavalry", "t1-cavalry").addOption("[T1] - Ballista", "t1-ballista")
                                            .addOption("[T2] - Gladiator", "t2-gladiator").addOption("[T2] - Crossbowmen", "t2-crossbowmen")
                                            .addOption("[T2] - Cataphract", "t2-cataphract").addOption("[T2] - Catapult", "t2-catapult").build()))
                    .queue();
        }
    }

    @Override
    public void onSelectionMenu(SelectionMenuEvent e) {
        if (e.getComponentId().equalsIgnoreCase("troopType")) {
            System.out.println(e.getValues());
            System.out.println(e.getComponent() + " comp");
        }
    }
}
