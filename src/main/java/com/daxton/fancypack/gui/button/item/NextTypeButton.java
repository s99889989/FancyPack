package com.daxton.fancypack.gui.button.item;

import com.daxton.fancycore.api.gui.button.GuiAction;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

public class NextTypeButton implements GuiAction {


	private final ItemTypeButton itemTypeButton;

	public NextTypeButton(ItemTypeButton itemTypeButton){
		this.itemTypeButton = itemTypeButton;
	}

	//下一頁
	public void execute(ClickType clickType, InventoryAction action, int slot){
		if(clickType == ClickType.LEFT){
			itemTypeButton.page++;
			itemTypeButton.page();
		}
	}

}
