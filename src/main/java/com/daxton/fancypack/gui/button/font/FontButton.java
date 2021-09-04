package com.daxton.fancypack.gui.button.font;

import com.daxton.fancycore.api.gui.GUI;
import com.daxton.fancycore.api.gui.button.GuiAction;
import com.daxton.fancycore.api.gui.button.GuiButton;
import com.daxton.fancycore.api.gui.item.GuiEditItem;
import com.daxton.fancycore.api.gui.item.GuiItem;
import com.daxton.fancycore.api.item.CItem;
import com.daxton.fancypack.config.FileConfig;
import com.google.common.collect.Lists;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.daxton.fancypack.config.FileConfig.languageConfig;

public class FontButton implements GuiAction {


	private final GUI gui;

	public int page = 0;

	public FontButton(GUI gui){
		this.gui = gui;
	}

	//文字材質
	public void execute(ClickType clickType, InventoryAction action, int slot){
		if(clickType == ClickType.LEFT){
			page();
			//gui.open(gui);
		}

	}

	public void page(){
		FileConfiguration config = FileConfig.config_Map.get("PackConfig/Font/font.yml");
		Integer[] ignore = new Integer[]{};
		gui.clearButtonFrom(10, 54);
		List<String> keyList = Lists.newArrayList(config.getConfigurationSection("").getKeys(false));
		//FancyPack.fancyPack.getLogger().info(" 值 "+page*45+" : "+keyList.size());

		while (page*45 > keyList.size()){
			page = 0;
		}
		int max = keyList.size() / 45;
		if(page < 0){
			page = max;
		}

		ItemStack fontButtonMaterial = GuiItem.valueOf(languageConfig,"Gui.Font");
		Map<String, String> fontRe = new HashMap<>();
		fontRe.put("%now_page%", (page+1)+"");fontRe.put("%max_page%", (max+1)+"");
		GuiEditItem.replaceName(fontButtonMaterial, fontRe);

		GuiButton fontButton = GuiButton.ButtonBuilder.getInstance().
			setItemStack(fontButtonMaterial).build();
		gui.setButton(fontButton, 1 , 2);

		for(int i = page*45; i < keyList.size() ; i++){
			String key = keyList.get(i);
			CItem cItem = new CItem("PLAYER_HEAD");
			cItem.setDisplayName("§a"+(i+1)+"");
			List<String> loreList = new ArrayList<>();
			String copy = "";
			for(String value : config.getConfigurationSection(key).getKeys(false)){
				String vv = config.getString(key+"."+value);
				loreList.add("§2"+value+" : §f"+vv);
				if(value.equals("png")){
					copy = vv;
				}
			}
			cItem.setHeadValue("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDY0NTY0Nzg0ZjlmM2Q0ZjVmZWFmODg4MTNlNzRlN2UyNTQ0MTU0M2Y4YzhjMjI1MDYxZjBiZDI5YTU2Y2U4MyJ9fX0=");
			cItem.setLore(loreList);

			GuiButton nextTypeButton = GuiButton.ButtonBuilder.getInstance().
				setItemStack(cItem.getItemStack()).
				setGuiAction(new FontCopyButton(copy)).
				build();
			gui.addButton(nextTypeButton, 10, 54, ignore);

		}
	}

}
