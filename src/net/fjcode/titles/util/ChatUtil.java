package net.fjcode.titles.util;

import org.bukkit.command.CommandSender;

public class ChatUtil {
	
	public static void info(CommandSender cs, String message) {
		cs.sendMessage(Config.getPrefix() + " " + message);
	}
}
