package net.fjcode.titles;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import net.fjcode.titles.commands.TitleCommand;
import net.fjcode.titles.util.Config;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public class Titles extends JavaPlugin {
	
	private static Economy econ = null;
    private static Permission perms = null;
    private static Chat chat = null;
	
	@Override
	public void onEnable() {
		
		Config.setInstance(this);
		
		// Setup config.
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		// Check SmartInvs installed.
		if (getServer().getPluginManager().getPlugin("SmartInvs") == null) {
			getLogger().severe("Disabled due to no SmartInvs dependency found!");
			getServer().getPluginManager().disablePlugin(this);
		}
		
		// Setup Vault dependency.
		if (!setupEconomy() ) {
            getLogger().severe(String.format("Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        setupPermissions();
        setupChat();
		        
        // Register commands.
		new TitleCommand(this);
		
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
    
    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }
    
    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
    
    public static Economy getEconomy() {
        return econ;
    }
    
    public static Permission getPermissions() {
        return perms;
    }
    
    public static Chat getChat() {
        return chat;
    }
	
    /*
     * Titles are defined in config.yml
     * Users are given access to titles with permission 'titles.<title>'
     * Users are given access to colours with permission 'titles.colour.<colour code e.g: 4 b a 7>'
     * Users are given access to formatting options with permission 'titles.format.<formatting code e.g: l m n o>'
     */
}
