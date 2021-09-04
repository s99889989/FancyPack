package com.daxton.fancypack.gui.button.item;

import com.daxton.fancycore.api.gui.button.GuiAction;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

public class NextItemButton implements GuiAction {

	private final ItemButton itemButton;

	public NextItemButton(ItemButton itemButton){
		this.itemButton = itemButton;
	}

	//下一頁
	public void execute(ClickType clickType, InventoryAction action, int slot){
		if(clickType == ClickType.LEFT){
			itemButton.page++;
			itemButton.page();
		}
	}

}
