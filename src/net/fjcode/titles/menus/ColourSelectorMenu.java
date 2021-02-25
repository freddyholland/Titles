package net.fjcode.titles.menus;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import net.fjcode.titles.objects.Formatter;
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
		contents.set(0, 0, ClickableItem.of(IconUtil.colourIcon(ChatColor.DARK_BLUE), e -> { addToFormat("1"); }));
		contents.set(0, 1, ClickableItem.of(IconUtil.colourIcon(ChatColor.DARK_GREEN), e -> { addToFormat("2"); }));
		contents.set(0, 2, ClickableItem.of(IconUtil.colourIcon(ChatColor.DARK_AQUA), e -> { addToFormat("3"); }));
		contents.set(0, 3, ClickableItem.of(IconUtil.colourIcon(ChatColor.DARK_RED), e -> { addToFormat("4"); }));
		contents.set(0, 4, ClickableItem.of(IconUtil.colourIcon(ChatColor.DARK_PURPLE), e -> { addToFormat("5"); }));
		contents.set(0, 5, ClickableItem.of(IconUtil.colourIcon(ChatColor.GOLD), e -> { addToFormat("6"); }));
		contents.set(0, 6, ClickableItem.of(IconUtil.colourIcon(ChatColor.GRAY), e -> { addToFormat("7"); }));
		contents.set(0, 7, ClickableItem.of(IconUtil.colourIcon(ChatColor.DARK_GRAY), e -> { addToFormat("8"); }));
		contents.set(0, 8, ClickableItem.of(IconUtil.colourIcon(ChatColor.BLUE), e -> { addToFormat("9"); }));
		contents.set(1, 0, ClickableItem.of(IconUtil.colourIcon(ChatColor.BLACK), e -> { addToFormat("0"); }));
		
		// Letter colours.
		contents.set(1, 1, ClickableItem.of(IconUtil.colourIcon(ChatColor.GREEN), e -> { addToFormat("a"); }));
		contents.set(1, 2, ClickableItem.of(IconUtil.colourIcon(ChatColor.AQUA), e -> { addToFormat("b"); }));
		contents.set(1, 3, ClickableItem.of(IconUtil.colourIcon(ChatColor.RED), e -> { addToFormat("c"); }));
		contents.set(1, 4, ClickableItem.of(IconUtil.colourIcon(ChatColor.LIGHT_PURPLE), e -> { addToFormat("d"); }));
		contents.set(1, 5, ClickableItem.of(IconUtil.colourIcon(ChatColor.YELLOW), e -> { addToFormat("e"); }));
		contents.set(1, 6, ClickableItem.of(IconUtil.colourIcon(ChatColor.WHITE), e -> { addToFormat("f"); }));
		
		// Formatting.
		contents.set(2, 0, ClickableItem.of(IconUtil.colourIcon(ChatColor.BOLD), e -> { addToFormat("l"); }));
		contents.set(2, 1, ClickableItem.of(IconUtil.colourIcon(ChatColor.ITALIC), e -> { addToFormat("o"); }));
		contents.set(2, 2, ClickableItem.of(IconUtil.colourIcon(ChatColor.STRIKETHROUGH), e -> { addToFormat("m"); }));
		contents.set(2, 3, ClickableItem.of(IconUtil.colourIcon(ChatColor.UNDERLINE), e -> { addToFormat("n"); }));
		
		contents.set(2, 4, ClickableItem.of(IconUtil.createIcon(Material.WRITABLE_BOOK, 1, "UPPERCASE", null), e -> { addToFormat("x"); }));
		contents.set(2, 5, ClickableItem.of(IconUtil.createIcon(Material.WRITABLE_BOOK, 1, "lowercase", null), e -> { addToFormat("y"); }));
		
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
	
	public void addToFormat(String c) {
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
