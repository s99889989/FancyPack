package com.daxton.fancypack.gui.button.item;

import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.GuiAction;
import com.daxton.fancycore.api.item.CItem;
import com.daxton.fancypack.config.FileConfig;
import com.daxton.fancypack.gui.button.GetItemButton;
import com.google.common.collect.Lists;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;

import java.util.ArrayList;
import java.util.List;

public class ItemButton implements GuiAction {

	private final Player player;
	private final GUI gui;
	private final String itemType;
	public int page = 0;
	private int place;

	public ItemButton(Player player, GUI gui, String itemType, int place){
		this.player = player;
		this.gui = gui;
		this.itemType = itemType;
		this.place = place;
	}

	//物品材質
	public void execute(ClickType clickType, InventoryAction action, int slot){
		if(clickType == ClickType.LEFT){
			page();
		}
	}

	public void page(){
		FileConfiguration config = FileConfig.config_Map.get("PackConfig/Item/item.yml");
		gui.clearFrom(19, 54);
		List<Integer> ignore = new ArrayList<>();

		List<String> itemList = Lists.newArrayList(config.getConfigurationSection(itemType).getKeys(false));

		while (page*36 >= itemList.size()){
			page = 0;
		}
		int max = itemList.size() / 36;
		int u = itemList.size() % 36;
		if(u == 0){
			max--;
		}
		if(page < 0){
			page = max;
		}
		CItem ccItem = new CItem(itemType);
		List<String> loreList= new ArrayList<>();
		loreList.add("§f("+(page+1)+"/"+(max+1)+")");
		ccItem.setLore(loreList);
		gui.setItem(ccItem.getItemStack(), false, 2, place);

		for(int i = page*36; i< itemList.size(); i++){

			String itemName = itemList.get(i);
			CItem cItem = new CItem(itemType);
			List<String> stringList = new ArrayList<>();
			stringList.add("§aMaterial:§f"+itemType);

			for(String value : config.getConfigurationSection(itemType+"."+itemName).getKeys(false)){
				if(value.equals("CustomModelData")){
					int cmd = config.getInt(itemType+"."+itemName+".CustomModelData");
					cItem.setCustomModelData(cmd);
					//copy += cmd+"";
					stringList.add("§aCustomModelData:§f"+cmd);
				}
			}
			cItem.setLore(stringList);
			gui.addItem(cItem.getItemStack(), false, 19, 54, ignore);
			gui.addAction(new GetItemButton(player, cItem.getItemStack()), 19, 54, ignore);
			gui.setAction(new PreviousItemButton(this, gui), 1, 7);
			gui.setAction(new NextItemButton(this, gui), 1, 8);
		}

	}


}
