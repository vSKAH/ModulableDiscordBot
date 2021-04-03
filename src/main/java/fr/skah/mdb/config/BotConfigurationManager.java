package fr.skah.mdb.config;

/*
 *  * @Created on jeudi/avril/2021 - 11:27
 *  * @Project ModulesDiscordBot
 *  * @Author Jimmy
 */

import fr.skah.mdb.utils.Files;
import fr.skah.mdb.utils.JsonManager;

public class BotConfigurationManager {

    private BotConfigObject botConfigObject;

    public BotConfigurationManager loadConfiguration() {
        checkToken();
        botConfigObject = new JsonManager().load(Files.CONFIGURATION_FILE, BotConfigObject.class);
        return this;
    }

    private void saveConfiguration(BotConfigObject botConfigObject) {
        new JsonManager().save(Files.CONFIGURATION_FILE, botConfigObject);
    }

    private void checkToken() {
        if (!Files.CONFIGURATION_FILE.exists()) {
            saveConfiguration(new BotConfigObject("YOUR-BOT-TOKEN"));
            System.out.println(" !!!!!! YOU MUST ENTER YOUR BOT TOKEN !!!!!!");
            System.exit(0);
        }
    }

    public BotConfigObject getBotConfigObject() {
        return botConfigObject;
    }

}
