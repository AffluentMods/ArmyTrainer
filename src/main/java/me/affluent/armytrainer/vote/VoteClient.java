package me.affluent.armytrainer.vote;

import me.affluent.armytrainer.ArmyTrainer;
import me.affluent.armytrainer.Constants;
import me.affluent.armytrainer.entity.Player;
import me.affluent.armytrainer.entity.PrefixUser;
import me.affluent.armytrainer.language.Language;
import me.affluent.armytrainer.util.system.MessageUtil;
import net.dv8tion.jda.api.entities.User;
import org.json.JSONObject;

import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.time.ZoneId;
import java.util.*;

public class VoteClient {

    public static boolean lastWeekend = false;
    private static final List<String> voteQueue = new ArrayList<>();

    VoteClient(final Socket socket, final InputStream in, final long id) {
        final DataInputStream dis = new DataInputStream(in);
        try {
            boolean authorized = false;
            while (socket != null && !socket.isClosed()) {
                @SuppressWarnings("deprecation") final String line = dis.readLine();
                if (line == null) continue;
                if (line.startsWith("Authorization: ")) {
                    if (line.substring("Authorization: ".length()).equals(Constants.dbl_vote_auth)) {
                        authorized = true;
                    }
                }
                if (line.startsWith("{")) {
                    final JSONObject json = new JSONObject(line);
                    final String uid = json.getString("user");
                    final boolean weekend = json.getBoolean("isWeekend");
                    lastWeekend = weekend;
                    vote(uid, weekend, authorized);
                    //
                    dis.close();
                    socket.close();
                    VoteServer.close(id);
                    break;
                }
            }
        } catch (SocketException | SocketTimeoutException ignored) {
            VoteServer.close(id);
        } catch (Exception ex) {
            VoteServer.close(id);
            ex.printStackTrace();
        }
    }

    public static void vote(final String uid, final boolean authorized) {
        vote(uid, isWeekend(), authorized);
    }

    public static void vote(final String uid, final boolean weekend, final boolean authorized) {
        if (!authorized) {
            return;
        }
        final User u = ArmyTrainer.getBot().getShardManager().retrieveUserById(uid).complete();
        if (u != null) {
            if (voteQueue.contains(uid)) return;
            if (!VoteSystem.hasVoted(uid)) {
                voteQueue.add(uid);
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        voteQueue.remove(uid);
                    }
                }, 7544);
                if (!Player.playerExists(uid)) {
                    return;
                }
                final Player p = Player.getPlayer(uid);
                long now = System.currentTimeMillis();
                final long diff = 43200000L;
                long until = now + diff;
                String userPrefix = PrefixUser.getPrefixUser(uid).getPrefix();
                final String finalRewardsDisplay = "";
                VoteSystem.addVote(u.getId(), until);
                String weekendVote = Language.getLocalized(u.getId(), "weekend_vote", "Weekend Vote");
                String vote = Language.getLocalized(u.getId(), "vote", "Vote");
                u.openPrivateChannel().queue(pc -> pc.sendMessage(MessageUtil.success(weekend ? weekendVote : vote,
                        Language.getLocalized(u.getId(), "vote_received_message",
                                "Thank you for upvoting ArmyTrainer!\n\n__Here are your rewards:__\n{rewards}")
                                .replace("{rewards}", finalRewardsDisplay))).queue());
            }
        }
    }

    public static int getTokenReward(boolean weekend) {
        return weekend ? 12 : 7;
    }

    public static int getGoldenTokenReward(boolean weekend) {
        return weekend ? 1 : 0;
    }

    public static final int expBoost = 20;

    public static HashMap<String, Long> getVoteRewards(int level) {
        HashMap<String, Long> voteRewards = new HashMap<>();
        voteRewards.put("exp-boost", (long) expBoost);
        return voteRewards;
    }

    public static boolean isWeekend() {
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("Europe/Berlin")));
        int dow = c.get(Calendar.DAY_OF_WEEK);
        return dow == Calendar.SATURDAY || dow == Calendar.SUNDAY;
    }
}