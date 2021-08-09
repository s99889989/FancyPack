package com.daxton.fancypack.simplepack;

import com.daxton.fancypack.FancyPack;

import com.daxton.fancypack.config.FileConfig;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WriteFoneFile {

    public static Map<String, Object> map = new HashMap<>();

    public static List<String> fontList = new ArrayList<>();

    public static void write(){

        FancyPack fancyPack = FancyPack.fancyPack;
        File json_file = new File(fancyPack.getDataFolder(),"FancyTexture\\assets\\minecraft\\font\\default.json");
        FileConfiguration config = FileConfig.config_Map.get("PackConfig/Font/font.yml");
        File file = new File(fancyPack.getDataFolder(), "PackConfig/Font/font.yml");
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(json_file));
            out.write("{");
            out.newLine();
            out.write("    \"providers\": [");
            out.newLine();
            int charKey = Integer.parseInt("4000", 16);
            for(int i = 0; i < fontList.size(); i++){
                String key = fontList.get(i);
                String c = String.valueOf(Character.toChars(charKey));
                String cKey = key.replace(".png", "");
                config.set(cKey+".chars", c);


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

                if(i == fontList.size()-1){
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

//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//        try {
//            map = gson.fromJson(new FileReader(json_file), new HashMap<String, Object>().getClass());
//            map.forEach((s, o) -> FancyPack.fancyPack.getLogger().info(s));
//            String json = gson.toJson(map);
//
//            //Files.write(json_file.toPath(), json.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
//        }catch (IOException exception){
//            exception.printStackTrace();
//        }


    }

}
