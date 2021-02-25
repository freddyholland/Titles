package net.fjcode.titles.util;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class IconUtil {
	
	public static ItemStack createIcon(Material material, int amount, String displayname, List<String> lore) {
		ItemStack icon = new ItemStack(material, amount);
		ItemMeta meta = icon.getItemMeta();
		meta.setDisplayName(displayname);
		meta.setLore(lore);
		icon.setItemMeta(meta);
		
		return icon;
	}
	
	public static ItemStack colourIcon(ChatColor colour) {
		
		String displayname = colour + colour.name().toUpperCase();
		List<String> lore = Arrays.asList();
		Material m = null;
		
		
		switch (colour) {
		case AQUA:
			m = Material.LIGHT_BLUE_WOOL;
			break;
		case BLACK:
			m = Material.BLACK_WOOL;
			break;
		case BLUE:
			m = Material.BLUE_WOOL;
			break;
		case DARK_AQUA:
			m = Material.CYAN_WOOL;
			break;
		case DARK_BLUE:
			m = Material.BLUE_WOOL;
			break;
		case DARK_GRAY:
			m = Material.GRAY_WOOL;
			break;
		case DARK_GREEN:
			m = Material.GREEN_WOOL;
			break;
		case DARK_PURPLE:
			m = Material.PURPLE_WOOL;
			break;
		case DARK_RED:
			m = Material.RED_WOOL;
			break;
		case GOLD:
			m = Material.ORANGE_WOOL;
			break;
		case GRAY:
			m = Material.LIGHT_GRAY_WOOL;
			break;
		case GREEN:
			m = Material.LIME_WOOL;
			break;
		case LIGHT_PURPLE:
			m = Material.MAGENTA_WOOL;
			break;
		case RED:
			m = Material.RED_WOOL;
			break;
		case WHITE:
			m = Material.WHITE_WOOL;
			break;
		case YELLOW:
			m = Material.YELLOW_WOOL;
			break;
		default:
			m = Material.WRITABLE_BOOK;
			break;
		}
		
		ItemStack icon = new ItemStack(m, 1);
		ItemMeta meta = icon.getItemMeta();
		
		meta.setDisplayName(displayname);
		meta.setLore(lore);
		
		icon.setItemMeta(meta);
		return icon;
		
	}
}
