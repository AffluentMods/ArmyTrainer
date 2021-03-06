package me.affluent.armytrainer.vote;

import me.affluent.armytrainer.ArmyTrainer;
import me.affluent.armytrainer.command.utility.VoteCommand;
import me.affluent.armytrainer.util.system.MessageUtil;
import net.dv8tion.jda.api.entities.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class VoteSystem {

    private static final HashMap<String, Long> activeVotes = new HashMap<>();

    public static void load() {
        //VoteServer.start();
        long now = System.currentTimeMillis();
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM votes;")) {
            while (rs.next()) {
                String uid = rs.getString("userId");
                long until = rs.getLong("until");
                if (until <= now) {
                    ArmyTrainer.getBot().getDatabase().update("DELETE FROM votes WHERE userId=?;", uid);
                    expireVote(uid);
                } else {
                    long diff = until - now;
                    activeVotes.put(uid, until);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            activeVotes.remove(uid, until);
                            ArmyTrainer.getBot().getDatabase().update("DELETE FROM votes WHERE userId=?;", uid);
                            expireVote(uid);
                        }
                    }, diff);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void addVote(String uid, long until) {
        activeVotes.put(uid, until);
        ArmyTrainer.getBot().getDatabase().update("INSERT INTO votes VALUES (?, ?);", uid, until);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                activeVotes.remove(uid, until);
                ArmyTrainer.getBot().getDatabase().update("DELETE FROM votes WHERE userId=?;", uid);
                expireVote(uid);
            }
        }, until - System.currentTimeMillis());
    }

    public static boolean hasVoted(String uid) {
        return activeVotes.getOrDefault(uid, -1L) > System.currentTimeMillis();
    }

    public static long getUntilVote(String uid) {
        return activeVotes.getOrDefault(uid, -1L);
    }

    private static void expireVote(String userId) {
        User u = ArmyTrainer.getBot().getShardManager().retrieveUserById(userId).complete();
        if (u == null) return;
        u.openPrivateChannel().queue(pc -> pc.sendMessage(MessageUtil.err("Vote",
                "Your vote boost expired!\nYou can vote again [here](" + VoteCommand.voteLink + " \"Vote Link\")."))
                .queue());
    }
}