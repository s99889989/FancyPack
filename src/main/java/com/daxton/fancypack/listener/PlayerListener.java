package com.daxton.fancypack.listener;

import com.daxton.fancypack.task.ResourcePackSend;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;

public class PlayerListener implements Listener {

    @EventHandler//當玩家登入
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        //玩家登入發送材質包
        ResourcePackSend.send(player,null);
    }


    @EventHandler//材質包狀態
    public void onResourcePack(PlayerResourcePackStatusEvent event){
        Player player = event.getPlayer();
        ResourcePackSend.send(player,event.getStatus().toString());
    }

}
