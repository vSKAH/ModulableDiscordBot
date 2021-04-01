package fr.skah.mdb.exceptions;

/*
 *  * @Created on jeudi/avril/2021 - 01:46
 *  * @Project ModulableDiscordBot
 *  * @Author Jimmy
 */

public class InvalidCommand extends Exception {

    public InvalidCommand(String message) {
        super(message);
    }
}
