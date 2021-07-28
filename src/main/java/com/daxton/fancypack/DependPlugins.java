package com.daxton.fancypack;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class DependPlugins {

    public static boolean depend(){

        FancyPack fancyPack = FancyPack.fancyPack;

        if (Bukkit.getServer().getPluginManager().getPlugin("FancyCore") != null && Bukkit.getPluginManager().isPluginEnabled("FancyCore")){
            fancyPack.getLogger().info(ChatColor.GREEN+"Loaded FancyCore");
        }else {
            fancyPack.getLogger().severe("*** FancyCore is not installed or not enabled. ***");
            fancyPack.getLogger().severe("*** FancyItemsy will be disabled. ***");
            fancyPack.getLogger().severe("*** FancyCore未安裝或未啟用。 ***");
            fancyPack.getLogger().severe("*** FancyItems將被卸載。 ***");
            return false;
        }

        return true;
    }

}
