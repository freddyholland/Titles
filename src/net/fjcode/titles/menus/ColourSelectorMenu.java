package net.fjcode.titles.menus;

import java.util.Arrays;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import net.fjcode.titles.objects.Formatter;
import net.fjcode.titles.util.ChatUtil;
import net.fjcode.titles.util.IconUtil;
import net.fjcode.titles.util.TitleUtil;

public class ColourSelectorMenu implements InventoryProvider {
	
	private Formatter formatter;
	private String current = "";
	
	public ColourSelectorMenu(Formatter formatter) {
		this.formatter = formatter;
	}

	@Override
	public void init(Player p, InventoryContents contents) {
		
		// Number colours.
		contents.set(0, 0, ClickableItem.of(IconUtil.colourIcon(ChatColor.DARK_BLUE), e -> { addToFormat(p, "1"); }));
		contents.set(0, 1, ClickableItem.of(IconUtil.colourIcon(ChatColor.DARK_GREEN), e -> { addToFormat(p, "2"); }));
		contents.set(0, 2, ClickableItem.of(IconUtil.colourIcon(ChatColor.DARK_AQUA), e -> { addToFormat(p, "3"); }));
		contents.set(0, 3, ClickableItem.of(IconUtil.colourIcon(ChatColor.DARK_RED), e -> { addToFormat(p, "4"); }));
		contents.set(0, 4, ClickableItem.of(IconUtil.colourIcon(ChatColor.DARK_PURPLE), e -> { addToFormat(p, "5"); }));
		contents.set(0, 5, ClickableItem.of(IconUtil.colourIcon(ChatColor.GOLD), e -> { addToFormat(p, "6"); }));
		contents.set(0, 6, ClickableItem.of(IconUtil.colourIcon(ChatColor.GRAY), e -> { addToFormat(p, "7"); }));
		contents.set(0, 7, ClickableItem.of(IconUtil.colourIcon(ChatColor.DARK_GRAY), e -> { addToFormat(p, "8"); }));
		contents.set(0, 8, ClickableItem.of(IconUtil.colourIcon(ChatColor.BLUE), e -> { addToFormat(p, "9"); }));
		contents.set(1, 0, ClickableItem.of(IconUtil.colourIcon(ChatColor.BLACK), e -> { addToFormat(p, "0"); }));
		
		// Letter colours.
		contents.set(1, 1, ClickableItem.of(IconUtil.colourIcon(ChatColor.GREEN), e -> { addToFormat(p, "a"); }));
		contents.set(1, 2, ClickableItem.of(IconUtil.colourIcon(ChatColor.AQUA), e -> { addToFormat(p, "b"); }));
		contents.set(1, 3, ClickableItem.of(IconUtil.colourIcon(ChatColor.RED), e -> { addToFormat(p, "c"); }));
		contents.set(1, 4, ClickableItem.of(IconUtil.colourIcon(ChatColor.LIGHT_PURPLE), e -> { addToFormat(p, "d"); }));
		contents.set(1, 5, ClickableItem.of(IconUtil.colourIcon(ChatColor.YELLOW), e -> { addToFormat(p, "e"); }));
		contents.set(1, 6, ClickableItem.of(IconUtil.colourIcon(ChatColor.WHITE), e -> { addToFormat(p, "f"); }));
		
		// Formatting.
		contents.set(2, 0, ClickableItem.of(IconUtil.colourIcon(ChatColor.BOLD), e -> { addToFormat(p, "l"); }));
		contents.set(2, 1, ClickableItem.of(IconUtil.colourIcon(ChatColor.ITALIC), e -> { addToFormat(p, "o"); }));
		contents.set(2, 2, ClickableItem.of(IconUtil.colourIcon(ChatColor.STRIKETHROUGH), e -> { addToFormat(p, "m"); }));
		contents.set(2, 3, ClickableItem.of(IconUtil.colourIcon(ChatColor.UNDERLINE), e -> { addToFormat(p, "n"); }));
		
		contents.set(2, 4, ClickableItem.of(IconUtil.createIcon(Material.WRITABLE_BOOK, 1, "UPPERCASE", null), e -> { addToFormat(p, "x"); }));
		contents.set(2, 5, ClickableItem.of(IconUtil.createIcon(Material.WRITABLE_BOOK, 1, "lowercase", null), e -> { addToFormat(p, "y"); }));
		
		// Submit
		contents.set(3, 8, ClickableItem.of(
				IconUtil.createIcon(
						Material.GREEN_STAINED_GLASS_PANE, 1, 
						ChatColor.GREEN + "Next.", 
						Arrays.asList(ChatColor.GRAY + "Move to the next letter.")
						),
				e -> {
					submitFormat(p);
				}));
	}

	@Override
	public void update(Player p, InventoryContents contents) {
		/*if (formatter.getSequence().size() == formatter.getTitle().length()) {
			p.closeInventory();
			TitleUtil.applyTitle(p, formatter);
		}*/
	}
	
	public void addToFormat(Player p, String c) {
		
		if (!p.hasPermission("title.colour." + c)) {
			ChatUtil.info(p, "You do not have access to that.");
			return;
		}
		
		current += c;
	}
	
	public void submitFormat(Player p) {
		formatter.addToSequence(current);
		p.closeInventory();
		
		if (formatter.getSequence().size() == formatter.getTitle().length()) {
			TitleUtil.applyTitle(p, formatter);
		} else {
			SmartInventory INVENTORY = SmartInventory.builder()
					.id("colourSelector")
					.provider(new ColourSelectorMenu(formatter))
					.size(4, 9)
					.title(Formatter.getFormattedTitle(formatter.getTitle(), formatter.getSequence()))
					.build();
			INVENTORY.open(p);
		}
	}
}
