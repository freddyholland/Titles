package net.fjcode.titles.util;

import java.util.Arrays;
import java.util.List;

import net.fjcode.titles.Titles;

public class Config {
	
	private static Titles instance;
	
	public static void setInstance(Titles inst) {
		instance = inst;
	}
	
	public static List<String> getAvailableTitles() {
		return Arrays.asList("one", "two", "three");
	}
	
	public static String getPrefix() {
		return "Titles >>";
	}
	
	// Individual user configuration.
	
	public static String getTitle(String p) {
		if (instance.getConfig().contains("titles." + p + ".title"))
			return instance.getConfig().getString("titles." + p);
		
		return "";
	}
	
	public static List<String> getFormatting(String p) {
		if (instance.getConfig().contains("titles." + p + ".formatting"))
			return instance.getConfig().getStringList("titles." + p + ".formatting");
		
		return Arrays.asList();
	}
	
	public static void setTitle(String p, String title) {
		instance.getConfig().set("titles." + p + ".title", title);
		instance.saveConfig();
	}
	
	public static void setFormatting(String p, List<String> formatting) {
		instance.getConfig().set("titles." + p + ".formatting", formatting);
		instance.saveConfig();
	}
	
	public static List<String> getUserTitles(String p) {
		if (instance.getConfig().contains("titles." + p + ".available"))
			return instance.getConfig().getStringList("titles." + p + ".available");
		
		return Arrays.asList();
	}
	
}
