package fr.skah.mdb.modules;

/*
 *  * @Created on jeudi/avril/2021 - 00:39
 *  * @Project ModulableDiscordBot
 *  * @Author Jimmy
 */

import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.UUID;

public abstract class Module {

    private final HashMap<UUID, ListenerAdapter> listenerAdapterHashMap = new HashMap<>();
    private String moduleName;
    private String author;
    private String version;

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

    /* Get Informations of Module */
    public String getModuleName() {
        return moduleName;
    }

    /* Set Information of Module */
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    /*Get listeners of bot.
     * You can add your listeners and load on startup of module
     * if you delete listener on runtime the listener will not be unload on shutdown of module*/

    public HashMap<UUID, ListenerAdapter> getListenerAdapters() {
        return listenerAdapterHashMap;
    }
}
