package fr.skah.mdb.listeners;

/*
 *  * @Created on jeudi/avril/2021 - 02:21
 *  * @Project ModulableDiscordBot
 *  * @Author Jimmy
 */

import fr.skah.mdb.commands.CommandExecutor;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Arrays;

public class MessageListener extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
        Message msg = event.getMessage();
        if (msg.getAuthor().isBot()) return;
        if (!msg.getContentRaw().startsWith("'")) return;

        new CommandExecutor().checkIfCommandExist(msg, msg.getContentRaw().length() != 0 ? get(msg.getContentRaw().split(" ")) : new String[0]);
    }

    private String[] get(String[] tab) {
        return new ArrayList<>(Arrays.asList(tab)).toArray(new String[0]);
    }

}

