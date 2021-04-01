package fr.skah.mdb.config;

/*
 *  * @Created on jeudi/avril/2021 - 11:25
 *  * @Project ModulesDiscordBot
 *  * @Author Jimmy
 */

public class BotConfigObject {

    private String botToken;

    public BotConfigObject() {
        super();
    }

    public BotConfigObject(String botToken) {
        this.botToken = botToken;
    }

    public String getBotToken() {
        return botToken;
    }
}
