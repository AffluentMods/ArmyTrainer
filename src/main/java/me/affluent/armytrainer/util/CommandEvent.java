package me.affluent.armytrainer.util;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Consumer;

public class CommandEvent {
    private static int MAX_MESSAGES = 2;

    private final GuildMessageReceivedEvent event;

    public CommandEvent(GuildMessageReceivedEvent event) {
        this.event = event;
    }

    public String[] getArgs() {
        String message = event.getMessage().getContentRaw();
        String[] split = message.split(" ");
        return Arrays.copyOfRange(split, 1, split.length);
    }

    public GuildMessageReceivedEvent getEvent() {
        return event;
    }

    public void reply(String message) {
        sendMessage(event.getChannel(), message);
    }

    public void reply(String message, Consumer<Message> success) {
        sendMessage(event.getChannel(), message, success);
    }

    public void reply(String message, Consumer<Message> success, Consumer<Throwable> failure) {
        sendMessage(event.getChannel(), message, success, failure);
    }

    public void reply(MessageEmbed embed) {
        event.getChannel().sendMessage(embed).queue();
    }

    public void reply(MessageEmbed embed, Consumer<Message> success) {
        event.getChannel().sendMessage(embed).queue(success);
    }

    public void reply(MessageEmbed embed, Consumer<Message> success, Consumer<Throwable> failure) {
        event.getChannel().sendMessage(embed).queue(success, failure);
    }

    public void reply(Message message) {
        event.getChannel().sendMessage(message).queue();
    }

    public void reply(Message message, Consumer<Message> success) {
        event.getChannel().sendMessage(message).queue(success);
    }

    public void reply(Message message, Consumer<Message> success, Consumer<Throwable> failure) {
        event.getChannel().sendMessage(message).queue(success, failure);
    }

    public void reply(File file, String filename) {
        event.getChannel().sendFile(file, filename).queue();
    }

    public void reply(String message, File file, String filename) {
        Message msg = message == null ? null : new MessageBuilder().append(splitMessage(message).get(0)).build();
        if (msg != null) event.getChannel().sendMessage(msg).addFile(file, filename).queue();
        else event.getChannel().sendFile(file, filename).queue();
    }

    //private methods

    private void sendMessage(MessageChannel chan, String message) {
        ArrayList<String> messages = splitMessage(message);
        for (int i = 0; i < MAX_MESSAGES && i < messages.size(); i++) {
            chan.sendMessage(messages.get(i)).queue();
        }
    }

    private void sendMessage(MessageChannel chan, String message, Consumer<Message> success) {
        ArrayList<String> messages = splitMessage(message);
        for (int i = 0; i < MAX_MESSAGES && i < messages.size(); i++) {
            if (i + 1 == MAX_MESSAGES || i + 1 == messages.size()) {
                chan.sendMessage(messages.get(i)).queue(success);
            } else {
                chan.sendMessage(messages.get(i)).queue();
            }
        }
    }

    private void sendMessage(MessageChannel chan, String message, Consumer<Message> success,
                             Consumer<Throwable> failure) {
        ArrayList<String> messages = splitMessage(message);
        for (int i = 0; i < MAX_MESSAGES && i < messages.size(); i++) {
            if (i + 1 == MAX_MESSAGES || i + 1 == messages.size()) {
                chan.sendMessage(messages.get(i)).queue(success, failure);
            } else {
                chan.sendMessage(messages.get(i)).queue();
            }
        }
    }

    private static ArrayList<String> splitMessage(String stringtoSend) {
        ArrayList<String> msgs = new ArrayList<>();
        if (stringtoSend != null) {
            stringtoSend = stringtoSend.replace("@everyone", "@\u0435veryone").replace("@here", "@h\u0435re").trim();
            while (stringtoSend.length() > 2000) {
                int leeway = 2000 - (stringtoSend.length() % 2000);
                int index = stringtoSend.lastIndexOf("\n", 2000);
                if (index < leeway) index = stringtoSend.lastIndexOf(" ", 2000);
                if (index < leeway) index = 2000;
                String temp = stringtoSend.substring(0, index).trim();
                if (!temp.equals("")) msgs.add(temp);
                stringtoSend = stringtoSend.substring(index).trim();
            }
            if (!stringtoSend.equals("")) msgs.add(stringtoSend);
        }
        return msgs;
    }

    // custom shortcuts

    SelfUser getSelfUser() {
        return event.getJDA().getSelfUser();
    }

    // shortcuts

    public User getAuthor() {
        return event.getAuthor();
    }

    public JDA getJDA() {
        return event.getJDA();
    }

    public Member getMember() {
        return event.getMember();
    }

    public Message getMessage() {
        return event.getMessage();
    }

    public TextChannel getTextChannel() {
        return event.getChannel();
    }
}