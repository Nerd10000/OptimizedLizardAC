package me.dragon.optimzedlizardac.managers;

import me.dragon.optimzedlizardac.LizardAC;

import java.io.File;

public class ConfigManager {

    private static int currentVersion = 1;
    private static int configVersion = LizardAC.getPlugin().getConfig().getInt("config-version");

    public static  void loadConfigVersion(){
        if (currentVersion != configVersion){
            System.out.println("\\u001B[32m[LizardAC] \\u001B[31mError happened while checking the ConfigVersion! The version is outdated.\\u001B[33mRegenerate config...");
            File file = new File(String.valueOf(LizardAC.getPlugin().getDataFolder()));
            file.delete();
            System.out.println("\\u001B[32m[LizardAC] \\u001B[33mThe config file was deleted.Please restart your server to load the config and the plugin.");
            LizardAC.getPlugin().getPluginLoader().disablePlugin(LizardAC.getPlugin());
        }
    }
}
