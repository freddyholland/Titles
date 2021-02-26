package net.fjcode.titles.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.fjcode.titles.util.ChatUtil;

public class Formatter {
	
	private List<String> sequence = new ArrayList<String>();
	private Player p;
	private String title;
	
	public Formatter(Player p, String title) {
		this.p = p;
		this.title = title;
	}
	
	public List<String> getSequence() {
		return sequence;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void addToSequence(String formattingOption) {
		
		if (!sequence.isEmpty() && sequence.size() == title.length())
			return;
		
		sequence.add(formattingOption);
	}
	
	public void openFormatter() {
		Inventory gui = Bukkit.createInventory(null, 27, getFormattedTitle(title, sequence));
		p.openInventory(gui);
	}
	
	public static String getFormattedTitle(String title, List<String> sequence) {
		
		String formattedTitle = "";
		
		int titleLoop = title.length() - 1;
		
		for (int i = 0; i <= titleLoop; i++) {
			
			// If there are not enough formatting options in the sequence then default to .RESET
			// Sequence.size()-1 is the last index in the array.
			
			if ((sequence.size()-1) < i) {
				String concat = ChatColor.RESET + "" + title.charAt(i);
				formattedTitle += concat;
			} else {
				
				// sequence = ["4l", "3l, "2l"]
				// options = "4l"
				String options = sequence.get(i);
				
				String prefix = "";
				
				// Creates a set of instructions on how to format the string provided.
				for (int c = 0; c < options.length(); c++) {
					// ch = "4" or "l"
					char ch = options.charAt(c);
					// prefix = "&4&l"
					prefix += "&" + ch;
				}
				
				// concat is a single character which has been properly formatted.
				String concat = prefix + title.charAt(i);
				
				if (prefix.contains("&x")) {
					concat = (prefix.replaceAll("&x", "") + Character.toUpperCase(title.charAt(i)));
				} else if (prefix.contains("y")) {
					concat = (prefix.replaceAll("&y", "") + Character.toLowerCase(title.charAt(i)));
				}
				
				concat = ChatColor.translateAlternateColorCodes('&', concat);
				
				formattedTitle += concat;
			}
		}
		
		return formattedTitle;
	}
}
