package com.daxton.fancypack.gui.button.item;

import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.button.GuiAction;
import com.daxton.fancycore.api.gui.button.GuiButton;
import com.daxton.fancycore.api.gui.item.GuiEditItem;
import com.daxton.fancycore.api.gui.item.GuiItem;
import com.daxton.fancycore.api.item.CItem;
import com.daxton.fancypack.config.FileConfig;
import com.google.common.collect.Lists;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.daxton.fancypack.config.FileConfig.languageConfig;

public class ItemTypeButton implements GuiAction {

	private final Player player;
	private final GUI gui;
	public int page = 0;

	public ItemTypeButton(Player player, GUI gui){
		this.player = player;
		this.gui = gui;
	}

	//物品材質
	public void execute(ClickType clickType, InventoryAction action, int slot){
		if(clickType == ClickType.LEFT){
			page();
		}
	}

	public void page(){
		gui.clearButtonFrom(10, 54);
		Integer[] ignore = new Integer[]{};

		FileConfiguration config = FileConfig.config_Map.get("PackConfig/Item/item.yml");

		List<String> itemTypeList = Lists.newArrayList(config.getConfigurationSection("").getKeys(false));

		while (page*9 > itemTypeList.size()){
			page = 0;
		}
		int max = itemTypeList.size() / 9;
		if(page < 0){
			page = max;
		}

		ItemStack itemItem = GuiItem.valueOf(languageConfig,"Gui.ItemType");
		Map<String, String> itemRe = new HashMap<>();
		itemRe.put("%now_page%", (page+1)+"");itemRe.put("%max_page%", (max+1)+"");
		GuiEditItem.replaceName(itemItem, itemRe);

		GuiButton itemButton = GuiButton.ButtonBuilder.getInstance().
			setItemStack(itemItem).
			build();

		gui.setButton(itemButton, 1 , 5);

		int k = 1;
		for(int i = page*9; i < itemTypeList.size() && k <= 10; i++){
			String itemType = itemTypeList.get(i);
			CItem cItem = new CItem(itemType);

			GuiButton getItemButton = GuiButton.ButtonBuilder.getInstance().
				setItemStack(cItem.getItemStack()).
				setGuiAction(new ItemButton(player, gui, itemType, k++)).
				build();
			gui.addButton(getItemButton, 10, 18, ignore);

		}

	}


}
