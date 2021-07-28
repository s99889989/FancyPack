package com.daxton.fancypack;

import com.daxton.fancypack.command.MainCommand;
import com.daxton.fancypack.command.TabCommand;
import com.daxton.fancypack.listener.PlayerListener;
import com.daxton.fancypack.task.Reload;
import com.daxton.fancypack.task.Start;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class FancyPack extends JavaPlugin {

    public static FancyPack fancyPack;

    @Override
    public void onEnable() {
        fancyPack = this;
        //前置插件
        if(!DependPlugins.depend()){
            fancyPack.setEnabled(false);
            fancyPack.onDisable();
            return;
        }
        //指令
        Objects.requireNonNull(Bukkit.getPluginCommand("fancypack")).setExecutor(new MainCommand());
        Objects.requireNonNull(Bukkit.getPluginCommand("fancypack")).setTabCompleter(new TabCommand());
        //開服執行任務
        Start.execute();
        //重新讀取用任務
        Reload.execute();
        //監聽
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), fancyPack);

    }

    @Override
    public void onDisable() {

    }
}
