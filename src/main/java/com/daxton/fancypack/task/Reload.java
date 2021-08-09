package com.daxton.fancypack.task;

import com.daxton.fancypack.config.FileConfig;
import com.daxton.fancypack.simplepack.LoadPng;
import org.bukkit.configuration.file.FileConfiguration;

public class Reload {
    //重新讀取的一些任務
    public static void execute(){
        //設定檔
        FileConfig.reload();
        //簡易材質包生成
        FileConfiguration config = FileConfig.config_Map.get("config.yml");
        boolean packCreateEnable = config.getBoolean("ResourcePackCreate.enable");
        if(packCreateEnable){
            LoadPng.execute();
        }
    }

}
