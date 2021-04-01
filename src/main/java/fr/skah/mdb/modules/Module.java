package fr.skah.mdb.modules;

/*
 *  * @Created on jeudi/avril/2021 - 00:39
 *  * @Project ModulableDiscordBot
 *  * @Author Jimmy
 */

import fr.skah.mdb.modules.loader.ModuleOptions;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;
import java.util.UUID;

public abstract class Module {

    private ModuleOptions moduleOptions;
    private final HashMap<UUID, ListenerAdapter> listenerAdapterHashMap = new HashMap<>();


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



    /*Get listeners of bot.
     * You can add your listeners and load on startup of module
     * if you delete listener on runtime the listener will not be unload on shutdown of module*/

    public HashMap<UUID, ListenerAdapter> getListenerAdapters() {
        return listenerAdapterHashMap;
    }
}
