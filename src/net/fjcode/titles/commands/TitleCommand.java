package net.fjcode.titles.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.fjcode.titles.Titles;
import net.fjcode.titles.menus.TitleSelectorMenu;
import net.fjcode.titles.util.ChatUtil;

public class TitleCommand implements CommandExecutor {
	
	private Titles instance;
	public TitleCommand(Titles instance) {
		this.instance = instance;
		
		instance.getCommand("title").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender cs, Command c, String s, String[] args) {
		
		if (!(cs instanceof Player))
			return true;
		
		Player p = (Player) cs;
		
		if (!p.hasPermission("title.select")) {
			ChatUtil.info(p, "You do not have permission for that command.");
			return true;
		}
		
		TitleSelectorMenu.INVENTORY.open(p);
		
		return false;
	}
}
