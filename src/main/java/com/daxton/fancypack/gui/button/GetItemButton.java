package com.daxton.fancypack.gui.button;

import com.daxton.fancycore.api.gui.button.GuiAction;
import com.daxton.fancycore.other.task.CopyClipboard;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;

public class GetItemButton implements GuiAction {

	private final ItemStack itemStack;
	private final Player player;

	public  GetItemButton(Player player, ItemStack itemStack){
		this.player = player;
		this.itemStack = itemStack;
	}

	//文字材質
	public void execute(ClickType clickType, InventoryAction action, int slot){
		if(clickType == ClickType.LEFT){
			player.getInventory().addItem(itemStack);
		}
		if(clickType == ClickType.RIGHT){
			String type = itemStack.getType().toString();
			int cmd = itemStack.getItemMeta().getCustomModelData();
			CopyClipboard.copy(type+":"+cmd);
		}
	}

}
