package fr.skah.mdb.commands;

/*
 *  * @Created on jeudi/avril/2021 - 01:15
 *  * @Project ModulableDiscordBot
 *  * @Author Jimmy
 */

import fr.skah.mdb.ModulableDiscordBot;
import fr.skah.mdb.utils.Utils;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;

import java.util.Map;
import java.util.UUID;

public class CommandExecutor {

    public boolean commandExist(Message message, String[] args) {

        for (Map.Entry<UUID, Command> commandEntry : ModulableDiscordBot.COMMANDS.entrySet()) {
            Command command = commandEntry.getValue();
            if (args[0].equalsIgnoreCase("'" + command.getCommandName())) {
                command.setMessage(message);
                processCommand(command, args);
                return true;
            }
        }

        Utils.sendTemporaryMessage(message.getTextChannel(), ":x: Cette commande n'existe pas. \nVeuillez saisir 'Help pour afficher la liste des commandes !", 10);
        return false;
    }

    private CommandState processCommand(Command command, String[] args) {

        if (command.getGuild().getMemberById(command.getUser().getIdLong()) != null && command.getPermission() != null && !command.getGuild().getMemberById(command.getUser().getIdLong()).hasPermission(command.getPermission())) {
            command.getTextChannel().sendMessage("Vous ne pouvez pas éxecuter la commande " + command.getCommandName() + " sans y être autorisé").queue();
            return CommandState.ERROR_PERMISSION;
        }

        if (command.isRespectSyntax() && args.length != command.getSyntaxe().split(" ").length) {
            Utils.sendTemporaryEmbed(command.getTextChannel(), new EmbedBuilder().setDescription("Veuillez utiliser la commande de la façon suivante\n" + command.getSyntaxe()), 10);
            return CommandState.SYNTAX_ERROR;
        }

        if (command.isDeleteCommand())
            command.deleteCommand();

        command.perform(command.getMessage(), args);
        return CommandState.SUCCES;
    }

}
