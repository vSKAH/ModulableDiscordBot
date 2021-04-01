package fr.skah.mdb.modules.loader;

/*
 *  * @Created on jeudi/avril/2021 - 08:07
 *  * @Project ModulesDiscordBot
 *  * @Author Jimmy
 */

import fr.skah.mdb.modules.Module;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ModuleClassLoader extends URLClassLoader {

    private final Module module;

    ModuleClassLoader(File moduleFile, ClassLoader parent, ModuleOptions moduleOptions) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        super(new URL[]{moduleFile.toURI().toURL()}, parent);

        Class<?> jarClass = Class.forName(moduleOptions.getModuleMainClass(), true, this);
        Class<? extends Module> moduleClass;
        moduleClass = jarClass.asSubclass(Module.class);

        module = moduleClass.newInstance();
    }

    public Module getModule() {
        return module;
    }
}
