package fr.skah.mdb.commands;

/*
 *  * @Created on jeudi/avril/2021 - 01:15
 *  * @Project ModulableDiscordBot
 *  * @Author Jimmy
 */

import fr.skah.mdb.ModulableDiscordBot;
import fr.skah.mdb.utils.Util;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;

import java.util.Map;

public class CommandExecutor {

    public void checkIfCommandExist(Message message, String[] args) {

        for (Map.Entry<String, Command> commandEntry : ModulableDiscordBot.COMMANDS.entrySet()) {
            Command command = commandEntry.getValue();
            if (args[0].equalsIgnoreCase("'" + command.getCommandName())) {
                command.setMessage(message);
                processCommand(command, args);
                return;
            }
            System.out.println(commandEntry.getKey() + "     " + commandEntry.getValue().getSyntaxe());
        }
        Util.sendTemporaryMessage(message.getTextChannel(), ":x: Cette commande n'existe pas. \nVeuillez saisir 'Help pour afficher la liste des commandes !", 10);
    }

    private CommandState processCommand(Command command, String[] args) {

        if (command.getGuild().getMemberById(command.getUser().getIdLong()) != null) {

            Member member = command.getGuild().getMemberById(command.getUser().getIdLong());
            if (member != null && command.getPermission() != null && !member.hasPermission(command.getPermission())) {
                command.getTextChannel().sendMessage("Vous ne pouvez pas exécuter la commande " + command.getCommandName() + " sans y être autorisé").queue();
                return CommandState.ERROR_PERMISSION;
            }

            if (command.isRespectSyntax() && args.length != command.getSyntaxe().split(" ").length) {
                Util.sendTemporaryEmbed(command.getTextChannel(), new EmbedBuilder().setDescription("Veuillez utiliser la commande de la façon suivante\n" + command.getSyntaxe()), 10);
                return CommandState.SYNTAX_ERROR;
            }

            if (command.isDeleteCommand())
                command.deleteCommand();

            command.perform(command.getMessage(), args);
        }
        return CommandState.SUCCES;
    }
}
