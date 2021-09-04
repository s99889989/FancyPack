package com.daxton.fancypack.gui;

import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.button.GuiButton;
import com.daxton.fancycore.api.gui.item.GuiEditItem;
import com.daxton.fancycore.api.gui.item.GuiItem;
import com.daxton.fancypack.gui.button.CloseButton;
import com.daxton.fancypack.gui.button.font.FontButton;
import com.daxton.fancypack.gui.button.font.NextFontButton;
import com.daxton.fancypack.gui.button.font.PreviousFontButton;
import com.daxton.fancypack.gui.button.item.ItemTypeButton;
import com.daxton.fancypack.gui.button.item.NextTypeButton;
import com.daxton.fancypack.gui.button.item.PreviousTypeButton;
import com.daxton.fancypack.manager.PackManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.daxton.fancypack.config.FileConfig.languageConfig;

public class MainPack {

	//打開Team介面
	public static void open(Player player){
		UUID uuid = player.getUniqueId();
		if(PackManager.playerUUID_GUI_Map.get(uuid) == null){
			GUI gui = GUI.GUIBuilder.getInstance().setPlayer(player).setSize(54).setTitle(languageConfig.getString("Title")).build();
			gui.setMove(false);
			FontButton fontButton = new FontButton(gui);
			//文字上一頁
			GuiButton previousFontButton = GuiButton.ButtonBuilder.getInstance().
				setItemStack(GuiItem.valueOf(languageConfig,"Gui.PreviousFontPage")).
				setGuiAction(new PreviousFontButton(fontButton)).
				build();
			gui.setButton(previousFontButton, 1, 1);
			//文字
			ItemStack fontItem = GuiItem.valueOf(languageConfig,"Gui.Font");
			Map<String, String> fontRe = new HashMap<>();
			fontRe.put("%now_page%", "0");fontRe.put("%max_page%", "0");
			GuiEditItem.replaceName(fontItem, fontRe);

			GuiButton fontButtonMaterial = GuiButton.ButtonBuilder.getInstance().
				setItemStack(fontItem).
				setGuiAction(new PreviousFontButton(fontButton)).
				build();
			gui.setButton(fontButtonMaterial, 1, 2);
			//文字下一頁
			GuiButton nextFontButton = GuiButton.ButtonBuilder.getInstance().
				setItemStack(GuiItem.valueOf(languageConfig,"Gui.NextFontPage")).
				setGuiAction(new NextFontButton(fontButton)).
				build();
			gui.setButton(nextFontButton, 1, 3);
			//---------------------------------------------------------------------//
			ItemTypeButton itemTypeButton = new ItemTypeButton(player, gui);
			//物品類別上一頁
			GuiButton previousTypeButton = GuiButton.ButtonBuilder.getInstance().
				setItemStack(GuiItem.valueOf(languageConfig,"Gui.PreviousTypePage")).
				setGuiAction(new PreviousTypeButton(itemTypeButton)).
				build();
			gui.setButton(previousTypeButton, 1, 4);
			//物品類別
			ItemStack itemItem = GuiItem.valueOf(languageConfig,"Gui.ItemType");
			Map<String, String> itemRe = new HashMap<>();
			itemRe.put("%now_page%", "0");itemRe.put("%max_page%", "0");
			GuiEditItem.replaceName(itemItem, itemRe);

			GuiButton ttTypeButton = GuiButton.ButtonBuilder.getInstance().
				setItemStack(itemItem).
				setGuiAction(itemTypeButton).
				build();
			gui.setButton(ttTypeButton, 1, 5);
			//物品類別下一頁
			GuiButton nextTypeButton = GuiButton.ButtonBuilder.getInstance().
				setItemStack(GuiItem.valueOf(languageConfig,"Gui.NextTypePage")).
				setGuiAction(new NextTypeButton(itemTypeButton)).
				build();
			gui.setButton(nextTypeButton, 1, 6);
			//---------------------------------------------------------------------//
			//關閉
			GuiButton closeButton = GuiButton.ButtonBuilder.getInstance().
				setItemStack(GuiItem.valueOf(languageConfig,"Gui.Close")).
				setGuiAction(new CloseButton(gui)).
				build();
			gui.setButton(closeButton, 1, 9);
			//
			PackManager.playerUUID_GUI_Map.put(uuid, gui);
			gui.open(gui);
		}else {
			GUI gui = PackManager.playerUUID_GUI_Map.get(uuid);

			gui.open(gui);
		}


	}

}
