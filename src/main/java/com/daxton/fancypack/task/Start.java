package com.daxton.fancypack.task;


import com.daxton.fancypack.config.FileConfig;
import com.daxton.fancypack.simplepack.LoadPng;
import org.bukkit.configuration.file.FileConfiguration;

public class Start {
    //只在開服時執行的任務
    public static void execute(){

        //簡易材質包生成
        FileConfiguration config = FileConfig.config_Map.get("config.yml");
        boolean packCreateEnable = config.getBoolean("ResourcePackCreate.enable");
        if(packCreateEnable){
            LoadPng.execute();
        }


    }

}
