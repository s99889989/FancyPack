package com.daxton.fancypack.gui.button;

import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.button.GuiAction;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

public class CloseButton implements GuiAction {

	private final GUI gui;

	public  CloseButton(GUI gui){
		this.gui = gui;
	}

	//文字材質
	public void execute(ClickType clickType, InventoryAction action, int slot){
		if(clickType == ClickType.LEFT){
			gui.close();
		}
	}

}
