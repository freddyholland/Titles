package net.fjcode.titles.menus;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import net.fjcode.titles.objects.Formatter;
import net.fjcode.titles.util.ChatUtil;
import net.fjcode.titles.util.Config;
import net.fjcode.titles.util.IconUtil;

public class TitleSelectorMenu implements InventoryProvider {
	
	public static final SmartInventory INVENTORY = SmartInventory.builder()
			.id("titleSelector")
			.provider(new TitleSelectorMenu())
			.size(4, 9)
			.title("Title Selector:")
			.build();

	@Override
	public void init(Player p, InventoryContents contents) {
		
		List<String> titles = Config.getUserTitles(p.getUniqueId().toString());
		
		for (String title : titles) {
			 contents.add(ClickableItem.of(
					 IconUtil.createIcon(
							 Material.NAME_TAG, 1, 
							 ChatColor.GREEN + title, 
							 Arrays.asList(ChatColor.GRAY + "Click me to customise.")
							),
					 e -> {
						 if (!p.hasPermission("titles." + title)) {
							 ChatUtil.info(p, "You do not have permission to use that title.");
							 return;
						 }
						 ChatUtil.info(p, "Selected: " + title);
						 Formatter formatter = new Formatter(p, title);
						 SmartInventory INVENTORY = SmartInventory.builder()
									.id("colourSelector")
									.provider(new ColourSelectorMenu(formatter))
									.size(4, 9)
									.title("Customise your title:")
									.build();
						 INVENTORY.open(p);
					 }
			));
		}
	}

	@Override
	public void update(Player p, InventoryContents contents) {
	}

}
