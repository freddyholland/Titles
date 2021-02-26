package net.fjcode.titles.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.fjcode.titles.Titles;

public class Config {
	
	private static Titles instance;
	
	public static void setInstance(Titles inst) {
		instance = inst;
	}
	
	public static List<String> getAvailableTitles() {
		if (instance.getConfig().contains("titles"))
			return instance.getConfig().getStringList("titles");
		return Arrays.asList("one", "two", "three");
	}
	
	public static String getPrefix() {
		if (instance.getConfig().contains("prefix")) {
			return ChatColor.translateAlternateColorCodes('&', instance.getConfig().getString("prefix"));
		}
		return ChatColor.AQUA + "Titles" + ChatColor.GREEN + " >>" + ChatColor.RESET;
	}
	
	// Individual user configuration.
	
	public static List<String> getUserTitles(Player p) {
		ArrayList<String> userTitles = new ArrayList<String>();
		for (String title : getAvailableTitles()) {
			if (p.hasPermission("titles." + title)) {
				userTitles.add(title);
			}
		}
		
		return userTitles;
	}
	
}
