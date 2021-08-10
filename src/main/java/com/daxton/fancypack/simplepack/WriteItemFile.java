package com.daxton.fancypack.simplepack;

import com.daxton.fancypack.FancyPack;
import com.daxton.fancypack.config.FileConfig;
import com.daxton.fancypack.manager.PackManager;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteItemFile {




    public static void execute(){
        FancyPack fancyPack = FancyPack.fancyPack;

        PackManager.item_Count_Map.forEach((s, integer) -> {
            File json_file = new File(fancyPack.getDataFolder(),"FancyTexture/assets/minecraft/models/item/"+s+".json");
            File fileFile = new File(fancyPack.getDataFolder(),"FancyTexture/assets/minecraft/models/item/"+s);
            if(!fileFile.exists()){
                fileFile.mkdir();
            }
            if(!json_file.exists()){
                try {
                    json_file.createNewFile();
                }catch (IOException exception){
                    exception.printStackTrace();
                }
            }
            if(json_file.exists()){
                writeItemType(json_file, s);
            }

        });
        //建立修改Item設定檔
        createItem();
    }

    public static void writeItemType(File json_file, String s){
        try {
            int amount = PackManager.item_Count_Map.get(s);
            BufferedWriter out = new BufferedWriter(new FileWriter(json_file));
            out.write("{");
            out.newLine();
            out.write("\"parent\": \"item/handheld\",");
            out.newLine();
            out.write("\"textures\": {");
            out.newLine();
            out.write("\"layer0\": \"item/"+s+"\"");
            out.newLine();
            out.write("},");
            out.newLine();
            out.write("\"overrides\": [");
            out.newLine();
            for(int i = 1; i <= amount ; i++){
                if(i == amount){
                    out.write("{\"predicate\": {\"custom_model_data\":"+i+"}, \"model\": \"item/"+s+"/"+i+"\"}");
                }else {
                    out.write("{\"predicate\": {\"custom_model_data\":"+i+"}, \"model\": \"item/"+s+"/"+i+"\"},");
                }
                out.newLine();
            }
            out.write("]");
            out.newLine();
            out.write("}");
            out.newLine();
            out.close();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public static void createItem(){
        FileConfiguration config = FileConfig.config_Map.get("PackConfig/Item/item.yml");

        FancyPack fancyPack = FancyPack.fancyPack;
        File saveFile = new File(fancyPack.getDataFolder(), "PackConfig/Item/item.yml");
        PackManager.item_Item_Map.forEach((s, strings) -> {
            for(int i = 1; i <= strings.size() ; i++){
                File fileFile = new File(fancyPack.getDataFolder(),"FancyTexture/assets/minecraft/models/item/"+s+"/"+i+".json");
                if(!fileFile.exists()){
                    try {
                        if(fileFile.createNewFile()){
                            config.set(s+"."+s+"/"+strings.get(i-1)+".Material", s);
                            config.set(s+"."+s+"/"+strings.get(i-1)+".CustomModelData", i);
                            writeItemFile(s, strings.get(i-1), fileFile);
                        }
                    }catch (IOException exception){
                        exception.printStackTrace();
                    }
                }else {
                    config.set(s+"."+s+"/"+strings.get(i-1)+".Material", s);
                    config.set(s+"."+s+"/"+strings.get(i-1)+".CustomModelData", i);
                    writeItemFile(s, strings.get(i-1), fileFile);
                }
            }
        });
        try {
            config.save(saveFile);
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public static void writeItemFile(String type, String name, File fileFile){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileFile));
            out.write("{");
            out.newLine();
            out.write("    \"parent\": \"item/handheld\",");
            out.newLine();
            out.write("    \"gui_light\": \"front\",");
            out.newLine();
            out.write("    \"textures\": {");
            out.newLine();
            out.write("        \"layer0\": \"item/"+type+"/"+name+"\"");
            out.newLine();
            out.write("    }");
            out.newLine();
            out.write("}");
            out.newLine();
            out.close();
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

}
