package fr.skah.mdb;

/*
 *  * @Created on jeudi/avril/2021 - 00:38
 *  * @Project ModulableDiscordBot
 *  * @Author Jimmy
 */

import fr.skah.mdb.commands.Command;
import fr.skah.mdb.exceptions.InvalidCommand;
import fr.skah.mdb.exceptions.InvalidModule;
import fr.skah.mdb.listeners.MessageListener;
import fr.skah.mdb.modules.Module;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.UUID;

public class ModulableDiscordBot extends Module {

    public static final HashMap<UUID, Command> COMMANDS = new HashMap<>();
    public static final HashMap<UUID, ListenerAdapter> LISTENERS = new HashMap<>();

    public ModulableDiscordBot() throws LoginException, InterruptedException {
        onLoad();
        JDA jda = JDABuilder.createDefault("MY-TOKEN").enableIntents(GatewayIntent.GUILD_PRESENCES).enableIntents(GatewayIntent.GUILD_MEMBERS).setMemberCachePolicy(MemberCachePolicy.ALL).setActivity(Activity.watching("MY DISCORD")).build();
        jda.addEventListener(new MessageListener());
        LISTENERS.values().parallelStream().forEach(jda::addEventListener);
        Thread.sleep(5000);
        onEnable();
    }

    public static void main(String[] args) {
        try {
            new ModulableDiscordBot();
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void registerCommand(Module module, Command command) throws InvalidModule, InvalidCommand {

        if (module == null || module.getModuleName() == null || module.getModuleName().equalsIgnoreCase(""))
            throw new InvalidModule("Can't register command", new Throwable("Module or Module Name cannot be null"));
        if (command == null)
            throw new InvalidCommand("Can't register command", new Throwable("Command cannot be null"));

        COMMANDS.put(UUID.fromString(module.getModuleName() + module.getAuthor() + module.getVersion()), command);
    }

    public static void registerListener(Module module, ListenerAdapter listener) throws InvalidModule {
        if (module == null || module.getModuleName() == null || module.getModuleName().equalsIgnoreCase(""))
            throw new InvalidModule("Can't register command", new Throwable("Module or Module Name cannot be null"));

        LISTENERS.put(UUID.fromString(module.getModuleName() + module.getAuthor() + module.getVersion()), listener);
    }

}
