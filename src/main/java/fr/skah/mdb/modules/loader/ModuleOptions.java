package fr.skah.mdb.modules.loader;

/*
 *  * @Created on jeudi/avril/2021 - 07:50
 *  * @Project ModulesDiscordBot
 *  * @Author Jimmy
 */

public class ModuleOptions {

    private String moduleName;
    private String moduleAuthor;
    private String moduleVersion;
    private String moduleMainClass;

    public ModuleOptions() {
        super();
    }

    public ModuleOptions(String moduleName, String moduleAuthor, String moduleVersion, String moduleMainClass) {
        this.moduleName = moduleName;
        this.moduleAuthor = moduleAuthor;
        this.moduleVersion = moduleVersion;
        this.moduleMainClass = moduleMainClass;
    }

    public String getModuleName() {
        return moduleName;
    }

    public String getModuleAuthor() {
        return moduleAuthor;
    }

    public String getModuleVersion() {
        return moduleVersion;
    }

    public String getModuleMainClass() {
        return moduleMainClass;
    }
}
