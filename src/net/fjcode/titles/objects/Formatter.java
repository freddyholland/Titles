package net.fjcode.titles.objects;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.fjcode.titles.util.ChatUtil;

public class Formatter {
	
	private List<String> sequence = Arrays.asList();
	private Player p;
	private String title;
	
	public Formatter(Player p, String title) {
		this.p = p;
		this.title = title;
	}
	
	public List<String> getSequence() {
		return sequence;
	}
	
	public void addToSequence(String formattingOption) {
		if (sequence.size() == title.length())
			return;
		
		if (!p.hasPermission("titles.formatting." + formattingOption)) {
			ChatUtil.info(p, "You do not have access to use &" + formattingOption);
			return;
		}
		
		sequence.add(formattingOption);
	}
	
	public void openFormatter() {
		Inventory gui = Bukkit.createInventory(null, 27, getFormattedTitle(title, sequence));
		p.openInventory(gui);
	}
	
	public static String getFormattedTitle(String title, List<String> sequence) {
		
		String formattedTitle = "";
		
		for (int i = 0; i < title.length(); i++) {
			if (sequence.size() < i) {
				title.concat(ChatColor.RESET + "" + title.charAt(i));
			} else {
				title.concat(ChatColor.translateAlternateColorCodes('&', sequence.get(i) + title.charAt(i)));
			}
		}
		
		return formattedTitle;
	}
}
