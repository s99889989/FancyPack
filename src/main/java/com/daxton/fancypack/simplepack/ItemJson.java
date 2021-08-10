package com.daxton.fancypack.simplepack;

import com.daxton.fancypack.FancyPack;
import com.daxton.fancypack.manager.PackManager;

import java.util.ArrayList;
import java.util.List;

public class ItemJson {


	public static void execute(){

		PackManager.item_Count_Map.clear();
		PackManager.item_Item_Map.clear();

		PackManager.item_List.forEach(itemPath->{
			String itemPatch = itemPath.replace(".png", "");
			String[] strings = itemPatch.split("/");
			if(strings.length == 2){
				String key = strings[0];
				String value = strings[1];

				//物品數量
				if(PackManager.item_Count_Map.get(key) != null){
					int amount = PackManager.item_Count_Map.get(key);
					amount++;
					PackManager.item_Count_Map.put(key, amount);
				}else {
					PackManager.item_Count_Map.put(key, 1);
				}
				//物品
				if(PackManager.item_Item_Map.get(key) != null){
					List<String> items = PackManager.item_Item_Map.get(key);
					items.add(value);
					PackManager.item_Item_Map.put(key, items);
				}else {
					List<String> items = new ArrayList<>();
					items.add(value);
					PackManager.item_Item_Map.put(key, items);
				}

			}

		});
		//開始建立檔案
		WriteItemFile.execute();
	}

}
