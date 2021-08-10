package com.daxton.fancypack.gui.button.item;

import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.GuiAction;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

public class PreviousTypeButton implements GuiAction {

	private final GUI gui;
	private final ItemTypeButton itemTypeButton;

	public PreviousTypeButton(ItemTypeButton itemTypeButton, GUI gui){
		this.gui = gui;
		this.itemTypeButton = itemTypeButton;
	}

	//上一頁
	public void execute(ClickType clickType, InventoryAction action, int slot){
		if(clickType == ClickType.LEFT){
			itemTypeButton.page--;
			itemTypeButton.page();
		}
	}

}
