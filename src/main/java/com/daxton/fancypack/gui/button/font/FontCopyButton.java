package com.daxton.fancypack.gui.button.font;

import com.daxton.fancycore.api.gui.GuiAction;
import com.daxton.fancycore.api.task.CopyClipboard;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

public class FontCopyButton implements GuiAction {

	private final String value;

	public FontCopyButton(String value){
		this.value = value;
	}

	//上一頁
	public void execute(ClickType clickType, InventoryAction action, int slot){
		if(clickType == ClickType.LEFT){
			CopyClipboard.copy(value);
		}
	}

}
