package com.daxton.fancypack.gui.button.font;

import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.GuiAction;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

public class PreviousFontButton implements GuiAction {

	private final FontButton fontButton;
	private final GUI gui;

	public PreviousFontButton(FontButton fontButton, GUI gui){
		this.fontButton = fontButton;
		this.gui = gui;
	}

	//上一頁
	public void execute(ClickType clickType, InventoryAction action, int slot){
		if(clickType == ClickType.LEFT){
			fontButton.page -= 1;
			fontButton.page();
			//gui.open(gui);
		}
	}

}
