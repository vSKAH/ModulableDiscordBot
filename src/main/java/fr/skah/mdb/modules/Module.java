package fr.skah.mdb.modules;

/*
 *  * @Created on jeudi/avril/2021 - 00:39
 *  * @Project ModulableDiscordBot
 *  * @Author Jimmy
 */

import fr.skah.mdb.modules.loader.ModuleOptions;
import fr.skah.mdb.utils.Files;

import java.io.File;

public abstract class Module {

    private ModuleOptions moduleOptions;


    /*
        On Bot Start Custom Module
        Method onLoad is called before method onEnable
     */
    public void onLoad() {
    }

    public void onEnable() {
    }

    /*
        On Bot Stop Custom Module
        Method onUnload is called before method onDisable
    */
    public void onUnload() {
    }

    public void onDisable() {
    }

    public ModuleOptions getModuleOptions() {
        return moduleOptions;
    }

    public void setModuleOptions(ModuleOptions moduleOptions) {
        this.moduleOptions = moduleOptions;
    }

    public File getDataFolder() {
        return new File(Files.BASE_FOLDER, getModuleOptions().getModuleName());
    }

}
