package me.affluent.armytrainer.listener;

import me.affluent.armytrainer.ArmyTrainer;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

public class BotInviteListener extends ListenerAdapter {

    @Override
    public void onGuildJoin(GuildJoinEvent e) {
        TextChannel botlogChannel = ArmyTrainer.getBotLog();
        Guild g = e.getGuild();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor("Joined Guild!", null, g.getIconUrl());
        eb.setColor(new Color(19, 255, 58));
        eb.appendDescription("ID: `{" + g.getId() + "}`\n" +
                "Server: **" + g.getName() + "**\n" +
                "This server has **" + g.getMemberCount() + "** members.\n" +
                "I am in **" + ArmyTrainer.getTotalGuilds() + " guilds** now!");
        eb.setFooter("ArmyTrainer RPG", "https://i.imgur.com/RbHmy82.png");
        botlogChannel.sendMessage(eb.build()).queue();
        ArmyTrainer.updateStatus();
    }

    @Override
    public void onGuildLeave(GuildLeaveEvent e) {
        TextChannel botlogChannel = ArmyTrainer.getBotLog();
        Guild g = e.getGuild();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setAuthor("Left Guild!", null, g.getIconUrl());
        eb.setColor(new Color(250, 58, 35));
        eb.appendDescription("ID: `{" + g.getId() + "}`\n" +
                "Server: **" + g.getName() + "**\n" +
                "This server has **" + g.getMemberCount() + "** members.\n" +
                "I am in **" + ArmyTrainer.getTotalGuilds() + " guilds** now!");
        eb.setFooter("ArmyTrainer RPG", "https://i.imgur.com/RbHmy82.png");
        botlogChannel.sendMessage(eb.build()).queue();
        ArmyTrainer.updateStatus();
    }
}