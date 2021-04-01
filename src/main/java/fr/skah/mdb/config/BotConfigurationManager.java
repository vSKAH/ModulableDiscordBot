package fr.skah.mdb.config;

/*
 *  * @Created on jeudi/avril/2021 - 11:27
 *  * @Project ModulesDiscordBot
 *  * @Author Jimmy
 */

import fr.skah.mdb.utils.JsonManager;

import java.io.File;

public class BotConfigurationManager {

    private static final File CONFIGURATION_FILE = new File(System.getProperty("user.dir"), "Configuration.json");
    private BotConfigObject botConfigObject;

    public BotConfigurationManager loadConfiguration() {
        checkToken();
        botConfigObject = new JsonManager().load(CONFIGURATION_FILE, BotConfigObject.class);
        return this;
    }

    private void saveConfiguration(BotConfigObject botConfigObject) {
        new JsonManager().save(CONFIGURATION_FILE, botConfigObject);
    }

    private void checkToken() {
        if (!CONFIGURATION_FILE.exists()) {
            saveConfiguration(new BotConfigObject("YOUR-BOT-TOKEN"));
            System.out.println(" !!!!!! YOU MUST ENTER YOUR BOT TOKEN !!!!!!");
            System.exit(0);
        }
    }

    public BotConfigObject getBotConfigObject() {
        return botConfigObject;
    }

}
