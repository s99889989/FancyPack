package com.daxton.fancypack.gui.button.font;

import com.daxton.fancycore.api.gui.button.GuiAction;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

public class NextFontButton implements GuiAction {

	private final FontButton fontButton;

	public NextFontButton(FontButton fontButton){
		this.fontButton = fontButton;
	}

	//下一頁
	public void execute(ClickType clickType, InventoryAction action, int slot){
		if(clickType == ClickType.LEFT){
			fontButton.page += 1;
			fontButton.page();
		}
	}

}
