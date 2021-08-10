package com.daxton.fancypack.gui;

import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.GuiButtom;
import com.daxton.fancypack.gui.button.CloseButton;
import com.daxton.fancypack.gui.button.font.FontButton;
import com.daxton.fancypack.gui.button.font.NextFontButton;
import com.daxton.fancypack.gui.button.font.PreviousFontButton;
import com.daxton.fancypack.gui.button.item.ItemTypeButton;
import com.daxton.fancypack.gui.button.item.NextTypeButton;
import com.daxton.fancypack.gui.button.item.PreviousTypeButton;
import com.daxton.fancypack.manager.PackManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.UUID;

import static com.daxton.fancypack.config.FileConfig.languageConfig;

public class MainPack {

	//打開Team介面
	public static void open(Player player){
		UUID uuid = player.getUniqueId();
		if(PackManager.playerUUID_GUI_Map.get(uuid) == null){
			GUI gui = GUI.createGui(player, 54, languageConfig.getString("Title"));
			gui.setMoveAll(false);
			FontButton fontButton = new FontButton(player, gui);
			//文字上一頁
			ItemStack previousFontButton = GuiButtom.valueOf(languageConfig,"Gui.PreviousFontPage");
			gui.setItem(previousFontButton, false, 1 , 1);
			gui.setAction(new PreviousFontButton(fontButton, gui), 1, 1);
			//文字
			ItemStack fontButtonMaterial = GuiButtom.valueOf(languageConfig,"Gui.Font");
			ItemMeta itemMeta =  fontButtonMaterial.getItemMeta();
			String ds = itemMeta.getDisplayName();
			itemMeta.setDisplayName(ds.replace("%now_page%","0").replace("%max_page%","0"));
			fontButtonMaterial.setItemMeta(itemMeta);
			gui.setItem(fontButtonMaterial, false, 1 , 2);
			gui.setAction(fontButton, 1, 2);
			//文字下一頁
			ItemStack nextFontButton = GuiButtom.valueOf(languageConfig,"Gui.NextFontPage");
			gui.setItem(nextFontButton, false, 1 , 3);
			gui.setAction(new NextFontButton(fontButton, gui), 1, 3);
			//---------------------------------------------------------------------//
			ItemTypeButton itemTypeButton = new ItemTypeButton(player, gui);
			//物品類別上一頁
			ItemStack previousTypeButton = GuiButtom.valueOf(languageConfig,"Gui.PreviousTypePage");
			gui.setItem(previousTypeButton, false, 1 , 4);
			gui.setAction(new PreviousTypeButton(itemTypeButton, gui), 1, 4);
			//物品類別
			ItemStack itemButton = GuiButtom.valueOf(languageConfig,"Gui.ItemType");
			ItemMeta itemItemMeta =  itemButton.getItemMeta();
			String itemDisplay = itemItemMeta.getDisplayName();
			itemItemMeta.setDisplayName(itemDisplay.replace("%now_page%","0").replace("%max_page%","0"));
			itemButton.setItemMeta(itemItemMeta);
			gui.setItem(itemButton, false, 1 , 5);
			gui.setAction(itemTypeButton, 1, 5);
			//物品類別下一頁
			ItemStack nextTypeButton = GuiButtom.valueOf(languageConfig,"Gui.NextTypePage");
			gui.setItem(nextTypeButton, false, 1 , 6);
			gui.setAction(new NextTypeButton(itemTypeButton, gui), 1, 6);
			//---------------------------------------------------------------------//
			//物品上一頁
			ItemStack previousItemButton = GuiButtom.valueOf(languageConfig,"Gui.PreviousItemPage");
			gui.setItem(previousItemButton, false, 1 , 7);
			//物品下一頁
			ItemStack nextItemButton = GuiButtom.valueOf(languageConfig,"Gui.NextItemPage");
			gui.setItem(nextItemButton, false, 1 , 8);
			//關閉
			ItemStack closeButton = GuiButtom.valueOf(languageConfig,"Gui.Close");
			gui.setItem(closeButton, false, 1 , 9);
			gui.setAction(new CloseButton(gui), 1, 9);
			//

			PackManager.playerUUID_GUI_Map.put(uuid, gui);
			gui.open(gui);
		}else {
			GUI gui = PackManager.playerUUID_GUI_Map.get(uuid);

			gui.open(gui);
		}


	}


}
