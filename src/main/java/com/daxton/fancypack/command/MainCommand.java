package com.daxton.fancypack.command;


import com.daxton.fancypack.FancyPack;
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
        //重新讀取設定
        if(args[0].equalsIgnoreCase("reload") && args.length == 1) {
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
