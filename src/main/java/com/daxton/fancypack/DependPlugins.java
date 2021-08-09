package com.daxton.fancypack;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import static com.daxton.fancypack.config.FileConfig.languageConfig;

public class DependPlugins {

    public static boolean depend(){

        FancyPack fancyPack = FancyPack.fancyPack;

        if (Bukkit.getServer().getPluginManager().getPlugin("FancyCore") != null && Bukkit.getPluginManager().isPluginEnabled("FancyCore")){
            fancyPack.getLogger().info(languageConfig.getString("LogMessage.LoadFancyCore")+"");
        }else {
            languageConfig.getStringList("LogMessage.UnLoadFancyCore").forEach(message->fancyPack.getLogger().info(message));
            return false;
        }

        return true;
    }

}
