package com.daxton.fancypack.simplepack;

import com.daxton.fancycore.api.config.FileCopy;
import com.daxton.fancycore.api.config.SerachFile;
import com.daxton.fancypack.FancyPack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadPng {

	public static void execute(){
		FancyPack fancyPack = FancyPack.fancyPack;
		String pluginPath = fancyPack.getDataFolder().getPath();
		SerachFile.pathNameList(pluginPath+"/PackConfig", "", "", "/PackConfig/").forEach(s -> {
			if(!s.endsWith(".yml")){
				if(s.contains("PackConfig/Font/")){
					String source = s.substring(s.indexOf("PackConfig/Font/"));
					String dest = source.replace("PackConfig/Font", "FancyTexture/assets/minecraft/textures/font");
					FileCopy.filePluginFile(fancyPack, source, dest);
				FancyPack.fancyPack.getLogger().info(source);
				FancyPack.fancyPack.getLogger().info(dest);
				}
				if(s.contains("PackConfig/Item/")){
					String source = s.substring(s.indexOf("PackConfig/Item/"));
					String dest = source.replace("PackConfig/Item", "FancyTexture/assets/minecraft/textures/item");
					FileCopy.filePluginFile(fancyPack, source, dest);
//					FancyPack.fancyPack.getLogger().info(source);
//					FancyPack.fancyPack.getLogger().info(dest);
				}
			}


		});


//		WriteItemFile.item_Count_Map.clear();
//
//		WriteFoneFile.write();
//		WriteItemFile.execute();
	}

	public static void fileItemList(String patch, String key){

		String ss = patch.substring(patch.indexOf(key)+key.length());
		ss = ss.replace("\\","/");
		String[] ssArray = ss.split("/");
		if(ssArray.length == 2){
			if(WriteItemFile.item_Count_Map.get(ssArray[0]) != null){
				int k =  WriteItemFile.item_Count_Map.get(ssArray[0]);
				k += 1;
				WriteItemFile.item_Count_Map.put(ssArray[0], k);
			}else {
				WriteItemFile.item_Count_Map.put(ssArray[0], 1);
			}
			if(WriteItemFile.item_Item_Map.get(ssArray[0]) != null){
				List<String> newList = WriteItemFile.item_Item_Map.get(ssArray[0]);
				String inKey = ssArray[1].replace(".png", "");
				if(!newList.contains(inKey)){
					newList.add(inKey);
					WriteItemFile.item_Item_Map.put(ssArray[0], newList);
				}
			}else {
				List<String> newList = new ArrayList<>();
				String inKey = ssArray[1].replace(".png", "");
				newList.add(inKey);
				WriteItemFile.item_Item_Map.put(ssArray[0], newList);
			}
		}
		//FancyPack.fancyPack.getLogger().info(ss);
	}

	public static void fileNameList(String patch, String key){
		String ss = patch.substring(patch.indexOf(key)+key.length());
		ss = ss.replace("\\","/");
		WriteFoneFile.fontList.add(ss);
		//FancyPack.fancyPack.getLogger().info(ss);
	}


}
