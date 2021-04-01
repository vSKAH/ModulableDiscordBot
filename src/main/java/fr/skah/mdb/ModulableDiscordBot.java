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
import fr.skah.mdb.modules.loader.ModuleLoader;
import fr.skah.mdb.modules.loader.ModuleOptions;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import javax.security.auth.login.LoginException;
import java.util.HashMap;

public class ModulableDiscordBot {

    public static final HashMap<String, Module> MODULES_LOADED = new HashMap<>();
    public static final HashMap<String, Command> COMMANDS = new HashMap<>();
    public static final HashMap<String, ListenerAdapter> LISTENERS = new HashMap<>();

    public static void main(String[] args) {
        try {
            new ModuleLoader().loadModules();
            MODULES_LOADED.values().forEach(Module::onLoad);
            JDA jda = JDABuilder.createDefault("YOUR-TOKEN").enableIntents(GatewayIntent.GUILD_PRESENCES).enableIntents(GatewayIntent.GUILD_MEMBERS).setMemberCachePolicy(MemberCachePolicy.ALL).setActivity(Activity.watching("MY DISCORD")).build();
            jda.addEventListener(new MessageListener());
            LISTENERS.values().parallelStream().forEach(jda::addEventListener);
            Thread.sleep(5000);
            MODULES_LOADED.values().forEach(Module::onEnable);
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void registerCommand(Module module, Command command) throws InvalidModule, InvalidCommand {

        ModuleOptions moduleOptions = module.getModuleOptions();

        if (moduleOptions == null || moduleOptions.getModuleName() == null || moduleOptions.getModuleName().equalsIgnoreCase(""))
            throw new InvalidModule("Can't register command, module or module name is null or empty");
        if (command == null)
            throw new InvalidCommand("Can't register command, module or module name is null or empty");

        COMMANDS.put(moduleOptions.getModuleName(), command);
    }

    public static void registerListener(Module module, ListenerAdapter listener) throws InvalidModule {

        ModuleOptions moduleOptions = module.getModuleOptions();

        if (moduleOptions == null || moduleOptions.getModuleName() == null || moduleOptions.getModuleName().equalsIgnoreCase(""))
            throw new InvalidModule("Can't register listener, module or module name is null or empty");

        LISTENERS.put(moduleOptions.getModuleName(), listener);
    }

}
