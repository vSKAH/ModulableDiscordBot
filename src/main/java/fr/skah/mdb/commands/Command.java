package fr.skah.mdb.commands;

/*
 *  * @Created on jeudi/avril/2021 - 01:05
 *  * @Project ModulableDiscordBot
 *  * @Author Jimmy
 */

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;


public abstract class Command {

    private final String commandName;
    private final Permission permission;
    private final boolean respectSyntax;
    private final boolean deleteCommand;
    private Message message;

    public Command(String commandName, Permission permission, boolean respectSyntax) {
        this(commandName, permission, respectSyntax, false);
    }

    public Command(String commandName, Permission permission, boolean respectSyntax, boolean deleteCommand) {
        this.commandName = commandName;
        this.permission = permission;
        this.respectSyntax = respectSyntax;
        this.deleteCommand = deleteCommand;
    }

    /**
     * @param msg New Message show
     * @return nothing;
     * @apiNote Edit the Message that called this command
     */
    protected void editMessage(Message msg) {
        message.editMessage(msg).queue();
    }

    /**
     * @return nothing;
     * @apiNote delete command
     */
    protected void deleteCommand() {
        message.delete().queue();
    }

    protected String getCommandName() {
        return commandName;
    }

    protected Permission getPermission() {
        return permission;
    }

    protected boolean isRespectSyntax() {
        return respectSyntax;
    }

    protected boolean isDeleteCommand() {
        return deleteCommand;
    }

    protected User getUser() {
        return message.getAuthor();
    }

    protected TextChannel getTextChannel() {
        return message.getTextChannel();
    }

    protected PrivateChannel getPriveChannel() {
        return message.getPrivateChannel();
    }

    protected Guild getGuild() {
        return message.getGuild();
    }

    /**
     * @return The Message that called this command
     */
    protected Message getMessage() {
        return message;
    }

    /**
     * @return Syntaxe of command in String
     */
    protected abstract String getSyntaxe();

    /**
     * @return Description of command in String
     */
    protected abstract String getDescription();

    /**
     * @param message return message sended
     * @param args    return arrays of args after first word
     * @return CommandState
     */
    protected abstract CommandState perform(Message message, String[] args);

    protected Message setMessage(Message message) {
        return this.message = message;
    }


}
