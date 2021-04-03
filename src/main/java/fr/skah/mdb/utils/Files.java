package fr.skah.mdb.utils;

/*
 *  * @Created on samedi/avril/2021 - 02:54
 *  * @Project ModulesDiscordBot
 *  * @Author Jimmy
 */

import java.io.File;

public class Files {

    public static final File BASE_FOLDER = new File(System.getProperty("user.dir"));
    public static final File MODULES_FOLDER = new File(BASE_FOLDER, "modules");
    public static final File CONFIGURATION_FILE = new File(BASE_FOLDER, "Configuration.json");


}
