package com.daxton.fancypack.gui.button.item;

import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.GuiAction;
import com.daxton.fancycore.api.gui.GuiButtom;
import com.daxton.fancycore.api.item.CItem;
import com.daxton.fancypack.config.FileConfig;
import com.google.common.collect.Lists;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

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
		gui.clearFrom(10, 54);
		List<Integer> ignore = new ArrayList<>();
		FileConfiguration config = FileConfig.config_Map.get("PackConfig/Item/item.yml");

		List<String> itemTypeList = Lists.newArrayList(config.getConfigurationSection("").getKeys(false));

		while (page*9 > itemTypeList.size()){
			page = 0;
		}
		int max = itemTypeList.size() / 9;
		if(page < 0){
			page = max;
		}

		ItemStack itemButton = GuiButtom.valueOf(languageConfig,"Gui.ItemType");
		ItemMeta itemItemMeta =  itemButton.getItemMeta();
		String itemDisplay = itemItemMeta.getDisplayName();
		itemItemMeta.setDisplayName(itemDisplay.replace("%now_page%",(page+1)+"").replace("%max_page%",(max+1)+""));
		itemButton.setItemMeta(itemItemMeta);
		gui.setItem(itemButton, false, 1 , 5);
		int k = 1;
		for(int i = page*9; i < itemTypeList.size() && k <= 10; i++){
			String itemType = itemTypeList.get(i);
			CItem cItem = new CItem(itemType);

			gui.addItem(cItem.getItemStack(), false, 10, 18, ignore);
			ItemButton itemButton1 = new ItemButton(player, gui, itemType, k++);

			gui.addAction(itemButton1, 10, 18, ignore);
		}

	}


}
