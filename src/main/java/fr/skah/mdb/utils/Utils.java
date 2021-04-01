package fr.skah.mdb.utils;

/*
 *  * @Created on jeudi/avril/2021 - 00:39
 *  * @Project ModulableDiscordBot
 *  * @Author Jimmy
 */

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static void sendTemporaryEmbed(TextChannel textChannel, EmbedBuilder embedBuilder, int seconds) {
        textChannel.sendMessage(embedBuilder.build()).delay(seconds, TimeUnit.SECONDS).flatMap(Message::delete).queue();
    }

    public static void sendTemporaryMessage(TextChannel textChannel, String message, int seconds) {
        textChannel.sendMessage(message).delay(seconds, TimeUnit.SECONDS).flatMap(Message::delete).queue();
    }

    public static boolean mentionnedUserExist(Message message) {
        return message.getMentionedMembers().size() == 0;
    }

    public static boolean targetHasMoreElevatedRank(Member target, Member executor) {

        if (target.getRoles().size() == 0) return false;

        int higherTarget = target.getRoles().parallelStream().mapToInt(Role::getPosition).max().orElse(0);
        int higherExecutor = executor.getRoles().parallelStream().mapToInt(Role::getPosition).max().orElse(0);

        return higherTarget > higherExecutor;
    }

    public static String getRemainingTime(long secondes) {
        long h = TimeUnit.SECONDS.toHours(secondes);
        long m = TimeUnit.SECONDS.toMinutes(secondes) % 60;
        long s = TimeUnit.SECONDS.toSeconds(secondes) % 60;

        return h + ":" + m + ":" + s + ":";
    }

    public static boolean isURL(String url) {
        try {
            new URL(url);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
