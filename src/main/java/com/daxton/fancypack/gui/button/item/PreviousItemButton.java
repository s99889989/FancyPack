package com.daxton.fancypack.gui.button.item;

import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.GuiAction;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

public class PreviousItemButton implements GuiAction {

	private final GUI gui;
	private final ItemButton itemButton;

	public PreviousItemButton(ItemButton itemButton, GUI gui){
		this.gui = gui;
		this.itemButton = itemButton;
	}

	//上一頁
	public void execute(ClickType clickType, InventoryAction action, int slot){
		if(clickType == ClickType.LEFT){
			itemButton.page--;
			itemButton.page();
		}
	}

}
