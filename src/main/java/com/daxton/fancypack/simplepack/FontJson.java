package com.daxton.fancypack.simplepack;

import com.daxton.fancycore.api.judgment.NumberJudgment;
import com.daxton.fancycore.api.other.CountWords;
import com.daxton.fancycore.api.other.StringSplit;
import com.daxton.fancypack.FancyPack;
import com.daxton.fancypack.config.FileConfig;
import com.daxton.fancypack.manager.PackManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class FontJson {

	public static void execute(){
		FancyPack fancyPack = FancyPack.fancyPack;
		File json_file = new File(fancyPack.getDataFolder(),"FancyTexture/assets/minecraft/font/default.json");
		FileConfiguration config = FileConfig.config_Map.get("PackConfig/Font/font.yml");
		File file = new File(fancyPack.getDataFolder(), "PackConfig/Font/font.yml");

		String startNumber = FileConfig.config_Map.get("config.yml").getString("ResourcePackCreate.FontCharsStart");
		if(startNumber.length() != 4 || !NumberJudgment.isHexNumber(startNumber)){
			startNumber = "4000";
		}
		writeJson(json_file, config, file, startNumber);
	}


	//檔案修改
	public static void writeJson(File json_file, FileConfiguration config, File file, String startNumber){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(json_file));
			out.write("{");
			out.newLine();
			out.write("  \"providers\": [");
			out.newLine();
			int charKey = Integer.parseInt(startNumber, 16);
			for(int i = 0; i < PackManager.font_List.size(); i++){
				String key = PackManager.font_List.get(i);

				String ccKey = key.replace(".png", "");

				String[] cKeyArray = toArray(ccKey);

				String cKey = cKeyArray[0];
				int row = Integer.parseInt(cKeyArray[1]);
				int columns = Integer.parseInt(cKeyArray[2]);

				//FancyPack.fancyPack.getLogger().info(cKeyArray[0]+" : "+cKeyArray[1]+" : "+cKeyArray[2]);

				int ascent = Integer.parseInt(cKeyArray[3]);
				config.set(cKey+".ascent", ascent);

				int height = Integer.parseInt(cKeyArray[4]);
				config.set(cKey+".height", height);

				out.write("    {");
				out.newLine();
				out.write("\t\t\t\"type\": \"bitmap\",");
				out.newLine();
				out.write("\t\t\t\"file\": \"minecraft:font/"+cKey+".png"+"\",");
				out.newLine();
				out.write("\t\t\t\"ascent\": "+ascent+",");
				out.newLine();
				out.write("\t\t\t\"height\": "+height+",");
				out.newLine();
				out.write("\t\t\t\"chars\": [");
				out.newLine();
				StringBuilder pngKey = new StringBuilder();
				StringBuilder charsAll = new StringBuilder();
				for(int r = 0; r < row ; r++){

					StringBuilder cAdd = new StringBuilder();
					for(int c = 0 ; c < columns;c++){
						cAdd.append("\\u").append(Integer.toHexString(charKey).toUpperCase());
						charsAll.append("\\u").append(Integer.toHexString(charKey).toUpperCase());
						pngKey.append(String.valueOf(Character.toChars(charKey)));
						charKey ++;
					}
					if(r == row-1){
						out.write("      \""+cAdd+"\"");
					}else {
						charsAll.append(",");
						pngKey.append(",");
						out.write("      \""+cAdd+"\",");
					}
					out.newLine();
				}
				String charsAllString = charsAll.toString();
				String pngKeyString = pngKey.toString();
				//FancyPack.fancyPack.getLogger().info(charsAllString);
				//FancyPack.fancyPack.getLogger().info(pngKeyString);
				config.set(cKey+".chars", charsAllString);
				config.set(cKey+".png", pngKeyString);
				out.write("      ]");
				out.newLine();
				if(i == PackManager.font_List.size()-1){
					out.write("\t\t}");
					out.newLine();
				}else {
					out.write("\t\t},");
					out.newLine();
				}
			}
			out.write("  ]");
			out.newLine();
			out.write("}");
			out.newLine();
			out.close();
			config.save(file);
		}catch (Exception exception){
			exception.printStackTrace();
		}
	}

	//把目標字串轉成，目標Map
	public static String[] toArray(String inputString){
		String[] strings = new String[5];
		strings[0] = inputString;
		strings[1] = "1";
		strings[2] = "1";
		strings[3] = "10";
		strings[4] = "10";

		if (inputString.contains("{") && inputString.contains("}")) {

			int num1 = CountWords.count(inputString, "\\{");
			int num2 = CountWords.count(inputString, "\\}");
			if (num1 == 1 && num2 == 1) {

				strings[0] = inputString.substring(0, inputString.indexOf("{")).trim();

				String midSet = inputString.substring(inputString.indexOf("{")+1, inputString.indexOf("}"));

				List<String> midSetList = StringSplit.toList(midSet,";");
				midSetList.forEach(midKey -> {
					String[] midArray = midKey.split("=");
					if(midArray.length == 2){

						if(!midArray[1].contains(".") && NumberJudgment.isNumber(midArray[1])){
							//FancyPack.fancyPack.getLogger().info(midArray[0]+" : "+midArray[1]);
							if(midArray[0].equalsIgnoreCase("r")){
								strings[1] = String.valueOf(midArray[1]);
							}
							if(midArray[0].equalsIgnoreCase("c")){
								strings[2] = String.valueOf(midArray[1]);
							}
							if(midArray[0].equalsIgnoreCase("a")){
								strings[3] = String.valueOf(midArray[1]);
							}
							if(midArray[0].equalsIgnoreCase("h")){
								strings[4] = String.valueOf(midArray[1]);
							}
						}
					}
				});
			}
		}

		return strings;
	}


}
