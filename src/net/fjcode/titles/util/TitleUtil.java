package net.fjcode.titles.util;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TitleUtil {
	
	public static boolean userHasPerm(CommandSender cs, String title) {
		
		if (!Config.getAvailableTitles().contains(title))
			return false;
		
		if (!cs.hasPermission("titles." + title))
			return false;
		
		return true;
	}
	
	public static void applyTitle(Player p, String title) { 
		
	}
}
