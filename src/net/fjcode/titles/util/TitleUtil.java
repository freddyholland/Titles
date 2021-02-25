package net.fjcode.titles.util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.fjcode.titles.Titles;
import net.fjcode.titles.objects.Formatter;

public class TitleUtil {
	
	public static boolean userHasPerm(CommandSender cs, String title) {
		
		if (!Config.getAvailableTitles().contains(title))
			return false;
		
		if (!cs.hasPermission("titles." + title))
			return false;
		
		return true;
	}
	
	public static void applyTitle(Player p, Formatter title) { 
		String formattedTitle = Formatter.getFormattedTitle(title.getTitle(), title.getSequence());
		Titles.getChat().setPlayerPrefix(p, ChatColor.DARK_GRAY + "[" + formattedTitle + ChatColor.DARK_GRAY + "]");
	}
}
