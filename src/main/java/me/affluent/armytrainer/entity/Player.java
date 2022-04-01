package me.affluent.armytrainer.entity;

import me.affluent.armytrainer.ArmyTrainer;
import me.affluent.armytrainer.database.Database;
import me.affluent.armytrainer.enums.Gender;
import net.dv8tion.jda.api.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class Player {

    private static HashMap<String, Player> cache = new HashMap<>();

    private static final HashMap<String, User> userObjectCache = new HashMap<>();

    private final String userId;
    private Gender gender;

    private Player(String userId) {
        this.userId = userId;
        cache.put(userId, this);
    }

    public Player(String userId, Gender gender) {
        this.userId = userId;
        this.gender  = gender;
        load(true);
    }

    public BadgeUser getBadgeUser() {
        return BadgeUser.getBadgeUser(userId);
    }

    public ExpUser getExpUser() {
        return ExpUser.getExpUser(userId);
    }

    public EcoUser getEcoUser() {
        return EcoUser.getEcoUser(userId);
    }

    public User getUser() {
        return Player.getUser(userId);
    }

    public Gender getGender() {
        return gender;
    }

    public static User getUser(String userId) {
        if (userObjectCache.containsKey(userId)) return userObjectCache.get(userId);
        User user = ArmyTrainer.getBot().getShardManager().retrieveUserById(userId).complete();
        userObjectCache.put(userId, user);
        return user;
    }

    public String getMention() {
        return getUser().getAsMention().replace("`", "").replace("*", "").replace("||", "").replace("||", ">");
    }

    public static boolean playerExists(String userId) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase()
                .query("SELECT userId FROM profiles WHERE userId=?;", userId)) {
            if (rs.next()) return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static Player getPlayer(String userId) {
        if (cache.containsKey(userId)) {
            return cache.get(userId);
        }
        Player player = new Player(userId);
        player.load(false);
        cache.put(userId, player);
        return player;
    }

    private void load(boolean isNewPlayer) {
        try (ResultSet rs = ArmyTrainer.getBot().getDatabase().query("SELECT * FROM profiles WHERE userId=?;", userId)) {
            if (rs.next()) {
                int genderInt = rs.getInt("gender");
                this.gender = genderInt == 2 ? Gender.SIR : (genderInt == 1 ? Gender.MADAM : Gender.NEUTRAL);
            } else {
                if (isNewPlayer) {
                    ArmyTrainer.getBot().getDatabase()
                            .update("INSERT INTO profiles (userId, prefix, language, gender, clan) VALUES (?, ?, ?, " +
                                    "?, ?);", userId, "a.", "en", gender == Gender.SIR ? 2 : (gender == Gender.MADAM ? 1 : 0), -1);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void reset() {
        Database db = ArmyTrainer.getBot().getDatabase();

        cache.remove(userId);
    }

    private void cache() {
        cache.put(userId, this);
    }

    public static String getDisplay(User u) {
        return u.getName() + "#" + u.getDiscriminator();
    }

    public String getUserId() {
        return userId;
    }
}
