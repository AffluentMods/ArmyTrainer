package me.affluent.armytrainer.command.utility;

import me.affluent.armytrainer.Constants;
import me.affluent.armytrainer.entity.Player;
import me.affluent.armytrainer.entity.PrefixUser;
import me.affluent.armytrainer.superclass.BotCommand;
import me.affluent.armytrainer.util.CommandEvent;
import me.affluent.armytrainer.util.SettingsUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.ButtonClickEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Button;
import net.dv8tion.jda.api.interactions.components.selections.SelectionMenu;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SettingsCommand extends BotCommand {

    public SettingsCommand() {
        this.name = "settings";
        this.aliases = new String[]{"setting"};
        this.cooldown = 2;
    }

    private static final List<String> colorPlayerList = new ArrayList<>();

    private static final List<String> colorAllyList = new ArrayList<>();

    private static final List<String> colorEnemyList = new ArrayList<>();

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
            eb.setTitle("Settings");
            eb.setDescription("""
                    Click a button to change to the opposite configuration.\s
                    For example, if you have `Confirmations` off, and you click the button, they will now be on.
                    Buttons with more than 2 options will load a new message to select the option.""");
            e.getTextChannel().sendMessage(eb.build())
                    .setActionRows(
                            ActionRow.of(
                                    Button.primary("confirm", "Confirmations [On, Off]"),
                                    Button.primary("format", "Format [Mobile, PC]"),
                                    Button.primary("numbers", "Number Format [Commas, Abbreviation]")),
                            ActionRow.of(
                                    Button.primary("color_player", "Player Color [Custom]"),
                                    Button.primary("color_enemy", "Enemy Color [Custom]"),
                                    Button.primary("color_ally", "Ally Color [Custom]")),
                            ActionRow.of(
                                    Button.danger("delete", "DELETE Profile")))
                    .queue();
        }
    }

    @Override
    public void onButtonClick(ButtonClickEvent e) {
        User u = e.getUser();
        String uid = u.getId();

        if (e.getComponentId().equals("confirm")) {
            SettingsUtil.getSettings(uid).setConfirm();
            if (SettingsUtil.getSettings(uid).getConfirm().equalsIgnoreCase("on")) {
                e.reply("Confirmations Enabled").setEphemeral(true).queue(ss -> ss.deleteOriginal().queueAfter(2, TimeUnit.SECONDS));
            } else {
                e.reply("Confirmations Disabled").setEphemeral(true).queue(ss -> ss.deleteOriginal().queueAfter(2, TimeUnit.SECONDS));
            }
        }

        if (e.getComponentId().equals("format")) {
            SettingsUtil.getSettings(uid).setFormat();
                e.reply("Switched Format to " + SettingsUtil.getSettings(uid).getFormat().substring(0, 1).toUpperCase() +
                        SettingsUtil.getSettings(uid).getFormat().substring(1)).setEphemeral(true).queue(ss -> ss.deleteOriginal().queueAfter(2, TimeUnit.SECONDS));
        }

        if (e.getComponentId().equals("numbers")) {
            SettingsUtil.getSettings(uid).setNumbersFormat();
            e.reply("Switched Format to " + SettingsUtil.getSettings(uid).getNumberFormat().substring(0, 1).toUpperCase() +
                    SettingsUtil.getSettings(uid).getNumberFormat().substring(1)).setEphemeral(true).queue(ss -> ss.deleteOriginal().queueAfter(2, TimeUnit.SECONDS));
        }

        if (e.getComponentId().equals("color_player")) {
            colorPlayerList.add(uid);
            e.reply("Send your hexcode below").setEphemeral(true).queue(ss -> ss.deleteOriginal().queueAfter(2, TimeUnit.SECONDS));
        }

        if (e.getComponentId().equals("color_enemy")) {
            colorEnemyList.add(uid);
            e.reply("Send your hexcode below").setEphemeral(true).queue(ss -> ss.deleteOriginal().queueAfter(2, TimeUnit.SECONDS));
        }

        if (e.getComponentId().equals("color_ally")) {
            colorAllyList.add(uid);
            e.reply("Send your hexcode below").setEphemeral(true).queue(ss -> ss.deleteOriginal().queueAfter(2, TimeUnit.SECONDS));
        }
        if (e.getComponentId().equals("delete")) {
            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Settings");
            eb.setDescription("""
                    Final Confirmation. **THIS WILL DELETE ALL DATA**. This is irreversible
                    
                    • This message will purge itself in 10 seconds.""");
            e.getTextChannel().sendMessage(eb.build())
                    .setActionRow(
                            Button.danger("delete_confirm", "Confirm"))
                    .queue(hook -> {
                       hook.delete().queueAfter(10, TimeUnit.SECONDS);
                    });
            if (e.getComponentId().equals("delete_confirm")) {
                Player.getPlayer(uid).reset();
                e.reply("Deleted all Data").setEphemeral(true).queue(ss -> ss.deleteOriginal().queueAfter(2, TimeUnit.SECONDS));
            }
        }
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent e) {
        Message message = e.getMessage();
        String msg = message.getContentRaw();
        User u = e.getAuthor();
        String uid = u.getId();
        String subString;
        if (msg.contains("#")) {
            boolean set = false;
            int hexcode = msg.indexOf("#");
            subString = msg.substring(hexcode, 6);
            /*if (msg.length() < 7) {
                e.getTextChannel().sendMessage("Please use the correct hexcode format; `#000000` for example would give you black.").queue(hook -> {
                    hook.delete().queueAfter(5, TimeUnit.SECONDS);
                });
                return;
            }*/
            if (colorAllyList.contains(uid)) {
                colorAllyList.remove(uid);
                SettingsUtil.getSettings(uid).setColorAlly(subString);
                set = true;
            }
            if (colorPlayerList.contains(uid)) {
                colorPlayerList.remove(uid);
                SettingsUtil.getSettings(uid).setColorPlayer(subString);
                set = true;
            }
            if (colorEnemyList.contains(uid)) {
                colorEnemyList.remove(uid);
                SettingsUtil.getSettings(uid).setColorEnemy(subString);
                set = true;
            }
            if (set) {
                e.getTextChannel().sendMessage("Successfully set Hexcode to `" + subString + "`").queue(hook -> {
                    hook.delete().queueAfter(5, TimeUnit.SECONDS);
                });
            }
        }
    }
}
