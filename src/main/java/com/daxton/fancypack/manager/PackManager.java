package com.daxton.fancypack.manager;

import com.daxton.fancycore.api.gui.GUI;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class PackManager {
	//Font
	public static List<String> font_List = new ArrayList<>();

	//Item
	public static List<String> item_List = new ArrayList<>();
	public static Map<String, Integer> item_Count_Map = new HashMap<>();
	public static Map<String, List<String>> item_Item_Map = new HashMap<>();

	//Gui
	//玩家GUI PlayerUUID Gui
	public static Map<UUID, GUI> playerUUID_GUI_Map = new ConcurrentHashMap<>();

}
