package com.daxton.fancypack.command;


import com.daxton.fancypack.FancyPack;
import com.daxton.fancypack.gui.MainPack;
import com.daxton.fancypack.task.Reload;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static com.daxton.fancypack.config.FileConfig.languageConfig;

public class MainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args){
        if(sender instanceof Player && !sender.isOp()){
            return true;
        }

        if(sender instanceof Player){
            Player player = (Player) sender;
            //打開組隊介面
            if(args.length == 1 && args[0].equalsIgnoreCase("gui")) {
                MainPack.open(player);
            }

        }

        //重新讀取設定
        if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            //重新讀取的一些程序
            Reload.execute();
            if(sender instanceof Player){
                Player player = (Player) sender;
                player.sendMessage(languageConfig.getString("OpMessage.Reload")+"");
            }
            FancyPack.fancyPack.getLogger().info(languageConfig.getString("LogMessage.Reload")+"");
        }

        return true;
    }

}
