package fr.skah.mdb.modules.loader;

/*
 *  * @Created on jeudi/avril/2021 - 07:34
 *  * @Project ModulesDiscordBot
 *  * @Author Jimmy
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.skah.mdb.ModulableDiscordBot;
import fr.skah.mdb.modules.Module;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModuleLoader {

    private static final File MODULES_FOLDER = new File(System.getProperty("user.dir"), "modules");

    public void loadModules() {
        if (!MODULES_FOLDER.exists()) {
            MODULES_FOLDER.mkdirs();
        }

        for (File file : MODULES_FOLDER.listFiles(file -> file.getName().endsWith(".jar"))) {
            try {
                Module module = loadModule(file.getName());
                ModulableDiscordBot.MODULES_LOADED.put(module.getModuleOptions().getModuleName(), module);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Module loadModule(String jarName) throws IOException {

        File moduleFile = new File(MODULES_FOLDER, jarName);
        try {
            ModuleOptions moduleOptions = getModuleOption(moduleFile);
            Module module = new ModuleClassLoader(moduleFile, getClass().getClassLoader(), Objects.requireNonNull(moduleOptions)).getModule();
            module.setModuleOptions(moduleOptions);
            return module;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ModuleOptions getModuleOption(File file) throws IOException {
        JarFile jarFile = new JarFile(file);
        JarEntry jarEntry = jarFile.getJarEntry("module.json");
        if (jarEntry == null) return null;
        return new ObjectMapper().readValue(jarFile.getInputStream(jarEntry), ModuleOptions.class);
    }
}
