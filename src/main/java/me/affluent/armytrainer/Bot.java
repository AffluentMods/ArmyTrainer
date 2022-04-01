package me.affluent.armytrainer;

import me.affluent.armytrainer.command.actions.RaidCommand;
import me.affluent.armytrainer.command.utility.SettingsCommand;
import me.affluent.armytrainer.database.Database;
import me.affluent.armytrainer.listener.BotInviteListener;
import me.affluent.armytrainer.superclass.CommandHandler;
import me.affluent.armytrainer.superclass.ShardTask;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.annotation.Nonnull;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Bot extends ListenerAdapter {

    ScheduledExecutorService scheduler
            = Executors.newSingleThreadScheduledExecutor();

    private final Database database;
    private ShardManager shardManager;
    private String token;
    private int shards;

    Bot() {
        this.database = new Database();
        this.token = "OTI2OTk1ODcwMjc5NDE3OTQ2.YdDyLg.IY8L17O8QgYeugR1arZq4zcfhzE"; //outdated token, change this
        this.shards = 2;
    }

    public List<JDA> getShards() {
        return shardManager.getShards();
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public Database getDatabase() {
        return database;
    }

    public void start() {
        try {
            if (database.getCon() == null) {
                System.out.println("[FATAL] Database could not connect! Stopping...");
                System.exit(0);
                return;
            }
            Collection<GatewayIntent> intents = Arrays
                    .asList(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_EMOJIS,
                            GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES);
            this.shardManager = DefaultShardManagerBuilder.createDefault(token, intents).disableCache(CacheFlag.VOICE_STATE,
                            CacheFlag.MEMBER_OVERRIDES).setMemberCachePolicy(MemberCachePolicy.ALL).setShardsTotal(shards).setAutoReconnect(true)
                    .setActivity(Activity.watching("loading process")).setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .addEventListeners(this, new CommandHandler(), new BotInviteListener(), new SettingsCommand(), new RaidCommand()) //TODO Event Listeners
                    .build();
            addDoneTask(ArmyTrainer::updateStatus);
            addDoneTask(() -> {
                this.shardManager.addEventListener();
                ArmyTrainer.fullyLoaded = true;
            });
            addDoneTask(() -> new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    ArmyTrainer.dblapi.setStats(ArmyTrainer.getTotalGuilds());
                }
            }, 60 * 1000, 10 * 60 * 1000));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private List<Runnable> doneTasks = new ArrayList<>();

    void addDoneTask(Runnable runnable) {
        doneTasks.add(runnable);
    }

    public void runShardTask(ShardTask shardTask) {
        for (JDA shard : getShards()) shardTask.run(shard);
    }

    private void loadBot() {
        for (Runnable r : doneTasks) r.run();
    }

    @Override
    public void onReady(@Nonnull ReadyEvent event) {
        readyShard(event.getJDA());
    }

    private int loadedShards = 0;

    private void readyShard(JDA jda) {
        if (ArmyTrainer.fullyLoaded) return;
        loadedShards++;
        if (loadedShards == shards) {
            System.out.println("[INTERN INFO] All shards loaded. Loading bot instance...");
            loadBot();
        }
    }
}
