package com.daxton.fancypack.task;

import com.daxton.fancypack.FancyPack;
import com.daxton.fancypack.config.FileConfig;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ResourcePackSend {


    //材質包
    public static void send(Player player, String status){
        FancyPack fancyPack = FancyPack.fancyPack;
        FileConfiguration resourcePackConfig = FileConfig.config_Map.get("config.yml");

        String nowLanguage = resourcePackConfig.getString("Language");

        FileConfiguration resourcePackMessageConfig = FileConfig.config_Map.get("Language/"+nowLanguage+".yml");
        if(resourcePackMessageConfig == null){
            resourcePackMessageConfig = FileConfig.config_Map.get("Language/English.yml");
        }

        boolean rpEnable = resourcePackConfig.getBoolean("ResourcePack.enable");
        String rpRUL = resourcePackConfig.getString("ResourcePack.url");
        String rpHash = resourcePackConfig.getString("ResourcePack.hash");
        int rpDownloadDelay = resourcePackConfig.getInt("ResourcePack.download-delay");
        boolean rpKickErrorDwnload = resourcePackConfig.getBoolean("ResourcePack.kick-error-download");
        int rpKickErrorDelay = resourcePackConfig.getInt("ResourcePack.kick-error-delay");
        boolean rpKickNoDownload = resourcePackConfig.getBoolean("ResourcePack.kick-no-download");
        int rpKickNoDelay = resourcePackConfig.getInt("ResourcePack.kick-no-delay");


        String rpmJoin = resourcePackMessageConfig.getString("Language.ResourcePack.join");
        String rpmSuccessfullyLoaded = resourcePackMessageConfig.getString("Language.ResourcePack.successfully-loaded");
        String rpmDownloadErrorPass = resourcePackMessageConfig.getString("Language.ResourcePack.download-error-pass");
        String rpmDownloadError = resourcePackMessageConfig.getString("Language.ResourcePack.download-error");
        String rpmDownloadErrorKick = resourcePackMessageConfig.getString("Language.ResourcePack.download-error-kick");
        String rpmKickPass = resourcePackMessageConfig.getString("Language.ResourcePack.kick-pass");
        String rpmKickDelay = resourcePackMessageConfig.getString("Language.ResourcePack.kick-delay");
        String rpmKick = resourcePackMessageConfig.getString("Language.ResourcePack.kick");

        if(!rpEnable){
            return;
        }


        //發送材質包
        if(status == null){
            int time = 1;
            String timeString = "";
            try{
                time = rpDownloadDelay;
                timeString = String.valueOf(time);
            }catch (NumberFormatException exception){
                exception.printStackTrace();
            }
            if(rpmJoin != null){
                player.sendMessage(rpmJoin.replace("{time}",timeString));
            }

            if(rpRUL != null && rpHash != null){
                BukkitRunnable bukkitRunnable = new BukkitRunnable() {
                    @Override
                    public void run() {
                        try {
                            player.setResourcePack(rpRUL , rpHash);
                        }catch (NoSuchMethodError exception){
                            player.setResourcePack(rpRUL);
                        }
                    }
                };
                bukkitRunnable.runTaskLater(fancyPack,(long) time*20);
            }




        }
        if(status !=null){
            //發送材質包成功
            if(status.contains("SUCCESSFULLY_LOADED")){
                if(rpmSuccessfullyLoaded != null){
                    player.sendMessage(rpmSuccessfullyLoaded);
                }
                return;
            }

            //材質包下載失敗
            if(status.contains("FAILED_DOWNLOAD")){
                int time = 1;
                String timeString = "";
                try{
                    time = rpKickErrorDelay;
                    timeString = String.valueOf(time);
                }catch (NumberFormatException exception){
                    exception.printStackTrace();
                }
                if(rpKickErrorDwnload){
                    if(rpmDownloadError != null){
                        player.sendMessage(rpmDownloadError.replace("{time}",timeString));
                    }
                    BukkitRunnable bukkitRunnable = new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.kickPlayer(rpmDownloadErrorKick);
                        }
                    };
                    bukkitRunnable.runTaskLater(fancyPack,(long) time*20);
                    return;
                }else {
                    if(rpmDownloadErrorPass != null){
                        player.sendMessage(rpmDownloadErrorPass);
                    }

                }
                return;
            }

            //拒絕接受材質包
            if(status.contains("DECLINED")){
                int time = 1;
                String timeString = "";
                try{
                    time = rpKickNoDelay;
                    timeString = String.valueOf(time);
                }catch (NumberFormatException exception){
                    exception.printStackTrace();
                }
                if(rpKickNoDownload){
                    if(rpmKickDelay != null){
                        player.sendMessage(rpmKickDelay.replace("{time}",timeString));
                    }
                    BukkitRunnable bukkitRunnable = new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.kickPlayer(rpmKick);
                        }
                    };
                    bukkitRunnable.runTaskLater(fancyPack,(long) time*20);


                }else {
                    if(rpmKickPass != null){
                        player.sendMessage(rpmKickPass);
                    }
                }

            }
        }


    }

}
