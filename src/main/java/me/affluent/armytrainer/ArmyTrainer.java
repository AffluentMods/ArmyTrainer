package me.affluent.armytrainer;

import me.affluent.armytrainer.manager.BanManager;
import me.affluent.armytrainer.superclass.CommandHandler;
import me.affluent.armytrainer.util.InGameTime;
import me.affluent.armytrainer.util.system.CooldownUtil;
import me.affluent.armytrainer.util.system.LoadUtil;
import me.affluent.armytrainer.vote.VoteServer;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;
import org.discordbots.api.client.DiscordBotListAPI;

import java.util.Objects;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ArmyTrainer {

    public static boolean maintenance = false;
    public static String maintenance_message = "The bot is currently in maintenance.";
    public static Timer mainTimer;
    public static DiscordBotListAPI dblapi = null;
    private static long started;
    private static long loaded;
    public static long restartAt;
    public static boolean shuttingdown = false;
    public static boolean fullyLoaded = false;

    public static long getLoaded() {
        return loaded;
    }

    public static long getStarted() {
        return started;
    }

    private static Bot bot;

    public static void main(String[] args) {
        started = System.currentTimeMillis();
        restartAt = started + (24 * 60 * 60 * 1000);
        start();
    }

    public static int getTotalGuilds() {
        int tg = 0;
        for (JDA jda : ArmyTrainer.getBot().getShards()) tg += jda.getGuilds().size();
        return tg;
    }

    public static int getTotalUsers() {
        int tu = 0;
        for (JDA jda : ArmyTrainer.getBot().getShards()) tu += jda.getUsers().size();
        return tu;
    }

    private static void start() {
        try {
            mainTimer = new Timer(true);
            bot = new Bot();
            bot.start();
            dblapi = new DiscordBotListAPI.Builder().botId(Constants.dbl_botid).token(Constants.dbl_token).build();
            registerListener();
            bot.addDoneTask(() -> new Timer().schedule(new TimerTask() {
                int lastShardRestarted = -1;

                @Override
                public void run() {
                    if (shuttingdown) {
                        this.cancel();
                        return;
                    }
                    long now = System.currentTimeMillis();
                    if (now > restartAt) {
                        doStop();
                        return;
                    }
                    lastShardRestarted++;
                    if (lastShardRestarted >= bot.getShardManager().getShardsTotal()) lastShardRestarted = 0;
                    bot.getShardManager().restart(lastShardRestarted);
                    System.out.println("Restarting shard #" + lastShardRestarted);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            try {
                                ArmyTrainer.updateStatus(
                                        Objects.requireNonNull(bot.getShardManager().getShardById(lastShardRestarted))
                                                .awaitReady());
                            } catch (InterruptedException e) {
                                System.out.println(
                                        "Error restarting shard " + lastShardRestarted + ": " + e.getMessage());
                            }
                        }
                    }, 15000);
                }
            }, 5 * 60 * 1000, 5 * 60 * 1000));
            Runnable doneTask = () -> {
                LoadUtil.load();
                CooldownUtil.loadCooldowns();
                runTimers();
                CommandHandler.load();
                loaded = System.currentTimeMillis();
            };
            bot.addDoneTask(doneTask);
            Scanner scanner = new Scanner(System.in);
            Thread thread = new Thread(() -> {
                while (scanner.hasNextLine()) {
                    String command = scanner.nextLine();
                    if (command.equalsIgnoreCase("stop")) {
                        doStop();
                        return;
                    }
                }
            });
            thread.setName("Console Command Handler");
            thread.setDaemon(true);
            thread.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void doStop() {
        if (shuttingdown) return;
        shuttingdown = true;
        System.out.println("[INTERN STOP] Cancelling timer...");
        mainTimer.cancel();
        System.out.println("[INTERN STOP] Stopping VoteServer...");
        VoteServer.stop();
        System.out.println("[INTERN STOP] Shutting down shard manager...");
        getBot().getShardManager().shutdown();
        System.out.println("[INTERN STOP] Disconnecting database...");
        getBot().getDatabase().disconnect();
        System.exit(0);
    }

    private static void registerListener() {
    }

    private static void runTimers() {
        BanManager.checkBans();
        InGameTime.load();
        InGameTime.updateTime();
        System.out.println("[INTERN INFO] Started Game Time");
    }

    public static Bot getBot() {
        return bot;
    }

    public static TextChannel getBotLog() {
        //This would be considered the Server log
        return getBot().getShardManager().getTextChannelById("926964806504816751");
    }

    public static TextChannel getModLog() {
        //This would be considered the bot log
        return getBot().getShardManager().getTextChannelById("926964805603061817");
    }

    public static Guild getHub() {
        return getBot().getShardManager().getGuildById(Constants.main_guild);
    }

    public static void updateStatus(JDA jda) {
        String prefix = Constants.PREFIX;
        getBot().getShardManager().setPresence(OnlineStatus.ONLINE, Activity.watching(
                prefix + "help | " + getTotalGuilds() + " servers [#" + (jda.getShardInfo().getShardId() + 1) + "]"));
    }

    public static void updateStatus() {
        ArmyTrainer.getBot().runShardTask(ArmyTrainer::updateStatus);
    }

}
