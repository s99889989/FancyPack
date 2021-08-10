package com.daxton.fancypack.simplepack;

import com.daxton.fancycore.api.judgment.NumberJudgment;
import com.daxton.fancypack.FancyPack;
import com.daxton.fancypack.config.FileConfig;
import com.daxton.fancypack.manager.PackManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class FontJson {

	public static void execute(){
		FancyPack fancyPack = FancyPack.fancyPack;
		File json_file = new File(fancyPack.getDataFolder(),"FancyTexture/assets/minecraft/font/default.json");
		FileConfiguration config = FileConfig.config_Map.get("PackConfig/Font/font.yml");
		File file = new File(fancyPack.getDataFolder(), "PackConfig/Font/font.yml");
		String startNumber = config.getString("");
		if(startNumber.length() != 4 || !NumberJudgment.isHexNumber(startNumber)){
			startNumber = "4000";
		}
		int charKey = Integer.parseInt(startNumber, 16);
		writeJson(json_file, config, file);
	}


	//檔案修改
	public static void writeJson(File json_file, FileConfiguration config, File file){
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(json_file));
			out.write("{");
			out.newLine();
			out.write("    \"providers\": [");
			out.newLine();
			int charKey = Integer.parseInt("4000", 16);
			for(int i = 0; i < PackManager.font_List.size(); i++){
				String key = PackManager.font_List.get(i);
				String c = String.valueOf(Character.toChars(charKey));
				String cKey = key.replace(".png", "");

				config.set(cKey+".chars", "\\u"+Integer.toHexString(charKey).toUpperCase());

				int ascent = 10;
				if(config.contains(cKey+".ascent")){
					ascent = config.getInt(cKey+".ascent");
				}else {
					config.set(cKey+".ascent", 10);
				}
				int height = 10;
				if(config.contains(cKey+".height")){
					height = config.getInt(cKey+".height");
				}else {
					config.set(cKey+".height", 10);
				}


				config.set(cKey+".png", c);



				if(i == PackManager.font_List.size()-1){
					out.write("    {");
					out.newLine();
					out.write("\t\t\t\"type\": \"bitmap\",");
					out.newLine();
					out.write("\t\t\t\"file\": \"minecraft:font/"+key+"\",");
					out.newLine();
					out.write("\t\t\t\"ascent\": "+ascent+",");
					out.newLine();
					out.write("\t\t\t\"height\": "+height+",");
					out.newLine();
					out.write("\t\t\t\"chars\": [\"\\u"+Integer.toHexString(charKey).toUpperCase()+"\"]");
					out.newLine();
					out.write("\t\t}");
					out.newLine();
				}else {
					out.write("    {");
					out.newLine();
					out.write("\t\t\t\"type\": \"bitmap\",");
					out.newLine();
					out.write("\t\t\t\"file\": \"minecraft:font/"+key+"\",");
					out.newLine();
					out.write("\t\t\t\"ascent\": "+ascent+",");
					out.newLine();
					out.write("\t\t\t\"height\": "+height+",");
					out.newLine();
					out.write("\t\t\t\"chars\": [\"\\u"+Integer.toHexString(charKey).toUpperCase()+"\"]");
					out.newLine();
					out.write("\t\t},");
					out.newLine();
				}
				charKey++;
			}
			out.write("    ]");
			out.newLine();
			out.write("}");
			out.newLine();
			out.close();
			config.save(file);
		}catch (Exception exception){
			exception.printStackTrace();
		}
	}

}
