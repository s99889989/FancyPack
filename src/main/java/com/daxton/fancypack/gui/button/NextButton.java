package com.daxton.fancypack.gui.button;

import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.GuiAction;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

public class NextButton implements GuiAction {

	private final GUI gui;

	public NextButton(GUI gui){
		this.gui = gui;

	}

	//下一頁
	public void execute(ClickType clickType, InventoryAction action, int slot){

	}

}
