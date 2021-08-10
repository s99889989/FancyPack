package com.daxton.fancypack.simplepack;

import com.daxton.fancycore.api.config.FileCopy;
import com.daxton.fancycore.api.config.SerachFile;
import com.daxton.fancypack.FancyPack;
import com.daxton.fancypack.manager.PackManager;

public class LoadPng {

	public static void execute(){
		FancyPack fancyPack = FancyPack.fancyPack;
		String pluginPath = fancyPack.getDataFolder().getPath();
		//清除font資料
		PackManager.font_List.clear();
		//清除物品資料
		PackManager.item_List.clear();
		//搜尋PackConfig/資料夾
		SerachFile.pathNameList(pluginPath+"/PackConfig", "", "", "/PackConfig/").forEach(s -> {
			if(!s.endsWith(".yml")){
				if(s.contains("PackConfig/Font/")){
					String source = s.substring(s.indexOf("PackConfig/Font/"));
					String dest = source.replace("PackConfig/Font", "FancyTexture/assets/minecraft/textures/font");
					String jsonPath = dest.substring(dest.indexOf("font/")+5);
					PackManager.font_List.add(jsonPath);
					FileCopy.filePluginFile(fancyPack, source, dest);
					//FancyPack.fancyPack.getLogger().info(source);
					//FancyPack.fancyPack.getLogger().info(dest);
					//FancyPack.fancyPack.getLogger().info(jsonPath);
				}
				if(s.contains("PackConfig/Item/")){
					String source = s.substring(s.indexOf("PackConfig/Item/"));
					String dest = source.replace("PackConfig/Item", "FancyTexture/assets/minecraft/textures/item");
					String jsonPath = dest.substring(dest.indexOf("item/")+5);
					if(jsonPath.endsWith(".png")){
						PackManager.item_List.add(jsonPath);
					}
					FileCopy.filePluginFile(fancyPack, source, dest);
					//FancyPack.fancyPack.getLogger().info(source);
					//FancyPack.fancyPack.getLogger().info(dest);
					//FancyPack.fancyPack.getLogger().info(jsonPath);
				}
			}


		});

		//建立Font設定和json檔案
		FontJson.execute();
		//建立Item檔案
		ItemJson.execute();

	}

}
