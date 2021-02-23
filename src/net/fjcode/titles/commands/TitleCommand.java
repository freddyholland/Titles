package net.fjcode.titles.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.fjcode.titles.util.ChatUtil;
import net.fjcode.titles.util.TitleUtil;

public class TitleCommand implements CommandExecutor {
	
	@Override
	public boolean onCommand(CommandSender cs, Command c, String s, String[] args) {
		
		if (!(cs instanceof Player))
			return true;
		
		Player p = (Player) cs;
		
		if (args.length != 1) {
			ChatUtil.info(p, "Syntax: /title <title>");
			return true;
		}
		
		String title = args[0].toLowerCase();
		
		if (!TitleUtil.userHasPerm(p, title)) {
			ChatUtil.info(p, "You do not have access to that title.");
			return true;
		}
		
		TitleUtil.applyTitle(p, title);
		
		return false;
	}
}
